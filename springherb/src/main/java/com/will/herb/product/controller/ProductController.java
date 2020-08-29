package com.will.herb.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.product.model.ProductService;
import com.will.herb.product.model.ProductVO;

@Controller
@RequestMapping("/shop/product")
public class ProductController {
	private static final Logger logger
		=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productEvent.do")
	public String pdEvent(@RequestParam String eventName, Model model) {
		logger.info("메인 - 이벤트상품 조회 파라미터 eventName={}", eventName);
		
		List<ProductVO> list=productService.selectEvent(eventName);
		logger.info("이벤트 상품 조회 결과, list.size={}", list.size());
		
		model.addAttribute("list", list);
		
		return "shop/product/productEvent";
	}
	
	@RequestMapping("/productByCategory.do")
	public void pdByCategory(@RequestParam String categoryName,
			@RequestParam(defaultValue = "0") int categoryNo,
			Model model) {
		logger.info("카테고리별 상품 목록, 파라미터  categoryNo={}, categoryName={}",
				categoryNo, categoryName);
		
		List<ProductVO> list
			=productService.selectPdByCategory(categoryNo);
		logger.info("카테고리별 조회 결과,list.size={}", list.size());
		
		model.addAttribute("list", list);
		
	}
	
	@RequestMapping("/productDetail.do")
	public String productDetail(@RequestParam(defaultValue = "0") 
		int productNo, Model model) {
		logger.info("상품 상세정보, 파라미터 no={}", productNo);
		
		if(productNo==0) {
			model.addAttribute("msg", "잘못된  url");
			model.addAttribute("url", "/index.do");
			
			return "common/message";
		}
		
		ProductVO vo=productService.selectProduct(productNo);
		logger.info("상품상세조회 결과, vo={}", vo);
		
		model.addAttribute("vo", vo);
		
		return "shop/product/productDetail";
	}
	
	@RequestMapping("/productImage.do")
	public void bigImage(@RequestParam String imageURL, 
			@RequestParam String productName) {
		logger.info("큰 이미지 보여주기, 파라미터 imageURL={}, productName={}",
				imageURL, productName);
	}
	
}








