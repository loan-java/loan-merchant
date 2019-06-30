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
		List<Map<String, Object>> list = userMapper.findUserList(param);
		int n = list.size();
		for (int i = 0; i < n ; i++) {
			Map<String, Object> map = list.get(i);
			Map<String, Object> map1risk=userMapper.countRiskResult(map.get("id").toString());
			if(map1risk.isEmpty()){
				map.put("orderId","");
				map.put("score","0");
				map.put("code","0");
			}else{
				map.put("orderId", map1risk.get("orderId").toString());
				map.put("score", map1risk.get("score").toString());
				map.put("code", map1risk.get("code").toString());
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> exportUserOriginReport(Map<String, Object> param) {
		return userMapper.exportUserOriginReport(param);
	}

}