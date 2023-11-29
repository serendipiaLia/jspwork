<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다국어 처리</title>
</head>
<body>
	<p>=== 기본 locale ===</p>
		<fmt:setLocale value="ko"/>
		<fmt:setBundle basename="com.bundle.message_ko"/>
	<p> 제목 : <fmt:message key="title" /></p>
	<p> 이름 : <fmt:message key="username" /> </p>
	<p> 비밀번호 : <fmt:message key="password" /> </p>
	
	<p>=== 영문 locale ===</p>
		<fmt:setLocale value="en"/>
		<fmt:setBundle basename="com.bundle.message_en"/>
	<p> 제목 : <fmt:message key="title" /></p>
	<p> 이름 : <fmt:message key="username" /> </p>
	<p> 비밀번호 : <fmt:message key="password" /> </p>
	
</body>
</html>