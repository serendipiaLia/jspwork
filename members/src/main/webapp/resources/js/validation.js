
	function checkMember(){
/* 		alert("test"); */
	// input의 name 속성을 변수에 할당
	let form = document.member; //폼 이름 
	
	let id = form.id.value;	//폼 이름
	let pw1 = form.passwd.value; // id - name속성
	let pw2 = form.passwd2.value;
	let name = form.name.value;
	let btnChk = form.btnChk.value;
	
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
	}else if(regexName.test(name)){ // pw1과 pw2 문자열이 일치하지 않으면
		alert("이름은 한글로 입력하세요.")
		name.select();
		return false;
		
	}else if(btnChk == 'N'){ 
		alert("ID 중복 버튼을 눌러주세요")
		return false;
		
	}else{
		form.submit(); // 오류가 없으면 폼을 MainController로 전송
		}
	
	}// checkMember()

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
						$("#btnChk").attr("value", "Y"); // 버튼 value값을 "Y"로 설정
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