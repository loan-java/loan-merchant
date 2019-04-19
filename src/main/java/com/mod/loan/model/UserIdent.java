package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user_ident")
public class UserIdent {
    /**
     * 用户id
     */
    @Id
    private Long uid;

    /**
     * 是否实名认证,,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    @Column(name = "real_name")
    private Integer realName;

    /**
     * 实名时间
     */
    @Column(name = "real_name_time")
    private Date realNameTime;

    /**
     * 是否个人信息认证（通讯录数据获取）,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    @Column(name = "user_details")
    private Integer userDetails;

    /**
     * 个人信息认证时间
     */
    @Column(name = "user_details_time")
    private Date userDetailsTime;

    /**
     * 绑卡：,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    private Integer bindbank;

    /**
     * 绑卡时间
     */
    @Column(name = "bindbank_time")
    private Date bindbankTime;

    /**
     * 是否运营商手机认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    private Integer mobile;

    /**
     * 手机认证时间
     */
    @Column(name = "mobile_time")
    private Date mobileTime;

    /**
     * 是否人脸识别认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    private Integer liveness;

    /**
     * 人脸识别认证时间
     */
    @Column(name = "liveness_time")
    private Date livenessTime;

    /**
     * 是否支付宝认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    private Integer alipay;

    /**
     * 支付宝认证时间
     */
    @Column(name = "alipay_time")
    private Date alipayTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取是否实名认证,,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @return real_name - 是否实名认证,,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public Integer getRealName() {
        return realName;
    }

    /**
     * 设置是否实名认证,,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @param realName 是否实名认证,,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public void setRealName(Integer realName) {
        this.realName = realName;
    }

    /**
     * 获取实名时间
     *
     * @return real_name_time - 实名时间
     */
    public Date getRealNameTime() {
        return realNameTime;
    }

    /**
     * 设置实名时间
     *
     * @param realNameTime 实名时间
     */
    public void setRealNameTime(Date realNameTime) {
        this.realNameTime = realNameTime;
    }

    /**
     * 获取是否个人信息认证（通讯录数据获取）,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @return user_details - 是否个人信息认证（通讯录数据获取）,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public Integer getUserDetails() {
        return userDetails;
    }

    /**
     * 设置是否个人信息认证（通讯录数据获取）,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @param userDetails 是否个人信息认证（通讯录数据获取）,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public void setUserDetails(Integer userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * 获取个人信息认证时间
     *
     * @return user_details_time - 个人信息认证时间
     */
    public Date getUserDetailsTime() {
        return userDetailsTime;
    }

    /**
     * 设置个人信息认证时间
     *
     * @param userDetailsTime 个人信息认证时间
     */
    public void setUserDetailsTime(Date userDetailsTime) {
        this.userDetailsTime = userDetailsTime;
    }

    /**
     * 获取绑卡：,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @return bindbank - 绑卡：,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public Integer getBindbank() {
        return bindbank;
    }

    /**
     * 设置绑卡：,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @param bindbank 绑卡：,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public void setBindbank(Integer bindbank) {
        this.bindbank = bindbank;
    }

    /**
     * 获取绑卡时间
     *
     * @return bindbank_time - 绑卡时间
     */
    public Date getBindbankTime() {
        return bindbankTime;
    }

    /**
     * 设置绑卡时间
     *
     * @param bindbankTime 绑卡时间
     */
    public void setBindbankTime(Date bindbankTime) {
        this.bindbankTime = bindbankTime;
    }

    /**
     * 获取是否运营商手机认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @return mobile - 是否运营商手机认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public Integer getMobile() {
        return mobile;
    }

    /**
     * 设置是否运营商手机认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @param mobile 是否运营商手机认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取手机认证时间
     *
     * @return mobile_time - 手机认证时间
     */
    public Date getMobileTime() {
        return mobileTime;
    }

    /**
     * 设置手机认证时间
     *
     * @param mobileTime 手机认证时间
     */
    public void setMobileTime(Date mobileTime) {
        this.mobileTime = mobileTime;
    }

    /**
     * 获取是否人脸识别认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @return liveness - 是否人脸识别认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public Integer getLiveness() {
        return liveness;
    }

    /**
     * 设置是否人脸识别认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @param liveness 是否人脸识别认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public void setLiveness(Integer liveness) {
        this.liveness = liveness;
    }

    /**
     * 获取人脸识别认证时间
     *
     * @return liveness_time - 人脸识别认证时间
     */
    public Date getLivenessTime() {
        return livenessTime;
    }

    /**
     * 设置人脸识别认证时间
     *
     * @param livenessTime 人脸识别认证时间
     */
    public void setLivenessTime(Date livenessTime) {
        this.livenessTime = livenessTime;
    }

    /**
     * 获取是否支付宝认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @return alipay - 是否支付宝认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public Integer getAlipay() {
        return alipay;
    }

    /**
     * 设置是否支付宝认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     *
     * @param alipay 是否支付宝认证,0，未认证，1认证中,2认证成功 3认证失败,4已失效 
     */
    public void setAlipay(Integer alipay) {
        this.alipay = alipay;
    }

    /**
     * 获取支付宝认证时间
     *
     * @return alipay_time - 支付宝认证时间
     */
    public Date getAlipayTime() {
        return alipayTime;
    }

    /**
     * 设置支付宝认证时间
     *
     * @param alipayTime 支付宝认证时间
     */
    public void setAlipayTime(Date alipayTime) {
        this.alipayTime = alipayTime;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}