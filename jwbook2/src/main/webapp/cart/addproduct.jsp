<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	// 상품을 저장 할 arraylist 생성
	ArrayList<String> productList = (ArrayList)session.getAttribute("sessionlist");
	if(productList == null){
		productList = new ArrayList<>();
		session.setAttribute("sessionlist", productList);
	}
	// 상품을 파라미터로 전달받음
	String product = request.getParameter("product");
	// 상품을 리스트에 추가	
	productList.add(product);
%>
<script>
	alert("<%=product%>가 추가되었습니다.");
	history.back();
</script>
