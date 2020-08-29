package com.will.herb.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxTestController {
	
	private static final Logger logger
		= LoggerFactory.getLogger(AjaxTestController.class);
	
	@RequestMapping("/ajaxTest/ajaxTest1.do")
	public void ajaxTest1() {
		logger.info("ajaxTest1 페이지 보여주기");
		
	}
	
	@RequestMapping("/ajaxTest/ajaxTest2.do")
	public void ajaxTest2() {
		logger.info("ajaxTest2 페이지 보여주기");
		
	}
	
	@RequestMapping("/ajaxTest/ajaxTest3.do")
	public void ajaxTest3() {
		logger.info("ajaxTest3 페이지 보여주기");
		
	}
	
	@RequestMapping("/ajaxTest/ajaxTest4.do")
	public void ajaxTest4() {
		logger.info("ajaxTest4 페이지 보여주기");
		
	}

	@RequestMapping("/search.do")
	@ResponseBody
	public String search(@RequestParam String keyword,
			@RequestParam String id) {
		logger.info("ajax - search.do 실행");
		
		String result = id+", "+keyword+"sk,sbs,sm";
		
		return result;
	}
	
	@RequestMapping("/ajaxList.do")
	@ResponseBody
	public List<MemoVO> list() {
		logger.info("ajaxList.do 실행");
		
		MemoVO vo = new MemoVO(1, "홍길동", "내용1");
		MemoVO vo2 = new MemoVO(2, "김길동", "내용2");
		MemoVO vo3 = new MemoVO(3, "이길동", "내용3");
		
		List<MemoVO>list = new ArrayList<MemoVO>();
		list.add(vo);
		list.add(vo2);
		list.add(vo3);
		
		return list;
		
		//[{"no":1,"name":"홍길동","content":"내용1"},{"no":2,"name":"김길동","content":"내용2"},{"no":3,"name":"이길동","content":"내용3"}]
	}
	
	@RequestMapping("/ajaxDetail.do")
	@ResponseBody
	public MemoVO Detail(@RequestParam (defaultValue = "0")int no) {
		logger.info("ajaxDetailVO.do  실행");
		
		MemoVO vo = new MemoVO(no, "홍길동", "내용");
		
		return vo;
		//{"no":0,"name":"홍길동","content":"내용"}
	}
	
	@RequestMapping("/ajaxView.do")
	@ResponseBody
	public MemoVO View(@RequestParam (defaultValue = "0")int no) {
		logger.info("ajaxView.do 실행, 파라미터 no={}",no);
		MemoVO vo = new MemoVO(no, "홍길동", "안녕");
		
		return vo;
		//{"no":0,"name":"홍길동","content":"안녕"}
	}
	
	@RequestMapping("/ajaxWrite.do")
	@ResponseBody
	public ResultVO  Write(@ModelAttribute MemoVO vo) {
		logger.info("ajaxWrite.do 실행");
		vo.setNo(10);
		
		ResultVO resultVo = new ResultVO();
		resultVo.setMessage("메모 등록 성공");
		resultVo.setData(vo);
		
		return resultVo;
		//{"message":"메모 등록 성공","data":{"no":10,"name":"hong","content":"내용"}}
	}
	
	@RequestMapping("/ajaxAll.do")
	@ResponseBody
	public List<MemoVO> all() {
		logger.info("ajaxAll.do 실행");
		
		
		MemoVO vo = new MemoVO(11, "홍길동", "내용");
		MemoVO vo2 = new MemoVO(12, "김길동", "내용2");
		MemoVO vo3 = new MemoVO(13, "이길동", "내용3");
		List<MemoVO>list = new ArrayList<MemoVO>();
		
		list.add(vo);
		list.add(vo2);
		list.add(vo3);
		
		return list;
		//[{"no":11,"name":"홍길동","content":"내용"},{"no":12,"name":"김길동","content":"내용2"},{"no":13,"name":"이길동","content":"내용3"}]
	}
	
}
