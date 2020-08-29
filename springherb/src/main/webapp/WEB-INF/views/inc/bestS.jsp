<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	dt{
		padding: 7px 5px 5px 10px;
		font-weight: bold;
		}			
		dd	{
				display: block;
				padding: 0 10px;
			}
			 dd a	{
				text-decoration: none;
				display: block;
				color: #585721;
				border-bottom: 1px solid #ddd;
				padding: 7px 5px;
				}	
</style>
	<dl>
		<dt>Best Of Best</dt>
		<!-- 반복시작 -->
		<c:forEach var="map" items="${list }">
			<dd>
				<a href
	="<c:url value='/shop/product/productDetail.do?productNo=${map["PRODUCTNO"] }'/>">
				${map['PRODUCTNAME'] }
				</a>
			</dd>
		</c:forEach>    	
    	<!-- 반복끝 -->    
	</dl>