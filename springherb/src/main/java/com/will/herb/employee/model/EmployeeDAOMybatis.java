package com.will.herb.employee.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOMybatis implements EmployeeDAO{
	@Autowired SqlSessionTemplate sqlSession;
	private String namespace = "config.mybatis.mapper.oracle.employee.";
	
	@Override
	public int insertEmp(EmployeeVO vo) {
		return sqlSession.insert(namespace+"insertEmp",vo);
	}

	

}
