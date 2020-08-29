package com.will.herb.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.will.herb.admin.controller.AdminController;
import com.will.herb.employee.model.EmployeeService;
import com.will.herb.employee.model.EmployeeVO;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
	private static final Logger logger
	=LoggerFactory.getLogger(AdminController.class);
	
	private EmployeeService employeeService;
	
	@RequestMapping("/addEmpView.do")
	public String addEmpView(Model model){
		//등록 화면 보여주기	
		logger.debug("등록 화면");
		
		model.addAttribute("employeeVO", new EmployeeVO());
		return "/emp/empRegister";
	}
	
	@RequestMapping("/addEmp.do")
	public String addEmp(@ModelAttribute EmployeeVO employeeVO){
		//사원 등록  - insert 처리
		//1. 파라미터
		logger.debug("파라미터 employeeVO ={}", employeeVO);		
		//2. db작업
		int key = employeeService.insertEmp(employeeVO);	
		logger.debug("등록 결과 : key=" + key);
				
		//3. 결과, 뷰페이지 저장		
		return "redirect:/emp/empList.do";
	}

}
