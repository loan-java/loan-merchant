package com.mod.loan.model;

import javax.persistence.*;

@Table(name = "tb_merchant_role")
public class Role {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 所属商户
     */
    private String merchant;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 备注
     */
    @Column(name = "role_remark")
    private String roleRemark;

    /**
     * 状态0-正常；1-已停用
     */
    @Column(name = "role_status")
    private Integer roleStatus;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
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

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取备注
     *
     * @return role_remark - 备注
     */
    public String getRoleRemark() {
        return roleRemark;
    }

    /**
     * 设置备注
     *
     * @param roleRemark 备注
     */
    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark == null ? null : roleRemark.trim();
    }

    /**
     * 获取状态0-正常；1-已停用
     *
     * @return role_status - 状态0-正常；1-已停用
     */
    public Integer getRoleStatus() {
        return roleStatus;
    }

    /**
     * 设置状态0-正常；1-已停用
     *
     * @param roleStatus 状态0-正常；1-已停用
     */
    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }
}