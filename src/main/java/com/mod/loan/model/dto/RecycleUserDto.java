package com.mod.loan.model.dto;

/**
 * Created by chenanle on 2018/6/28.
 */
public class RecycleUserDto {

    private Long id;

    private Integer status;

    private String remark;

    private String recycleName;

    private String merchant;

    private Long groupId;

    private Long followUserId;


    public Long getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecycleName() {
        return recycleName;
    }

    public void setRecycleName(String recycleName) {
        this.recycleName = recycleName;
    }

}
