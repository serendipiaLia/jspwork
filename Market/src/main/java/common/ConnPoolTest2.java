package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/cptest2")
public class ConnPoolTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ConnPoolTest2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			//연결됐는지 브라우저로 확인하기 >> 출력 45행 주석
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			// DataSource 객체 생성
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();
			
//			out.println("Connection Pool set success : " + conn);
			
			//sql 실행
			String sql = "select * from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pid = rs.getString("p_id");
				String pname = rs.getString("p_name");
				int price = rs.getInt("p_price");
				
				out.println(pid + " / " + pname + " / " + price + "<br>");
			}
			
			// 연결종료
			rs.close();
			pstmt.close();
			conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
