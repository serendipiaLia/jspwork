<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/배송조회</title>
</head>
<body>
<jsp:include page="../header.jsp" />
	
	<div class="container my-3">
		<h2>주문/배송조회</h2>
		<div class="row my-3">
			<div class="col-4">
				[주문자/ 배송지 정보]<br>
				성명 : ${shipping_sname }<br>
				우편번호 : ${shipping_zipcode }<br>
				주소 : ${shipping_address }<br>
			</div>
			<div class="col-8" align="right">
				배송일 : ${shipping_shippingdate }
			
			</div>
			
			<!-- 장바구니 품목 -->
			<table class="table table-hover my-3 px-3">
				<thead>
					<tr>	
						<th>상품</th> <th>가격</th> <th>수량</th> <th>소계</th> <th>비고</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${cartlist }" var="product">
						<tr>
							<td>${product.pid }-${product.pname }</td>
							<td><fmt:formatNumber value="${product.price}" /></td>
							<td>${product.quantity }</td>
							<!--  <td>${product.price * product.quantity }</td> -->
							<td><fmt:formatNumber value="${product.price * product.quantity}" /></td>
							<td><a href="/removecart.do?pid=${product.pid }" class="badge bg-info" >삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
				
				<tfoot>
					<tr>
						<td></td> <td></td> <td>총 액</td> <td><fmt:formatNumber value="${sum}" /></td><td>원</td>
					</tr>					
				</tfoot>				
			</table>
			<p class="text-center">
				<a href="/thankscustomer.do" class="btn btn-success">주문 완료</a>
			</p>
		</div> <!-- row -->
	</div>
	
	<jsp:include page="../footer.jsp"/>
</body>
</html>