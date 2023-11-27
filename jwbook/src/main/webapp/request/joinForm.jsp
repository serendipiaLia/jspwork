<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원가입 폼</title>
<style type="text/css">
	ul li{list-style: none; margin: 10px;}
</style>
</head>
<body>
	<form action="joinProcess.jsp" method="post">
		<ul>
			<li>
				<label for="uid">아이디</label>
				<input type="text" id="uid" name="uid" required autofocus> 
			</li>	
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" id="passwd" name="passwd" required>
			</li>	
			<li>
				<label for="name">이름</label>
				<input type="text" id="name" name="name" required>
			</li>	
			<li>
				<label for="email">이메일</label>
				<input type="text" id="email" name="email" required>
			</li>	
			<li>
				<label for="tel">연락처</label>
				<input type="text" id="tel1" name="tel1" size=4 maxlength=3>
				 - <input type="text" id="tel2" name="tel2" size=4 maxlength=4>
				 - <input type="text" id="tel3" name="tel3" size=4 maxlength=4>
			</li>	
			<li>
				<label for="hobby">취미<small>(다중선택가능)</small></label>
				<label><input type="checkbox" id="hobby" name="hobby" value="운동" >운동</label>
				<label><input type="checkbox" id="hobby" name="hobby" value="영화" >영화보기</label>
				<label><input type="checkbox" id="hobby" name="hobby" value="게임" >게임하기</label>
			</li>
			<li>
				<label for="gender">성별</label>
				<label><input type="radio" id="gender" name="gender" value="male" >남성</label>
				<label><input type="radio" id="gender" name="gender" value="female" >여성</label>
			</li>
			<li>
				<textarea rows="3" cols="30" name="comment" placeholder="가입인사를 남겨주세요."></textarea>
			</li>
			<li>
				<!-- <input type="submit" value="로그인"> -->
				<button type="submit">가입</button>
				<button type="reset">취소</button>
			</li>
		</ul>
	</form>
</body>
</html>