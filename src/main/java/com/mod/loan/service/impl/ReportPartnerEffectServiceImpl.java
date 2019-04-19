package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.ReportPartnerEffectMapper;
import com.mod.loan.model.ReportPartnerEffect;
import com.mod.loan.service.ReportPartnerEffectService;

@Service
public class ReportPartnerEffectServiceImpl extends BaseServiceImpl<ReportPartnerEffect, Long> implements ReportPartnerEffectService {

	@Autowired
	private ReportPartnerEffectMapper reportPartnerEffectMapper;

	@Override
	public List<Map<String, Object>> findReportPartnerEffectList(Map<String, Object> param, Page page) {
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		page.setTotalCount(reportPartnerEffectMapper.reportPartnerEffectCount(param));
		return reportPartnerEffectMapper.findReportPartnerEffectList(param);
	}

	@Override
	public List<Map<String, Object>> exportReport(Map<String, Object> param) {
		return reportPartnerEffectMapper.exportReport(param);
	}
}