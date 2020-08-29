package com.will.herb.authority.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAOMybatis implements AuthorityDAO{

	@Autowired private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.authority.";

	@Override
	public List<AuthorityVO> selectAuthority() {
		return sqlSession.selectList(namespace+"selectAuthority");
	}
	
	
}
