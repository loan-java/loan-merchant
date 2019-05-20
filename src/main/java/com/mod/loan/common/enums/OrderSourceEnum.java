package com.mod.loan.common.enums;

/**
 * 订单来源
 */
public enum OrderSourceEnum {

    JUHE(0, "聚合"),
    RONGZE(1, "融泽");

    private Integer soruce;
    private String desc;

    OrderSourceEnum() {
    }

    OrderSourceEnum(Integer soruce, String desc) {
        this.soruce = soruce;
        this.desc = desc;
    }

    public static boolean isJuHe(int source) {
        return JUHE.getSoruce() == source;
    }

    public static boolean isRongZe(int source) {
        return RONGZE.getSoruce() == source;
    }

    public Integer getSoruce() {
        return soruce;
    }

    public void setSoruce(Integer soruce) {
        this.soruce = soruce;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
