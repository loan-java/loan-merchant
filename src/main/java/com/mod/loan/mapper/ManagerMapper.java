package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Manager;

public interface ManagerMapper extends MyBaseMapper<Manager> {

	List<Map<String, Object>> findManagerList(Map<String, Object> param);

	int managerCount(Map<String, Object> param);

	Manager selectById(@Param("id") Long id);

}