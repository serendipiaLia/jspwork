<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
<style>
	#container{width: 80%; margin : 30px auto; text-align: center;}
</style>
</head>
<jsp:useBean id="product" class="el.Product" />
<body>
	<div id="container">
		<h2>상품 목록</h2>
		<hr>
		<form action="selProduct.jsp" method="get">
			<select name="select">
			<c:forEach items="${product.productList}" var="product">
				<%-- <option>${product }</option> --%>
				<option><c:out value="${product}"/></option>
			</c:forEach>
			
				<!-- <option value="apple">사과</option> 사용 대신, --> 
<%-- 			<% 
					/* for(int i=0; i<product.getProductList().length; i++){
						out.println("<option>" + product.getProductList()[i] + "</option>");
					}  
						위의 식을 향상된 for문으로 입력 ▼ */
						for(String item : product.getProductList()){
							out.println("<option>" + item + "</option>");
						}
				%> 
--%>	
				
			</select>
			<input type="submit" value="선택">
		</form>
	</div>
</body>
</html>