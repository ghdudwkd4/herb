package com.will.herb.cart.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.cart.model.CartService;
import com.will.herb.cart.model.CartVO;

@Controller
@RequestMapping("/shop/cart")
public class CartController {
	private static final Logger logger
		=LoggerFactory.getLogger(CartController.class);
		
	@Autowired private CartService cartService;
	
	@RequestMapping("/cartAdd.do")
	public String cartAdd(@ModelAttribute CartVO cartVo, 
				@RequestParam String mode, HttpSession session) {
		String userid=(String) session.getAttribute("userid");
		cartVo.setCustomerId(userid);
		
		logger.info("장바구니 담기, 파라미터 cartVo={}, mode={}", cartVo, mode);
		
		int cnt=cartService.insertCart(cartVo);
		logger.info("장바구니 담기 결과, cnt={}", cnt);
		
		String page="";
		if(mode.equals("order")) {
			page = "/shop/order/orderSheet.do";
		}else{
			page = "/shop/cart/cartList.do";
		}
		
		return "redirect:" + page;
	}

	@RequestMapping("/cartList.do")
	public String cartList(HttpSession session, Model model) {
		String userid=(String) session.getAttribute("userid");
		logger.info("장바구니 목록, 파라미터 userid={}", userid);
				
		List<Map<String, Object>> list=cartService.selectCartView(userid);
		logger.info("장바구니 목록 결과, list.size={}", list.size());
		
		model.addAttribute("list", list);
		
		return "shop/cart/cartList";
	}
	
	@RequestMapping("/cartEdit.do")
	public String cartEdit(@ModelAttribute CartVO cartVo) {
		logger.info("장바구니 수정, 파라미터 cartVo={}", cartVo);
		
		int cnt=cartService.updateCart(cartVo);
		logger.info("장바구니 수정 결과 cnt={}", cnt);
		
		return "redirect:/shop/cart/cartList.do";
	}
	
	@RequestMapping("/cartDelete.do")
	public String cartEdit(@RequestParam(defaultValue = "0") int cartNo) {
		logger.info("장바구니 삭제, 파라미터 cartNo={}", cartNo);
		
		int cnt=cartService.deleteCart(cartNo);
		logger.info("장바구니 삭제 결과 cnt={}", cnt);
		
		return "redirect:/shop/cart/cartList.do";
	}
}






