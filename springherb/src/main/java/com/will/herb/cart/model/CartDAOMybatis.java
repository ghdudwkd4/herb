package com.will.herb.cart.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOMybatis implements CartDAO{

	@Autowired private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.cart.";

	@Override
	public int selectPdCount(CartVO vo) {
		return sqlSession.selectOne(namespace+"selectPdCount", vo);
	}

	@Override
	public int insertCart(CartVO vo) {
		return sqlSession.insert(namespace+"insertCart", vo);
	}

	@Override
	public int updatePdQty(CartVO vo) {
		return sqlSession.update(namespace+"updatePdQty", vo);
	}

	@Override
	public List<Map<String, Object>> selectCartView(String customerId) {
		return sqlSession.selectList(namespace+"selectCartView",
				customerId);
	}

	@Override
	public int updateCart(CartVO vo) {
		return sqlSession.update(namespace+"updateCart", vo);
	}

	@Override
	public int deleteCart(int cartNo) {
		return sqlSession.delete(namespace+"deleteCart", cartNo);
	}

	@Override
	public int deleteCartByUserid(String userid) {
		return sqlSession.delete(namespace+"deleteCartByUserid", userid);
	}
	
	
	
	
}
