<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 예제</title>
<script src="../resources/js/jquery-3.7.1.js"></script>
<script>
	// 파일이 실행되면 제목이 파랑색으로 바뀜
	$(document).ready(function(){
		$("h3").css("color", "pink");
	});
	
	// 전송 버튼을 누르면 메시지 박스에 메시지를 출력함
	function fnProcess(){
		// ajax로 구현
		// 자바스크립트 객체{key : value}
		$.ajax({
			type: "get", // 전송방식 get
			dataType: "text", // 데이터 유형 text
			url : "http://localhost:8080/jwbook2/ajax/ajax1", // servlet에 요청(mapping 주소)
		  //data : {message : "<p>hello ajax</p>"}, >> 26행 append사용 시,,,
			data : {message : "hello ajax"},
			success : function(data){ // 서블릿에서 응답
			  //$("#message").append(date); 
				$("#message").text(data).css("margin-top", "10px");
			},
			error : function(){
				alert("에러 발생");
			}
	
		});
	}
</script>

</head>
<body>
	<h3>ajax(에이젝스) 테스트</h3>
	<button type="button" onclick="fnProcess()">전송</button>
	<div id="message"></div>
</body>
</html>