<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다중 선택 처리</title>
</head>
<body>
	<form action="process2.jsp" method="post">
		<p>
			<label>오렌지  
			<input type="checkbox" name="fruit" value="orange"></label>
			
			<label>사과 
			<input type="checkbox" name="fruit" value="apple"></label> 
			
			<label>포도  
			<input type="checkbox" name="fruit" value="grape"></label>
			<input type="submit" value="선택">
		</p>
	</form>
</body>
</html>