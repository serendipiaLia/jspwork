package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TestDAO;

@WebServlet("*.do") // 디렉터리 패턴 directory pattern : 끝에 와일드 카드(*) 사용해야 함
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TestDAO dao = new TestDAO(); //import
       
    public TestController() {
    	dao = new TestDAO(); // dao객체사용 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8080/testweb/board/list.do
		
		// 경로 생성
		// uri >> 컨텍스트 + 요청경로
		// context >> 컨텍스트
		String uri = request.getRequestURI();
		System.out.println(uri);
		/* String contextPath = request.getContextPath();
		  System.out.println(contextPath);
		 */
		// 커맨트 패턴(요청 경로)
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(command);
		String nextPage = "";
		
		if(command.equals("/list.do")) {
//			System.out.println("/list.do 경로가 요청되었습니다.");
			dao.list();
			nextPage = "/board/list.jsp";
		}else if(command.equals("/write.do")) {
//			System.out.println("/write.do 경로가 요청되었습니다.");
			dao.write();
		}else if(command.equals("/test.do")) {
			String message = "안녕하세용";
		
			//모델 생성 후 저장
			request.setAttribute("msg", message);
			nextPage = "/test.jsp";
		}
		// 페이지 이동
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	

}
