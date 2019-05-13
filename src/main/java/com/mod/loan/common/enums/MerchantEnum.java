package com.mod.loan.common.enums;

/**
 * @ author liujianjian
 * @ date 2019/5/13 18:47
 */
public enum MerchantEnum {

    HUASHIDAI("huashidai", "华时贷"),
    JISHIDAI("jishidai", "及时贷");

    private String key;
    private String name;

    MerchantEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public static boolean isHuaShiDai(String key) {
        return HUASHIDAI.getKey().equalsIgnoreCase(key);
    }

    public static boolean isJiShiDai(String key) {
        return JISHIDAI.getKey().equalsIgnoreCase(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
