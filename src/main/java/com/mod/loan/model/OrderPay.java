package com.mod.loan.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_order_pay")
public class OrderPay {
    /**
     * 第三方放款流水号
     */
    @Id
    @Column(name = "pay_no")
    private String payNo;

    private Long uid;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 0-初始；1:受理成功；2:受理失败； 3:放款成功；4:放款失败
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 支付金额
     */
    @Column(name = "pay_money")
    private BigDecimal payMoney;

    /**
     * 到帐银行
     */
    private String bank;

    /**
     * 银行卡号
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
     * 支付类型
     */
    @Column(name = "pay_type")
    private Integer payType;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 获取第三方放款流水号
     *
     * @return pay_no - 第三方放款流水号
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     * 设置第三方放款流水号
     *
     * @param payNo 第三方放款流水号
     */
    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
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
     * 获取0-初始；1:受理成功；2:受理失败； 3:放款成功；4:放款失败
     *
     * @return pay_status - 0-初始；1:受理成功；2:受理失败； 3:放款成功；4:放款失败
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置0-初始；1:受理成功；2:受理失败； 3:放款成功；4:放款失败
     *
     * @param payStatus 0-初始；1:受理成功；2:受理失败； 3:放款成功；4:放款失败
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取支付金额
     *
     * @return pay_money - 支付金额
     */
    public BigDecimal getPayMoney() {
        return payMoney;
    }

    /**
     * 设置支付金额
     *
     * @param payMoney 支付金额
     */
    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * 获取到帐银行
     *
     * @return bank - 到帐银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置到帐银行
     *
     * @param bank 到帐银行
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     * 获取银行卡号
     *
     * @return bank_no - 银行卡号
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * 设置银行卡号
     *
     * @param bankNo 银行卡号
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