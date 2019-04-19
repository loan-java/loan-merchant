package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.ReportOrderRepay;

public interface ReportOrderRepayMapper extends MyBaseMapper<ReportOrderRepay> {

	List<Map<String, Object>> findReportOrderRepayList(Map<String, Object> param);

	int reportOrderRepayCount(Map<String, Object> param);

	List<Map<String, Object>> exportReport(Map<String, Object> param);
	
}