package com.mod.loan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.ReportOrderLoanMapper;
import com.mod.loan.model.ReportOrderLoan;
import com.mod.loan.service.ReportOrderLoanService;

@Service
public class ReportOrderLoanServiceImpl extends BaseServiceImpl<ReportOrderLoan, Long> implements ReportOrderLoanService {

	@Autowired
	private ReportOrderLoanMapper reportOrderLoanMapper;

	@Override
	public List<Map<String, Object>> findReportOrderLoanList(Map<String, Object> param, Page page) {
		param.put("startIndex", page.getStartIndex());
		param.put("pageSize", page.getPageSize());
		page.setTotalCount(reportOrderLoanMapper.reportOrderLoanCount(param));
		return reportOrderLoanMapper.findReportOrderLoanList(param);
	}

	@Override
	public List<Map<String, Object>> exportReport(Map<String, Object> param) {
		return reportOrderLoanMapper.exportReport(param);
	}
}