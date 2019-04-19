package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.model.Page;
import com.mod.loan.model.AppEntry;

public interface AppEntryService {
	
	List<AppEntry> findInfoList(Page page, Map<String, Object> queryParams);

	void insertSelective(AppEntry appEntry) ;

	void updateByPrimaryKey(AppEntry appEntry);

}
