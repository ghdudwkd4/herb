package com.will.herb.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AdminLoginInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger
		=LoggerFactory.getLogger(AdminLoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("AdminLoginInterceptor - preHandle()호출!!");
		
		HttpSession session =request.getSession();
		String adminUserid=(String) session.getAttribute("adminUserid");
		
		if(adminUserid==null || adminUserid.isEmpty()) {
			request.setAttribute("msg", "먼저 관리자 로그인하세요");
			request.setAttribute("url", "/admin/login/adminLogin.do");
			
			RequestDispatcher dispatcher
	=request.getRequestDispatcher("/WEB-INF/views/common/message.jsp");
			dispatcher.forward(request, response);
			
			return false;
		}
		
		return true;
	}

}








