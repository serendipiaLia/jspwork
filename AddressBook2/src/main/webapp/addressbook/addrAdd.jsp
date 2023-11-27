<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8"); %>

<title>주소록 내용 추가(등록)</title>
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<jsp:useBean id="addrBook" class="addressbook.AddrBook" />
	<jsp:setProperty property="*" name="addrBook"/>
	<jsp:useBean id="abDAO" class="addressbook.AddrBookDAO" scope="application"/>
	<%
		abDAO.addAddrBook(addrBook); // 주소 추가
		// 회원 가입 후 자동 로그인
		session.setAttribute("sessionId", addrBook.getEmail());
	%>
	
	<div id="container">
	<h2>등록 내용</h2>
	<hr>
	<p>이름 : <%=addrBook.getUsername() %> </p>
	<p>전화번호 : <%=addrBook.getTel() %> </p>
	<p>이메일 : <%=addrBook.getEmail() %> </p>
	<p>성별 : <%=addrBook.getGender() %> </p>
	<hr>
	<p><a href="addrList.jsp">목록 보기</a></p>
	<p><a href="../index.jsp">메인으로 가기</a></p>
</div>
</body>
</html>