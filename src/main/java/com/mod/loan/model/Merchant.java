package com.mod.loan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_merchant")
public class Merchant {
	/**
	 * 商户别名与app别名一致
	 */
	@Id
	@Column(name = "merchant_alias")
	private String merchantAlias;

	/**
	 * 商户名称
	 */
	@Column(name = "merchant_name")
	private String merchantName;

	/**
	 * 商户app别名
	 */
	@Column(name = "merchant_app")
	private String merchantApp;
	/**
	 * 商户支付宝
	 */
	@Column(name = "merchant_zfb")
	private String merchantZfb;

	/**
	 * 商户状态
	 */
	@Column(name = "merchant_status")
	private Integer merchantStatus;

	/**
	 * 商户支付公司渠道
	 */
	@Column(name = "merchant_channel")
	private String merchantChannel;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "hlb_id")
	private String hlb_id;

	@Column(name = "hlb_rsa_private_key")
	private String hlb_rsa_private_key;

	@Column(name = "hlb_rsa_public_key")
	private String hlb_rsa_public_key;

	@Column(name = "hlb_md5_key")
	private String hlb_md5_key;

	@Column(name = "hlb_des_key")
	private String hlb_des_key;

	@Column(name = "fuyou_merid")
	private String fuyou_merid;

	@Column(name = "fuyou_secureid")
	private String fuyou_secureid;

	@Column(name = "fuyou_h5key")
	private String fuyou_h5key;

	@Column(name = "huiju_id")
	private String huiju_id;

	@Column(name = "huiju_md5_key")
	private String huiju_md5_key;


	/**
	 * 第三方支付类型：baofoo   kuaiqian
	 */
	@Column(name = "payment_type")
	private String paymentType;


	/**
	 * 1-新颜，2-十露盘，3-指谜（默认2）
	 */
	@Column(name = "risk_type")
	private Integer riskType;

	public Integer getRiskType() {
		return riskType;
	}

	public void setRiskType(Integer riskType) {
		this.riskType = riskType;
	}

	public String getPaymentType() { return paymentType; }

	public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

	/**
	 * 获取商户名称
	 *
	 * @return merchant_name - 商户名称
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * 设置商户名称
	 *
	 * @param merchantName
	 *            商户名称
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName == null ? null : merchantName.trim();
	}

	/**
	 * 获取商户支付宝
	 *
	 * @return merchant_zfb - 商户支付宝
	 */
	public String getMerchantZfb() {
		return merchantZfb;
	}

	/**
	 * 设置商户支付宝
	 *
	 * @param merchantZfb
	 *            商户支付宝
	 */
	public void setMerchantZfb(String merchantZfb) {
		this.merchantZfb = merchantZfb == null ? null : merchantZfb.trim();
	}

	/**
	 * 获取商户状态
	 *
	 * @return merchant_status - 商户状态
	 */
	public Integer getMerchantStatus() {
		return merchantStatus;
	}

	/**
	 * 设置商户状态
	 *
	 * @param merchantStatus
	 *            商户状态
	 */
	public void setMerchantStatus(Integer merchantStatus) {
		this.merchantStatus = merchantStatus;
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

	public String getMerchantApp() {
		return merchantApp;
	}

	public void setMerchantApp(String merchantApp) {
		this.merchantApp = merchantApp;
	}

	public String getHlb_id() {
		return hlb_id;
	}

	public void setHlb_id(String hlb_id) {
		this.hlb_id = hlb_id;
	}

	public String getHlb_rsa_private_key() {
		return hlb_rsa_private_key;
	}

	public void setHlb_rsa_private_key(String hlb_rsa_private_key) {
		this.hlb_rsa_private_key = hlb_rsa_private_key;
	}

	public String getHlb_rsa_public_key() {
		return hlb_rsa_public_key;
	}

	public void setHlb_rsa_public_key(String hlb_rsa_public_key) {
		this.hlb_rsa_public_key = hlb_rsa_public_key;
	}

	public String getHlb_md5_key() {
		return hlb_md5_key;
	}

	public void setHlb_md5_key(String hlb_md5_key) {
		this.hlb_md5_key = hlb_md5_key;
	}

	public String getHlb_des_key() {
		return hlb_des_key;
	}

	public void setHlb_des_key(String hlb_des_key) {
		this.hlb_des_key = hlb_des_key;
	}

	public String getMerchantAlias() {
		return merchantAlias;
	}

	public void setMerchantAlias(String merchantAlias) {
		this.merchantAlias = merchantAlias;
	}

	public String getMerchantChannel() {
		return merchantChannel;
	}

	public void setMerchantChannel(String merchantChannel) {
		this.merchantChannel = merchantChannel;
	}

	public String getHuiju_id() {
		return huiju_id;
	}

	public void setHuiju_id(String huiju_id) {
		this.huiju_id = huiju_id;
	}

	public String getHuiju_md5_key() {
		return huiju_md5_key;
	}

	public void setHuiju_md5_key(String huiju_md5_key) {
		this.huiju_md5_key = huiju_md5_key;
	}

	public String getFuyou_merid() {
		return fuyou_merid;
	}

	public void setFuyou_merid(String fuyou_merid) {
		this.fuyou_merid = fuyou_merid;
	}

	public String getFuyou_secureid() {
		return fuyou_secureid;
	}

	public void setFuyou_secureid(String fuyou_secureid) {
		this.fuyou_secureid = fuyou_secureid;
	}

	public String getFuyou_h5key() {
		return fuyou_h5key;
	}

	public void setFuyou_h5key(String fuyou_h5key) {
		this.fuyou_h5key = fuyou_h5key;
	}

}