package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.User;

public interface UserService extends BaseService<User, Long> {

	// 多条件查找
	List<Map<String, Object>> findUserList(Map<String, Object> param, Page page);

	List<Map<String, Object>> exportUserOriginReport(Map<String, Object> param);

}