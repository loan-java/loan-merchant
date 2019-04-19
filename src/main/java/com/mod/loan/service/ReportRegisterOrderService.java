package com.mod.loan.service;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.ReportRegisterOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by chenanle on 2018/8/28.
 */
public interface ReportRegisterOrderService extends BaseService<ReportRegisterOrder, Long> {

    List<Map<String, Object>> findReportRegisterOrderList(Map<String, Object> param, Page page);
}
