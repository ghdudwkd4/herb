package com.will.herb.admin.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.authority.model.AuthorityService;
import com.will.herb.authority.model.AuthorityVO;
import com.will.herb.manager.model.ManagerService;
import com.will.herb.manager.model.ManagerVO;
import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger
		=LoggerFactory.getLogger(AdminController.class);
	
	@Autowired private AuthorityService authorityService;
	@Autowired private ManagerService managerService;
	
	@RequestMapping("/adminMain.do")
	public void adminMain() {
		logger.info("관리자 메인 페이지");		
	}
	
	@RequestMapping(value="/member/join.do", method = RequestMethod.GET)
	public void join_get(Model model) {
		logger.info("관리자 등록 화면 보여주기");
		
		List<AuthorityVO> list=authorityService.selectAuthority();
		logger.info("권한 조회 결과, list.size={}", list.size());
		
		model.addAttribute("list", list);		
	}
	
	@RequestMapping("/member/checkUserid.do")
	public String chkUserid(@RequestParam String userid, Model model) {
		logger.info("관리자 아이디 중복확인, 파라미터  userid={}", userid);
		
		int result=managerService.checkUserid(userid);
		logger.info("관리자 아이디 중복확인 결과, result={}", result);
		
		model.addAttribute("result", result);
		model.addAttribute("EXIST_ID", MemberService.EXIST_ID);
		model.addAttribute("NON_EXIST_ID", MemberService.NON_EXIST_ID);
		
		return "admin/member/checkUserid";
	}
	
	@RequestMapping(value="/member/join.do", method = RequestMethod.POST)
	public String join_post(@ModelAttribute ManagerVO managerVo,
			Model model) {
		logger.info("관리자 등록 처리, 파라미터 vo={}", managerVo);
		
		int cnt=managerService.insertManager(managerVo);
		logger.info("관리자 등록 결과, cnt={}", cnt);
		
		String msg="관리자 등록 실패", url="/admin/member/join.do";
		if(cnt>0) {
			msg="관리자 등록되었습니다.";			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/login/adminLogin.do", method = RequestMethod.GET)
	public void login_get() {
		logger.info("관리자 로그인 화면 보여주기");
		
	}
	
	@RequestMapping(value="/login/adminLogin.do", method=RequestMethod.POST)
	public String login_post(@RequestParam String userid, 
			@RequestParam String pwd, 
			@RequestParam(required = false) String saveId,
			HttpServletRequest request, HttpServletResponse response,	
			Model model) {
		logger.info("관리자 로그인 처리 파라미터 userid={}, pwd={}", userid, pwd);
		logger.info("saveId={}", saveId);
		
		int result=managerService.loginCheck(userid, pwd);
		logger.info("관리자 로그인 처리 결과, result={}", result);
		
		String msg="관리자 로그인 처리 실패",url="/admin/login/adminLogin.do";
		if(result==MemberService.LOGIN_OK) {
			//상세정보 조회
			ManagerVO vo=managerService.selectManager(userid);
			logger.info("관리자 정보 조회 결과 vo={}", vo);
			
			//session에 저장
			HttpSession session=request.getSession();
			session.setAttribute("adminUserid", userid);
			session.setAttribute("adminUserName", vo.getName());
			
			//쿠키에 저장
			if(saveId!=null) {  //아이디 저장에 체크한 경우
				Cookie ck = new Cookie("ck_admin_userid", userid);
				ck.setMaxAge(1000*24*60*60); //1000일
				ck.setPath("/");
				response.addCookie(ck);
			}else {
				Cookie ck = new Cookie("ck_admin_userid", userid);
				ck.setMaxAge(0); //기존 쿠키 삭제
				ck.setPath("/");
				response.addCookie(ck);
			}
			
			msg=vo.getName() + "님 관리자로 로그인되었습니다.";
			url="/admin/adminMain.do";
		}else if(result==MemberService.PWD_DISAGREE){
			msg="비밀번호가 일치하지 않습니다.";			
		}else if(result==MemberService.ID_NONE) {
			msg="해당 아이디가 존재하지 않습니다.";			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
		
	@RequestMapping("/login/logout.do")
	public String logout(HttpSession session) {
		logger.info("관리자 로그아웃");
		
		session.removeAttribute("adminUserid");
		session.removeAttribute("adminUserName");
		
		return "redirect:/admin/login/adminLogin.do";
	}
}






