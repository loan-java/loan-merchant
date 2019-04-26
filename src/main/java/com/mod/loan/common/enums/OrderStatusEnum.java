package com.mod.loan.common.enums;

/**
 * 订单状态
 *
 * @author kk
 */
public enum OrderStatusEnum {

    /**
     * 订单状态
     */
    APPROVE("APPROVE", "审核中"),
    TO_PAY("TO_PAY", "放款中"),
    PAYED("PAYED", "已放款"),
    PART_REPAY("PART_REPAY", "部分还款"),
    REPAYED("REPAYED", "已还款"),
    OVERDUEREPAYED("OVERDUEREPAYED", "逾期还款"),
    REPAY_FAILED("REPAY_FAILED", "还款失败"),
    OVERDUE("OVERDUE", "逾期"),
    PAY_FAILED("PAY_FAILED", "放款失败"),
    BAD_DEBT("BAD_DEBT", "坏账"),
    AUDIT_REFUSE("AUDIT_REFUSE", "审核失败"),
    REPAYING("REPAYING", "还款中"),
    WAIT_PAY("WAIT_PAY", "待放款"),
    CANCEL("CANCEL", "取消"),
    REFUSE_PAY("REFUSE_PAY", "拒绝放款");

    private String code;
    private String msg;

    OrderStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
