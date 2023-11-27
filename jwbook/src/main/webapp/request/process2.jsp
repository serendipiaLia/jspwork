<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 메모리(DB)에 value값이 들어감!(=orange, apple, grape)
	String[] fruitList = request.getParameterValues("fruit");
%>

<p>선택한 과일 ▼</p>
<p>
<%	
	for(int i=0; i<fruitList.length; i++){
			out.println(fruitList[i]);
		}
%>
</p>