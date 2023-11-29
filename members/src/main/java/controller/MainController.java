package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.Member;
import member.MemberDAO;

@WebServlet("*.do")
public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = 10L;
    MemberDAO mDAO; 
	
    public MainController() {
    	mDAO = new MemberDAO();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 경로 설정(mapping)
		// uri = context(/) + 파일경로(.do)
		String uri = request.getRequestURI();
		System.out.println(uri);
		// command 패턴
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(command);
		
		String nextPage = ""; //초기화
		
		if(command.equals("/memberlist.do")) {
			// 회원정보를 db에서 가져옴
			List<Member> memberList = mDAO.getMemberList();
			// 모델 생성
			request.setAttribute("memberList", memberList);
			// 이동할 페이지
			nextPage = "/member/memberlist.jsp";	
		}else if(command.equals("/joinform.do")) { // 회원가입폼
			// get 방식
			nextPage = "/member/joinform.jsp"; 
		}else if(command.equals("/insertmember.do")) {
			//빈 회원 객체 생성 > 파라미터 데이터를 받아와서 세팅
			
			// 폼 데이터 받기
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			
			// 객체에 데이터 세팅
			Member m = new Member(); 
			m.setId(id);
			m.setPasswd(passwd);
			m.setName(name);
			m.setEmail(email);
			m.setGender(gender);
			// db에 저장
			mDAO.insertMember(m);
			// 회원 가입 후 이동
			nextPage = "index.jsp"; 
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
