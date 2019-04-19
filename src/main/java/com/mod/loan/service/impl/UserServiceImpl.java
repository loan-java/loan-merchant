package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.User;
import com.mod.loan.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<Map<String, Object>> findUserList(Map<String, Object> param, Page page) {
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		page.setTotalCount(userMapper.userCount(param));
		return userMapper.findUserList(param);
	}

	@Override
	public List<Map<String, Object>> exportUserOriginReport(Map<String, Object> param) {
		return userMapper.exportUserOriginReport(param);
	}

}