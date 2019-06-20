package com.mod.loan.common.enums;

/**
 * loan-pay 2019/4/22 huijin.shuailijie Init
 */
public enum PbResultEnum {

    APPROVE("APPROVE", "通过"),
    MANUAL("MANUAL", "处理失败"),
    DENY("DENY", "拒绝"),
    HANDLING("HANDLING", "处理中");


    private final String code;
    private final String text;

    PbResultEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static boolean isAPPROVE(String code) {
        return APPROVE.getCode().equals(code);
    }

    public static boolean isMANUAL(String code) {
        return MANUAL.getCode().equals(code);
    }

    public static boolean isDENY(String code) {
        return DENY.getCode().equals(code);
    }

    public static boolean isHANDLING(String code) {
        return HANDLING.getCode().equals(code);
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

}
