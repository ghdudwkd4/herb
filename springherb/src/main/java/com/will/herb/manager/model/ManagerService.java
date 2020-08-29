package com.will.herb.manager.model;

public interface ManagerService {
	int checkUserid(String userid);
	int insertManager(ManagerVO vo);
	int loginCheck(String userid, String pwd);
	ManagerVO selectManager(String userid);
	
}
