<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productImage.jsp</title>
<style type="text/css">
	.divImg{
		text-align: center;
		width:390px;
	}
	.divImg img{
		width:390px;
	}
</style>
<script type="text/javascript" 
	src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$('.divImg button').click(function(){
			self.close();	
		});
	});
</script>
</head>
<body>
<div class="divImg">
	<p>
		<img src="<c:url value='/pd_images/${param.imageURL }'/>" 
			alt="${param.productName }">
	</p>
	<button>닫기</button>
</div>
</body>
</html>