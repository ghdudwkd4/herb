<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/top.jsp"%>

<style type="text/css">	
	#info, #desc{
		width:850px;		
	}	
	#viewImg{
		float:left;
		padding:10px 30px 30px 30px;
		width:25%;				
	}
	#viewPd{
		float:left;
		padding:0 10px 30px 20px;
		width:45%;			
	}
	#viewBest{
		float:left;
		padding:0 10px 30px 20px;	
	}
	#desc{
		clear:both;
		padding:10px 0 5px 0;		
	}
	
	.line2{
		border-bottom:solid silver 2px;
		padding:0 0 10px 7px;
	}
	.line
	{
		border-bottom:solid 1px silver;
	}
	.boldF{
		font-size:1.2em;
		font-weight:bold;

	}
	.center{
		text-align:center;
	}
	span{
		font-size:0.9em;
	}
	.sp1{
		width:50%;
		float:left;		
	}
	
	form{
		width:350px;
	}
	
		
	p{
		padding:8px;
		font-size:1.0em;
	}
	
	input[type="text"]{
		width:20%;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#viewImg a").click(function(){
			window.open("<c:url value='/shop/product/productImage.do?imageURL=${vo.imageURL}&productName=${vo.productName}'/>",
				"bigImg","width=400,height=500,left=0,top=0,location=yes,resizable=yes");			
		});
		
		$("form[name=frmPd] input[type=button]").click(function(){
			var mode = $(this).attr('id');
			//alert(mode);
			
			$("form[name=frmPd]")
.prop("action", "<c:url value='/shop/cart/cartAdd.do?mode="+mode+"'/>");
			$("form[name=frmPd]").submit();	
		});
		
	});
</script>

<h2>상품 상세 보기</h2>
<div id="info">
	<div id="viewImg">
		<!-- 상품 이미지 -->
		<p class="center">
		<a href="#">
			<img src="<c:url value='/pd_images/${vo.imageURL}' />"
		 		border="0" width="150" alt="${vo.productName }">
		</a>	
		</p>
		<p class="center">
		<a href="#">
		 	큰이미지 보기</a></p>
	</div>
	<div id="viewPd">
		<form name="frmPd" method="post">			
			<!-- 상품명 -->
			<p class="line2">
				<span class="boldF">
					${vo.productName }
				</span>
			</p>
			<p class="line"><span class="sp1">
			<img src="<c:url value='/resources/images/dot2.JPG'/>"> 판매가격</span>				
				<span>
					<fmt:formatNumber value="${vo.sellPrice }" 
						pattern="#,###"/>원
				</span>
			</p>
			<p class="line"><span class="sp1">
			<img src="<c:url value='/resources/images/dot2.JPG'/>"> 적립금</span>
				<span><fmt:formatNumber value="${vo.mileage }" 
						pattern="#,###"/>원</span>
			</p>
			<p class="line"><span class="sp1">
			<img src="<c:url value='/resources/images/dot2.JPG'/>"> 제조사</span>
				<span>${vo.company}</span>
			</p>
		
			<p class="line">
				<span class="sp1">
				<img src="<c:url value='/resources/images/dot2.JPG'/>"> 
				구매수량</span>
				<input type="hidden" name="productNo" id="pdNo" 
					value="${vo.productNo}" >
				<label for="qty">
					<input type="text" name="quantity" id="qty" value="1" >
				</label>
			</p>
			<p class="center">
				<input type="button" id="order" value="바로구매" >
				<input type="button" id="cart" value="장바구니담기">
			</p>
		</form>
	</div>
	<div id="viewBest">
		<c:import url="/shop/order/bestS.do">
			<c:param name="productNo" value="${vo.productNo }"></c:param>
		</c:import>
	</div>
</div>
<div id="desc">
	<img src="<c:url value='/resources/images/dot6.JPG'/>">
	<span style="font-size:12pt;font-weight:bold">
		상품상세정보</span>
	<br><br>
	<p>${vo.explains}</p>
	<p>${vo.description}</p>	
</div>


<%@ include file="../../inc/bottom.jsp"%>





