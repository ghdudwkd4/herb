package com.will.herb.member.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//db작업 이외의 비즈니스 로직을 처리하는 클래스
//jsp => DAO
//jsp => Service => DAO

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDao;
	
	public int insertMember(MemberVO vo){
		int cnt=memberDao.insertMember(vo);
		return cnt;
	}
	
	public int duplicateId(String userid){
		int count = memberDao.selectDup(userid);
		
		int result=0;
		if(count>0) {
			result=EXIST_ID; //이미 해당 아이디 존재
		}else {
			result=NON_EXIST_ID;
		}
		
		return result;
	}
		
	public int loginCheck(String userid, String pwd){
		String dbPwd = memberDao.selectPwd(userid);
		
		int result=0;
		if(dbPwd!=null && !dbPwd.isEmpty()) {
			if(dbPwd.equals(pwd)) {
				result=LOGIN_OK;
			}else {
				result=PWD_DISAGREE;
			}
		}else {
			result=ID_NONE;
		}
		
		return result;
	}
	
	public MemberVO selectMember(String userid){
		return memberDao.selectMember(userid);
	}
	
	public int updateMember(MemberVO vo){
		return memberDao.updateMember(vo);
	}

	public int deleteMember(String userid) {
		return memberDao.deleteMember(userid);
	}
}
