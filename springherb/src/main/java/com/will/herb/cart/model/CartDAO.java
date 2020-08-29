package com.will.herb.cart.model;

import java.util.List;
import java.util.Map;

public interface CartDAO {
	int selectPdCount(CartVO vo);
	int insertCart(CartVO vo);
	int updatePdQty(CartVO vo);
	List<Map<String, Object>> selectCartView(String customerId);
	int updateCart(CartVO vo);
	int deleteCart(int cartNo);
	int deleteCartByUserid(String userid);
	
	
}
