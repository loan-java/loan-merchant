package com.mod.loan.model;

import com.mod.loan.util.TimeUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_app_notice")
public class Notice {
    @Id
    private Long id;

    /**
     * 标签
     */
    @Column(name = "notice_tag")
    private String noticeTag;

    /**
     * 标题
     */
    @Column(name = "notice_title")
    private String noticeTitle;

    /**
     * 链接地址
     */
    @Column(name = "notice_url")
    private String noticeUrl;

    /**
     * 排序，从大到小排列
     */
    @Column(name = "notice_idx")
    private Integer noticeIdx;

    /**
     * 状态,0-停用，1-定时，2-启用
     */
    @Column(name = "notice_status")
    private Integer noticeStatus;

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
     * 获取标题
     *
     * @return notice_title - 标题
     */
    public String getNoticeTitle() {
        return noticeTitle;
    }

    /**
     * 设置标题
     *
     * @param noticeTitle 标题
     */
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    /**
     * 获取链接地址
     *
     * @return notice_url - 链接地址
     */
    public String getNoticeUrl() {
        return noticeUrl;
    }

    /**
     * 设置链接地址
     *
     * @param noticeUrl 链接地址
     */
    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl == null ? null : noticeUrl.trim();
    }

    /**
     * 获取排序，从大到小排列
     *
     * @return notice_idx - 排序，从大到小排列
     */
    public Integer getNoticeIdx() {
        return noticeIdx;
    }

    /**
     * 设置排序，从大到小排列
     *
     * @param noticeIdx 排序，从大到小排列
     */
    public void setNoticeIdx(Integer noticeIdx) {
        this.noticeIdx = noticeIdx;
    }

    /**
     * 获取状态,0-停用，1-定时，2-启用
     *
     * @return notice_status - 状态,0-停用，1-定时，2-启用
     */
    public Integer getNoticeStatus() {
        return noticeStatus;
    }

    /**
     * 设置状态,0-停用，1-定时，2-启用
     *
     * @param noticeStatus 状态,0-停用，1-定时，2-启用
     */
    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
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

    /**
     * noticeTag
     *
     * @return
     */
    public String getNoticeTag() {
        return noticeTag;
    }

    /**
     * @param noticeTag
     */
    public void setNoticeTag(String noticeTag) {
        this.noticeTag = noticeTag;
    }
}