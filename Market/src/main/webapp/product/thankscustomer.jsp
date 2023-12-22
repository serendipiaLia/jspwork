<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 완료</title>
</head>
<body>
<jsp:include page="../header.jsp" />
	
	<div class="container my-3">
		<h2>주문 완료</h2><hr>
		<h3>주문해 주셔서 감사합니다 :D</h3><br>
		<p>주문은 ${shipping_shippingdate } 일에 배송 될 예정입니다.</p>
		<p>주문 번호 : ${shipping_cartId }</p>
		<p><a href="/productlist.do" class="btn btn-secondary">상품 목록</a></p>
	</div>
	
	<jsp:include page="../footer.jsp"/>
</body>
</html>