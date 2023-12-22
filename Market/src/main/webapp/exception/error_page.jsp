<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 오류입니다.</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	
	<div class="container my-5">
		<div class="text-center">
			<h2 class="alert-danger">코드 오류로 인한 장애입니다. 빠른 조치 하겠습니다.</h2>
		</div>
		<p><a href="/productlist.do" class="btn btn-secondary">&laquo; 상품 목록</a></p>
	</div>
	
	<jsp:include page="../footer.jsp"/>
</body>
</html>