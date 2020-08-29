package com.will.herb.product.model;

import java.util.List;

public interface ProductDAO {
	List<ProductVO> selectEvent(String eventName);
	List<ProductVO> selectPdByCategory(int categoryNo);
	ProductVO selectProduct(int productNo);
	int insertProduct(ProductVO vo);
	List<ProductVO> selectProductAll(EventProductVO searchVo);
	int getTotalRecord(EventProductVO searchVo);
	int deleteProduct(int productNo);
	
	int selectEventCount(EventProductVO eventVo);
	int insertEventProduct(EventProductVO eventVo);
	
}
