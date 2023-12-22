package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/cptest")
public class ConnPoolTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConnPoolTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			Connection conn = null;
			
			//연결됐는지 브라우저로 확인하기 >> 출력 40행
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			// DataSource 객체 생성
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();
			
			out.println("Connection Pool set success : " + conn);
			
			 conn.close(); // 연결종료
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
