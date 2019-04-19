package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.ReportPartnerEffect;

public interface ReportPartnerEffectService extends BaseService<ReportPartnerEffect, Long> {

	List<Map<String, Object>> findReportPartnerEffectList(Map<String, Object> param, Page page);

	List<Map<String, Object>> exportReport(Map<String, Object> param);

}
