package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

// 회원을 추가, 검색, 수정, 삭제할 클래스
public class MemberDAO {
	
	Connection conn = null; // DB연결
	PreparedStatement pstmt = null; // SQL처리
	ResultSet rs = null; // 검색한 데이터set
	
	// 회원 목록(DB에서 꺼내옴) >> memberlist.jsp 
	public List<Member> getMemberList(){
		List<Member> memberList = new ArrayList<>();
		
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			
			// sql 처리
			String sql = "SELECT * FROM member ORDER BY mno";
			pstmt = conn.prepareStatement(sql);
			
			// sql 실행
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setMno(rs.getInt("mno"));
				m.setId(rs.getString("id"));
				m.setPasswd(rs.getString("passwd"));
				m.setName(rs.getString("name"));
				m.setEmail(rs.getString("email"));
				m.setGender(rs.getString("gender"));
				m.setJoindate(rs.getTimestamp("joindate"));
				//리스트에 회원을 추가
				memberList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		// db종료
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return memberList;
	}
	
	// 회원 가입
	public void insertMember(Member m) {
		
		try {
			// db연결
			conn = JDBCUtil.getConnection();
			// sql 처리
			String sql = "INSERT INTO member (mno, id, passwd, name, email, gender) "
					+ "VALUES(seq_mno.NEXTVAL, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			// 폼에 입력된 데이터, m을 가져와서 db에 저장
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPasswd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getGender());
			// sql 실행 
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	
}
