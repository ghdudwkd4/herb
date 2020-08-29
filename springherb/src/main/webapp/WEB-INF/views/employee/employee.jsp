<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="registerFlag" value="${employeeVO.emp_no==0 ? '등록' : '수정'}"/>
<title>사원 <c:out value= "${registerFlag}" /> </title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>
<script>
	function fn_save() { //등록, 수정 버튼 클릭시 처리
	   	frm.action = "<c:url value=" + ${registerFlag == '등록' ? '/emp/addEmp.do' : '/emp/updateEmp.do'}"/>";
		frm.submit();   
	}
</script>
</head>
<body>
	<c:if test="${registerFlag == '수정'}">
		<tr>
			<td class="tbtd_caption">사번</td>
			<td class="tbtd_content">
			       <input type="text" name="emp_no" class="essentiality" maxlength="10" 
				readonly value="${employeeVO.emp_no }" />
			</td>			
		</tr>
	</c:if>
	
	
	<li><a href="javascript:fn_save();">
		<c:out value='${registerFlag}'/>
	</a>
	</li>
				
	<c:if test="${registerFlag == '수정'}">
			<li>
				<a href="#" onclick="fn_delete(${employeeVO.emp_no })">삭제</a>
			</li>
	</c:if>
	
	
	
</body>
</html>