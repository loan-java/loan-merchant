package com.mod.loan.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "report_partner_effect")
public class ReportPartnerEffect {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 注册日期
     */
    @Column(name = "day_key")
    private Date dayKey;

    /**
     * 注册渠道，来源
     */
    @Column(name = "user_origin")
    private String userOrigin;

    /**
     * 注册人数
     */
    @Column(name = "reg_cnt")
    private Integer regCnt;

    /**
     * 实名人数
     */
    @Column(name = "real_name_cnt")
    private Integer realNameCnt;

    /**
     * 提单人数
     */
    @Column(name = "submit_order_cnt")
    private Integer submitOrderCnt;

    /**
     * 首借人数
     */
    @Column(name = "first_submit_cnt")
    private Integer firstSubmitCnt;

    /**
     * 首借金额
     */
    @Column(name = "first_submit_amount")
    private BigDecimal firstSubmitAmount;

    /**
     * 插入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 商户
     */
    @Column(name = "merchant")
    private String merchant;

    /**
     * 注册的登录数量
     */
    @Column(name = "login_cnt")
    private Integer loginCnt;

    /**
     * 获取注册的登录数量
     *
     * @return loginCnt
     */
    public Integer getLoginCnt() {
        return loginCnt;
    }

    /**
     * 设置注册的登录数量
     *
     * @param loginCnt
     */
    public void setLoginCnt(Integer loginCnt) {
        this.loginCnt = loginCnt;
    }

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
     * 获取注册日期
     *
     * @return day_key - 注册日期
     */
    public Date getDayKey() {
        return dayKey;
    }

    /**
     * 设置注册日期
     *
     * @param dayKey 注册日期
     */
    public void setDayKey(Date dayKey) {
        this.dayKey = dayKey;
    }

    /**
     * 获取注册渠道，来源
     *
     * @return user_origin - 注册渠道，来源
     */
    public String getUserOrigin() {
        return userOrigin;
    }

    /**
     * 设置注册渠道，来源
     *
     * @param userOrigin 注册渠道，来源
     */
    public void setUserOrigin(String userOrigin) {
        this.userOrigin = userOrigin == null ? null : userOrigin.trim();
    }

    /**
     * 获取注册人数
     *
     * @return reg_cnt - 注册人数
     */
    public Integer getRegCnt() {
        return regCnt;
    }

    /**
     * 设置注册人数
     *
     * @param regCnt 注册人数
     */
    public void setRegCnt(Integer regCnt) {
        this.regCnt = regCnt;
    }

    /**
     * 获取实名人数
     *
     * @return real_name_cnt - 实名人数
     */
    public Integer getRealNameCnt() {
        return realNameCnt;
    }

    /**
     * 设置实名人数
     *
     * @param realNameCnt 实名人数
     */
    public void setRealNameCnt(Integer realNameCnt) {
        this.realNameCnt = realNameCnt;
    }

    /**
     * 获取提单人数
     *
     * @return submit_order_cnt - 提单人数
     */
    public Integer getSubmitOrderCnt() {
        return submitOrderCnt;
    }

    /**
     * 设置提单人数
     *
     * @param submitOrderCnt 提单人数
     */
    public void setSubmitOrderCnt(Integer submitOrderCnt) {
        this.submitOrderCnt = submitOrderCnt;
    }

    /**
     * 获取首借人数
     *
     * @return first_submit_cnt - 首借人数
     */
    public Integer getFirstSubmitCnt() {
        return firstSubmitCnt;
    }

    /**
     * 设置首借人数
     *
     * @param firstSubmitCnt 首借人数
     */
    public void setFirstSubmitCnt(Integer firstSubmitCnt) {
        this.firstSubmitCnt = firstSubmitCnt;
    }

    /**
     * 获取首借金额
     *
     * @return first_submit_amount - 首借金额
     */
    public BigDecimal getFirstSubmitAmount() {
        return firstSubmitAmount;
    }

    /**
     * 设置首借金额
     *
     * @param firstSubmitAmount 首借金额
     */
    public void setFirstSubmitAmount(BigDecimal firstSubmitAmount) {
        this.firstSubmitAmount = firstSubmitAmount;
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

    /**
     * 获取商户
     *
     * @return merchant - 商户
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * 设置商户
     *
     * @param merchant 商户
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant == null ? null : merchant.trim();
    }
}