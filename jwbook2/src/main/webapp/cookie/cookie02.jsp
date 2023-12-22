<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies(); //쿠키 가져오기
	out.println("현재 쿠키의 수 : " + cookies.length);
	
	// 개발자 도구에서 안봐도 쿠키 속성 보는 방법!
	for(int i=0; i<cookies.length; i++){
		out.println("쿠기 속성의 이름 : " + cookies[i].getName() + "<br>"); 
		out.println("쿠기 속성의 값 : " + cookies[i].getValue() + "<br>");
		out.println("<br>");
	}
	
%>
