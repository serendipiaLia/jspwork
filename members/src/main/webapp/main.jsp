<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<link rel="stylesheet" href="resources/css/style.css">

</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="container">
		<section id="main">
			<h1>KH-IT community</h1>
			<div class="main_img">
				<img src="resources/images/bear.jpeg" alt="곰" width="500" height="800">
			</div>
			
			<!-- 최근 게시글 박스 -->
			<div class="newB">
			<h3>최근 게시글</h3>
			<c:forEach items="${boardList }" var="board">
				<p>
					<a href="/boardlist.do?bno=${board.bno }"> ${board.title }(글쓴이 : ${board.id }, 
					작성일 :<fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>)
					</a>
				</p>
			</c:forEach>
			</div>
		</section>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>