package com.mod.loan.common.enums;

public enum SmsTemplate {

    T002("002"),//打款还款提示
    T005("005"),//逾期提示
    ;
    private String key;  //自有模板key

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    SmsTemplate(String key) {
        this.key = key;
    }

    public static SmsTemplate getTemplate(String key) {
        for (SmsTemplate enumYunpianApikey : SmsTemplate.values()) {
            if (enumYunpianApikey.getKey().equals(key)) {
                return enumYunpianApikey;
            }
        }
        return null;
    }
}
