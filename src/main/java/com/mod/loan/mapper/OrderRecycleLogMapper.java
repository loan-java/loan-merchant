package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.OrderRecycleLog;

public interface OrderRecycleLogMapper extends MyBaseMapper<OrderRecycleLog> {
	
	void insertOrderRecycleLog(@Param("followUserId") Long followUserId, @Param("merchant") String merchant, @Param("ids") Long... ids);
}