package com.will.herb.manager.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.will.herb.member.model.MemberVO;

@Repository
public class ManagerDAOMybatis implements ManagerDAO{

	@Autowired private SqlSessionTemplate sqlSession;

	private String namespace="config.mybatis.mapper.oracle.manager.";
	
	@Override
	public int selectDup(String userid) {
		return sqlSession.selectOne(namespace+"selectDup", userid);
	}

	@Override
	public int insertManager(ManagerVO vo) {
		return sqlSession.insert(namespace+"insertManager", vo);
	}
	
	@Override
	public String selectPwd(String userid) {
		return sqlSession.selectOne(namespace+"selectPwd", userid);
	}
	
	public ManagerVO selectManager(String userid) {
		return sqlSession.selectOne(namespace+"selectManager", userid);
	}
}




