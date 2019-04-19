package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "report_register_order")
public class ReportRegisterOrder {
    @Id
    private Long id;

    @Column(name = "day_key")
    private Date dayKey;

    private String merchant;

    @Column(name = "register_cnt")
    private Integer registerCnt;

    @Column(name = "realname_cnt")
    private Integer realnameCnt;

    @Column(name = "zfb_cnt")
    private Integer zfbCnt;

    @Column(name = "mobile_cnt")
    private Integer mobileCnt;

    @Column(name = "order_cnt")
    private Integer orderCnt;

    @Column(name = "first_cnt")
    private Integer firstCnt;

    @Column(name = "second_cnt")
    private Integer secondCnt;

    @Column(name = "old_cnt")
    private Integer oldCnt;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return day_key
     */
    public Date getDayKey() {
        return dayKey;
    }

    /**
     * @param dayKey
     */
    public void setDayKey(Date dayKey) {
        this.dayKey = dayKey;
    }

    /**
     * @return merchant
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * @param merchant
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant == null ? null : merchant.trim();
    }

    /**
     * @return register_cnt
     */
    public Integer getRegisterCnt() {
        return registerCnt;
    }

    /**
     * @param registerCnt
     */
    public void setRegisterCnt(Integer registerCnt) {
        this.registerCnt = registerCnt;
    }

    /**
     * @return realname_cnt
     */
    public Integer getRealnameCnt() {
        return realnameCnt;
    }

    /**
     * @param realnameCnt
     */
    public void setRealnameCnt(Integer realnameCnt) {
        this.realnameCnt = realnameCnt;
    }

    /**
     * @return zfb_cnt
     */
    public Integer getZfbCnt() {
        return zfbCnt;
    }

    /**
     * @param zfbCnt
     */
    public void setZfbCnt(Integer zfbCnt) {
        this.zfbCnt = zfbCnt;
    }

    /**
     * @return mobile_cnt
     */
    public Integer getMobileCnt() {
        return mobileCnt;
    }

    /**
     * @param mobileCnt
     */
    public void setMobileCnt(Integer mobileCnt) {
        this.mobileCnt = mobileCnt;
    }

    /**
     * @return order_cnt
     */
    public Integer getOrderCnt() {
        return orderCnt;
    }

    /**
     * @param orderCnt
     */
    public void setOrderCnt(Integer orderCnt) {
        this.orderCnt = orderCnt;
    }

    /**
     * @return first_cnt
     */
    public Integer getFirstCnt() {
        return firstCnt;
    }

    /**
     * @param firstCnt
     */
    public void setFirstCnt(Integer firstCnt) {
        this.firstCnt = firstCnt;
    }

    /**
     * @return second_cnt
     */
    public Integer getSecondCnt() {
        return secondCnt;
    }

    /**
     * @param secondCnt
     */
    public void setSecondCnt(Integer secondCnt) {
        this.secondCnt = secondCnt;
    }

    /**
     * @return old_cnt
     */
    public Integer getOldCnt() {
        return oldCnt;
    }

    /**
     * @param oldCnt
     */
    public void setOldCnt(Integer oldCnt) {
        this.oldCnt = oldCnt;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}