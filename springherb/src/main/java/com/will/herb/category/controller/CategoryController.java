package com.will.herb.category.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.will.herb.category.model.CategoryService;
import com.will.herb.category.model.CategoryVO;

@Controller
public class CategoryController {
	private static final Logger logger
		=LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired private CategoryService categoryService;
		
	@RequestMapping("/inc/categoryList.do")
	public String cgList(Model model) {
		logger.info("카테고리 조회 페이지");
		
		List<CategoryVO> list=categoryService.selectCategory();
		logger.info("카테고리 조회 결과 list.size={}", list.size());
		
		model.addAttribute("list", list);
		
		return "inc/categoryList";
	}
	
}





