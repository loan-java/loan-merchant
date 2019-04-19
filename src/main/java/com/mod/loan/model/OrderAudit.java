package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_order_audit")
public class OrderAudit {
    @Id
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 审核人id
     */
    @Column(name = "audit_id")
    private Long auditId;

    /**
     * 审核人姓名
     */
    @Column(name = "audit_name")
    private String auditName;

    /**
     * 审核失败原因
     */
    @Column(name = "fail_reason")
    private String failReason;

    /**
     * 0:审核通过；1:审核失败
     */
    private Integer status;

    /**
     * 审核时间
     */
    @Column(name = "crete_time")
    private Date creteTime;

    private String merchant;

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
     * 获取审核人id
     *
     * @return audit_id - 审核人id
     */
    public Long getAuditId() {
        return auditId;
    }

    /**
     * 设置审核人id
     *
     * @param auditId 审核人id
     */
    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    /**
     * 获取审核人姓名
     *
     * @return audit_name - 审核人姓名
     */
    public String getAuditName() {
        return auditName;
    }

    /**
     * 设置审核人姓名
     *
     * @param auditName 审核人姓名
     */
    public void setAuditName(String auditName) {
        this.auditName = auditName == null ? null : auditName.trim();
    }

    /**
     * 获取审核失败原因
     *
     * @return fail_reason - 审核失败原因
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * 设置审核失败原因
     *
     * @param failReason 审核失败原因
     */
    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    /**
     * 获取0:审核通过；1:审核失败
     *
     * @return status - 0:审核通过；1:审核失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0:审核通过；1:审核失败
     *
     * @param status 0:审核通过；1:审核失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取审核时间
     *
     * @return crete_time - 审核时间
     */
    public Date getCreteTime() {
        return creteTime;
    }

    /**
     * 设置审核时间
     *
     * @param creteTime 审核时间
     */
    public void setCreteTime(Date creteTime) {
        this.creteTime = creteTime;
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
}