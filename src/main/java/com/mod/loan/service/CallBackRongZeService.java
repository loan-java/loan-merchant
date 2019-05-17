package com.mod.loan.service;

import com.mod.loan.model.Order;

public interface CallBackRongZeService {

    /**
     * 推送订单状态
     *
     */
    void pushOrderStatus(Order order);

    /**
     * 推送风控审批结果
     *
     */
    void pushRiskResult(Order order, String riskCode, String riskDesc);
}
