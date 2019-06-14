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



    /*
     * @Description:
     * @Param: 推送还款计划
     * @return:
     * @Author: huijin.shuailijie
     * @Date: 2019/5/19
     */
    void pushRepayPlan(Order order);


    void pushRiskResultForPb(Order order, String riskCode, String riskDesc);
}
