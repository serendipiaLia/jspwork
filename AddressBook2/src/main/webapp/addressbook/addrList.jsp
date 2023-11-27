<%@page import="addressbook.AddrBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소 목록</title>
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<jsp:useBean id="abDAO" class="addressbook.AddrBookDAO" scope="application"/>
	<%
		// 세션이 없는 경우(로그아웃 상태), 로그인 하도록 알림 창 띄움
		// 로그인 세션 아이디 발급(sessionId) : loginProcess.jsp
		String sessionId = null; 
		if(session.getAttribute("sessionId") != null){ // 로그인 된 경우
			sessionId = (String)session.getAttribute("sessionId");
		}else {
			out.println("<script>");
			out.println("alert('로그인을 해주세요')");
			out.println("location.href='loginForm.jsp' ");  // 로그인할 수 있도록 href를 로그임 폼으로 맞춰주기!
			out.println("</script>");
		}
	%>
	<div id="container">
		<h2>주소 목록</h2>
		<hr>
		<p>
			<!-- <a href="addrForm.jsp">[주소추가]</a> &nbsp;&nbsp; -->
			<a href="logout.jsp">
				<span class="accent">(<%=session.getAttribute("sessionId") %> 님)</span>[로그아웃] <!-- 새로만든 주소록만 뜸 -->
			</a> 
		</p>
		
		<table id="tbl_list">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>성별</th>
					<th>등록일</th>
					<th>상세보기</th>
				</tr>
			</thead>	
			
			<tbody>
				<%
					/* abDAO.getListAll() : addrList와 같음 */
					for(int i=0; i<abDAO.getListAll().size(); i++){
						AddrBook addrBook = abDAO.getListAll().get(i);
				%>
				<tr>
					<td><%=addrBook.getBnum() %></td>
					<td><%=addrBook.getUsername() %></td>
					<td><%=addrBook.getTel() %></td>
					<td><%=addrBook.getEmail() %></td>
					<td><%=addrBook.getGender() %></td>
					<td><%=addrBook.getRegDate() %></td>
					<td>
						<a href="addrView.jsp?bnum=<%=addrBook.getBnum() %>">
							<button type="button">보기</button>
						</a>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
			<p><a href="../index.jsp">메인으로 가기</a></p>
	</div>
</body>
</html>