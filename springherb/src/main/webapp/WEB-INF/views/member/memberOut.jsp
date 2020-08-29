<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<script type="text/javascript">
	$(function(){
		$('#pwd').focus();
		
		$('form[name=frmOut]').submit(function(){
			if($('#pwd').val().length<1){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				event.preventDefault();
			}else{
				if(!confirm("회원탈퇴하시겠습니까?")){
					event.preventDefault();
				}
			}
		});
		
	});
</script>
<article class="simpleForm">
	<h2>회원탈퇴</h2>
	<p class="p">회원탈퇴하시겠습니까?</p>
	<form name="frmOut" method="post" 
		action="<c:url value='/member/memberOut.do'/>">
		<div>
			<label for="pwd" >비밀번호</label>
			<input type="password" name="pwd" id="pwd">
		</div>	
		<div class="align_center">
			<input type="submit" value="회원탈퇴">
			<input type="reset" value="취소">			
		</div>
	</form>
</article>
<%@ include file="../inc/bottom.jsp" %>
