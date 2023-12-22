<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* 파일 다운로드 시, 확인할 수 있는 경로 */
	ServletContext context = getServletContext();
	String realPath = context.getRealPath("upload");
	out.println(realPath);
%>