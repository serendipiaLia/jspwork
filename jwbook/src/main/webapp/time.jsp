<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>시간이란...</title>
<style>
	#wrap{width: 80%; margin: 0 auto; text-align: center}
	h2{color: blue;}
	h3{line-height: 2;}
</style>
<script src="resources/js/time.js"></script>
</head>
<body>
    <%!
        //전역 변수 선언(!붙임) - 선언문
        //전역 변수는 프로그램이 종료될때 메모리에서 소멸한다.
        int count = 0;
    %>
	<div id="wrap">
		<h2>시간이란...</h2>
		<h3>내일 죽을 것처럼 오늘을 살고<br>
		    영원히 살 것처럼 내일을 꿈꾸어라</h3>
		<p>이 사이트 방문은 <%= ++count %>번째 입니다</p>
		<div>
			<img src="resources/images/time.jpg" alt="시계">
		</div>
		<p id="display"></p>
	</div>
</body>
</html>