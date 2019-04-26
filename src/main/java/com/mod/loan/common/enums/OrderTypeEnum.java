package com.mod.loan.common.enums;

/**
 * @author kk
 */
public enum OrderTypeEnum {
    /**
     * 订单类型
     */
    JK("JK", "订单"),
    HK("HK", "还款单");

    private String code;
    private String msg;

    OrderTypeEnum(String code, String msg) {
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
