package com.will.herb.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	private static final Logger logger
		=LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//컨트롤러 수행전 실행됨
		logger.info("preHandle() 실행");
		String userid
			=(String) request.getSession().getAttribute("userid");
		if(userid==null || userid.isEmpty()) {
			request.setAttribute("msg", "먼저 로그인하세요!!!");
			request.setAttribute("url", "/login/login.do");
			
			RequestDispatcher dispatcher 
	= request.getRequestDispatcher("/WEB-INF/views/common/message.jsp");
			dispatcher.forward(request, response);
			
			return false; //다음 컨트롤러를 실행하지 않는다
		}else { 
			//로그인 한 경우에는 다음 컨트롤러 실행			
			return true;  
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//컨트롤러 수행 후 실행됨
		logger.info("postHandle() 실행");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//클라이언트의 요청을 처리한 뒤, 즉 뷰를 통해서 클라이언트에 응답을 전송한 뒤에 실행됨
		logger.info("afterCompletion() 실행");		
	}

}
