package com.will.herb.order.model;

import java.util.List;
import java.util.Map;

import com.will.herb.common.DateSearchVO;

public interface OrderDAO {
	int insertOrder(OrderVO orderVo);
	int insertOrderDetails(OrderVO orderVo);
	List<Map<String, Object>> selectOrderDetailsView(int orderNo);
	Map<String, Object> selectOrdersView(int orderNo);
	List<OrderAllVO> selectOrderList(DateSearchVO dateSearchVo);
	int selectTotalRecord(DateSearchVO dateSearchVo);
	List<Map<String, Object>> selectBest(int productNo);
	
}
