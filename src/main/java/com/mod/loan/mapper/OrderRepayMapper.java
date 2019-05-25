package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.OrderRepay;
import org.apache.ibatis.annotations.Param;

public interface OrderRepayMapper extends MyBaseMapper<OrderRepay> {

	int orderRepayCount(Map<String, Object> param);

	List<Map<String, Object>> findOrderRepayList(Map<String, Object> param);

	List<Map<String, Object>> exportReport(Map<String, Object> param);


	Integer getLastRepayStatus(@Param("orderId")String orderId);


}