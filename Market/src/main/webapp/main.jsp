<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH market..</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<%-- <%@ include file= "header.jsp" %> --%>
	
	<div class="container my-5">
		<h1 class="text-center">웹 마켓에 오신 것을 환영합니다.</h1>
		<div class="text-center mt-3">
			<img alt="coffee.." src="resources/images/coffee.jpg" class="rounded">		
		</div>
	</div>
	
	<jsp:include page="footer.jsp"/>
	<%-- <%@ include file="footer.jsp" %> --%>
</body>
</html>