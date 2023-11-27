<%@page import="java.util.regex.Pattern"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	String num = request.getParameter("num");
	String regex = "[0-9]+"; // 숫자만
	String result = ""; // 문자열 변수를 선언
	// Pattern.matches(정규표현식, 문자열)
	if(num == "" || !Pattern.matches(regex, num)){ // 빈 문자 처리
		out.println("<script>");
		out.println("alert('숫자를 입력해주세요')");
		out.println("history.back()"); // 이전 페이지 이동
		out.println("</script>");
	}else{
		int n = Integer.parseInt(num); // 문자를 숫자로 변환
		if(n % 2 == 0){
			result = "짝수";
		}else{
			result = "홀수";
		}
	}
%>

<p>결과는 : <%=result %> 입니다.</p>