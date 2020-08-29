package com.will.herb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;

@Controller
public class IndexController {
	private static final Logger logger
		=LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/index.do")
	public String index() {
		logger.info("메인 페이지 보여주기");
		
		return "index";
	}
	
	@RequestMapping("/inc/mainNotice.do")
	public String mainNotice(Model model) {
		//1
		logger.info("메인 - 공지사항 페이지");
		
		//2
		List<BoardVO> list=boardService.selectMainNotice();
		logger.info("메인 공지사항 조회 결과, list.size={}", list.size());
		
		//3
		model.addAttribute("list", list);
		
		//4
		return "/inc/mainNotice";
	}
	
	
}






