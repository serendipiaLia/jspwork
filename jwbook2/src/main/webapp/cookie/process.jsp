<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id"); 
	String pw = request.getParameter("pw");
	
	if(id.equals("khit") && pw.equals("1234")) {
		// 쿠키 발행 : Cookie(쿠키이름, 쿠키 값) 
		Cookie cookieId = new Cookie("userId", id);
		Cookie cookiePwd = new Cookie("userPw", pw);
		
		// 브라우저(로컬 컴)에 응답(=보내줌)
		response.addCookie(cookieId); //(쿠키객체)
		response.addCookie(cookiePwd); 
		out.println("쿠키 생성에 성공했습니다.");
		
	}else {
		out.println("쿠키 생성에 실패했습니다.");
	}
	
%>