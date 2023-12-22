package voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCUtil;

public class VoterDAO {
	
	Connection conn = null; // DB연결
	PreparedStatement pstmt = null; // SQL처리
	ResultSet rs = null; // 검색한 데이터set
	
	// 좋아요 추가
	public void insertVote(Voter voter) { // Voter 클래스
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO voter(vno, bno, mid) VALUES(seq_vno.NEXTVAL, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, voter.getBno());
			pstmt.setString(2, voter.getMid());
			
			//sql 실행 >> 회원가입해서 회원이 한명 추가 되듯이...
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 해당 게시글의 좋아요 총 개수
	public int voteCount(int bno) {
			try {
				conn = JDBCUtil.getConnection();
				String sql = "SELECT COUNT(*) AS total FROM voter WHERE bno = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				
				//sql 검색
				rs = pstmt.executeQuery();
				if(rs.next()) { // 검색 결과값이 있으면...(=카운트한 값이 있으면 숫자 반환)
					return rs.getInt("total"); //칼럼 이름명 대신 별칭 AS total
				}
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt, rs);
			}	
			return 0; // rs 검색 값(total)없으면 0처리 (=해당 게시글이 없을때,, 카운트 값 없으면 0)
		
	}

	// 좋아요 저장 유무 확인(체크) - 세션아이디, 글번호가 일치 / 일치하지 않으면 저장x >> 체크 후, 좋아요 삭제 기능
	public int checkVoter(int bno, String id) {
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) AS result FROM voter WHERE bno = ? AND mid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, id);
			
			//sql 검색
			rs = pstmt.executeQuery();
			if(rs.next()) { // 검색 결과값이 있으면...(=카운트한 값이 있으면 숫자 반환)
				return rs.getInt("result"); // 일치하면 1이 반환됨
			}
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs); //insert이니까 rs 없는 것
		}	
		return 0; // 검색 값(result)없으면 0처리 (=해당 게시글이 없을때,, 카운트 값 없으면 0)
	}

	// 좋아요 삭제
	public void deleteVote(Voter voter) {
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "DELETE FROM voter WHERE bno = ? AND mid = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, voter.getBno());
			pstmt.setString(2, voter.getMid());
			
			//sql 실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
} // voterDAO
