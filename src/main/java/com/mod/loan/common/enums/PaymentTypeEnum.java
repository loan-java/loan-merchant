package com.mod.loan.common.enums;

/**
 * 第三方放款得支付类型
 * @author kk
 */
public enum PaymentTypeEnum {
    /**
     * 支付类型
     */
    BAOFOO("baofoo", "宝付"),
    KUAIQIAN("kuaiqian", "快钱"),
    CHANPAY("chanpay", "畅捷"),
    YEEPAY("yeepay", "易宝");

    private String code;
    private String msg;

    PaymentTypeEnum(String code, String msg) {
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
