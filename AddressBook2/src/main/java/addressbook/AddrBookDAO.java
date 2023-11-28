package addressbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

// AddrBook 자료형을 이용하여 주소를 생성, 검색, 수정, 삭제
public class AddrBookDAO {
	// jdbc 관련 변수 (db와 연결할 필드 생성)
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; //db에 있는 자료 꺼내올 때 사용!
	
	// 주소 추가
		public void addAddrBook(AddrBook addrBook) {
			//db 연결
			conn = JDBCUtil.getConnection();
			
			try {
			//sql처리
			String sql = "INSERT INTO addrbook(bnum, username, tel, email, gender)" +
					"VALUES (seq_bnum.NEXTVAL, ?, ?, ?, ?)";
			
				pstmt = conn.prepareStatement(sql);
				
				// 폼에 입력된 자료를 가져와서 DB에 저장
				pstmt.setString(1, addrBook.getUsername());
				pstmt.setString(2, addrBook.getTel());
				pstmt.setString(3, addrBook.getEmail());
				pstmt.setString(4, addrBook.getGender());
				
				//**sql 실행문!
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt);
			}
		}
		
		// 주소 전체 목록 >> 1.db연결 2.sql처리 3.db종료
		public List<AddrBook> getListAll(){
			// db 연결
			conn = JDBCUtil.getConnection();
			List<AddrBook> addrList = new ArrayList<>();
			try {
				// sql 처리
				String sql = "SELECT * FROM addrbook ORDER BY regdate DESC"; 
				pstmt = conn.prepareStatement(sql);
				//db의 주소록을 꺼내옴.. 16행
				rs = pstmt.executeQuery();
				while(rs.next()) {
					//SQL에서 addrbook table의 column이름으로 가져옴 "" >> SQL에서 가져올때 변수이름 무조건 소문자로..
					AddrBook addrBook = new AddrBook(); // 빈 객체 생성
					
					addrBook.setBnum(rs.getInt("bnum")); // db에 있는 주소를 가져와서 화면에 출력하기(=주소객체에 저장)
					addrBook.setUsername(rs.getString("username")); 
					addrBook.setTel(rs.getString("tel"));
					addrBook.setEmail(rs.getString("email"));
					addrBook.setGender(rs.getString("gender"));
					addrBook.setRegDate(rs.getTimestamp("regdate"));
					
					// 리스트에 1개 객체를 저장(순서에 따라 저장! > 인덱싱)
					addrList.add(addrBook);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { // db 종료
				JDBCUtil.close(conn, pstmt, rs);
			}
			 return addrList;
		}
		
		// 주소 정보(1건) 보기
		public AddrBook getAddrBook(int bnum) {
			// db 연결
			conn = JDBCUtil.getConnection();
			AddrBook addrBook = new AddrBook(); // 빈 객체 생성
			
			try {
			// sql 처리
			String sql = "SELECT * FROM addrbook WHERE bnum=?"; 
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, bnum);
				
				//bnum에 일치하는 1개의 주소 가져옴
				rs = pstmt.executeQuery();
				if(rs.next()) {
					
					addrBook.setBnum(rs.getInt("bnum")); // db에 있는 주소를 가져와서 화면에 출력하기(=주소객체에 저장)
					addrBook.setUsername(rs.getString("username")); 
					addrBook.setTel(rs.getString("tel"));
					addrBook.setEmail(rs.getString("email"));
					addrBook.setGender(rs.getString("gender"));
					addrBook.setRegDate(rs.getTimestamp("regdate"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn, pstmt, rs);
			}
			return addrBook;
		}
		
		// 로그인 체크
		public boolean checkLogin(String email) {
			// db 연결
			conn = JDBCUtil.getConnection();
			
			// sql 처리
			try {
				String sql = "SELECT email FROM addrbook WHERE email = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				if(rs.next()) { // 검색한 이메일이 있으면 true 리턴
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { // db 종료
				JDBCUtil.close(conn, pstmt, rs);
			}
			return false;
		}
		
		// 주소록 삭제 >> addrDelete.jsp
		public void deleteAddrBook(int bnum) {
			// db연결
			conn = JDBCUtil.getConnection();
			
			try {
				// sql처리
				String sql = "DELETE FROM addrbook WHERE bnum = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bnum); // bnum =? >> 외부에서 받은 번호를 입력! 
				// sql실행
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			// db연결 종료
			} finally {
				JDBCUtil.close(conn, pstmt);
			}
		}
		
		// 주소록 수정! >> addrUpdate.jsp
		public void updateAddrBook(AddrBook addrBook) {
			// db연결
			conn = JDBCUtil.getConnection();
			try {
				// sql처리
				String sql = "UPDATE addrbook SET username = ?, tel= ?, email = ?, gender =? "
						+ "WHERE bnum = ?";
				
				pstmt = conn.prepareStatement(sql);
				// 수정 폼에 입력한 수정된 데이터를 가져와서 db에 다시 저장!
				pstmt.setString(1, addrBook.getUsername());
				pstmt.setString(2, addrBook.getTel());
				pstmt.setString(3, addrBook.getEmail());
				pstmt.setString(4, addrBook.getGender());
				pstmt.setInt(5, addrBook.getBnum());
				
				// sql실행
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			// db연결 종료
			} finally {
				JDBCUtil.close(conn, pstmt);
			}
		}
		
		// 세션으로 사용할 이름 가져오기
		public String getNameByEmail(String email) {
			//db연결
			conn = JDBCUtil.getConnection();
			String name = ""; // 이메일이 없으면 null처리 됨 (하기 if문 실행 안됨)
			
			try {
				//sql 처리 및 실행
				String sql = "SELECT * FROM addrbook WHERE email=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				// sql 검색 실행
				rs = pstmt.executeQuery();
				if(rs.next()) { // 이메일이 있으면 
					// db에서 이름(username)을 꺼내옴!
					name = rs.getString("username");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { //db 종료
				JDBCUtil.close(conn, pstmt, rs);
			}
			return name;
		}
		
} //AddrBookDAO
