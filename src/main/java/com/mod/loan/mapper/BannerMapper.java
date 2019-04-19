package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Banner;

public interface BannerMapper extends MyBaseMapper<Banner> {

	// 多条件查询
	List<Map<String, Object>> findBannerList(Map<String, Object> param);

}