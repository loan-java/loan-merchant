package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user_bank")
public class UserBank {
    @Id
    private Long id;

    /**
     * 用户Id
     */
    private Long uid;

    /**
     * 银行代码
     */
    @Column(name = "card_code")
    private String cardCode;

    /**
     * 银行名称
     */
    @Column(name = "card_name")
    private String cardName;

    /**
     * 卡号
     */
    @Column(name = "card_no")
    private String cardNo;

    /**
     * 预留手机号
     */
    @Column(name = "card_phone")
    private String cardPhone;

    /**
     * 是否使用,0：禁用,1:使用
     */
    @Column(name = "card_status")
    private Integer cardStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 第三方标识
     */
    @Column(name = "foreign_id")
    private String foreignId;

    @Column(name = "bind_type")
    private Integer bindType;

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
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
     * 获取银行代码
     *
     * @return card_code - 银行代码
     */
    public String getCardCode() {
        return cardCode;
    }

    /**
     * 设置银行代码
     *
     * @param cardCode 银行代码
     */
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

    /**
     * 获取银行名称
     *
     * @return card_name - 银行名称
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * 设置银行名称
     *
     * @param cardName 银行名称
     */
    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    /**
     * 获取卡号
     *
     * @return card_no - 卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 设置卡号
     *
     * @param cardNo 卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * 获取预留手机号
     *
     * @return card_phone - 预留手机号
     */
    public String getCardPhone() {
        return cardPhone;
    }

    /**
     * 设置预留手机号
     *
     * @param cardPhone 预留手机号
     */
    public void setCardPhone(String cardPhone) {
        this.cardPhone = cardPhone == null ? null : cardPhone.trim();
    }

    /**
     * 获取是否使用,0：禁用,1:使用
     *
     * @return card_status - 是否使用,0：禁用,1:使用
     */
    public Integer getCardStatus() {
        return cardStatus;
    }

    /**
     * 设置是否使用,0：禁用,1:使用
     *
     * @param cardStatus 是否使用,0：禁用,1:使用
     */
    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取第三方标识
     *
     * @return foreign_id - 第三方标识
     */
    public String getForeignId() {
        return foreignId;
    }

    /**
     * 设置第三方标识
     *
     * @param foreignId 第三方标识
     */
    public void setForeignId(String foreignId) {
        this.foreignId = foreignId == null ? null : foreignId.trim();
    }
}