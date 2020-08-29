<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript">
	$(function() {
		$("#frm1").submit(function() {
			var str = $(this).serialize(); //form submit 일때
			//=> 입력 양식의 내용을 쿼리문자열로 만든다
			
			var str2 = $("#frm1").serializeArray(); //form submit 아닐때 둘다 가능
			//=> 입력 형식의 내용을 객체로 만든다
			
			$("<p></p>").html(str).appendTo("#result");
			$("<p></p>").text(str2).appendTo("#result");
			
			
			var str = $.param(str2); //객체를 쿼리문자열로 반환해 준다
			$("<h3></h3>").text(str).appendTo("#result");
			
			event.preventDefault();
			
			
		});
	});
</script>
</head>
<body>
	<h1>serialize(),serializeArray() 연습</h1>
	<form id="frm1">
		이름 : <input type="text" id="name" name="name"/><br>
		메모 : <input type="text" id="content" name="content" size="50" /><br>
		<input type="submit" value="입력"><br>
		<h2>결과</h2>
		<div id="result"></div>
	</form> 
</body>
</html>