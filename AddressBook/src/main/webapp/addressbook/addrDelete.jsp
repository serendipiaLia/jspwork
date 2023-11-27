<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 주소 목록(addrList.jsp)에서 주소 정보 삭제! -->
<jsp:useBean id="abDAO" class="addressbook.AddrBookDAO" scope="application"/>

<%
	String username = request.getParameter("username");
	abDAO.deleteAddrBook(username);
	
	// 삭제 시, 페이지 이동(redirext - 리다이렉트)
	response.sendRedirect("addrList.jsp");
	
	
%>