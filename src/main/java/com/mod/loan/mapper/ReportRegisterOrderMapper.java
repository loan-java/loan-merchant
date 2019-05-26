package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.ReportRegisterOrder;

import java.util.List;
import java.util.Map;

public interface ReportRegisterOrderMapper extends MyBaseMapper<ReportRegisterOrder> {

    int countReportRegisterOrder(Map<String, Object> param);

    List<Map<String, Object>> findReportRegisterOrderList(Map<String, Object> param);

    int countUserReport(Map<String, Object> param);

    List<Map<String, Object>> findUserReportList(Map<String, Object> param);
}