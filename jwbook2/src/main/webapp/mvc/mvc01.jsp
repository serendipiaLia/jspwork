<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 실습</title>
</head>
<body>
	<h3>데이터 받기</h3>
	<p>계절: ${season } </p>
<%-- <p>
		Four Seasons : 
			${seasons[0]},
			${seasons[1]},
			${seasons[2]},
			${seasons[3]}
	</p> 
--%>
	<c:forEach items="${seasons }" var="season">
		<p>${season }</p>
	</c:forEach>
	
	<hr>
	<h3>데이터 받기2</h3>
	<p>자동차<br>
	<p>	브랜드 : ${car.brand }<br>
		배기량 : ${car.cc }
	</p>
</body>
</html>