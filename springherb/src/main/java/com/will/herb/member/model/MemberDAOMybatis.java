package com.will.herb.member.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOMybatis implements MemberDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.member.";
	
	public int insertMember(MemberVO vo){
		return sqlSession.insert(namespace+"insertMember", vo);
	}
	
	@Override
	public int selectDup(String userid) {
		return sqlSession.selectOne(namespace+"selectDup", userid);
	}

	@Override
	public String selectPwd(String userid) {
		return sqlSession.selectOne(namespace+"selectPwd", userid);
	}
	
	public MemberVO selectMember(String userid) {
		return sqlSession.selectOne(namespace+"selectMember", userid);
	}
		
	public int updateMember(MemberVO vo) {
		return sqlSession.update(namespace+"updateMember", vo);
	}
	
	public int deleteMember(String userid){
		return sqlSession.update(namespace+"withdrawMember", userid);
	}
	
	
}










