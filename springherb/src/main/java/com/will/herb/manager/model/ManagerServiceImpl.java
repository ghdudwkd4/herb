package com.will.herb.manager.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired private ManagerDAO managerDao;
	
	@Override
	public int checkUserid(String userid) {
		int count = managerDao.selectDup(userid);
		int result=0;
		if(count>0) { //이미 해당 아이디가 존재
			result=MemberService.EXIST_ID;
		}else {
			result=MemberService.NON_EXIST_ID;
		}
		
		return result;
	}

	@Override
	public int insertManager(ManagerVO vo) {
		return managerDao.insertManager(vo);
	}

	public int loginCheck(String userid, String pwd){
		String dbPwd = managerDao.selectPwd(userid);
		
		int result=0;
		if(dbPwd!=null && !dbPwd.isEmpty()) {
			if(dbPwd.equals(pwd)) {
				result=MemberService.LOGIN_OK;
			}else {
				result=MemberService.PWD_DISAGREE;
			}
		}else {
			result=MemberService.ID_NONE;
		}
		
		return result;
	}
	
	public ManagerVO selectManager(String userid){
		return managerDao.selectManager(userid);
	}
}
