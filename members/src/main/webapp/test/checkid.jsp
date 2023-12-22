<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id 중복 검사</title>
<script src="../resources/js/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$(function(){
		$("h3").css("color", "deepskyblue");
	});
	function checkId(){
		/* alert("test"); */
		// input의 선택자(id)의 값을 사용
		if($("#tid").val() == ""){
			alert("아이디를 입력해주세요.");
			tid.focus();
			return false;
		}
			$.ajax({
				type: "get",
				dataType: "text",
				url: "/test/checkid",
				data: {id: $("#tid").val()},
				success: function(data){
					if(data == "usable"){
						$("#message").text("사용할 수 있는 ID입니다.");
					}else{ // data == notusable
						$("#message").text("중복된 ID입니다. 다시 입력하세요.");
					}
				},
				error: function(){
					alert("error!");
				}
			});
	}

</script>
</head>
<body>
	<h3>ID 중복 검사</h3>
	<p><input type="text" id="tid" name="id">
		<input type="button" value="중복 검사" onclick="checkId()"> </p>
	<div id="message"></div>
	
</body>
</html>