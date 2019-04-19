package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_recycle_user")
public class RecycleUser {
    @Id
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态0-正常；1-已停用
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 催收人员id
     */
    @Column(name = "follow_user_id")
    private Long followUserId;

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
     * @return group_id
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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
     * 获取状态0-正常；1-已停用
     *
     * @return status - 状态0-正常；1-已停用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0-正常；1-已停用
     *
     * @param status 状态0-正常；1-已停用
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取催收人员id
     *
     * @return follow_user_id - 催收人员id
     */
    public Long getFollowUserId() {
        return followUserId;
    }

    /**
     * 设置催收人员id
     *
     * @param followUserId 催收人员id
     */
    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }
}