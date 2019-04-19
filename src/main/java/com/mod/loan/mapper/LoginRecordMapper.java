package com.mod.loan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.LoginRecord;

public interface LoginRecordMapper extends MyBaseMapper<LoginRecord> {

	List<String> selectRecentlyIps(@Param("managerId") Long managerId, @Param("merchant") String merchant);
}