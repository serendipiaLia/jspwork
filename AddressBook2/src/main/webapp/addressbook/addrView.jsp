<%@page import="addressbook.AddrBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 정보</title>
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<jsp:useBean id="abDAO" class="addressbook.AddrBookDAO" scope="application"/>
	<%
		// bnum으로 찾은 객체 생성
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		AddrBook addrBook = abDAO.getAddrBook(bnum);
	%>
	
	<div id="container">
		<h2>주소 정보</h2>
		<hr>
		<table id="tbl_view">
			<tr>
				<td>이름</td>
				<td><%=addrBook.getUsername() %></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><%=addrBook.getTel() %></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><%=addrBook.getEmail() %></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><%=addrBook.getGender() %></td>
			</tr>
			<tr>
				<td colspan="2">
					<!-- session.getAttribute("sessionId") -->
					<% 
						String sessionId = (String)session.getAttribute("sessionId"); 
						if(sessionId.equals(addrBook.getEmail())){ %> <!-- sessionId와 로그인한 이메일이 같으면, 수정/삭제 -->
					
					<a href="addrUpdate.jsp?bnum=<%=addrBook.getBnum() %>">
						<button type="button">수정</button>
					</a>
					<a onclick="return confirm('정말로 해당 주소를 삭제 하시겠습니까?')"
						href="addrDelete.jsp?bnum=<%=addrBook.getBnum() %>">
						<button type="button">삭제</button>
					</a>
					<% } %>
					<a href="addrList.jsp">
						<button type="button">목록보기</button>
					</a>
				</td>
			</tr>
		
		</table>
		
	</div>
</body>
</html>