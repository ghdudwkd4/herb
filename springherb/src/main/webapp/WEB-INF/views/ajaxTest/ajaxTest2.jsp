<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxTest2.jsp</title>
<script type="text/javascript" 
	src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>

<script type="text/javascript">
	$(function(){
		//[{"no":1,"name":"홍길동","content":"내용1"},{"no":2,"name":"김길동","content":"내용2"},{"no":3,"name":"이길동","content":"내용3"}]
		$('#bt1').click(function(){
			$.ajax({
				url:"<c:url value='/ajaxList.do'/>",
				type:"get",
				dataType:"json",
				success:function(res){
					//alert(res.length);
					
					if(res.length>0){
						var str="";
						$.each(res, function(idx, item){
							str+="번호 : " +  item.no+"<br>";
							str+="이름 : " +  item.name+"<br>";
							str+="내용 : " +  item.content+"<br><br>";							
						});	
						$("#info").html(str);
					}
				},
				error:function(xhr, status, error){
					alert(status+","+ error);
				}
			});
		});
		
		//{"no":0,"name":"홍길동","content":"내용"}
		$('#bt2').click(function() {
			$.ajax({
				url : "<c:url value='/ajaxDetail.do'/>",
				type : "get",
				dataType : "json",
				data : "no=5", 
				success:function(res){
					//alert(res.name);
					var str = "번호 - " + res.no + "<br>";
					str += "이름 - " + res.name + "<br>";
					str += "내용 - " + res.content + "<br><br>";
					
					$('#info').html(str);
				},
				error:function(xhr, status, error){
					alert(status + ", " + error);
				}
			});
		});
	}); //doc
</script>
</head>
<body>
<h1>ajax 연습2</h1>
<button id="bt1">List</button>
<button id="bt2">VO</button> <!-- ajaxDetail.do -->
<hr>
<div id="info"></div>
</body>
</html>


