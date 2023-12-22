package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/checkid")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 컨텐츠 응답
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 메시지 받기
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		boolean result = dao.getDuplicatedId(id);
		if(result) { // result==true
			// 중복 데이터가 있으면 
			out.println("not_usable"); 
		}else { 
			out.print("usable");
		}
		
	}


}
