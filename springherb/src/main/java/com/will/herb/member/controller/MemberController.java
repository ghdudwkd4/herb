package com.will.herb.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger
		=LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/agreement.do")
	public String agreement() {
		logger.info("회원약관 페이지 보여주기");
		
		return "member/agreement";
	}
	
	@RequestMapping("/register.do")
	public void register() {
		logger.info("회원가입 페이지 보여주기");
		
		//return "member/register";
	}
	
	@RequestMapping("/memberWrite.do")
	public String memWrite(@ModelAttribute MemberVO memVo,
			@RequestParam String email3,
			Model model) {
		logger.info("회원가입 처리 파라미터 vo={}", memVo);

		String hp2=memVo.getHp2();
		String hp3=memVo.getHp3();
		
		if(hp2==null || hp2.isEmpty() || hp3==null || hp3.isEmpty()) {
			memVo.setHp1("");
			memVo.setHp2("");
			memVo.setHp3("");			
		}
		
		String email1=memVo.getEmail1();
		String email2=memVo.getEmail2();
		
		if(email1==null || email1.isEmpty()) {
			memVo.setEmail1("");
			memVo.setEmail2("");			
		}else {
			if(email2.equals("etc")) {
				memVo.setEmail2(email3);
			}
		}
		
		int cnt=memberService.insertMember(memVo);
		logger.info("회원가입 결과, cnt={}", cnt);
		
		String msg="회원가입 실패", url="/member/register.do";
		if(cnt>0) {
			msg="회원가입되었습니다.";
			url="/index.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/checkUserid.do")
	public String checkUserid(@RequestParam String userid,
			ModelMap model) {
		logger.info("아이디 중복확인, 파라미터 userid={}", userid);
		
		int result=0;
		if(userid!=null && !userid.isEmpty()) {
			result=memberService.duplicateId(userid);
		}
		logger.info("아이디 중복확인 결과, result={}", result);
		
		model.addAttribute("result", result);
		model.addAttribute("EXIST_ID", MemberService.EXIST_ID);
		model.addAttribute("NON_EXIST_ID", MemberService.NON_EXIST_ID);
		
		return "member/checkUserid";
	}
	
	@RequestMapping("/ajaxDupId.do")
	@ResponseBody
	public boolean ajaxDupId(@RequestParam String userid) {
		logger.info("ajax - 아이디 중복확인, 파라미터 userid={}",userid);
		
		int result = memberService.duplicateId(userid);
		logger.info("ajax 중복확인 결과 result={}",result);
		
		boolean bool=false;
		if(result==memberService.EXIST_ID) {
			bool = false; //사용불가능
		}else if(result==memberService.NON_EXIST_ID) {
			bool = true; //사용가능
			
		}
		
		return bool;
	}
	
	@RequestMapping(value="/memberEdit.do", method = RequestMethod.GET)
	public String memEdit_get(HttpSession session, Model model) {
		String userid=(String) session.getAttribute("userid");		
		logger.info("회원정보 수정 화면 보여주기, 파라미터 userid={}", userid);
		/*
		if(userid==null || userid.isEmpty()) {
			model.addAttribute("msg", "먼저 로그인하세요");
			model.addAttribute("url", "/login/login.do");
			
			return "common/message";
		}*/
		
		MemberVO vo=memberService.selectMember(userid);
		logger.info("회원조회 결과, vo={}", vo);
		
		model.addAttribute("vo", vo);
		
		return "member/memberEdit";
	}

	@RequestMapping(value="/memberEdit.do", method = RequestMethod.POST)
	public String memEdit_post(@ModelAttribute MemberVO vo,
			@RequestParam String email3,
			HttpSession session, Model model) {
		String userid=(String) session.getAttribute("userid");
		vo.setUserid(userid);
		logger.info("회원정보 수정 처리, 파라미터 vo={}", vo);
		
		int result=memberService.loginCheck(userid, vo.getPwd());
		logger.info("비밀번호 체크 결과, result={}", result);
		
		String msg="비밀번호 체크 실패",url="/member/memberEdit.do";
		if(result==MemberService.LOGIN_OK) {
			String hp2=vo.getHp2();
			String hp3=vo.getHp3();
			if(hp2==null || hp2.isEmpty() || hp3==null || hp3.isEmpty()){
				vo.setHp1("");
				vo.setHp2("");
				vo.setHp3("");				
			}
			
			String email1=vo.getEmail1();
			if(email1==null || email1.isEmpty()) {
				vo.setEmail1("");
				vo.setEmail2("");
			}else {
				if(vo.getEmail2().equals("etc")) {
					vo.setEmail2(email3);
				}
			}
			
			int cnt=memberService.updateMember(vo);
			logger.info("회원수정 결과, cnt={}", cnt);
			
			if(cnt>0) {
				msg="회원정보 수정되었습니다.";
			}else {
				msg="회원정보 수정 실패.";
			}
		}else if(result==MemberService.PWD_DISAGREE) {
			msg="비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/memberOut.do", method=RequestMethod.GET)
	public void memOut_get() {
		logger.info("회원탈퇴 화면 보여주기");
		
	}
	
	@RequestMapping(value="/memberOut.do", method=RequestMethod.POST)
	public String memOut_post(@RequestParam String pwd,
			HttpSession session, HttpServletResponse response, 
			Model model) {
		String userid=(String) session.getAttribute("userid");
		
		logger.info("회원탈퇴 처리 파라미터, userid={}, pwd={}", userid, pwd);
		
		int result=memberService.loginCheck(userid, pwd);
		logger.info("비밀번호 체크 결과, result={}", result);
		
		String msg="비밀번호 체크 실패", url="/member/memberOut.do";
		if(result==MemberService.LOGIN_OK) {
			int cnt=memberService.deleteMember(userid);
			if(cnt>0) {
				msg="회원탈퇴되었습니다.";
				url="/index.do";
				
				session.invalidate();
				
				Cookie ck = new Cookie("ck_userid", userid);
				ck.setMaxAge(0); //쿠키 삭제
				ck.setPath("/");
				response.addCookie(ck);
			}else {
				msg="회원탈퇴 실패";
			}
		}else if(result==MemberService.PWD_DISAGREE) {
			msg="비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
}





