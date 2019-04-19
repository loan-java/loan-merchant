package com.mod.loan.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "report_order_repay")
public class ReportOrderRepay {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 应还日期
     */
    @Column(name = "day_key")
    private Date dayKey;

    /**
     * 商户别名
     */
    private String merchant;

    /**
     * 应还笔数
     */
    @Column(name = "should_repay_cnt")
    private Integer shouldRepayCnt;

    /**
     * 提前还笔数
     */
    @Column(name = "early_repay_cnt")
    private Integer earlyRepayCnt;

    /**
     * 正常还笔数
     */
    @Column(name = "normal_repay_cnt")
    private Integer normalRepayCnt;

    /**
     * 逾期还笔数
     */
    @Column(name = "overdue_repay_cnt")
    private Integer overdueRepayCnt;

    /**
     * 逾期中笔数
     */
    @Column(name = "overdue_cnt")
    private Integer overdueCnt;

    /**
     * 坏账笔数
     */
    @Column(name = "bad_cnt")
    private Integer badCnt;

    /**
     * 当天逾期还笔数
     */
    @Column(name = "overdue1_repay_cnt")
    private Integer overdue1RepayCnt;
    
    /**
     * 3天内逾期还笔数
     */
    @Column(name = "overdue3_repay_cnt")
    private Integer overdue3RepayCnt;

    /**
     * 7天内逾期还笔数
     */
    @Column(name = "overdue7_repay_cnt")
    private Integer overdue7RepayCnt;

    /**
     * 15天内逾期还笔数
     */
    @Column(name = "overdue15_repay_cnt")
    private Integer overdue15RepayCnt;

    /**
     * 应还金额
     */
    @Column(name = "repay_amount")
    private BigDecimal repayAmount;

    /**
     * 实际还款金额
     */
    @Column(name = "real_repay_amount")
    private BigDecimal realRepayAmount;

    /**
     * 实际放款金额，成本
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 逾期费
     */
    @Column(name = "overdue_fee")
    private BigDecimal overdueFee;

    /**
     * 减免金额
     */
    @Column(name = "reduce_money")
    private BigDecimal reduceMoney;

    /**
     * 逾期待还金额
     */
    @Column(name = "overdue_repay_amount")
    private BigDecimal overdueRepayAmount;
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
     * 获取应还日期
     *
     * @return day_key - 应还日期
     */
    public Date getDayKey() {
        return dayKey;
    }

    /**
     * 设置应还日期
     *
     * @param dayKey 应还日期
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
     * 获取应还笔数
     *
     * @return should_repay_cnt - 应还笔数
     */
    public Integer getShouldRepayCnt() {
        return shouldRepayCnt;
    }

    /**
     * 设置应还笔数
     *
     * @param shouldRepayCnt 应还笔数
     */
    public void setShouldRepayCnt(Integer shouldRepayCnt) {
        this.shouldRepayCnt = shouldRepayCnt;
    }

    /**
     * 获取提前还笔数
     *
     * @return early_repay_cnt - 提前还笔数
     */
    public Integer getEarlyRepayCnt() {
        return earlyRepayCnt;
    }

    /**
     * 设置提前还笔数
     *
     * @param earlyRepayCnt 提前还笔数
     */
    public void setEarlyRepayCnt(Integer earlyRepayCnt) {
        this.earlyRepayCnt = earlyRepayCnt;
    }

    /**
     * 获取正常还笔数
     *
     * @return normal_repay_cnt - 正常还笔数
     */
    public Integer getNormalRepayCnt() {
        return normalRepayCnt;
    }

    /**
     * 设置正常还笔数
     *
     * @param normalRepayCnt 正常还笔数
     */
    public void setNormalRepayCnt(Integer normalRepayCnt) {
        this.normalRepayCnt = normalRepayCnt;
    }

    /**
     * 获取逾期还笔数
     *
     * @return overdue_repay_cnt - 逾期还笔数
     */
    public Integer getOverdueRepayCnt() {
        return overdueRepayCnt;
    }

    /**
     * 设置逾期还笔数
     *
     * @param overdueRepayCnt 逾期还笔数
     */
    public void setOverdueRepayCnt(Integer overdueRepayCnt) {
        this.overdueRepayCnt = overdueRepayCnt;
    }

    /**
     * 获取逾期中笔数
     *
     * @return overdue_cnt - 逾期中笔数
     */
    public Integer getOverdueCnt() {
        return overdueCnt;
    }

    /**
     * 设置逾期中笔数
     *
     * @param overdueCnt 逾期中笔数
     */
    public void setOverdueCnt(Integer overdueCnt) {
        this.overdueCnt = overdueCnt;
    }

    /**
     * 获取坏账笔数
     *
     * @return bad_cnt - 坏账笔数
     */
    public Integer getBadCnt() {
        return badCnt;
    }

    /**
     * 设置坏账笔数
     *
     * @param badCnt 坏账笔数
     */
    public void setBadCnt(Integer badCnt) {
        this.badCnt = badCnt;
    }

    /**
     * 获取3天内逾期还笔数
     *
     * @return overdue3_repay_cnt - 3天内逾期还笔数
     */
    public Integer getOverdue3RepayCnt() {
        return overdue3RepayCnt;
    }

    /**
     * 设置3天内逾期还笔数
     *
     * @param overdue3RepayCnt 3天内逾期还笔数
     */
    public void setOverdue3RepayCnt(Integer overdue3RepayCnt) {
        this.overdue3RepayCnt = overdue3RepayCnt;
    }

    /**
     * 获取7天内逾期还笔数
     *
     * @return overdue7_repay_cnt - 7天内逾期还笔数
     */
    public Integer getOverdue7RepayCnt() {
        return overdue7RepayCnt;
    }

    /**
     * 设置7天内逾期还笔数
     *
     * @param overdue7RepayCnt 7天内逾期还笔数
     */
    public void setOverdue7RepayCnt(Integer overdue7RepayCnt) {
        this.overdue7RepayCnt = overdue7RepayCnt;
    }

    /**
     * 获取15天内逾期还笔数
     *
     * @return overdue15_repay_cnt - 15天内逾期还笔数
     */
    public Integer getOverdue15RepayCnt() {
        return overdue15RepayCnt;
    }

    /**
     * 设置15天内逾期还笔数
     *
     * @param overdue15RepayCnt 15天内逾期还笔数
     */
    public void setOverdue15RepayCnt(Integer overdue15RepayCnt) {
        this.overdue15RepayCnt = overdue15RepayCnt;
    }

    /**
     * 获取应还还款金额
     *
     * @return repay_amount - 应还还款金额
     */
    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    /**
     * 设置应还还款金额
     *
     * @param repayAmount 应还还款金额
     */
    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    /**
     * 获取实际还款金额
     *
     * @return real_repay_amount - 实际还款金额
     */
    public BigDecimal getRealRepayAmount() {
        return realRepayAmount;
    }

    /**
     * 设置实际还款金额
     *
     * @param realRepayAmount 实际还款金额
     */
    public void setRealRepayAmount(BigDecimal realRepayAmount) {
        this.realRepayAmount = realRepayAmount;
    }

    /**
     * 获取实际放款金额，成本
     *
     * @return pay_amount - 实际放款金额，成本
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置实际放款金额，成本
     *
     * @param payAmount 实际放款金额，成本
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取逾期费
     *
     * @return overdue_fee - 逾期费
     */
    public BigDecimal getOverdueFee() {
        return overdueFee;
    }

    /**
     * 设置逾期费
     *
     * @param overdueFee 逾期费
     */
    public void setOverdueFee(BigDecimal overdueFee) {
        this.overdueFee = overdueFee;
    }

    /**
     * 获取减免金额
     *
     * @return reduce_money - 减免金额
     */
    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    /**
     * 设置减免金额
     *
     * @param reduceMoney 减免金额
     */
    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
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

	public Integer getOverdue1RepayCnt() {
		return overdue1RepayCnt;
	}

	public void setOverdue1RepayCnt(Integer overdue1RepayCnt) {
		this.overdue1RepayCnt = overdue1RepayCnt;
	}

	public BigDecimal getOverdueRepayAmount() {
		return overdueRepayAmount;
	}

	public void setOverdueRepayAmount(BigDecimal overdueRepayAmount) {
		this.overdueRepayAmount = overdueRepayAmount;
	}
    
    
}