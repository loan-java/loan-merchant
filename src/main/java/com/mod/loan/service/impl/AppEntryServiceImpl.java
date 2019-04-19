package com.mod.loan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.AppEntryMapper;
import com.mod.loan.model.AppEntry;
import com.mod.loan.service.AppEntryService;

@Service
public class AppEntryServiceImpl implements AppEntryService {

	@Autowired
	AppEntryMapper appEntryMapper;

	@Override
	public List<AppEntry> findInfoList(Page page, Map<String, Object> queryParams) {
		queryParams.put("pageSize", page.getPageSize());
		queryParams.put("startIndex", page.getStartIndex());
		page.setTotalCount(appEntryMapper.getCount(queryParams));
		return appEntryMapper.findInfoList(queryParams);
	}

	@Override
	public void insertSelective(AppEntry appEntry) {
		if (appEntry.getEntryStatus() == 2 && appEntry.getStartTime() == null) {
			appEntry.setStartTime(new Date());
		}
		appEntry.setCreateTime(new Date());
		appEntryMapper.insertSelective(appEntry);
	}

	@Override
	public void updateByPrimaryKey(AppEntry appEntry)  {
		if (appEntry.getEntryStatus() == 2 && appEntry.getStartTime() == null) {
			appEntry.setStartTime(new Date());
		}
		appEntryMapper.updateByPrimaryKey(appEntry);
	}

}
