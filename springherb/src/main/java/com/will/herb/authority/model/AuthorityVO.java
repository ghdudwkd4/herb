package com.will.herb.authority.model;

import java.sql.Timestamp;

public class AuthorityVO {
	private String authCode;
	private String authName;
	private String authDesc;
	private int authLevel;     
	private Timestamp regdate;
	
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthDesc() {
		return authDesc;
	}
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
	public int getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(int authLevel) {
		this.authLevel = authLevel;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "AuthorityVO [authCode=" + authCode + ", authName=" + authName + ", authDesc=" + authDesc
				+ ", authLevel=" + authLevel + ", regdate=" + regdate + "]";
	}
	
	
}
