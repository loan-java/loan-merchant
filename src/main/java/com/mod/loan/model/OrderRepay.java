package com.mod.loan.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_order_repay")
public class OrderRepay {
    /**
     * 还款流水号
     */
    @Id
    @Column(name = "repay_no")
    private String repayNo;

    private Long uid;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 还款方式1-银行卡，2-支付宝，3-微信，4-线下转账
     */
    @Column(name = "repay_type")
    private Integer repayType;

    /**
     * 0-初始；1:受理成功；2:受理失败； 3:还款成功；4:还款失败
     */
    @Column(name = "repay_status")
    private Integer repayStatus;

    /**
     * 支付金额
     */
    @Column(name = "repay_money")
    private BigDecimal repayMoney;

    /**
     * 还款凭证
     */
    @Column(name = "repay_cert")
    private String repayCert;

    /**
     * 还款银行
     */
    private String bank;

    /**
     * 还款卡号
     */
    @Column(name = "bank_no")
    private String bankNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取还款流水号
     *
     * @return repay_no - 还款流水号
     */
    public String getRepayNo() {
        return repayNo;
    }

    /**
     * 设置还款流水号
     *
     * @param repayNo 还款流水号
     */
    public void setRepayNo(String repayNo) {
        this.repayNo = repayNo == null ? null : repayNo.trim();
    }

    /**
     * @return uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取还款方式1-银行卡，2-支付宝，3-微信，4-线下转账
     *
     * @return repay_type - 还款方式1-银行卡，2-支付宝，3-微信，4-线下转账
     */
    public Integer getRepayType() {
        return repayType;
    }

    /**
     * 设置还款方式1-银行卡，2-支付宝，3-微信，4-线下转账
     *
     * @param repayType 还款方式1-银行卡，2-支付宝，3-微信，4-线下转账
     */
    public void setRepayType(Integer repayType) {
        this.repayType = repayType;
    }

    /**
     * 获取0-初始；1:受理成功；2:受理失败； 3:还款成功；4:还款失败
     *
     * @return repay_status - 0-初始；1:受理成功；2:受理失败； 3:还款成功；4:还款失败
     */
    public Integer getRepayStatus() {
        return repayStatus;
    }

    /**
     * 设置0-初始；1:受理成功；2:受理失败； 3:还款成功；4:还款失败
     *
     * @param repayStatus 0-初始；1:受理成功；2:受理失败； 3:还款成功；4:还款失败
     */
    public void setRepayStatus(Integer repayStatus) {
        this.repayStatus = repayStatus;
    }

    /**
     * 获取支付金额
     *
     * @return repay_money - 支付金额
     */
    public BigDecimal getRepayMoney() {
        return repayMoney;
    }

    /**
     * 设置支付金额
     *
     * @param repayMoney 支付金额
     */
    public void setRepayMoney(BigDecimal repayMoney) {
        this.repayMoney = repayMoney;
    }

    /**
     * 获取还款凭证
     *
     * @return repay_cert - 还款凭证
     */
    public String getRepayCert() {
        return repayCert;
    }

    /**
     * 设置还款凭证
     *
     * @param repayCert 还款凭证
     */
    public void setRepayCert(String repayCert) {
        this.repayCert = repayCert == null ? null : repayCert.trim();
    }

    /**
     * 获取还款银行
     *
     * @return bank - 还款银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置还款银行
     *
     * @param bank 还款银行
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     * 获取还款卡号
     *
     * @return bank_no - 还款卡号
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * 设置还款卡号
     *
     * @param bankNo 还款卡号
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}