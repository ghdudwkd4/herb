package com.will.herb.product.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired private ProductDAO productDao;

	@Override
	public List<ProductVO> selectEvent(String eventName) {
		return productDao.selectEvent(eventName);
	}

	@Override
	public List<ProductVO> selectPdByCategory(int categoryNo) {
		return productDao.selectPdByCategory(categoryNo);
	}

	@Override
	public ProductVO selectProduct(int productNo) {
		return productDao.selectProduct(productNo);
	}

	@Override
	public int insertProduct(ProductVO vo) {
		return productDao.insertProduct(vo);
	}

	@Override
	public List<ProductVO> selectProductAll(EventProductVO searchVo) {
		return productDao.selectProductAll(searchVo);
	}

	@Override
	public int getTotalRecord(EventProductVO searchVo) {
		return productDao.getTotalRecord(searchVo);
	}

	@Override
	@Transactional
	public int deleteMulti(List<ProductVO> list) {
		int cnt=0;
		try {
			for(ProductVO vo : list) {
				if(vo.getProductNo()!=0) { //체크한 상품만 삭제
					cnt=productDao.deleteProduct(vo.getProductNo());
				}
			}
		}catch(RuntimeException e) {
			cnt=-1;
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		//선언적 트랜잭션에서는 런타임 예외가 발생하면 롤백한다. 
		//반면에 예외가 전혀 발생하지 않거나 체크 예외가 발생하면 커밋한다. 

		return cnt;
	}

	@Override
	@Transactional
	public int insertEventPdMulti(List<ProductVO> list, String eventName) {
		int cnt=0;
		
		try {
			for(ProductVO pdVo : list) {
				int productNo=pdVo.getProductNo();
				if(productNo!=0) { //체크한 상품만 등록
					EventProductVO eventVo = new EventProductVO();
					eventVo.setProductNo(productNo);
					eventVo.setEventName(eventName);
					
					int count=productDao.selectEventCount(eventVo);
					if(count>0) {  //이미 해당 상품이 특정 이벤트로 등록된 경우=> skip
						cnt=1;	
					}else {
						cnt=productDao.insertEventProduct(eventVo);
					}
				}//if
			}//for
		}catch(RuntimeException e) {
			cnt=-1;
			e.printStackTrace();
			throw e;
		}
		
		return cnt;
	}
	
	
}








