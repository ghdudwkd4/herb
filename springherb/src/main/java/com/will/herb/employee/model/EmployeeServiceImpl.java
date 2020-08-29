package com.will.herb.employee.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired private EmployeeDAO employeeDao;

	@Override
	public int insertEmp(EmployeeVO employeeVO) {
		return employeeDao.insertEmp(employeeVO);
	}
	
	
	
}
