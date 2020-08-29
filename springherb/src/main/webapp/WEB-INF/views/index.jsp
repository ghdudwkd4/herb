<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="inc/top.jsp" %>    


<article id="centerCon">
	<img src="${pageContext.request.contextPath}/resources/images/herb.JPG" alt="허브 이미지">
</article>
<article id="rightCon">
	<c:import url="/inc/mainNotice.do"></c:import>
</article>
<article id="listCon">	
	<c:import url="shop/product/productCatalog.jsp"></c:import>
	<%-- <%@ include file="shop/product/productCatalog.jsp" %> --%>    
</article>


<%@ include file="inc/bottom.jsp" %>    

