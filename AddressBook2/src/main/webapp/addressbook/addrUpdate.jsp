<%@page import="addressbook.AddrBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 상세보기 수정</title>
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
		<h2>주소 수정</h2>
		<hr>
		<form action="addrUpdateProcess.jsp" method="post">
			<table id="tbl_view">
				<tr>
					<td>번호</td>									<!-- 고칠 수 없도록 하기 : readonly -->
					<td><input type="text" name="bnum" value=<%=addrBook.getBnum() %> readonly></td> 
				</tr>
				<tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="username" value=<%=addrBook.getUsername() %>></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="tel" value=<%=addrBook.getTel() %>></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" value=<%=addrBook.getEmail() %>></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<% if(addrBook.getGender().equals("남")) { %> 
							<input type="radio" name="gender" value="남" checked>남
							<input type="radio" name="gender" value="여">여
						<% }else {  %>
							<input type="radio" name="gender" value="남">남
							<input type="radio" name="gender" value="여" checked>여
						<% } %>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="저장">
						<input type="reset" value="취소">
						<a href="addrList.jsp">
							<button type="button">목록보기</button>
						</a>
					</td>
				</tr>
			</table>
		</form>		
	</div>
</body>
</html>