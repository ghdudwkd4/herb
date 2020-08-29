package com.will.herb.manager.model;

public interface ManagerDAO {
	int selectDup(String userid);
	int insertManager(ManagerVO vo);
	String selectPwd(String userid);
	ManagerVO selectManager(String userid);
}
