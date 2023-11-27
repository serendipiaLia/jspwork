<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="addrBook" class="addressbook.AddrBook" />  
<jsp:setProperty property="bnum" name="addrBook"/> <!-- setBnum과 property="num"과 같음 -->
<jsp:setProperty property="username" name="addrBook"/>
<jsp:setProperty property="tel" name="addrBook"/>
<jsp:setProperty property="email" name="addrBook"/>
<jsp:setProperty property="gender" name="addrBook"/>
<jsp:useBean id="abDAO" class="addressbook.AddrBookDAO" scope="application"/>

<%
	// 수정한 것을 처리하는 메서드 호출
	abDAO.updateAddrBook(addrBook);
	
	out.println("<script>");
	out.println("alert('주소를 수정했습니다.')");
	out.println("location.href='addrList.jsp' "); // 주소목록 페이지로 이동
	out.println("</script>");
	// 수정 후 

%>