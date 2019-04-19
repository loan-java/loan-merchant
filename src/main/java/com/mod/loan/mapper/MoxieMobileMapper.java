package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.MoxieMobile;

public interface MoxieMobileMapper extends MyBaseMapper<MoxieMobile> {
	
	/**
	 * 查找最新的用户支付宝认证
	 * 
	 * @param id
	 * @return
	 */
	MoxieMobile selectLastOne(@Param("uid") Long id);

	/**
	 * 根据taskId查询uid
	 */
	MoxieMobile selectByTaskId(@Param("taskId")String taskId);
}