package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.MoxieZfb;

public interface MoxieZfbMapper extends MyBaseMapper<MoxieZfb> {

	/**
	 * 查找最新的用户支付宝认证
	 * 
	 * @param id
	 * @return
	 */
	MoxieZfb selectLastOne(@Param("uid") Long id);
}