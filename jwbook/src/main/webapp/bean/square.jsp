<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제곱을 계산하는 프로그램 사용</title>
<!-- Java Resources > bean > Calculator.java와 연결 -->
</head>
<body>
	<!--  자바 빈즈(자바 클래스를 의미함) -->
	
	<!-- Calculator calc = new Calculator() -->
	<jsp:useBean id="calc" class="bean.Calculator" />
	<% int num = calc.calculate(4);
		out.println("결과:" + num);
	%>
</body>
</html>