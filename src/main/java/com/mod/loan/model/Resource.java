package com.mod.loan.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Table(name = "tb_merchant_resource")
public class Resource {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 父主键
     */
    private Long pid;

    /**
     * 模块名称
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 链接地址
     */
    @Column(name = "resource_url")
    private String resourceUrl;

    /**
     * 菜单图标
     */
    @Column(name = "resource_icon")
    private String resourceIcon;

    /**
     * 排列顺序
     */
    @Column(name = "resource_order")
    private Integer resourceOrder;

    /**
     * 状态0-正常；1-已停用 
     */
    @Column(name = "resource_status")
    private Integer resourceStatus;
    
    //是否拥有当前菜单权限,默认false
    @Transient
  	private boolean hasResource=false;		
  //子权限菜单
    @Transient
  	private List<Resource> childResource=new ArrayList<>();
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
     * 获取父主键
     *
     * @return pid - 父主键
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父主键
     *
     * @param pid 父主键
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取模块名称
     *
     * @return resource_name - 模块名称
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置模块名称
     *
     * @param resourceName 模块名称
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    /**
     * 获取链接地址
     *
     * @return resource_url - 链接地址
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * 设置链接地址
     *
     * @param resourceUrl 链接地址
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    /**
     * 获取菜单图标
     *
     * @return resource_icon - 菜单图标
     */
    public String getResourceIcon() {
        return resourceIcon;
    }

    /**
     * 设置菜单图标
     *
     * @param resourceIcon 菜单图标
     */
    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon == null ? null : resourceIcon.trim();
    }

    /**
     * 获取排列顺序
     *
     * @return resource_order - 排列顺序
     */
    public Integer getResourceOrder() {
        return resourceOrder;
    }

    /**
     * 设置排列顺序
     *
     * @param resourceOrder 排列顺序
     */
    public void setResourceOrder(Integer resourceOrder) {
        this.resourceOrder = resourceOrder;
    }

    /**
     * 获取状态0-正常；1-已停用 
     *
     * @return resource_status - 状态0-正常；1-已停用 
     */
    public Integer getResourceStatus() {
        return resourceStatus;
    }

    /**
     * 设置状态0-正常；1-已停用 
     *
     * @param resourceStatus 状态0-正常；1-已停用 
     */
    public void setResourceStatus(Integer resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

	public List<Resource> getChildResource() {
		return childResource;
	}

	public void setChildResource(List<Resource> childResource) {
		this.childResource = childResource;
	}

	public boolean isHasResource() {
		return hasResource;
	}

	public void setHasResource(boolean hasResource) {
		this.hasResource = hasResource;
	}
    
}