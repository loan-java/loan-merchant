package com.mod.loan.common.enums;

/**
 * @author kk
 */
public enum PayStatusEnum {
    /**
     * 支付状态
     */
    PAYED("PAYED", "已支付"),
    NOTPAY("NOTPAY", "未支付");

    private String code;
    private String msg;

    PayStatusEnum(String code, String msg) {
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
