package com.will.herb.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.cart.controller.CartController;
import com.will.herb.cart.model.CartService;
import com.will.herb.cart.model.CartVO;
import com.will.herb.common.DateSearchVO;
import com.will.herb.common.PaginationInfo;
import com.will.herb.common.Utility;
import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;
import com.will.herb.order.model.OrderAllVO;
import com.will.herb.order.model.OrderService;
import com.will.herb.order.model.OrderVO;

@Controller
@RequestMapping("/shop/order")
public class OrderController {

	private static final Logger logger
	=LoggerFactory.getLogger(OrderController.class);

	@Autowired private CartService cartService;
	@Autowired private MemberService memberService;
	@Autowired private OrderService orderService;
		
	@RequestMapping(value="/orderSheet.do", method=RequestMethod.GET)
	public String orderSheet_get(HttpSession session, Model model) {
		String userid=(String) session.getAttribute("userid");
		
		logger.info("주문하기 - 조회, 파라미터 userid={}", userid);
		
		List<Map<String, Object>> list=cartService.selectCartView(userid);
		logger.info("주문하기 - 장바구니 목록 결과 list.size={}", list.size());
		
		MemberVO memVo=memberService.selectMember(userid);
		logger.info("주문하기 - 회원조회 결과 vo={}", memVo);

		model.addAttribute("list", list);
		model.addAttribute("memVo", memVo);
		
		return "shop/order/orderSheet";		
	}
	
	@RequestMapping(value="/orderSheet.do", 
				method=RequestMethod.POST)
	public String orderSheet_post(@ModelAttribute OrderVO orderVo,
			HttpSession session) {
		String userid=(String) session.getAttribute("userid");
		orderVo.setCustomerId(userid);
		logger.info("주문하기, 파라미터 ordervo={}", orderVo);
		
		int cnt=orderService.insertOrder(orderVo);
		logger.info("주문하기 결과, cnt={}", cnt);
		
		return "redirect:/shop/order/orderComplete.do?orderNo=" 
			+ orderVo.getOrderNo();
	}
	
	@RequestMapping("/orderComplete.do")
	public void orderComplete(@RequestParam(defaultValue = "0") 
		int orderNo, Model model) {
		logger.info("주문완료 페이지, 파라미터 orderNo={}", orderNo);
		
		List<Map<String, Object>> list 
			=orderService.selectOrderDetailsView(orderNo);
		logger.info("주문완료 - 상세주문 내역 조회 결과, list.size={}", list.size());
		
		Map<String, Object> map=orderService.selectOrdersView(orderNo);
		logger.info("주문완료 - 주문 내역 조회 결과, map={}", map);
		
		model.addAttribute("list", list);
		model.addAttribute("orderMap", map);
		
	}
	
	@RequestMapping("/orderList.do")
	public void orderList(@ModelAttribute DateSearchVO dateSearchVo,
			HttpSession session, Model model) {		
		String userid=(String) session.getAttribute("userid");
		dateSearchVo.setCustomerId(userid);
		logger.info("주문 내역 파라미터 dateSearchVo={}", dateSearchVo);
		
		//
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCKSIZE);
		pagingInfo.setCurrentPage(dateSearchVo.getCurrentPage());
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		
		//
		dateSearchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		dateSearchVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		
		//현재일자에 주문이 있는 경우 조회해온다
		//=> 현재일자가 null인 경우 현재일자를 startDay, endDay에 setting
		String startDay=dateSearchVo.getStartDay();
		if(startDay==null || startDay.isEmpty()) {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String str=sdf.format(today);
			dateSearchVo.setStartDay(str);
			dateSearchVo.setEndDay(str);			
		}
		
		List<OrderAllVO> list
			=orderService.selectOrderList(dateSearchVo);
		logger.info("주문내역 결과, list.size={}",list.size());
		
		int totalRecord=orderService.selectTotalRecord(dateSearchVo);
		logger.info("주문내역 개수 조회 결과, totalRecord={}", totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);		
	}
	
	@RequestMapping("/bestS.do")
	public String bestS(@RequestParam (defaultValue = "0") int productNo,Model model) {
		logger.info("카테고리별 판매가 가장 많은 상품, 파라미터 productNo={}",productNo);
		
		List<Map<String, Object>>list = orderService.selectBest(productNo);
		logger.info("카테고리별 판매가 가장 많은 상품 조회 결과, list.size={}",list.size());
		
		model.addAttribute("list",list);
		
		return "inc/bestS";
	}
}







