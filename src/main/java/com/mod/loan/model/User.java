package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user")
public class User {
	@Id
	private Long id;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "user_pwd")
	private String userPwd;

	/**
	 * 注册渠道，来源
	 */
	@Column(name = "user_origin")
	private String userOrigin;

	/**
	 * 用户姓名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 身份证号
	 */
	@Column(name = "user_cert_no")
	private String userCertNo;

	/**
	 * 人脸识别
	 */
	@Column(name = "img_face")
	private String imgFace;

	/**
	 * 身份证识别正面
	 */
	@Column(name = "img_cert_front")
	private String imgCertFront;

	/**
	 * 身份证识别背面
	 */
	@Column(name = "img_cert_back")
	private String imgCertBack;

	/**
	 * 签发机关
	 */
	private String ia;

	/**
	 * 有效期
	 */
	private String indate;

	/**
	 * 住址
	 */
	private String address;

	/**
	 * 民族
	 */
	private String nation;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 所属商户
	 */
	private String merchant;

	private String commonInfo;

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
	 * @return user_phone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	/**
	 * @return user_pwd
	 */
	public String getUserPwd() {
		return userPwd;
	}

	/**
	 * @param userPwd
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd == null ? null : userPwd.trim();
	}

	/**
	 * 获取注册渠道，来源
	 *
	 * @return user_origin - 注册渠道，来源
	 */
	public String getUserOrigin() {
		return userOrigin;
	}

	/**
	 * 设置注册渠道，来源
	 *
	 * @param userOrigin
	 *            注册渠道，来源
	 */
	public void setUserOrigin(String userOrigin) {
		this.userOrigin = userOrigin == null ? null : userOrigin.trim();
	}

	/**
	 * 获取用户姓名
	 *
	 * @return user_name - 用户姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户姓名
	 *
	 * @param userName
	 *            用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * 获取身份证号
	 *
	 * @return user_cert_no - 身份证号
	 */
	public String getUserCertNo() {
		return userCertNo;
	}

	/**
	 * 设置身份证号
	 *
	 * @param userCertNo
	 *            身份证号
	 */
	public void setUserCertNo(String userCertNo) {
		this.userCertNo = userCertNo == null ? null : userCertNo.trim();
	}

	/**
	 * 获取人脸识别
	 *
	 * @return img_face - 人脸识别
	 */
	public String getImgFace() {
		return imgFace;
	}

	/**
	 * 设置人脸识别
	 *
	 * @param imgFace
	 *            人脸识别
	 */
	public void setImgFace(String imgFace) {
		this.imgFace = imgFace == null ? null : imgFace.trim();
	}

	/**
	 * 获取身份证识别正面
	 *
	 * @return img_cert_front - 身份证识别正面
	 */
	public String getImgCertFront() {
		return imgCertFront;
	}

	/**
	 * 设置身份证识别正面
	 *
	 * @param imgCertFront
	 *            身份证识别正面
	 */
	public void setImgCertFront(String imgCertFront) {
		this.imgCertFront = imgCertFront == null ? null : imgCertFront.trim();
	}

	/**
	 * 获取身份证识别背面
	 *
	 * @return img_cert_back - 身份证识别背面
	 */
	public String getImgCertBack() {
		return imgCertBack;
	}

	/**
	 * 设置身份证识别背面
	 *
	 * @param imgCertBack
	 *            身份证识别背面
	 */
	public void setImgCertBack(String imgCertBack) {
		this.imgCertBack = imgCertBack == null ? null : imgCertBack.trim();
	}

	/**
	 * 获取签发机关
	 *
	 * @return ia - 签发机关
	 */
	public String getIa() {
		return ia;
	}

	/**
	 * 设置签发机关
	 *
	 * @param ia
	 *            签发机关
	 */
	public void setIa(String ia) {
		this.ia = ia == null ? null : ia.trim();
	}

	/**
	 * 获取有效期
	 *
	 * @return indate - 有效期
	 */
	public String getIndate() {
		return indate;
	}

	/**
	 * 设置有效期
	 *
	 * @param indate
	 *            有效期
	 */
	public void setIndate(String indate) {
		this.indate = indate == null ? null : indate.trim();
	}

	/**
	 * 获取住址
	 *
	 * @return address - 住址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置住址
	 *
	 * @param address
	 *            住址
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	/**
	 * 获取民族
	 *
	 * @return nation - 民族
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * 设置民族
	 *
	 * @param nation
	 *            民族
	 */
	public void setNation(String nation) {
		this.nation = nation == null ? null : nation.trim();
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
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取修改时间
	 *
	 * @return update_time - 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置修改时间
	 *
	 * @param updateTime
	 *            修改时间
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
	 * @param merchant
	 *            所属商户
	 */
	public void setMerchant(String merchant) {
		this.merchant = merchant == null ? null : merchant.trim();
	}

	public String getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(String commonInfo) {
		this.commonInfo = commonInfo;
	}
}