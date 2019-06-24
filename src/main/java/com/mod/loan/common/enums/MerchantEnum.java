package com.mod.loan.common.enums;

/**
 * @ author liujianjian
 * @ date 2019/5/13 18:47
 */
public enum MerchantEnum {

    HUASHIDAI("huashidai", "华时贷"),
    XIAOHUQIANBAO("xiaohuqianbao", "小虎钱包");

    private String key;
    private String name;

    MerchantEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public static boolean isHuaShiDai(String key) {
        return HUASHIDAI.getKey().equalsIgnoreCase(key);
    }

    public static boolean isXiaoHuQianBao(String key) {
        return XIAOHUQIANBAO.getKey().equalsIgnoreCase(key);
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
