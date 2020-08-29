<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">
	#divMainNotice{
		width: 310px;
	}
	#divMainNotice div span:first-of-type{
		padding: 0 0 0 160px;
	}
	#divMainNotice div:nth-of-type(2) img {
		width: 310px;
		height: 5px;
	} 
	#divMainNotice div:nth-of-type(3) ul{
		list-style-type: none;		
		margin: 8px 0 0 0;
		padding: 0;
		font-size: 0.9em;
	}
	#divMainNotice div:nth-of-type(3) ul li{
		padding: 5px 0 5px 2px;		
	}
</style>

<div id="divMainNotice">
	<div>
		<img src="${pageContext.request.contextPath }/resources/images/notice2.JPG" 
			alt="공지사항">
		<span>
			<a href="<c:url value='/board/list.do'/>">
				<img src="${pageContext.request.contextPath }/resources/images/more.JPG" 
					alt="more">
			</a>
		</span>
	</div>	
	<div>
		<img src="${pageContext.request.contextPath }/resources/images/Line.JPG" 
			alt="line">
	</div>
	<div>
		<ul>
			<c:if test="${empty list }">
				<li>공지사항이 없습니다.</li>
			</c:if>
			<c:if test="${!empty list }">
				<!-- 반복 시작 -->
				<c:forEach var="vo" items="${list }">
					<li>
						<a href
						="<c:url value='/board/detail.do?no=${vo.no}'/>">
							<img src="${pageContext.request.contextPath }/resources/images/dot.JPG" >
							<c:if test="${fn:length(vo.title)>30}">
								${fn:substring(vo.title, 0, 30) }...
							</c:if>
							<c:if test="${fn:length(vo.title)<=30}">							
								${vo.title}
							</c:if>
						</a>
					</li>		
				</c:forEach>
				<!-- 반복 끝 -->
			</c:if>
		</ul>
	</div>
</div>


