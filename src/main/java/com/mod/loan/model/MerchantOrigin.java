package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_merchant_origin")
public class MerchantOrigin {
    @Id
    private Long id;

    /**
     * 所属商户
     */
    private String merchant;

    /**
     * 渠道别名
     */
    @Column(name = "origin_name")
    private String originName;

    @Column(name = "create_time")
    private Date createTime;

    private String originNo;

    private Integer status;

    public String getOriginNo() {
        return originNo;
    }

    public void setOriginNo(String originNo) {
        this.originNo = originNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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
     * 获取所属商户
     *
     * @return merchant - 所属商户
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * 设置所属商户
     *
     * @param merchant 所属商户
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant == null ? null : merchant.trim();
    }

    /**
     * 获取渠道别名
     *
     * @return origin_name - 渠道别名
     */
    public String getOriginName() {
        return originName;
    }

    /**
     * 设置渠道别名
     *
     * @param originName 渠道别名
     */
    public void setOriginName(String originName) {
        this.originName = originName == null ? null : originName.trim();
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