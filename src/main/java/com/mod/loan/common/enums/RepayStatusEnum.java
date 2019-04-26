package com.mod.loan.common.enums;

/**
 * @author kk
 */
public enum RepayStatusEnum {
    /**
     * 还款状态
     */
    REPAYING("REPAYING", "还款中"),
    NOT_REPAY("NOT_REPAY", "未还款"),
    REPAYED("REPAYED", "已还款"),
    PART_REPAY("PART_REPAY", "部分还款"),
    OVERDUE_REPAY("PART_REPAY", "逾期还款"),
    REPAY_FAILED("REPAY_FAILED", "还款失败");

    private String code;
    private String msg;

    RepayStatusEnum(String code, String msg) {
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
