package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_app_entry")
public class AppEntry {
    @Id
    private Long id;

    /**
     * 图片url
     */
    @Column(name = "entry_imgurl")
    private String entryImgurl;

    /**
     * 点击url
     */
    @Column(name = "entry_url")
    private String entryUrl;

    /**
     * 排序，从大到小排列
     */
    @Column(name = "entry_idx")
    private Integer entryIdx;

    /**
     * 备注
     */
    @Column(name = "entry_remark")
    private String entryRemark;

    /**
     * 状态,0-停用，1-定时，2-启用
     */
    @Column(name = "entry_status")
    private Integer entryStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 定时开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 定时结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 商户
     */
    @Column(name = "merchant")
    private String merchant;

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
        this.merchant = merchant;
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
     * 获取图片url
     *
     * @return entry_imgurl - 图片url
     */
    public String getEntryImgurl() {
        return entryImgurl;
    }

    /**
     * 设置图片url
     *
     * @param entryImgurl 图片url
     */
    public void setEntryImgurl(String entryImgurl) {
        this.entryImgurl = entryImgurl == null ? null : entryImgurl.trim();
    }

    /**
     * 获取点击url
     *
     * @return entry_url - 点击url
     */
    public String getEntryUrl() {
        return entryUrl;
    }

    /**
     * 设置点击url
     *
     * @param entryUrl 点击url
     */
    public void setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl == null ? null : entryUrl.trim();
    }

    /**
     * 获取排序，从大到小排列
     *
     * @return entry_idx - 排序，从大到小排列
     */
    public Integer getEntryIdx() {
        return entryIdx;
    }

    /**
     * 设置排序，从大到小排列
     *
     * @param entryIdx 排序，从大到小排列
     */
    public void setEntryIdx(Integer entryIdx) {
        this.entryIdx = entryIdx;
    }

    /**
     * 获取备注
     *
     * @return entry_remark - 备注
     */
    public String getEntryRemark() {
        return entryRemark;
    }

    /**
     * 设置备注
     *
     * @param entryRemark 备注
     */
    public void setEntryRemark(String entryRemark) {
        this.entryRemark = entryRemark == null ? null : entryRemark.trim();
    }

    /**
     * 获取状态,0-停用，1-定时，2-启用
     *
     * @return entry_status - 状态,0-停用，1-定时，2-启用
     */
    public Integer getEntryStatus() {
        return entryStatus;
    }

    /**
     * 设置状态,0-停用，1-定时，2-启用
     *
     * @param entryStatus 状态,0-停用，1-定时，2-启用
     */
    public void setEntryStatus(Integer entryStatus) {
        this.entryStatus = entryStatus;
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
     * 获取定时开始时间
     *
     * @return start_time - 定时开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置定时开始时间
     *
     * @param startTime 定时开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取定时结束时间
     *
     * @return end_time - 定时结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置定时结束时间
     *
     * @param endTime 定时结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}