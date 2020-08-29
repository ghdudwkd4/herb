<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/top.jsp" %>

<style type="text/css">
	.divEvent{
		width: 780px;
	}
	
	.divPd{
		width: 140px;
		float:left;
		border: 1px solid #eeeeee;
		margin: 0 10px 10px 0;
		padding: 20px;
		text-align: center;
	}
	
	.line{
		width:100%;
		border: 1px solid #eeeeee;
		padding: 20px;
		text-align: center;
	}
	
	.pCgName{
		padding: 20px 0 20px 0;
		font-size: 1.5em;
		font-weight: bold;		
	}
</style>
<p class="pCgName">
	<img src="${pageContext.request.contextPath }/resources/images/dotLong4.JPG"
			align="absmiddle">
	<span>${param.categoryName }</span>
</p>

<div class="divEvent">
	<c:if test="${empty list }">
		<div class="line">
			해당 상품이 없습니다.
		</div>
	</c:if>
	<c:if test="${!empty list }">
		<c:forEach var="vo" items="${list }">
			<!-- 반복 시작 -->
			<div class="divPd">
				<a href
="<c:url value='/shop/product/productDetail.do?productNo=${vo.productNo }'/>">
					<img src
	="${pageContext.request.contextPath }/pd_images/${vo.imageURL}" 
						alt="${vo.productName }">
				</a>	
				<br> 
				${vo.productName }<br>
				<fmt:formatNumber value="${vo.sellPrice }"
					pattern="#,###"/>원	
			</div>
			<!-- 반복 끝 -->
		</c:forEach>
	</c:if>
</div>

<%@ include file="../../inc/bottom.jsp" %>
