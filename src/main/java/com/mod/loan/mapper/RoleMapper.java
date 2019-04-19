package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Role;

public interface RoleMapper extends MyBaseMapper<Role> {
	
	List<Role> findRoleList(Map<String, Object> params);
}