package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.UserDevice;

public interface UserDeviceMapper extends MyBaseMapper<UserDevice> {
	
	UserDevice selectOneByUid(@Param("uid") Long uid);
	
}