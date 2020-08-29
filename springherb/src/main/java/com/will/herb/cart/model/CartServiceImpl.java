package com.will.herb.cart.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

	@Autowired private CartDAO cartDao;

	@Override
	public int insertCart(CartVO vo) {
		int count=cartDao.selectPdCount(vo);
		
		int cnt=0;
		if(count>0) {
			cnt=cartDao.updatePdQty(vo);
		}else {
			cnt=cartDao.insertCart(vo);
		}
		
		return cnt;
	}

	@Override
	public List<Map<String, Object>> selectCartView(String customerId) {
		return cartDao.selectCartView(customerId);
	}

	@Override
	public int updateCart(CartVO vo) {
		return cartDao.updateCart(vo);
	}

	@Override
	public int deleteCart(int cartNo) {
		return cartDao.deleteCart(cartNo);
	}
	
	
}
