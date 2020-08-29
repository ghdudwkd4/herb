package com.will.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;

@Controller
@RequestMapping("/board/delete.do")
public class DeleteController {
	private static final Logger logger
		=LoggerFactory.getLogger(DeleteController.class);
	
	@Autowired private BoardService boardService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue="0") int no,
			ModelMap model) {
		//1
		logger.info("삭제 화면, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		
		//2
		//3
		
		//4
		return "board/delete";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String delete_post(@ModelAttribute BoardVO boardVo,
			Model model) {
		//1
		logger.info("삭제처리 파라미터 vo={}", boardVo);
		
		//2
		String msg="글삭제 실패", url="/board/delete.do?no="+boardVo.getNo();
		if(boardService.checkPwd(boardVo.getNo(), boardVo.getPwd())) {
			int cnt=boardService.deleteBoard(boardVo.getNo());
			if(cnt>0) {
				msg="글삭제 처리되었습니다";
				url="/board/list.do";
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






