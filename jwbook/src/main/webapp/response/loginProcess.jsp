<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("uid");
	String pw = request.getParameter("passwd");
	
//  id - today, pw - 1234
	if(id.equals("today") && pw.equals("1234")){
		//out.println("로그인을 성공했습니다.");
		response.sendRedirect("loginSuccess.jsp");
	}else{
		//out.println("로그인을 실패했습니다.");
		response.sendRedirect("loginFail.jsp");
		
	}
//	out.println(id + "," + pw);

%>