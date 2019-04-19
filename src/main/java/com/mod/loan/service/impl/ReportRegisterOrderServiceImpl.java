package com.mod.loan.service.impl;

import com.mod.loan.common.mapper.BaseServiceImpl;
import com.mod.loan.common.model.Page;
import com.mod.loan.mapper.ReportRegisterOrderMapper;
import com.mod.loan.model.ReportRegisterOrder;
import com.mod.loan.service.ReportRegisterOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by chenanle on 2018/8/28.
 */
@Service
public class ReportRegisterOrderServiceImpl extends BaseServiceImpl<ReportRegisterOrder,Long> implements ReportRegisterOrderService {

    @Autowired
    private ReportRegisterOrderMapper reportRegisterOrderMapper;

    @Override
    public List<Map<String, Object>> findReportRegisterOrderList(Map<String, Object> param, Page page) {
        param.put("startIndex",page.getStartIndex());
        param.put("pageSize",page.getPageSize());
        page.setTotalCount(reportRegisterOrderMapper.countReportRegisterOrder(param));
        return reportRegisterOrderMapper.findReportRegisterOrderList(param);
    }
}
