package com.will.herb.email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
public class EmailController {
	private static final Logger logger 
		= LoggerFactory.getLogger(EmailController.class);
	
	@Autowired private EmailSender emailSender;
	@RequestMapping("/emailTest.do")
	public void emailTest() {
		logger.info("emailTest 화면 보여주기");
		
	}
	
	@RequestMapping("/send.do")
	public String send() {
		logger.info("메일 발송 처리 ");
		
		String subject = "문의에 대한 답변입니다.";
		String content = "이메일 내용입니다.";
		String receiver = "ghdudwkd4@naver.com";
		String sender = "admin@herbmall.com";
		
		try {
			emailSender.mailSend(subject, content, receiver, sender);
			logger.info("이메일 발송 성공!");
		} catch (AddressException e) {
			e.printStackTrace();
			logger.info("이메일 발송 실패..");
		} catch (MessagingException e) {
			e.printStackTrace();
			logger.info("이메일 발송 실패..");
		}
		 return "redirect:/email/emailTest.do";
	}
}
