<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxTest/ajaxTest1.jsp</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:"<c:url value='/search.do'/>",
			type:"get",
			/* data:{
				keyword:"s",
				id:"hong"
			}, */
			data:"keyword=s&id=kim",
			success:function(res){
				$("#result").append(res);
			},
			error:function(xhr, status, error){
				alert(status+", " + error);
			}
		});
	});
</script>
</head>
<body>
	<h1>ajax 연습1</h1>
	<div id="result"></div>
	
</body>
</html>