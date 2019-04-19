package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.AppEntry;

public interface AppEntryMapper extends MyBaseMapper<AppEntry> {

	List<AppEntry> findInfoList(Map<String, Object> queryParams);

	int getCount(Map<String, Object> queryParams);
}