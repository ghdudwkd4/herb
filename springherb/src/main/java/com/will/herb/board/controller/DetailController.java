package com.will.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;

@Controller
public class DetailController {
	private static final Logger logger
		=LoggerFactory.getLogger(DetailController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue = "0") int no,
			Model model) {
		//1
		logger.info("조회수 증가, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url 입니다.");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		
		//2
		int cnt=boardService.updateReadCount(no);
		logger.info("조회수 증가 결과, cnt={}", cnt);
		
		//3		
		//4
		return "redirect:/board/detail.do?no="+no;
	}
	
	@RequestMapping("/board/detail.do")
	public String detail(@RequestParam(defaultValue = "0") int no,
			Model model) {
		//1
		logger.info("상세보기, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url 입니다.");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		
		//2
		BoardVO vo=boardService.selectByNo(no);
		logger.info("상세보기 조회 결과, vo={}", vo);
		
		//3		
		model.addAttribute("vo", vo);
		
		//4
		return "board/detail";
	}
	
}
