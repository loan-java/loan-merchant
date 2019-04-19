package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user_info")
public class UserInfo {
    @Id
    private Long uid;

    /**
     * 学历
     */
    private String education;

    /**
     * 居住省份
     */
    @Column(name = "live_province")
    private String liveProvince;

    /**
     * 居住城市
     */
    @Column(name = "live_city")
    private String liveCity;

    /**
     * 居住区
     */
    @Column(name = "live_district")
    private String liveDistrict;

    /**
     * 具体地址
     */
    @Column(name = "live_address")
    private String liveAddress;

    /**
     * 居住时长
     */
    @Column(name = "live_time")
    private String liveTime;

    /**
     * 婚姻状况
     */
    @Column(name = "live_marry")
    private String liveMarry;

    /**
     * 职业
     */
    @Column(name = "work_type")
    private String workType;

    /**
     * 公司
     */
    @Column(name = "work_company")
    private String workCompany;

    /**
     * 工作地址
     */
    @Column(name = "work_address")
    private String workAddress;

    /**
     * 直系联系人关系。 父子
     */
    @Column(name = "direct_contact")
    private String directContact;

    /**
     * 直系联系人姓名。李三
     */
    @Column(name = "direct_contact_name")
    private String directContactName;

    /**
     * 直系联系人电话。1263637
     */
    @Column(name = "direct_contact_phone")
    private String directContactPhone;

    /**
     * 其他联系人关系。 朋友
     */
    @Column(name = "others_contact")
    private String othersContact;

    /**
     * 其他联系人姓名。李三
     */
    @Column(name = "others_contact_name")
    private String othersContactName;

    /**
     * 其他联系人电话。1263637
     */
    @Column(name = "others_contact_phone")
    private String othersContactPhone;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 获取居住省份
     *
     * @return live_province - 居住省份
     */
    public String getLiveProvince() {
        return liveProvince;
    }

    /**
     * 设置居住省份
     *
     * @param liveProvince 居住省份
     */
    public void setLiveProvince(String liveProvince) {
        this.liveProvince = liveProvince == null ? null : liveProvince.trim();
    }

    /**
     * 获取居住城市
     *
     * @return live_city - 居住城市
     */
    public String getLiveCity() {
        return liveCity;
    }

    /**
     * 设置居住城市
     *
     * @param liveCity 居住城市
     */
    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity == null ? null : liveCity.trim();
    }

    /**
     * 获取居住区
     *
     * @return live_district - 居住区
     */
    public String getLiveDistrict() {
        return liveDistrict;
    }

    /**
     * 设置居住区
     *
     * @param liveDistrict 居住区
     */
    public void setLiveDistrict(String liveDistrict) {
        this.liveDistrict = liveDistrict == null ? null : liveDistrict.trim();
    }

    /**
     * 获取具体地址
     *
     * @return live_address - 具体地址
     */
    public String getLiveAddress() {
        return liveAddress;
    }

    /**
     * 设置具体地址
     *
     * @param liveAddress 具体地址
     */
    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress == null ? null : liveAddress.trim();
    }

    /**
     * 获取居住时长
     *
     * @return live_time - 居住时长
     */
    public String getLiveTime() {
        return liveTime;
    }

    /**
     * 设置居住时长
     *
     * @param liveTime 居住时长
     */
    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime == null ? null : liveTime.trim();
    }

    /**
     * 获取婚姻状况
     *
     * @return live_marry - 婚姻状况
     */
    public String getLiveMarry() {
        return liveMarry;
    }

    /**
     * 设置婚姻状况
     *
     * @param liveMarry 婚姻状况
     */
    public void setLiveMarry(String liveMarry) {
        this.liveMarry = liveMarry == null ? null : liveMarry.trim();
    }

    /**
     * 获取职业
     *
     * @return work_type - 职业
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * 设置职业
     *
     * @param workType 职业
     */
    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    /**
     * 获取公司
     *
     * @return work_company - 公司
     */
    public String getWorkCompany() {
        return workCompany;
    }

    /**
     * 设置公司
     *
     * @param workCompany 公司
     */
    public void setWorkCompany(String workCompany) {
        this.workCompany = workCompany == null ? null : workCompany.trim();
    }

    /**
     * 获取工作地址
     *
     * @return work_address - 工作地址
     */
    public String getWorkAddress() {
        return workAddress;
    }

    /**
     * 设置工作地址
     *
     * @param workAddress 工作地址
     */
    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress == null ? null : workAddress.trim();
    }

    /**
     * 获取直系联系人关系。 父子
     *
     * @return direct_contact - 直系联系人关系。 父子
     */
    public String getDirectContact() {
        return directContact;
    }

    /**
     * 设置直系联系人关系。 父子
     *
     * @param directContact 直系联系人关系。 父子
     */
    public void setDirectContact(String directContact) {
        this.directContact = directContact == null ? null : directContact.trim();
    }

    /**
     * 获取直系联系人姓名。李三
     *
     * @return direct_contact_name - 直系联系人姓名。李三
     */
    public String getDirectContactName() {
        return directContactName;
    }

    /**
     * 设置直系联系人姓名。李三
     *
     * @param directContactName 直系联系人姓名。李三
     */
    public void setDirectContactName(String directContactName) {
        this.directContactName = directContactName == null ? null : directContactName.trim();
    }

    /**
     * 获取直系联系人电话。1263637
     *
     * @return direct_contact_phone - 直系联系人电话。1263637
     */
    public String getDirectContactPhone() {
        return directContactPhone;
    }

    /**
     * 设置直系联系人电话。1263637
     *
     * @param directContactPhone 直系联系人电话。1263637
     */
    public void setDirectContactPhone(String directContactPhone) {
        this.directContactPhone = directContactPhone == null ? null : directContactPhone.trim();
    }

    /**
     * 获取其他联系人关系。 朋友
     *
     * @return others_contact - 其他联系人关系。 朋友
     */
    public String getOthersContact() {
        return othersContact;
    }

    /**
     * 设置其他联系人关系。 朋友
     *
     * @param othersContact 其他联系人关系。 朋友
     */
    public void setOthersContact(String othersContact) {
        this.othersContact = othersContact == null ? null : othersContact.trim();
    }

    /**
     * 获取其他联系人姓名。李三
     *
     * @return others_contact_name - 其他联系人姓名。李三
     */
    public String getOthersContactName() {
        return othersContactName;
    }

    /**
     * 设置其他联系人姓名。李三
     *
     * @param othersContactName 其他联系人姓名。李三
     */
    public void setOthersContactName(String othersContactName) {
        this.othersContactName = othersContactName == null ? null : othersContactName.trim();
    }

    /**
     * 获取其他联系人电话。1263637
     *
     * @return others_contact_phone - 其他联系人电话。1263637
     */
    public String getOthersContactPhone() {
        return othersContactPhone;
    }

    /**
     * 设置其他联系人电话。1263637
     *
     * @param othersContactPhone 其他联系人电话。1263637
     */
    public void setOthersContactPhone(String othersContactPhone) {
        this.othersContactPhone = othersContactPhone == null ? null : othersContactPhone.trim();
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
}