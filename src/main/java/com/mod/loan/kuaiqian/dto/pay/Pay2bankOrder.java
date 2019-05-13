package com.mod.loan.kuaiqian.dto.pay;

import javax.xml.bind.annotation.*;

/**
 * 
 * @ClassName: Order
 * @Description: 请求对象
 * @date 2017-3-30
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)   
@XmlRootElement   
@XmlType(name = "pay2bankOrder", propOrder = {"orderId","bankName","branchName","creditName","mobile",
		"bankAcctId","amount","province","city","remark","feeAction"})  
public class Pay2bankOrder { 
	
	
	/**
	 * 订单号  必填
	 */
	@XmlElement(required = true) 
	private String orderId="";  

	/**
	 * 银行名称    必填
	 */
	@XmlElement(required = true) 
	private String bankName="";
	
	/**
	 * 开户行    非必填
	 */
	@XmlElement(required = true) 
	private String branchName="";
	
	/**
	 * 收款人姓名   必填
	 */
	@XmlElement(required = true) 
	private String creditName="";
	
	
	/**
	 * 收款人手机号  非必填
	 */
	@XmlElement(required = true) 
	private String mobile="";
	
	/**
	 * 银行卡号  必填
	 */
	@XmlElement(required = true) 
	private String bankAcctId="";
	
	/**
	 * 交易金额 必填
	 */
	@XmlElement(required = true) 
	private String amount="";
	
	/**
	 * 省份 非必填
	 */
	@XmlElement(required = true) 
	private String province="";
	
	/**
	 * 城市 非必填
	 */
	@XmlElement(required = true) 
	private String city="";


	/**
	 * 交易描述  非必填
	 */
	@XmlElement(required = true) 
	private String remark="";
	/**
	 * 0收款方付费
	 * 1付款方付费
	 * 默认1付款方付费
	 * 必填
	 */
	@XmlElement(required = true) 
	private String feeAction="1";


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public String getCreditName() {
		return creditName;
	}


	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getBankAcctId() {
		return bankAcctId;
	}


	public void setBankAcctId(String bankAcctId) {
		this.bankAcctId = bankAcctId;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}




	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getFeeAction() {
		return feeAction;
	}


	public void setFeeAction(String feeAction) {
		this.feeAction = feeAction;
	}


	@Override
	public String toString() {
		return "Pay2bankOrder [orderId=" + orderId + ", bankName="
				+ bankName + ", branchName=" + branchName
				+ ", creditName=" + creditName + ", mobile="
				+ mobile + ", bankAcctId=" + bankAcctId
				+ ", amount=" + amount + ", province=" + province
				+ ", city=" + city  + "]";
	}


	
}
