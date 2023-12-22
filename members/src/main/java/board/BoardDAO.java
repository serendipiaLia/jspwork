package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

//게시글을 추가, 검색, 수정, 삭제할 클래스
public class BoardDAO {
	
	Connection conn = null; // DB연결
	PreparedStatement pstmt = null; // SQL처리
	ResultSet rs = null; // 검색한 데이터set

	// 게시글 목록
	public List<Board> getBoardList(){
		List<Board> boardList = new ArrayList<>();
		
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리
			String sql = "SELECT * FROM board ORDER BY bno DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 검색한 데이터set(모음)
			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCreateDate(rs.getTimestamp("createdate"));
				b.setModifyDate(rs.getTimestamp("modifydate"));
				b.setHit(rs.getInt("hit"));
				b.setFilename(rs.getString("filename"));
				b.setId(rs.getString("id"));
			
				boardList.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	// 게시글 목록 (*******페이지 처리*************) 
	public List<Board> getBoardList(int page){
		List<Board> boardList = new ArrayList<>();
		
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리
			String sql = " SELECT * FROM (SELECT ROWNUM RN, bo.* "
					+ "    FROM (SELECT * FROM board ORDER BY bno DESC)bo) "
					+ "WHERE RN >= ? AND RN <=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * 10 + 1); // 페이지 시작 행(startRow)
			pstmt.setInt(2, page * 10); // 페이지 당 게시글 수 (ex.1번페이지 : 1 - 10번까지,,)
			rs = pstmt.executeQuery(); // 검색한 데이터set(모음)
			
			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCreateDate(rs.getTimestamp("createdate"));
				b.setModifyDate(rs.getTimestamp("modifydate"));
				b.setHit(rs.getInt("hit"));
				b.setFilename(rs.getString("filename"));
				b.setId(rs.getString("id"));
			
				boardList.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	// 게시글 목록(************검색처리****************)
	public List<Board> getBoardList(String field, String keyword){
		List<Board> boardList = new ArrayList<>();
		
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리 : field 에 "title" 이나 "id"가 들어옴!(=입력됨)
			String sql = "SELECT * FROM board " + "WHERE " + field + " LIKE ? ORDER BY bno DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery(); // 검색한 데이터set(모음)
			
			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCreateDate(rs.getTimestamp("createdate"));
				b.setModifyDate(rs.getTimestamp("modifydate"));
				b.setHit(rs.getInt("hit"));
				b.setFilename(rs.getString("filename"));
				b.setId(rs.getString("id"));
				
				boardList.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	// 게시글 목록 (************페이지와 검색처리****************)
	public List<Board> getBoardList(String field, String keyword, int page){
		List<Board> boardList = new ArrayList<>();
		
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리 : field 에 "title" 이나 "id"가 들어옴!(=입력됨)
			String sql = "SELECT * FROM (SELECT ROWNUM RN, bo.* FROM (SELECT * FROM board WHERE " 
						+ field + " LIKE ? ORDER BY bno DESC)bo) "
						+ " WHERE RN >= ? AND RN <= ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, (page - 1) * 10 + 1);
			pstmt.setInt(3, page * 10);
			rs = pstmt.executeQuery(); // 검색한 데이터set(모음)
			
			while(rs.next()) {
				Board b = new Board();
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCreateDate(rs.getTimestamp("createdate"));
				b.setModifyDate(rs.getTimestamp("modifydate"));
				b.setHit(rs.getInt("hit"));
				b.setFilename(rs.getString("filename"));
				b.setId(rs.getString("id"));
				
				boardList.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	// 총 게시글 수
	public int getBoardCount() {
		int total = 0;
			try {
				// db연결
				conn = JDBCUtil.getConnection();
				// sql 처리
				String sql = "SELECT COUNT(*) AS total FROM board";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery(); // 검색한 데이터set(모음)
				if(rs.next()) {
					total = rs.getInt("total");
				}
			
				} catch (Exception e) {
						e.printStackTrace();
					} finally {
					JDBCUtil.close(conn, pstmt, rs);
				}
		return total;
	}
	
	
	// 글쓰기 처리
	public void write(Board b) {
		
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리
			String sql = "INSERT INTO board (bno, title, content, filename, id) "
					+ "VALUES(seq_bno.NEXTVAL, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getFilename());
			pstmt.setString(4, b.getId());
			
			//sql 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 게시글 상세보기
	public Board getBoard(int bno) { //bno(글번호)로 찾아서 데이터 가져오기 
		
		Board b = new Board();
	
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql처리
			String sql = "SELECT * FROM board WHERE bno=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 검색한 데이터가 있으면,,
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setCreateDate(rs.getTimestamp("createdate"));
				b.setModifyDate(rs.getTimestamp("modifydate"));
				b.setHit(rs.getInt("hit"));
				b.setFilename(rs.getString("filename"));
				b.setId(rs.getString("id"));
				
				// 조회 수 1 증가
//				sql = "UPDATE board SET hit = ? WHERE bno = ?"
				sql = "UPDATE board SET hit = hit + 1 WHERE bno = ?";
				pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, rs.getInt("hit") + 1); // 이 명령문 사용 시, sql 102행 사용
				pstmt.setInt(1, bno);
				
				pstmt.executeUpdate(); // sql실행문
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return b;
	}
	
	// 게시글 삭제
	public void deleteboard(int bno) {
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리
			String sql = "DELETE FROM board WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			//sql 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 게시글 수정 
	public void updateboard(Board b) {
		// 현재 날짜와 시간 객체 생성
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리 
			String sql = "UPDATE board SET title= ?, content= ?, modifydate = ?, "
					+ " filename = ? WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setTimestamp(3, now); // 수정일 처리는 현재 날짜와 시간으로
			pstmt.setString(4, b.getFilename());
			pstmt.setInt(5, b.getBno());
			
			//sql 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	// 게시글 수정시 파일없을때 
	public void updateboardNofile(Board b) {
		// 현재 날짜와 시간 객체 생성
		Timestamp now = new Timestamp(System.currentTimeMillis()); 
		
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리 
			String sql = "UPDATE board SET title= ?, content= ?, modifydate = ?, "
					+ " WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setTimestamp(3, now); // 수정일 처리는 현재 날짜와 시간으로
			pstmt.setInt(4, b.getBno());
			
			//sql 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	
} // BoardDAO class
