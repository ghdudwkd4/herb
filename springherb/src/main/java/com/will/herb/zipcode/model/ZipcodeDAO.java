package com.will.herb.zipcode.model;

import java.util.List;

public interface ZipcodeDAO {
	public List<ZipcodeVO> selectZipcode(String dong);
}
