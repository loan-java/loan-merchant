package com.mod.loan.model;

import com.mod.loan.util.TimeUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_app_startup")
public class Startup {
    @Id
    private Long id;

    /**
     * 图片地址
     */
    @Column(name = "ad_imgurl")
    private String adImgurl;

    /**
     * 图片链接地址
     */
    @Column(name = "ad_url")
    private String adUrl;

    /**
     * 排序
     */
    @Column(name = "ad_idx")
    private Integer adIdx;

    /**
     * 状态,0-停用，1-定时，2-启用
     */
    @Column(name = "ad_status")
    private Integer adStatus;

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
     * @return ad_imgurl - 图片地址
     */
    public String getAdImgurl() {
        return adImgurl;
    }

    /**
     * 设置图片地址
     *
     * @param adImgurl 图片地址
     */
    public void setAdImgurl(String adImgurl) {
        this.adImgurl = adImgurl == null ? null : adImgurl.trim();
    }

    /**
     * 获取图片链接地址
     *
     * @return ad_url - 图片链接地址
     */
    public String getAdUrl() {
        return adUrl;
    }

    /**
     * 设置图片链接地址
     *
     * @param adUrl 图片链接地址
     */
    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl == null ? null : adUrl.trim();
    }

    /**
     * 获取排序
     *
     * @return ad_idx - 排序
     */
    public Integer getAdIdx() {
        return adIdx;
    }

    /**
     * 设置排序
     *
     * @param adIdx 排序
     */
    public void setAdIdx(Integer adIdx) {
        this.adIdx = adIdx;
    }

    /**
     * 获取状态,0-停用，1-定时，2-启用
     *
     * @return ad_status - 状态,0-停用，1-定时，2-启用
     */
    public Integer getAdStatus() {
        return adStatus;
    }

    /**
     * 设置状态,0-停用，1-定时，2-启用
     *
     * @param adStatus 状态,0-停用，1-定时，2-启用
     */
    public void setAdStatus(Integer adStatus) {
        this.adStatus = adStatus;
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