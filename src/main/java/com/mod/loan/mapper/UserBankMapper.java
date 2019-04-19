package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.UserBank;

public interface UserBankMapper extends MyBaseMapper<UserBank> {

	UserBank selectOneByUid(@Param("uid") Long uid);

}