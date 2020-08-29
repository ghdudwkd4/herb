package com.will.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;

@Controller
public class EditController {
	private static final Logger logger
		=LoggerFactory.getLogger(EditController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/board/edit.do", method=RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue = "0") int no,
			Model model) {
		//1
		logger.info("수정화면, 파라미터  no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		
		//2
		BoardVO vo=boardService.selectByNo(no);
		logger.info("수정화면 조회 결과, vo={}", vo);
		
		//3
		model.addAttribute("vo", vo);
		
		//4
		return "board/edit";
	}
	
	@RequestMapping(value="/board/edit.do", method=RequestMethod.POST)
	public String edit_post(@ModelAttribute BoardVO boardVo,
			Model model) {
		//1
		logger.info("수정처리 파라미터 vo={}", boardVo);
		
		//2
		String msg="글수정 실패", url="/board/edit.do?no="+boardVo.getNo();
		if(boardService.checkPwd(boardVo.getNo(), boardVo.getPwd())) {
			int cnt=boardService.updateBoard(boardVo);
			if(cnt>0) {
				msg="글수정 처리되었습니다";
				url="/board/detail.do?no="+boardVo.getNo();
			}
		}else {
			msg="비밀번호가 일치하지 않습니다.";
		}
				
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4
		return "common/message";
	}
	
}






