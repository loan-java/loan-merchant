package com.mod.loan.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_merchant_rate")
public class MerchantRate {
	@Id
	private Long id;

	/**
	 * 产品名称
	 */
	@Column(name = "product_name")
	private String productName;

	/**
	 * 借款期限
	 */
	@Column(name = "product_day")
	private Integer productDay;

	/**
	 * 借款金额
	 */
	@Column(name = "product_money")
	private BigDecimal productMoney;

	/**
	 * 优先级
	 */
	@Column(name = "product_level")
	private Integer productLevel;

	/**
	 * 状态：1:启用；0:禁用
	 */
	@Column(name = "product_status")
	private Integer productStatus;

	/**
	 * 年化利率
	 */
	@Column(name = "product_rate")
	private BigDecimal productRate;

	/**
	 * 综合费率
	 */
	@Column(name = "total_rate")
	private BigDecimal totalRate;

	/**
	 * 逾期费率
	 */
	@Column(name = "overdue_rate")
	private BigDecimal overdueRate;

	/**
	 * 借款次数
	 */
	@Column(name = "borrow_type")
	private Integer borrowType;

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
	 * 商户别名
	 */
	private String merchant;

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
	 * 获取产品名称
	 *
	 * @return product_name - 产品名称
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置产品名称
	 *
	 * @param productName
	 *            产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	/**
	 * 获取借款期限
	 *
	 * @return product_day - 借款期限
	 */
	public Integer getProductDay() {
		return productDay;
	}

	/**
	 * 设置借款期限
	 *
	 * @param productDay
	 *            借款期限
	 */
	public void setProductDay(Integer productDay) {
		this.productDay = productDay;
	}

	/**
	 * 获取借款金额
	 *
	 * @return product_money - 借款金额
	 */
	public BigDecimal getProductMoney() {
		return productMoney;
	}

	/**
	 * 设置借款金额
	 *
	 * @param productMoney
	 *            借款金额
	 */
	public void setProductMoney(BigDecimal productMoney) {
		this.productMoney = productMoney;
	}

	/**
	 * 获取优先级
	 *
	 * @return product_level - 优先级
	 */
	public Integer getProductLevel() {
		return productLevel;
	}

	/**
	 * 设置优先级
	 *
	 * @param productLevel
	 *            优先级
	 */
	public void setProductLevel(Integer productLevel) {
		this.productLevel = productLevel;
	}

	/**
	 * 获取状态：1:启用；0:禁用
	 *
	 * @return product_status - 状态：1:启用；0:禁用
	 */
	public Integer getProductStatus() {
		return productStatus;
	}

	/**
	 * 设置状态：1:启用；0:禁用
	 *
	 * @param productStatus
	 *            状态：1:启用；0:禁用
	 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * 获取年化利率
	 *
	 * @return product_rate - 年化利率
	 */
	public BigDecimal getProductRate() {
		return productRate;
	}

	/**
	 * 设置年化利率
	 *
	 * @param productRate
	 *            年化利率
	 */
	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	/**
	 * 获取综合费率
	 *
	 * @return total_rate - 综合费率
	 */
	public BigDecimal getTotalRate() {
		return totalRate;
	}

	/**
	 * 设置综合费率
	 *
	 * @param totalRate
	 *            综合费率
	 */
	public void setTotalRate(BigDecimal totalRate) {
		this.totalRate = totalRate;
	}

	/**
	 * 获取逾期费率
	 *
	 * @return overdue_rate - 逾期费率
	 */
	public BigDecimal getOverdueRate() {
		return overdueRate;
	}

	/**
	 * 设置逾期费率
	 *
	 * @param overdueRate
	 *            逾期费率
	 */
	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
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

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public Integer getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(Integer borrowType) {
		this.borrowType = borrowType;
	}

}