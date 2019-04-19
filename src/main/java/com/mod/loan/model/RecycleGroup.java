package com.mod.loan.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_recycle_group")
public class RecycleGroup {
    @Id
    private Long id;

    /**
     * 状态0-正常；1-已停用
     */
    private Integer status;

    /**
     * 组名
     */
    @Column(name = "group_name")
    private String groupName;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取组名
     *
     * @return group_name - 组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置组名
     *
     * @param groupName 组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
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
}