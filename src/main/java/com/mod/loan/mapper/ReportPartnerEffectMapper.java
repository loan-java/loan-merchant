package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.ReportPartnerEffect;

public interface ReportPartnerEffectMapper extends MyBaseMapper<ReportPartnerEffect> {

	List<Map<String, Object>> findReportPartnerEffectList(Map<String, Object> param);

	int reportPartnerEffectCount(Map<String, Object> param);

	List<Map<String, Object>> exportReport(Map<String, Object> param);

}