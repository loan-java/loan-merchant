package com.mod.loan.service;

import java.util.List;
import java.util.Map;

import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.common.model.Page;
import com.mod.loan.model.Order;
import com.mod.loan.model.OrderRepay;

public interface OrderRepayService extends BaseService<OrderRepay, Long> {

    // 多条件查找
    List<Map<String, Object>> findOrderRepayList(Map<String, Object> param, Page page);

    /**
     * 线下还款
     *
     * @param orderRepay
     */
    void updateOrderOffline(Order order, OrderRepay orderRepay);

    List<Map<String, Object>> exportReport(Map<String, Object> param);

}
