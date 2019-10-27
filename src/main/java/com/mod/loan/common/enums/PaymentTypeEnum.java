package com.mod.loan.common.enums;

public enum PaymentTypeEnum {
    baofoo(4, "baofoo", "宝付"),
    kuaiqian(5, "kuaiqian", "快钱"),
    chanpay(6, "chanpay", "畅捷"),
    yeepay(7, "yeepay", "易宝"),
    ;

    private Integer code;
    private String name;
    private String desc;

    PaymentTypeEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static String getDescByName(String name) {
        for (PaymentTypeEnum e : PaymentTypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e.getDesc();
            }
        }
        return "";
    }

    public static String getDesc(Integer code) {
        for (PaymentTypeEnum status : PaymentTypeEnum.values()) {
            if (status.getCode().equals(code)) {
                return status.getDesc();
            }
        }
        return null;
    }

    public static String getName(Integer code) {
        for (PaymentTypeEnum status : PaymentTypeEnum.values()) {
            if (status.getCode().equals(code)) {
                return status.getName();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
