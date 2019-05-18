package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSON;
import com.mod.loan.common.enums.OrderSourceEnum;
import com.mod.loan.common.enums.PolicyResultEnum;
import com.mod.loan.config.Constant;
import com.mod.loan.model.Order;
import com.mod.loan.service.CallBackRongZeService;
import com.mod.loan.service.OrderService;
import com.mod.loan.util.rongze.BizDataUtil;
import com.mod.loan.util.rongze.RongZeRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class CallBackRongZeServiceImpl implements CallBackRongZeService {

    @Resource
    private OrderService orderService;

    @Override
    public void pushOrderStatus(Order order) {
        try {
            order = checkOrder(order);
            if (order == null) return;

            unbindOrderNo(order);
            postOrderStatus(order);
        } catch (Exception e) {
            log.error("给融泽推送订单状态失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void pushRiskResult(Order order, String riskCode, String riskDesc) {
        try {
            //只推审核通过跟审核拒绝
            if (PolicyResultEnum.isUnsettled(riskCode)) return;

            order = checkOrder(order);
            if (order == null) return;

            unbindOrderNo(order);

            //先推审批结果，再推订单状态，order_status=100（审批通过），order_status=110（审批拒绝）
            postRiskResult(order, riskCode, riskDesc);

            int orderStatus = PolicyResultEnum.isAgree(riskCode) ? 100 : 110;

            Map<String, Object> map = new HashMap<>();
            map.put("order_no", order.getOrderNo());
            map.put("order_status", orderStatus);
            map.put("update_time", System.currentTimeMillis());
            postOrderStatus(map);
        } catch (Exception e) {
            log.error("给融泽推送风控审批结果失败: " + e.getMessage(), e);
        }
    }

    private void postRiskResult(Order order, String riskCode, String riskDesc) throws Exception {
        String orderNo = order.getOrderNo();
        String reapply = ""; //是否可再次申请
        String reapplyTime = ""; //可再申请的时间
        String remark = ""; //拒绝原因

        Date now = new Date();
        long refuseTime = now.getTime(); //审批拒绝时间
        long approvalTime = now.getTime(); //审批通过时间
        int conclusion = 10; //通过

        if (!PolicyResultEnum.isAgree(riskCode)) {
            conclusion = 40; //拒绝
            remark = riskDesc;
            reapply = "1";

            reapplyTime = DateFormatUtils.format(refuseTime + (1000L * 3600 * 24 * 7), "yyyy-MM-dd");
        }

        int proType = 1; //单期产品
        int amountType = 0; //审批金额是否固定，0 - 固定
        int termType = 0; //审批期限是否固定，0 - 固定
        int approvalAmount = 1500; //审批金额
        int approvalTerm = 6; //审批期限
        int termUnit = 1; //期限单位，1 - 天
        String creditDeadline = DateFormatUtils.format(now, "yyyy-MM-dd"); //审批结果有效期，当前时间

        Map<String, Object> map = new HashMap<>();
        map.put("order_no", orderNo);
        map.put("conclusion", conclusion);
        map.put("reapply", reapply);
        map.put("reapplytime", reapplyTime);
        map.put("remark", remark);
        map.put("refuse_time", refuseTime);
        map.put("approval_time", approvalTime);
        map.put("pro_type", proType);
        map.put("term_unit", termUnit);
        map.put("amount_type", amountType);
        map.put("term_type", termType);
        map.put("approval_term", approvalTerm);
        map.put("credit_deadline", creditDeadline);
        map.put("approval_amount", approvalAmount);
        RongZeRequestUtil.doPost(Constant.rongZeCallbackUrl, "api.audit.result", JSON.toJSONString(map));
    }

    private Map<String, Object> postOrderStatus(Order order) throws Exception {
        int status;
        if (order.getStatus() == 23) status = 169; //放款失败
        else if (order.getStatus() == 31) status = 170; //放款成功
        else if (order.getStatus() == 21 || order.getStatus() == 22 || order.getStatus() == 11 || order.getStatus() == 12)
            status = 171; //放款处理中
        else if (order.getStatus() == 33) status = 180; //贷款逾期
        else if (order.getStatus() == 41 || order.getStatus() == 42) status = 200; //贷款结清
        else status = 169;

        long updateTime = order.getCreateTime().getTime();
        switch (status) {
            case 170:
                updateTime = order.getArriveTime().getTime();
                break;
            case 200:
                updateTime = order.getRealRepayTime().getTime();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("order_no", order.getOrderNo());
        map.put("order_status", status);
        map.put("update_time", updateTime);
        map.put("remark", "");
        postOrderStatus(map);
        return map;
    }

    private Order checkOrder(Order order) {
        if (StringUtils.isBlank(order.getOrderNo()) && order.getId() != null && order.getId() > 0) {
            order = orderService.selectByPrimaryKey(order.getId());
        }
        if (order == null) return null;
        if (!OrderSourceEnum.isRongZe(order.getSource())) return null;
        return order;
    }

    private void postOrderStatus(Map<String, Object> map) throws Exception {
        RongZeRequestUtil.doPost(Constant.rongZeCallbackUrl, "api.order.status", JSON.toJSONString(map));
    }

    private void unbindOrderNo(Order o) {
        o.setOrderNo(unbindOrderNo(o.getOrderNo()));
    }

    private String unbindOrderNo(String orderNo) {
        return BizDataUtil.unbindRZOrderNo(orderNo);
    }
}
