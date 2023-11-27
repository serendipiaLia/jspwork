<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<p>인사하기 ▼</p>
<%-- <%
	int cnt = Integer.parseInt(request.getParameter("cnt"));

	for(int i=0; i < cnt; i++){
		out.println("안녕~ JSP!<br>");
	}

%> --%>

<!-- 위의 500오류 처리 : null string -->
<% 
	int cnt = 0;
	
	if(request.getParameter("cnt") != null){
		cnt = Integer.parseInt(request.getParameter("cnt"));
	}
	
	for(int i=0; i < cnt; i++){
		out.println("안녕~ JSP!<br>"); /* 오류는 아니지만 출력x */
	}
%>