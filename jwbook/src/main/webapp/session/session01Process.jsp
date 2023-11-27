<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("uid");
	String userPw = request.getParameter("passwd");
	
	// 아이디 : khit, 비밀번호: 1234
	if(userId.equals("khit") && userPw.equals("1234")){
		//로그인 성공시, 세션 발급 ! 세션이름 : userID >> setAttribute("세션이름", 객체)사용
		session.setAttribute("userID", userId);
		out.println("세션이 발급되었습니다.");
		session.setMaxInactiveInterval(2 * 60); // 세션 유효시간 2분(=120초)
	}else {
		out.println("<script>");
		out.println("alert('아이디나 비밀번호가 일치하지 않습니다.')");
		out.println("history.back()"); /* 이전페이지로 이동 : history.back() */
		out.println("</script>");
	}
%>

<p><%=session.getAttribute("userID") %>님이 로그인한 상태입니다.</p>