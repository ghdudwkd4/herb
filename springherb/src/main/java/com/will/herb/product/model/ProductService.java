package com.will.herb.product.model;

import java.util.List;

public interface ProductService {
	List<ProductVO> selectEvent(String eventName);
	List<ProductVO> selectPdByCategory(int categoryNo);
	ProductVO selectProduct(int productNo);
	int insertProduct(ProductVO vo);
	List<ProductVO> selectProductAll(EventProductVO searchVo);
	int getTotalRecord(EventProductVO searchVo);
	int deleteMulti(List<ProductVO> list);
	int insertEventPdMulti(List<ProductVO> list, String eventName);
	
}
