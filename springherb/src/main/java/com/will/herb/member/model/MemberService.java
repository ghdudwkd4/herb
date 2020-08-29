package com.will.herb.member.model;

public interface MemberService {
	//아이디 중복확인 관련 상수
	public static final int EXIST_ID=1;  //해당 아이디가 이미 존재하는 경우
	public static final int NON_EXIST_ID=2;  //아이디가 존재하지 않는 경우
	
	//로그인 처리 관련 상수
	int LOGIN_OK=1; //로그인 성공
	int PWD_DISAGREE=2; //비밀번호 불일치
	int ID_NONE=3; //아이디 존재하지 않음
		
	int insertMember(MemberVO vo);
	int duplicateId(String userid);
	int loginCheck(String userid, String pwd);
	public MemberVO selectMember(String userid);
	public int updateMember(MemberVO vo);
	public int deleteMember(String userid);
	
}
