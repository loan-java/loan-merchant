package com.mod.loan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.BannerMapper;
import com.mod.loan.model.Banner;
import com.mod.loan.service.BannerService;

@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner, Long> implements BannerService {

	@Autowired
	private BannerMapper bannerMapper;

	@Override
	public List<Map<String, Object>> findBannerList(Banner banner, Page page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bannerStatus", banner.getBannerStatus());
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		param.put("merchant",banner.getMerchant());
		page.setTotalCount(bannerMapper.selectCount(banner));
		return bannerMapper.findBannerList(param);
	}

}