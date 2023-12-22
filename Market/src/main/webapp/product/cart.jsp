<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>

</head>
<body>
	<jsp:include page="../header.jsp" />
	
	<div class="container my-3">
		<h2>장바구니</h2>
		<div class="row my-3">
			
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
							<td id="removebtn"><a href="/removecart.do?pid=${product.pid }" class="badge bg-info" >삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
				
				<tfoot>
					<tr>
						<td></td> <td></td> <td>총 액</td> <td><fmt:formatNumber value="${sum}" /></td><td>원</td>
					</tr>					
				</tfoot>				
			</table>
			<table>
				<tr>
					<td align="left">
						<a href="/deletecart.do" class="btn btn-danger">전체삭제</a> 
					
					</td>
					<td align="right">
						<!-- 장바구니 주문이므로 카트아이디를 전달해줘야함! -->
						<a href="/shippingform.do?cartId=${cartId }" class="btn btn-success">주문하기</a> 
					</td>
				</tr>
			</table>

		</div> <!-- row -->
	</div>
	
	<jsp:include page="../footer.jsp"/>
</body>
</html>