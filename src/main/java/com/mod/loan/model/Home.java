package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_app_home")
public class Home {
    @Id
    private Long id;

    /**
     * 图片url
     */
    private String imgurl;

    /**
     * 点击url
     */
    private String url;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态,0-停用，1-启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 所属商户
     */
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
     * 获取图片url
     *
     * @return imgurl - 图片url
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 设置图片url
     *
     * @param imgurl 图片url
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    /**
     * 获取点击url
     *
     * @return url - 点击url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置点击url
     *
     * @param url 点击url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取状态,0-停用，1-启用
     *
     * @return status - 状态,0-停用，1-启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态,0-停用，1-启用
     *
     * @param status 状态,0-停用，1-启用
     */
    public void setStatus(Integer status) {
        this.status = status;
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
}