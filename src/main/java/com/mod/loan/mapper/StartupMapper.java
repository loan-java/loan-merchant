package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Startup;

public interface StartupMapper extends MyBaseMapper<Startup> {

	List<Map<String, Object>> findStartupList(Map<String, Object> param);
	
}