package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.BlacklistMapper;
import com.mod.loan.model.Blacklist;
import com.mod.loan.service.BlacklistService;

@Service
public class BlacklistServiceImpl extends BaseServiceImpl<Blacklist, Long> implements BlacklistService {

	@Autowired
	private BlacklistMapper blacklistMapper;

	@Override
	public List<Map<String, Object>> findBlacklistList(Map<String, Object> param, Page page) {
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		page.setTotalCount(blacklistMapper.blacklistCount(param));
		return blacklistMapper.findBlacklistList(param);
	}

	@Override
	public int findByUid(Long uid) {
		int resultCount = blacklistMapper.findByUid(uid);
		if (resultCount == 0) {
			return 0;
		}
		return resultCount;
	}

	@Override
	public Blacklist findObjectByUid(Long uid) {
		Blacklist resultObject = blacklistMapper.findObjectByUid(uid);
		if (resultObject == null) {
			return null;
		}
		return resultObject;
	}

	@Override
	public boolean addBlacklist(Blacklist blacklist_new) {
		Blacklist record = new Blacklist();
		record.setUid(blacklist_new.getUid());
		Blacklist blacklist_old = blacklistMapper.selectOne(record);
		if (blacklist_old == null) {
			blacklistMapper.insertSelective(blacklist_new);
			return true;
		}
		if (blacklist_new.getType() < blacklist_old.getType()) {
			return false;
		}
		blacklist_new.setId(blacklist_old.getId());
		blacklistMapper.updateByPrimaryKeySelective(blacklist_new);
		return true;
	}

}