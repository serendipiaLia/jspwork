<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 폼</title>
<link rel="stylesheet" href="resources/css/style.css">
<script src="../resources/js/jquery-3.7.1.js"></script>
<script src="../resources/js/validation.js"></script>
<script>
	function checkMember(){
/* 		alert("test"); */
	// input의 name 속성을 변수에 할당
	let form = document.member; //폼 이름 
	
	let id = form.id.value;
	let pw1 = form.passwd.value;
	let pw2 = form.passwd2.value;
	let name = form.name.value;
	
	// 정규 표현식
	// 비밀번호
	let regexPw1 = /[0-9]+/; // 숫자
	let regexPw2 = /[a-zA-Z]+/; // 영문자
	let regexPw3 = /[~!@#$%^&*()_+|]+/; //특수문자
	
	// 이름
	let regexName = /^[가-힣]+$/ // 한글만
	
	if(id.length < 4 || id.length > 15){ // false가 났을때,
		alert("아이디는 4~15까지 입력 가능합니다.")
		id.select();
		return false;
		
	}else if(pw1.length < 8 || !regexPw1.test(pw1) || !regexPw2.test(pw1) || !regexPw3.test(pw1)){
		alert("비밀번호는 8자 이상(영문자, 숫자, 특수문자 포함).")
		pw1.select();
		return false;
		
	}else if(pw1 != pw2){ // pw1과 pw2 문자열이 일치하지 않으면
		alert("비밀번호가 일치하지 않습니다.다시 입력하세요")
		pw2.select();
		return false;
	}else if(!regexName.test(name)){ // pw1과 pw2 문자열이 일치하지 않으면
		alert("이름은 한글로 입력하세요.")
		name.select();
		return false;
	}else{
		form.submit(); // 오류가 없으면 폼을 MainController로 전송
		}
	
	}// checkMember()
</script>
</head>
<body>	
	<jsp:include page="../header.jsp"/>
	<div id="container">
		<section id="join">
			<h2>회원 가입</h2>
				<form action="/insertmember.do" method="post" name="member">
					<fieldset>
						<ul>
							<li>
								<label for="tid">아이디</label>
								<input type="text" id="tid" name="id" placeholder="4~15자 까지 입력 가능" >
								<button type="button" id="btnChk" value="N" class="btn_check" onclick="checkId()">중복 확인</button>
								<p id="message"></p>
							</li>
							<li>
								<label for="passwd">비밀번호</label>
								<input type="password" id="passwd" name="passwd" placeholder="8자 이상(영문자, 숫자, 특수문자 포함)">
							</li>
							<li>
								<label for="passwd2">비밀번호 확인</label>
								<input type="password" id="passwd2" name="passwd2" placeholder="위와 동일하게 입력하세요">
							</li>
							<li>
								<label for="name">이름</label>
								<input type="text" id="name" name="name" placeholder="한글만 입력하세요">
							</li>
							<li>
								<label for="email">이메일</label>
								<input type="text" id="email" name="email">
							</li>
							<li>
								<label for="gender">성별</label>
								<input type="radio" id="genderM" name="gender" value="남" checked>남
								<input type="radio" id="genderF" name="gender" value="여">여
							</li>
						</ul>
					</fieldset>
					<div class="button">
						<button type="button" onclick="checkMember()">가입</button>
						<button type="reset">취소</button>
					</div>
				</form>
		</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>