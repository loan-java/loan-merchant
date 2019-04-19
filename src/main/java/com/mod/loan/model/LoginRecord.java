package com.mod.loan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_merchant_login_record")
public class LoginRecord {
	@Id
	private Long id;

	@Column(name = "manager_id")
	private Long managerId;

	/**
	 * 所属商户
	 */
	private String merchant;

	/**
	 * 账号
	 */
	@Column(name = "login_name")
	private String loginName;

	/**
	 * 姓名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 登录ip
	 */
	@Column(name = "login_ip")
	private String loginIp;

	@Column(name = "user_ua")
	private String userUa;

	@Column(name = "user_host")
	private String userHost;

	@Column(name = "create_time")
	private Date createTime;

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
	 * 获取账号
	 *
	 * @return login_name - 账号
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置账号
	 *
	 * @param loginName
	 *            账号
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	/**
	 * 获取姓名
	 *
	 * @return user_name - 姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置姓名
	 *
	 * @param userName
	 *            姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * 获取登录ip
	 *
	 * @return login_ip - 登录ip
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 设置登录ip
	 *
	 * @param loginIp
	 *            登录ip
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp == null ? null : loginIp.trim();
	}

	/**
	 * @return user_ua
	 */
	public String getUserUa() {
		return userUa;
	}

	/**
	 * @param userUa
	 */
	public void setUserUa(String userUa) {
		this.userUa = userUa == null ? null : userUa.trim();
	}

	/**
	 * @return user_host
	 */
	public String getUserHost() {
		return userHost;
	}

	/**
	 * @param userHost
	 */
	public void setUserHost(String userHost) {
		this.userHost = userHost == null ? null : userHost.trim();
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