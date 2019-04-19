package com.mod.loan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.StartupMapper;
import com.mod.loan.model.Startup;
import com.mod.loan.service.StartupService;

@Service
public class StartupServiceImpl extends BaseServiceImpl<Startup, Long> implements StartupService {

	@Autowired
	private StartupMapper startupMapper;

	@Override
	public List<Map<String, Object>> findStartupList(Startup startup, Page page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("adStatus", startup.getAdStatus());
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		param.put("merchant",startup.getMerchant());
		page.setTotalCount(startupMapper.selectCount(startup));
		return startupMapper.findStartupList(param);
	}

}