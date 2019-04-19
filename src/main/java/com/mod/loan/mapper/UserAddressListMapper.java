package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.UserAddressList;

public interface UserAddressListMapper extends MyBaseMapper<UserAddressList> {

	UserAddressList selectOneByUid(@Param("uid") Long uid);

}