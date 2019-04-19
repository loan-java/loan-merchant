package com.mod.loan.mapper;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.ReportOrderLoan;

public interface ReportOrderLoanMapper extends MyBaseMapper<ReportOrderLoan> {

	List<Map<String, Object>> findReportOrderLoanList(Map<String, Object> param);

	int reportOrderLoanCount(Map<String, Object> param);

	List<Map<String, Object>> exportReport(Map<String, Object> param);
	
}