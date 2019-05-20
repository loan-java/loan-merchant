package com.mod.loan.common.enums;

/**
 * loan-pay 2019/4/22 huijin.shuailijie Init
 */
public enum PolicyResultEnum {

    AGREE("AGREE", "通过"),
    REJECT("REJECT", "拒绝"),
    UNSETTLED("UNSETTLED", "人工审核"),
    ERROR("ERROR", "规则执行异常");


    private final String code;
    private final String text;

    PolicyResultEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static boolean isAgree(String code) {
        return AGREE.getCode().equals(code);
    }
    public static boolean isUnsettled(String code) {
        return UNSETTLED.getCode().equals(code);
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
