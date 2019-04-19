package com.mod.loan.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "report_order_loan")
public class ReportOrderLoan {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 放款日期
     */
    @Column(name = "day_key")
    private Date dayKey;

    /**
     * 商户别名
     */
    private String merchant;

    /**
     * 放款笔数
     */
    @Column(name = "arrive_cnt")
    private Integer arriveCnt;

    /**
     * 放款金额
     */
    @Column(name = "arrive_amount")
    private BigDecimal arriveAmount;

    /**
     * 首借人数
     */
    @Column(name = "first_cnt")
    private Integer firstCnt;

    /**
     * 首借金额
     */
    @Column(name = "first_amount")
    private BigDecimal firstAmount;

    /**
     * 次新人数
     */
    @Column(name = "second_cnt")
    private Integer secondCnt;

    /**
     * 次新金额
     */
    @Column(name = "second_amount")
    private BigDecimal secondAmount;

    /**
     * 续借人数
     */
    @Column(name = "old_cnt")
    private Integer oldCnt;

    /**
     * 续借金额
     */
    @Column(name = "old_amount")
    private BigDecimal oldAmount;

    /**
     * 综合费用
     */
    @Column(name = "total_fee")
    private BigDecimal totalFee;

    /**
     * 插入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取放款日期
     *
     * @return day_key - 放款日期
     */
    public Date getDayKey() {
        return dayKey;
    }

    /**
     * 设置放款日期
     *
     * @param dayKey 放款日期
     */
    public void setDayKey(Date dayKey) {
        this.dayKey = dayKey;
    }

    /**
     * 获取商户别名
     *
     * @return merchant - 商户别名
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * 设置商户别名
     *
     * @param merchant 商户别名
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant == null ? null : merchant.trim();
    }

    /**
     * 获取放款笔数
     *
     * @return arrive_cnt - 放款笔数
     */
    public Integer getArriveCnt() {
        return arriveCnt;
    }

    /**
     * 设置放款笔数
     *
     * @param arriveCnt 放款笔数
     */
    public void setArriveCnt(Integer arriveCnt) {
        this.arriveCnt = arriveCnt;
    }

    /**
     * 获取放款金额
     *
     * @return arrive_amount - 放款金额
     */
    public BigDecimal getArriveAmount() {
        return arriveAmount;
    }

    /**
     * 设置放款金额
     *
     * @param arriveAmount 放款金额
     */
    public void setArriveAmount(BigDecimal arriveAmount) {
        this.arriveAmount = arriveAmount;
    }

    /**
     * 获取首借人数
     *
     * @return first_cnt - 首借人数
     */
    public Integer getFirstCnt() {
        return firstCnt;
    }

    /**
     * 设置首借人数
     *
     * @param firstCnt 首借人数
     */
    public void setFirstCnt(Integer firstCnt) {
        this.firstCnt = firstCnt;
    }

    /**
     * 获取首借金额
     *
     * @return first_amount - 首借金额
     */
    public BigDecimal getFirstAmount() {
        return firstAmount;
    }

    /**
     * 设置首借金额
     *
     * @param firstAmount 首借金额
     */
    public void setFirstAmount(BigDecimal firstAmount) {
        this.firstAmount = firstAmount;
    }

    /**
     * 获取次新人数
     *
     * @return second_cnt - 次新人数
     */
    public Integer getSecondCnt() {
        return secondCnt;
    }

    /**
     * 设置次新人数
     *
     * @param secondCnt 次新人数
     */
    public void setSecondCnt(Integer secondCnt) {
        this.secondCnt = secondCnt;
    }

    /**
     * 获取次新金额
     *
     * @return second_amount - 次新金额
     */
    public BigDecimal getSecondAmount() {
        return secondAmount;
    }

    /**
     * 设置次新金额
     *
     * @param secondAmount 次新金额
     */
    public void setSecondAmount(BigDecimal secondAmount) {
        this.secondAmount = secondAmount;
    }

    /**
     * 获取续借人数
     *
     * @return old_cnt - 续借人数
     */
    public Integer getOldCnt() {
        return oldCnt;
    }

    /**
     * 设置续借人数
     *
     * @param oldCnt 续借人数
     */
    public void setOldCnt(Integer oldCnt) {
        this.oldCnt = oldCnt;
    }

    /**
     * 获取续借金额
     *
     * @return old_amount - 续借金额
     */
    public BigDecimal getOldAmount() {
        return oldAmount;
    }

    /**
     * 设置续借金额
     *
     * @param oldAmount 续借金额
     */
    public void setOldAmount(BigDecimal oldAmount) {
        this.oldAmount = oldAmount;
    }

    /**
     * 获取综合费用
     *
     * @return total_fee - 综合费用
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    /**
     * 设置综合费用
     *
     * @param totalFee 综合费用
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 获取插入时间
     *
     * @return create_time - 插入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置插入时间
     *
     * @param createTime 插入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}