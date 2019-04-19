package com.mod.loan.model;

import com.mod.loan.util.TimeUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_app_banner")
public class Banner {
    @Id
    private Long id;

    /**
     * 图片地址
     */
    @Column(name = "banner_imgurl")
    private String bannerImgurl;

    /**
     * 连接地址
     */
    @Column(name = "banner_url")
    private String bannerUrl;

    /**
     * 排序，从大到小排列
     */
    @Column(name = "banner_idx")
    private Integer bannerIdx;

    /**
     * 备注
     */
    @Column(name = "banner_remark")
    private String bannerRemark;

    /**
     * 状态,0-停用，1-定时，2-启用
     */
    @Column(name = "banner_status")
    private Integer bannerStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 定时开始时间
     */
    @Column(name = "start_time")
    @DateTimeFormat(pattern = TimeUtils.dateformat1)
    private Date startTime;

    /**
     * 定时结束时间
     */
    @Column(name = "end_time")
    @DateTimeFormat(pattern = TimeUtils.dateformat1)
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
     * 获取图片地址
     *
     * @return banner_imgurl - 图片地址
     */
    public String getBannerImgurl() {
        return bannerImgurl;
    }

    /**
     * 设置图片地址
     *
     * @param bannerImgurl 图片地址
     */
    public void setBannerImgurl(String bannerImgurl) {
        this.bannerImgurl = bannerImgurl == null ? null : bannerImgurl.trim();
    }

    /**
     * 获取连接地址
     *
     * @return banner_url - 连接地址
     */
    public String getBannerUrl() {
        return bannerUrl;
    }

    /**
     * 设置连接地址
     *
     * @param bannerUrl 连接地址
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    /**
     * 获取排序，从大到小排列
     *
     * @return banner_idx - 排序，从大到小排列
     */
    public Integer getBannerIdx() {
        return bannerIdx;
    }

    /**
     * 设置排序，从大到小排列
     *
     * @param bannerIdx 排序，从大到小排列
     */
    public void setBannerIdx(Integer bannerIdx) {
        this.bannerIdx = bannerIdx;
    }

    /**
     * 获取备注
     *
     * @return banner_remark - 备注
     */
    public String getBannerRemark() {
        return bannerRemark;
    }

    /**
     * 设置备注
     *
     * @param bannerRemark 备注
     */
    public void setBannerRemark(String bannerRemark) {
        this.bannerRemark = bannerRemark == null ? null : bannerRemark.trim();
    }

    /**
     * 获取状态,0-停用，1-定时，2-启用
     *
     * @return banner_status - 状态,0-停用，1-定时，2-启用
     */
    public Integer getBannerStatus() {
        return bannerStatus;
    }

    /**
     * 设置状态,0-停用，1-定时，2-启用
     *
     * @param bannerStatus 状态,0-停用，1-定时，2-启用
     */
    public void setBannerStatus(Integer bannerStatus) {
        this.bannerStatus = bannerStatus;
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