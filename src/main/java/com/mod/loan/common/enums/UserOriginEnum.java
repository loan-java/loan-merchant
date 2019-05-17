package com.mod.loan.common.enums;

/**
 * 用户注册渠道
 * @author kk
 */
public enum UserOriginEnum {
    /**
     * 订单类型
     */
    JH("1", "聚合"),
    RZ("2", "融泽");

    private String code;
    private String msg;

    UserOriginEnum(String code, String msg) {
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
