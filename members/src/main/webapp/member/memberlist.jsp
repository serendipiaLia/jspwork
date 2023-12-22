<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 페이지</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>	
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="memberlist">
			<div class="logout"> 
				<p><a href="/logout.do">👉관리자 로그 아웃</a> </p>
			</div>
			<h2>회원 목록</h2>
				<table>
					<thead>
						<tr>
							<th>번호</th> <th>아이디</th> <th>비밀번호</th> <th>이름</th> <th>이메일</th> <th>성별</th> <th>가입일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${memberList}" var="m">
							<tr>
								<td>${m.mno }</td> 
								<!-- id를 클릭하면 상세보기로 이동함 >> 링크 걸어주기 -->
								<td><a href="/memberview.do?id=${m.id }"> ${m.id }</a></td> 
								<td>${m.passwd }</td> 
								<td>${m.name }</td>
								<td>${m.email }</td> 
								<td>${m.gender }</td> 
								<td><fmt:formatDate value= "${m.joindate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>