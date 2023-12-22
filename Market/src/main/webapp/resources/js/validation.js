/**
 *  상품 등록 유효성 검사 
 * 	<pform.jsp>
 * 	- 상품 코드는 P와 숫자를 조합하여 5~12자로 입력
 * 	- 상품명은 4~20자 입력
 * 	- 가격 및 재고수는 숫자만 입력
 */

 let checkProduct = function(){
	/* alert("test"); */
	let pid = document.getElementById("pid");
	let pname = document.getElementById("pname");
	let price = document.getElementById("price");
	let pstock = document.getElementById("pstock");
	
	// 정규표현식
	let regexPid = /P[0-9]{4,11}/ /*상품 코드는 P와 숫자를 조합하여 5~12자로 입력*/
	
	if(!regexPid.test(pid.value)){ /* 정규표현식이 상품코드와 일치하지 않으면! */
		alert("상품 코드는 P와 숫자를 조합하여 5~12자로 입력하세용!")
		pid.select();
		return false;
		
	}else if(pname.value.length < 4 || pname.value.length > 20){
		alert("상품명은 4~20자 입력하세용")
		pname.select();
		return false;
	
	}else if(price.value.length==0 ||  isNaN(price.value) || price.value < 0 ){ /* 빈칸 || 번호만 || 음수 */
		alert("가격은 0보다 큰 숫자만 입력하세용")
		price.select();
		return false;
		
	}else if(pstock.value.length==0 ||  isNaN(pstock.value) || pstock.value < 0 ){ /* 빈칸 || 번호만 || 음수 */
		alert("재고 수는 0보다 큰 숫자만 입력하세용")
		pstock.select();
		return false;
	}else{
		document.newProduct.submit();
	
	}
 }