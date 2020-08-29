package com.will.herb.order.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.will.herb.cart.model.CartDAO;
import com.will.herb.common.DateSearchVO;

@Service
public class OrderServiceImpl implements OrderService{
	private static final Logger logger
		=LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired private OrderDAO orderDao;
	@Autowired private CartDAO cartDao;

	@Override
	@Transactional
	public int insertOrder(OrderVO orderVo) {
		int cnt1=0, cnt2=0, cnt3=0;
		cnt1=orderDao.insertOrder(orderVo);
		logger.info("orders insert결과, cnt1={}", cnt1);
		
		cnt2=orderDao.insertOrderDetails(orderVo);
		logger.info("orderDetails insert결과, cnt2={}, 매개변수 orderVo={}",
			cnt2, orderVo);
		
		cnt3 = cartDao.deleteCartByUserid(orderVo.getCustomerId());
		logger.info("cart delete결과, cnt3={}", cnt3);
		
		return cnt3;
	}

	@Override
	public List<Map<String, Object>> selectOrderDetailsView(int orderNo) {
		return orderDao.selectOrderDetailsView(orderNo);
	}

	@Override
	public Map<String, Object> selectOrdersView(int orderNo) {
		return orderDao.selectOrdersView(orderNo);
	}

	@Override
	public List<OrderAllVO> selectOrderList(DateSearchVO dateSearchVo) {
		return orderDao.selectOrderList(dateSearchVo);
	}

	@Override
	public int selectTotalRecord(DateSearchVO dateSearchVo) {
		return orderDao.selectTotalRecord(dateSearchVo);
	}

	@Override
	public List<Map<String, Object>> selectBest(int productNo) {
		return orderDao.selectBest(productNo);
	}
	
	
}





