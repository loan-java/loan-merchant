package com.mod.loan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_merchant_manager")
public class Manager {
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
	 * 角色ID
	 */
	@Column(name = "role_id")
	private Long roleId;

	/**
	 * 登陆账号
	 */
	@Column(name = "login_name")
	private String loginName;

	/**
	 * 登录密码
	 */
	@Column(name = "login_password")
	private String loginPassword;

	/**
	 * 登陆手机号
	 */
	@Column(name = "user_phone")
	private String userPhone;

	/**
	 * 人员姓名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 最后登录时间
	 */
	@Column(name = "last_login_time")
	private Date lastLoginTime;

	/**
	 * 最后登录IP
	 */
	@Column(name = "last_login_ip")
	private String lastLoginIp;

	/**
	 * 状态0-正常；1-已停用
	 */
	@Column(name = "account_status")
	private Integer accountStatus;

	/**
	 * 类型0-公司员工；1-管理员，拥有所有权限
	 */
	@Column(name = "account_type")
	private Integer accountType;

	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 邮箱
	 */
	@Column(name = "user_email")
	private String userEmail;

	/**
	 * 0-不安全，1-安全。默认1
	 */
	@Column(name = "user_security")
	private Integer userSecurity;

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
	 * @param id
	 *            主键
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
	 * 获取角色ID
	 *
	 * @return role_id - 角色ID
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色ID
	 *
	 * @param roleId
	 *            角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取登陆账号
	 *
	 * @return login_name - 登陆账号
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置登陆账号
	 *
	 * @param loginName
	 *            登陆账号
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	/**
	 * 获取登录密码
	 *
	 * @return login_password - 登录密码
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/**
	 * 设置登录密码
	 *
	 * @param loginPassword
	 *            登录密码
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword == null ? null : loginPassword.trim();
	}

	/**
	 * 获取登陆手机号
	 *
	 * @return user_phone - 登陆手机号
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * 设置登陆手机号
	 *
	 * @param userPhone
	 *            登陆手机号
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	/**
	 * 获取人员姓名
	 *
	 * @return user_name - 人员姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置人员姓名
	 *
	 * @param userName
	 *            人员姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * 获取最后登录时间
	 *
	 * @return last_login_time - 最后登录时间
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * 设置最后登录时间
	 *
	 * @param lastLoginTime
	 *            最后登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 获取最后登录IP
	 *
	 * @return last_login_ip - 最后登录IP
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * 设置最后登录IP
	 *
	 * @param lastLoginIp
	 *            最后登录IP
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
	}

	/**
	 * 获取状态0-正常；1-已停用
	 *
	 * @return account_status - 状态0-正常；1-已停用
	 */
	public Integer getAccountStatus() {
		return accountStatus;
	}

	/**
	 * 设置状态0-正常；1-已停用
	 *
	 * @param accountStatus
	 *            状态0-正常；1-已停用
	 */
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * 获取类型0-公司员工；1-管理员，拥有所有权限
	 *
	 * @return account_type - 类型0-公司员工；1-管理员，拥有所有权限
	 */
	public Integer getAccountType() {
		return accountType;
	}

	/**
	 * 设置类型0-公司员工；1-管理员，拥有所有权限
	 *
	 * @param accountType
	 *            类型0-公司员工；1-管理员，拥有所有权限
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
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
	 * 获取邮箱
	 *
	 * @return user_email - 邮箱
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * 设置邮箱
	 *
	 * @param userEmail
	 *            邮箱
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail == null ? null : userEmail.trim();
	}

	/**
	 * 获取0-不安全，1-安全。默认1
	 *
	 * @return user_security - 0-不安全，1-安全。默认1
	 */
	public Integer getUserSecurity() {
		return userSecurity;
	}

	/**
	 * 设置0-不安全，1-安全。默认1
	 *
	 * @param userSecurity
	 *            0-不安全，1-安全。默认1
	 */
	public void setUserSecurity(Integer userSecurity) {
		this.userSecurity = userSecurity;
	}
}