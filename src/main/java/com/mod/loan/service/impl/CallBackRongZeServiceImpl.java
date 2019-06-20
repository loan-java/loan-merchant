package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSON;
import com.mod.loan.common.enums.OrderSourceEnum;
import com.mod.loan.common.enums.PbResultEnum;
import com.mod.loan.common.enums.PolicyResultEnum;
import com.mod.loan.config.Constant;
import com.mod.loan.model.Order;
import com.mod.loan.model.UserBank;
import com.mod.loan.service.CallBackRongZeService;
import com.mod.loan.service.OrderService;
import com.mod.loan.service.UserBankService;
import com.mod.loan.util.rongze.RongZeRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


@Slf4j
@Service
public class CallBackRongZeServiceImpl implements CallBackRongZeService {

    @Resource
    private OrderService orderService;


    @Resource
    private UserBankService userBankService;

    @Override
    public void pushOrderStatus(Order order) {
        try {
            order = checkOrder(order);
            if (order == null) return;

            postOrderStatus(order);
        } catch (Exception e) {
            log.error("给融泽推送订单状态失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void pushRepayPlan(Order order) {
        try {
            order = checkOrder(order);
            postRepayPlan(order);
        } catch (Exception e) {
            log.error("给融泽推送还款计划失败: " + e.getMessage(), e);
        }
    }


    @Override
    public void pushRiskResultForQjld(Order order, String riskCode, String riskDesc) {
        try {
            //只推审核通过跟审核拒绝
            if (!(PolicyResultEnum.isAgree(riskCode) || PolicyResultEnum.isReject(riskCode))) return;

            order = checkOrder(order);
            if (order == null) return;


            //先推审批结果，再推订单状态，order_status=100（审批通过），order_status=110（审批拒绝）
            postRiskResultForQjld(order, riskCode, riskDesc);

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


    @Override
    public void pushRiskResultForPb(Order order, String riskCode, String riskDesc) {
        try {
            //只推审核通过跟审核拒绝
            if (!(PbResultEnum.isAPPROVE(riskCode) || PbResultEnum.isDENY(riskCode) || PbResultEnum.isMANUAL(riskCode))) {
                return;
            }
            order = checkOrder(order);
            if (order == null) return;
            //先推审批结果，再推订单状态，order_status=100（审批通过），order_status=110（审批拒绝）
            postRiskResultForPb(order, riskCode, riskDesc);

            int orderStatus = PbResultEnum.isAPPROVE(riskCode) ? 100 : 110;

            Map<String, Object> map = new HashMap<>();
            map.put("order_no", order.getOrderNo());
            map.put("order_status", orderStatus);
            map.put("update_time", System.currentTimeMillis());
            postOrderStatus(map);

        } catch (Exception e) {
            log.error("给融泽推送风控审批结果失败: " + e.getMessage(), e);
        }
    }


    private void postRiskResultForQjld(Order order, String riskCode, String riskDesc) throws Exception {
        String orderNo = order.getOrderNo();

        String reapply = null; //是否可再次申请
        String reapplyTime = null; //可再申请的时间
        String remark = "审批中"; //拒绝原因

        Long refuseTime = null; //审批拒绝时间
        Long approvalTime = null; //审批通过时间
        int conclusion = 30; //处理中
        Integer proType = null; //单期产品
        Integer amountType = null; //审批金额是否固定，0 - 固定
        Integer termType = null; //审批期限是否固定，0 - 固定
        Integer approvalAmount = null; //审批金额
        Integer approvalTerm = null; //审批期限
        Integer termUnit = null; //期限单位，1 - 天
        String creditDeadline = null; //审批结果有效期，当前时间

        if (PolicyResultEnum.isAgree(riskCode)) {
            //通过
            conclusion = 10;
            approvalTime = System.currentTimeMillis();
            creditDeadline = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            proType = 1; //单期产品
            amountType = 0; //审批金额是否固定，0 - 固定
            termType = 0; //审批期限是否固定，0 - 固定
            approvalAmount = 1500; //审批金额
            approvalTerm = 6; //审批期限
            termUnit = 1; //期限单位，1 - 天
            remark = "通过";
        } else {
            //拒绝
            refuseTime = System.currentTimeMillis();
            conclusion = 40;
            remark = StringUtils.isNotBlank(riskDesc) ? riskDesc : "拒绝";
            reapply = "1";
            reapplyTime = DateFormatUtils.format(refuseTime + (1000L * 3600 * 24 * 7), "yyyy-MM-dd");
        }

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


    private void postRiskResultForPb(Order order, String riskCode, String riskDesc) throws Exception {
        String orderNo = order.getOrderNo();

        String reapply = null; //是否可再次申请
        String reapplyTime = null; //可再申请的时间
        String remark = "审批中"; //拒绝原因

        Long refuseTime = null; //审批拒绝时间
        Long approvalTime = null; //审批通过时间
        int conclusion = 30; //处理中
        Integer proType = null; //单期产品
        Integer amountType = null; //审批金额是否固定，0 - 固定
        Integer termType = null; //审批期限是否固定，0 - 固定
        Integer approvalAmount = null; //审批金额
        Integer approvalTerm = null; //审批期限
        Integer termUnit = null; //期限单位，1 - 天
        String creditDeadline = null; //审批结果有效期，当前时间

        if (PbResultEnum.isAPPROVE(riskCode)) {
            //通过
            conclusion = 10;
            approvalTime = System.currentTimeMillis();
            creditDeadline = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            proType = 1; //单期产品
            amountType = 0; //审批金额是否固定，0 - 固定
            termType = 0; //审批期限是否固定，0 - 固定
            approvalAmount = 1500; //审批金额
            approvalTerm = 6; //审批期限
            termUnit = 1; //期限单位，1 - 天
            remark = "通过";
        } else {
            //拒绝
            refuseTime = System.currentTimeMillis();
            conclusion = 40;
            remark = StringUtils.isNotBlank(riskDesc) ? riskDesc : "拒绝";
            reapply = "1";
            reapplyTime = DateFormatUtils.format(refuseTime + (1000L * 3600 * 24 * 7), "yyyy-MM-dd");
        }

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
        if (order.getStatus() == 23 || order.getStatus() == 53) status = 169; //放款失败或者取消
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


    private Map<String, Object> postRepayPlan(Order order) throws Exception {
        List<Map<String, Object>> repayPlan = new ArrayList<>();
        Map<String, Object> repay = new HashMap<>(11);
        // 还款计划编号  期数
        repay.put("period_no", "1");
        // 账单状态：1=未到期待还款 2=已还款 3=逾期
        if (order.getRealRepayTime() != null) {
            repay.put("bill_status", "2");
        } else if (order.getOverdueDay() > 0) {
            repay.put("bill_status", "3");
        } else {
            repay.put("bill_status", "1");
        }
        // 账单到期时间 精确到毫秒（比如 1539073086805)
        repay.put("due_time", new DateTime(order.getRepayTime()).toDateTime().getMillis());
        // 当期最早可以还款的时间 精确到毫秒（比如 1539073086805 ）
        repay.put("can_repay_time", System.currentTimeMillis());
        // 还款方式：1=主动还款 2=跳转机构 H5 还款  4=银行代扣 5=主动还款+银行代扣
        repay.put("pay_type", 5);
        // 当前所需的还款金额，单位元，保留小数点后两位 （该金额应该是本金利息加上逾期金额减去已还款金额的结果，逾期金额、已还款金额可能为零）
        repay.put("amount", order.getShouldRepay());
        // 已还款金额+减免金额，单位元，保留小数点后两位
        repay.put("paid_amount", (order.getHadRepay().add(order.getReduceMoney())).toPlainString());
        // 逾期费用，单位元，保留小数点后两位
        repay.put("overdue_fee", order.getOverdueFee().toPlainString());
        // 还款成功的时间
        repay.put("success_time", order.getRealRepayTime() == null ? "" : order.getRealRepayTime().getTime() / 1000);
        // 当期还款金额描述
        if (order.getRealRepayTime() != null) {
            StringBuilder remark = new StringBuilder("含本金 ");
            remark.append(order.getActualMoney().toPlainString());
            remark.append(" 元，利息&手续费 ").append(order.getTotalFee().add(order.getInterestFee()).toPlainString()).append(" 元");
            if (order.getOverdueFee() != null && order.getOverdueFee().compareTo(new BigDecimal("0")) > 0) {
                remark.append("，逾期 ").append(order.getOverdueFee().toPlainString()).append(" 元");
            }
            repay.put("remark", remark.toString());
        }
        // 费用项集合
        List<Map<String, Object>> billItem = new ArrayList<>();
        if (order.getActualMoney() != null) {
            // 实际金额
            Map<String, Object> actualMoney = new HashMap<>(2);
            actualMoney.put("feetype", "1");
            actualMoney.put("dueamount", order.getActualMoney().toPlainString());
            billItem.add(actualMoney);
        }
        if (order.getTotalFee() != null) {
            // 服务费
            Map<String, Object> totalFee = new HashMap<>(2);
            totalFee.put("feetype", "5");
            totalFee.put("dueamount", order.getTotalFee().toPlainString());
            billItem.add(totalFee);
        }
        if (order.getInterestFee() != null && order.getInterestFee().compareTo(new BigDecimal("0")) > 0) {
            // 利息
            Map<String, Object> interestFee = new HashMap<>(2);
            interestFee.put("feetype", "2");
            interestFee.put("dueamount", order.getInterestFee().toPlainString());
            billItem.add(interestFee);
        }
        if (order.getOverdueFee() != null && order.getOverdueFee().compareTo(new BigDecimal("0")) > 0) {
            // 逾期费
            Map<String, Object> overdueFee = new HashMap<>(2);
            overdueFee.put("feetype", "3");
            overdueFee.put("dueamount", order.getOverdueFee().toPlainString());
            billItem.add(overdueFee);
        }
        repay.put("billitem", billItem);
        repayPlan.add(repay);

        UserBank userBank = userBankService.selectUserCurrentBankCard(order.getUid());
        Map<String, Object> map = new HashMap<>(4);
        //账单的订单编号
        map.put("order_no", order.getOrderNo());
        //银行名称编码（并非汉字）
        map.put("open_bank", userBank.getCardCode());
        //银行卡号
        map.put("bank_card", userBank.getCardNo());
        //还款计划
        map.put("repayment_plan", repayPlan);
        postRepayPlan(map);
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


    private void postRepayPlan(Map<String, Object> map) throws Exception {
        RongZeRequestUtil.doPost(Constant.rongZeCallbackUrl, "api.payment.plan", JSON.toJSONString(map));
    }

    @Override
    public void pushRiskResultForZm(Order order, String riskCode) {
        try {
            order = checkOrder(order);
            if (order == null) return;
            //先推审批结果，再推订单状态，order_status=100（审批通过），order_status=110（审批拒绝）
            postRiskResultForZm(order, riskCode);

            int orderStatus = "0".equals(riskCode) ? 100 : 110;

            Map<String, Object> map = new HashMap<>();
            map.put("order_no", order.getOrderNo());
            map.put("order_status", orderStatus);
            map.put("update_time", System.currentTimeMillis());
            postOrderStatus(map);

        } catch (Exception e) {
            log.error("给融泽推送风控审批结果失败: " + e.getMessage(), e);
        }
    }
    private void postRiskResultForZm(Order order, String riskCode) throws Exception {
        String orderNo = order.getOrderNo();

        String reapply = null; //是否可再次申请
        String reapplyTime = null; //可再申请的时间
        String remark = "审批中"; //拒绝原因

        Long refuseTime = null; //审批拒绝时间
        Long approvalTime = null; //审批通过时间
        int conclusion = 30; //处理中
        Integer proType = null; //单期产品
        Integer amountType = null; //审批金额是否固定，0 - 固定
        Integer termType = null; //审批期限是否固定，0 - 固定
        Integer approvalAmount = null; //审批金额
        Integer approvalTerm = null; //审批期限
        Integer termUnit = null; //期限单位，1 - 天
        String creditDeadline = null; //审批结果有效期，当前时间

        if ("0".equals(riskCode)) {
            //通过
            conclusion = 10;
            approvalTime = System.currentTimeMillis();
            creditDeadline = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            proType = 1; //单期产品
            amountType = 0; //审批金额是否固定，0 - 固定
            termType = 0; //审批期限是否固定，0 - 固定
            approvalAmount = 1500; //审批金额
            approvalTerm = 6; //审批期限
            termUnit = 1; //期限单位，1 - 天
            remark = "通过";
        } else {
            //拒绝
            refuseTime = System.currentTimeMillis();
            conclusion = 40;
            remark = "拒绝" ;
            reapply = "1";
            reapplyTime = DateFormatUtils.format(refuseTime + (1000L * 3600 * 24 * 7), "yyyy-MM-dd");
        }

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


}
