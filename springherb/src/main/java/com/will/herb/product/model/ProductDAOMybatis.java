package com.will.herb.product.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOMybatis implements ProductDAO{

	@Autowired private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.product.";

	@Override
	public List<ProductVO> selectEvent(String eventName) {
		return sqlSession.selectList(namespace+"selectEvent", eventName);
	}

	@Override
	public List<ProductVO> selectPdByCategory(int categoryNo) {
		return sqlSession.selectList(namespace+"selectPdByCg", categoryNo);
	}

	@Override
	public ProductVO selectProduct(int productNo) {
		return sqlSession.selectOne(namespace+"selectProduct", productNo);
	}

	@Override
	public int insertProduct(ProductVO vo) {
		return sqlSession.insert(namespace+"insertProduct", vo);
	}

	@Override
	public List<ProductVO> selectProductAll(EventProductVO searchVo) {
		return sqlSession.selectList(namespace+"selectProductAll",
				searchVo);
	}

	@Override
	public int getTotalRecord(EventProductVO searchVo) {
		return sqlSession.selectOne(namespace+"getTotalRecord",searchVo);
	}

	@Override
	public int deleteProduct(int productNo) {
		return sqlSession.delete(namespace+"deleteProduct", productNo);
	}

	@Override
	public int selectEventCount(EventProductVO eventVo) {
		return sqlSession.selectOne(namespace+"selectEventCount", eventVo);
	}

	@Override
	public int insertEventProduct(EventProductVO eventVo) {
		return sqlSession.insert(namespace+"insertEventProduct", eventVo);
	}
	
	
}









