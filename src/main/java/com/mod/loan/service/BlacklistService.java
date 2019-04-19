package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.Blacklist;

public interface BlacklistService extends BaseService<Blacklist, Long> {

	List<Map<String, Object>> findBlacklistList(Map<String, Object> param, Page page);

	/**
	 * 查找黑名单是否存在
	 *
	 * @param uid
	 * @return
	 */
	int findByUid(Long uid);

	/**
	 * 查找黑名单对象
	 *
	 * @param uid
	 * @return
	 */
	Blacklist findObjectByUid(Long uid);

	boolean addBlacklist(Blacklist blacklist);

}
