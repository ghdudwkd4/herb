package com.will.herb.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger
		=LoggerFactory.getLogger(LoginController.class);
		
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login_get() {
		logger.info("로그인 화면 보여주기");
		
		return "login/login";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login_post(@RequestParam String userid, 
			@RequestParam String pwd, 
			@RequestParam(required = false) String saveId,
			HttpServletRequest request, HttpServletResponse response,	
			Model model) {
		logger.info("로그인 처리 파라미터 userid={}, pwd={}", userid, pwd);
		logger.info("saveId={}", saveId);
		
		int result=memberService.loginCheck(userid, pwd);
		logger.info("로그인 처리 결과, result={}", result);
		
		String msg="로그인 처리 실패",url="/login/login.do";
		if(result==MemberService.LOGIN_OK) {
			//상세정보 조회
			MemberVO memVo=memberService.selectMember(userid);
			logger.info("회원정보 조회 결과vo={}", memVo);
			
			//session에 저장
			HttpSession session=request.getSession();
			session.setAttribute("userid", userid);
			session.setAttribute("userName", memVo.getName());
			
			//쿠키에 저장
			if(saveId!=null) {  //아이디 저장에 체크한 경우
				Cookie ck = new Cookie("ck_userid", userid);
				ck.setMaxAge(1000*24*60*60); //1000일
				ck.setPath("/");
				response.addCookie(ck);
			}else {
				Cookie ck = new Cookie("ck_userid", userid);
				ck.setMaxAge(0); //기존 쿠키 삭제
				ck.setPath("/");
				response.addCookie(ck);
			}
			
			msg=memVo.getName() + "님 로그인되었습니다.";
			url="/index.do";
		}else if(result==MemberService.PWD_DISAGREE){
			msg="비밀번호가 일치하지 않습니다.";			
		}else if(result==MemberService.ID_NONE) {
			msg="해당 아이디가 존재하지 않습니다.";			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		logger.info("로그아웃 처리");
		
		//session.invalidate();
		session.removeAttribute("userid");
		session.removeAttribute("userName");
		
		return "redirect:/index.do";
	}
	
}









