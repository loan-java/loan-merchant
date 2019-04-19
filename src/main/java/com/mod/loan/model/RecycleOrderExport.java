package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_recycle_order_export")
public class RecycleOrderExport {
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;

	/**
	 * 所属商户
	 */
	private String merchant;

	@Column(name = "manager_id")
	private Long managerId;

	/**
	 * 请求参数
	 */
	private String param;

	/**
	 * 状态（0，正在进行；1，已完成。）
	 */
	private Integer status;

	/**
	 * 文件下载地址
	 */
	private String url;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

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
	 * @param merchant
	 *            所属商户
	 */
	public void setMerchant(String merchant) {
		this.merchant = merchant == null ? null : merchant.trim();
	}

	/**
	 * @return manager_id
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 * 获取请求参数
	 *
	 * @return param - 请求参数
	 */
	public String getParam() {
		return param;
	}

	/**
	 * 设置请求参数
	 *
	 * @param param
	 *            请求参数
	 */
	public void setParam(String param) {
		this.param = param == null ? null : param.trim();
	}

	/**
	 * 获取状态（0，正在进行；1，已完成。）
	 *
	 * @return status - 状态（0，正在进行；1，已完成。）
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态（0，正在进行；1，已完成。）
	 *
	 * @param status
	 *            状态（0，正在进行；1，已完成。）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取文件下载地址
	 *
	 * @return url - 文件下载地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置文件下载地址
	 *
	 * @param url
	 *            文件下载地址
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
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
}