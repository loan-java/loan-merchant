package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.OrderPay;

public interface OrderPayMapper extends MyBaseMapper<OrderPay> {

	int orderPayCount(Map<String, Object> param);

	List<Map<String, Object>> findOrderPayList(Map<String, Object> param);

	List<Map<String, Object>> exportReport(Map<String, Object> param);



	
}