package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.OrderPay;

public interface OrderPayService extends BaseService<OrderPay, Long> {

    // 多条件查找
    List<Map<String, Object>> findOrderPayList(Map<String, Object> param, Page page);

    List<Map<String, Object>> exportReport(Map<String, Object> param);

}
