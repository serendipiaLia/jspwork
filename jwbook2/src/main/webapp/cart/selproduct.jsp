<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 선택</title>
<style>
	#content{width: 800px; margin: 0 auto; text-align: center;}
</style>
</head>
<body>
	<% 	request.setCharacterEncoding("utf-8");
		// 로그인 처리 후 세션 발급
		String username = request.getParameter("username");
		session.setAttribute("sessionName", username); // 세션 발급
	%>
	<div id="content">
		<h2>상품 선택</h2>
		<hr>
		<p><%=session.getAttribute("sessionName") %>님이 로그인한 상태입니다. </p>
		<form action="addproduct.jsp" method="post" name="form1">
			<select name="product" id="product" onchange="selproduct()">
				<option> -- 상품 선택 -- </option>
				<option value="사과">사과</option>
				<option value="포도">포도</option>
				<option value="귤">귤</option>
			</select>
		<!--  <input type="submit" value="추가"> -->
		</form>
		<p><button onclick="location.href='cart.jsp'">장바구니</button> </p>
	</div>
	
	<script>
//		let selproduct =  function(){ //익명함수
//			let product = document.getElementById("product").value; //select 태그 선택
// 			alert(product + "(이)가 선택 되었습니다."); // 장바구니에는 안담기고 선택만 됨	
//			document.form1.submit(); // 폼의 내용이 전송됨
//		}

		// 화살표 함수(실행 함수) =>
		let selproduct = () => { //익명함수
			let product = document.getElementById("product").value; //select 태그 선택
// 			alert(product + "(이)가 선택 되었습니다."); // 장바구니에는 안담기고 선택만 됨	
			document.form1.submit(); // 폼의 내용이 전송됨
		}
	</script>
</body>
</html>