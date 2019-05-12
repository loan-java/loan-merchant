package com.mod.loan.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_order")
public class Order {
    @Id
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 用户Id
     */
    private Long uid;

    /**
     * 借款期限
     */
    @Column(name = "borrow_day")
    private Integer borrowDay;

    /**
     * 借款金额
     */
    @Column(name = "borrow_money")
    private BigDecimal borrowMoney;

    /**
     * 实际到账金额=借款金额-综合费
     */
    @Column(name = "actual_money")
    private BigDecimal actualMoney;
    /**
     * 综合费率
     */
    @Column(name = "total_rate")
    private BigDecimal totalRate;

    /**
     * 综合费=借款金额*综合费率
     */
    @Column(name = "total_fee")
    private BigDecimal totalFee;
    /**
     * 利率
     */
    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    /**
     * 利息费
     */
    @Column(name = "interest_fee")
    private BigDecimal interestFee;
    /**
     * 逾期费率
     */
    @Column(name = "overdue_rate")
    private BigDecimal overdueRate;

    /**
     * 逾期天数
     */
    @Column(name = "overdue_day")
    private Integer overdueDay;

    /**
     * 逾期费用=逾期费用+借款金额*逾期费率
     */
    @Column(name = "overdue_fee")
    private BigDecimal overdueFee;

    /**
     * 应还金额=利息+借款金额+逾期费用-还款减免金额
     */
    @Column(name = "should_repay")
    private BigDecimal shouldRepay;

    /**
     * 已还金额
     */
    @Column(name = "had_repay")
    private BigDecimal hadRepay;

    /**
     * 还款减免金额
     */
    @Column(name = "reduce_money")
    private BigDecimal reduceMoney;

    /**
     * 审核中10+：11-新建;12-等待复审;
     * 放款中20+；21-待放款;22-放款中;23-放款失败(可以重新放款);
     * 还款中30+；31-已放款/还款中;32-还款确认中;33-逾期;34-坏账
     * 已结清中40+；41-已结清;42-逾期还款;
     * 订单结束50+；51-自动审核失败 ;52-复审失败;53-取消;
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 到账时间
     */
    @Column(name = "arrive_time")
    private Date arriveTime;

    /**
     * 应全部结清时间=到账时间+借款期限-1
     */
    @Column(name = "repay_time")
    private Date repayTime;

    /**
     * 实际全部结清时间
     */
    @Column(name = "real_repay_time")
    private Date realRepayTime;

    /**
     * 版本号，防止并发操作
     */
    @Column(name = "order_version")
    private Integer orderVersion;

    /**
     * 商户别名
     */
    private String merchant;

    /**
     * 催收人id
     */
    @Column(name = "follow_user_id")
    private Long followUserId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_type")
    private Integer userType;

    /**
     * 催收标签
     */
    @Column(name = "recycle_type")
    private Integer recycleType;


    /**
     * 第三方支付类型：baofoo   kuaiqian
     */
    @Column(name = "payment_type")
    private String paymentType;

    public String getPaymentType() { return paymentType; }

    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

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
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取用户Id
     *
     * @return uid - 用户Id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户Id
     *
     * @param uid 用户Id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取借款期限
     *
     * @return borrow_day - 借款期限
     */
    public Integer getBorrowDay() {
        return borrowDay;
    }

    /**
     * 设置借款期限
     *
     * @param borrowDay 借款期限
     */
    public void setBorrowDay(Integer borrowDay) {
        this.borrowDay = borrowDay;
    }

    /**
     * 获取借款金额
     *
     * @return borrow_money - 借款金额
     */
    public BigDecimal getBorrowMoney() {
        return borrowMoney;
    }

    /**
     * 设置借款金额
     *
     * @param borrowMoney 借款金额
     */
    public void setBorrowMoney(BigDecimal borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    /**
     * 获取实际到账金额=借款金额-综合费
     *
     * @return actual_money - 实际到账金额=借款金额-综合费
     */
    public BigDecimal getActualMoney() {
        return actualMoney;
    }

    /**
     * 设置实际到账金额=借款金额-综合费
     *
     * @param actualMoney 实际到账金额=借款金额-综合费
     */
    public void setActualMoney(BigDecimal actualMoney) {
        this.actualMoney = actualMoney;
    }

    /**
     * 获取综合费率
     *
     * @return total_rate - 综合费率
     */
    public BigDecimal getTotalRate() {
        return totalRate;
    }

    /**
     * 设置综合费率
     *
     * @param totalRate 综合费率
     */
    public void setTotalRate(BigDecimal totalRate) {
        this.totalRate = totalRate;
    }

    /**
     * 获取综合费=借款金额*综合费率
     *
     * @return total_fee - 综合费=借款金额*综合费率
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    /**
     * 设置综合费=借款金额*综合费率
     *
     * @param totalFee 综合费=借款金额*综合费率
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 获取逾期费率
     *
     * @return overdue_rate - 逾期费率
     */
    public BigDecimal getOverdueRate() {
        return overdueRate;
    }

    /**
     * 设置逾期费率
     *
     * @param overdueRate 逾期费率
     */
    public void setOverdueRate(BigDecimal overdueRate) {
        this.overdueRate = overdueRate;
    }

    /**
     * 获取逾期天数
     *
     * @return overdue_day - 逾期天数
     */
    public Integer getOverdueDay() {
        return overdueDay;
    }

    /**
     * 设置逾期天数
     *
     * @param overdueDay 逾期天数
     */
    public void setOverdueDay(Integer overdueDay) {
        this.overdueDay = overdueDay;
    }

    /**
     * 获取逾期费用=逾期费用+借款金额*逾期费率
     *
     * @return overdue_fee - 逾期费用=逾期费用+借款金额*逾期费率
     */
    public BigDecimal getOverdueFee() {
        return overdueFee;
    }

    /**
     * 设置逾期费用=逾期费用+借款金额*逾期费率
     *
     * @param overdueFee 逾期费用=逾期费用+借款金额*逾期费率
     */
    public void setOverdueFee(BigDecimal overdueFee) {
        this.overdueFee = overdueFee;
    }

    /**
     * 获取应还金额=借款金额+逾期费用-还款减免金额
     *
     * @return should_repay - 应还金额=借款金额+逾期费用-还款减免金额
     */
    public BigDecimal getShouldRepay() {
        return shouldRepay;
    }

    /**
     * 设置应还金额=借款金额+逾期费用-还款减免金额
     *
     * @param shouldRepay 应还金额=借款金额+逾期费用-还款减免金额
     */
    public void setShouldRepay(BigDecimal shouldRepay) {
        this.shouldRepay = shouldRepay;
    }

    /**
     * 获取已还金额
     *
     * @return had_repay - 已还金额
     */
    public BigDecimal getHadRepay() {
        return hadRepay;
    }

    /**
     * 设置已还金额
     *
     * @param hadRepay 已还金额
     */
    public void setHadRepay(BigDecimal hadRepay) {
        this.hadRepay = hadRepay;
    }

    /**
     * 获取还款减免金额
     *
     * @return reduce_money - 还款减免金额
     */
    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    /**
     * 设置还款减免金额
     *
     * @param reduceMoney 还款减免金额
     */
    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    /**
     * 审核中10+：11-新建;12-等待复审;
     * 放款中20+；21-待放款;22-放款中;23-放款失败(可以重新放款);
     * 还款中30+；31-已放款/还款中;32-还款确认中;33-逾期;34-坏账
     * 已结清中40+；41-已结清;42-逾期还款;
     * 订单结束50+；51-自动审核失败 ;52-复审失败;53-取消;
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 审核中10+：11-新建;12-等待复审;
     * 放款中20+；21-待放款;22-放款中;23-放款失败(可以重新放款);
     * 还款中30+；31-已放款/还款中;32-还款确认中;33-逾期;34-坏账
     * 已结清中40+；41-已结清;42-逾期还款;
     * 订单结束50+；51-自动审核失败 ;52-复审失败;53-取消;
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取审核时间
     *
     * @return audit_time - 审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 设置审核时间
     *
     * @param auditTime 审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 获取到账时间
     *
     * @return arrive_time - 到账时间
     */
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * 设置到账时间
     *
     * @param arriveTime 到账时间
     */
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * 获取应全部结清时间=到账时间+借款期限-1
     *
     * @return repay_time - 应全部结清时间=到账时间+借款期限-1
     */
    public Date getRepayTime() {
        return repayTime;
    }

    /**
     * 设置应全部结清时间=到账时间+借款期限-1
     *
     * @param repayTime 应全部结清时间=到账时间+借款期限-1
     */
    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    /**
     * 获取实际全部结清时间
     *
     * @return real_repay_time - 实际全部结清时间
     */
    public Date getRealRepayTime() {
        return realRepayTime;
    }

    /**
     * 设置实际全部结清时间
     *
     * @param realRepayTime 实际全部结清时间
     */
    public void setRealRepayTime(Date realRepayTime) {
        this.realRepayTime = realRepayTime;
    }

    /**
     * 获取版本号，防止并发操作
     *
     * @return order_version - 版本号，防止并发操作
     */
    public Integer getOrderVersion() {
        return orderVersion;
    }

    /**
     * 设置版本号，防止并发操作
     *
     * @param orderVersion 版本号，防止并发操作
     */
    public void setOrderVersion(Integer orderVersion) {
        this.orderVersion = orderVersion;
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
     * 获取催收人id
     *
     * @return follow_user_id - 催收人id
     */
    public Long getFollowUserId() {
        return followUserId;
    }

    /**
     * 设置催收人id
     *
     * @param followUserId 催收人id
     */
    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestFee() {
        return interestFee;
    }

    public void setInterestFee(BigDecimal interestFee) {
        this.interestFee = interestFee;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 催收标签
     *
     * @return
     */
    public Integer getRecycleType() {
        return recycleType;
    }

    /**
     * 催收标签0-其他1-承诺还款2-谈判-高负债
     * 3-谈判-还款意愿低4-无人接听5-关机6-无法接通
     * 7-设置拒接8-通话中9-停机10-跳票11-家人代偿
     * 12-线下已还款13-失联（本人通讯录无效）
     * 14-拒绝还款15-部分还款16-谈判中17-第三方转告18-停止催收
     *
     * @param recycleType
     */
    public void setRecycleType(Integer recycleType) {
        this.recycleType = recycleType;
    }
}