<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxTest3.jsp</title>
<script type="text/javascript" 
	src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		//{"no":11,"name":"홍길동","content":"안녕"}
		$('#query').click(function(){
			$.ajax({
				url:"<c:url value='/ajaxView.do'/>",
				type:"get",
				data: "no=" + $("#no").val(),
				dataType:"json",
				success:function(res){
					//alert(res);
					var str="번호:"+ res.no+"<Br>";
					str+="이름:"+ res.name+"<Br>";
					str+="메모:"+ res.content+"<Br><br>";
					
					$("#result").append(str);
				},
				error:function(xhr, status,error){
					alert(status +", " + error);
				}
			});	
		});
		
		//{"message":"메모 등록 성공","data":{"no":10,"name":"hong","content":"hi"}}
		$('#frm1').submit(function(){
			var memo = $('#content').val();
			
			$.ajax({
				url:"<c:url value='/ajaxWrite.do'/>",
				type:"post",
				dataType:"json",
				/* data:{
					name: $('#name').val(),
					content: memo	
				}, */
				data: $(this).serializeArray(),
				success:function(res){
					//alert(res.message);
					var str=res.message+"<br>";
					str+="번호 : " + res.data.no+"<br>";
					str+="이름 : " + res.data.name+"<br>";
					str+="메모 : " + res.data.content+"<br><br>";
					
					$("#result").html(str);
				},
				error:function(xhr, status, error){
					alert(status+","+error);
				}
			});
			
			event.preventDefault();
		});	
		
		//[{"no":11,"name":"홍길동","content":"안녕"},{"no":12,"name":"김길동","content":"안녕하세요"},{"no":13,"name":"박길동","content":"내용"}]
		$('#btAll').click(function(){
			$.ajax({
				url:"<c:url value='/ajaxAll.do'/>",
				type:"post",
				dataType:"json",
				success:function(res){
					//alert(res.length);
					var str="";
					
					if(res.length>0){
						$.each(res, function(idx, item){
							str += "번호 - "+item.no +"<br>";
							str += "이름 - "+item.name +"<br>";
							str += "메모 - "+item.content +"<br><br>";							
						});
						
						$("#result").html(str);
					}
				},
				error:function(xhr, status, error){
					alert(status+","+error);
				}
			});	
		});
		
	})
</script>
</head>
<body>
<form id="frm1">
	 번호 : <input type="text" id="no" size="7"/>
	 <input type="button" id="query" value="조회"><br><Br>
	 <button type="button" id="btAll">전체 조회</button>
	
	 <h2>메모를 남기세요</h2>
	 이름 : <input type="text" id="name" name="name"/><br>
	 메모 : <input type="text" id="content" name="content" size="50" /><br>
	 <input type="submit" value="입력">
	     
	 <h2>결과</h2>
	 <div id="result" style="background:#eeeeee;width:500px"></div>
</form>
</body>
</html>