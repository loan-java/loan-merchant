package com.mod.loan.kuaiqian.dto.query;

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
@XmlType(name = "pay2bankSearchResponseParam", propOrder = {"orderId","bankName","startDate","endDate","branchName","creditName",
		"bankAcctId","amount","targetPage","pageSize","totalCnt"})  
public class Pay2bankSearchResponseParam { 
	
	
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
	 * 交易开始时间
	 */
	@XmlElement(required = true) 
	private String startDate="";
	
	/**
	 * 交易结束时间
	 */
	@XmlElement(required = true) 
	private String endDate="";
	
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
	 * 页码
	 */
	@XmlElement(required = true) 
	private String targetPage="";

	/**
	 * 每页显示条数
	 */
	@XmlElement(required = true) 
	private String pageSize="";


	/**
	 * 总条数
	 */
	@XmlElement(required = true) 
	private String totalCnt="";




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







	public String getStartDate() {
		return startDate;
	}







	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}







	public String getEndDate() {
		return endDate;
	}







	public void setEndDate(String endDate) {
		this.endDate = endDate;
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







	public String getTargetPage() {
		return targetPage;
	}







	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}







	public String getPageSize() {
		return pageSize;
	}







	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}







	public String getTotalCnt() {
		return totalCnt;
	}







	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}







	@Override
	public String toString() {
		return "Pay2bankQueryHead [orderId=" + orderId + ", bankName="
				+ bankName + ", branchName=" + branchName
				+ ", creditName=" + creditName + ", startDate="
				+ startDate + ", bankAcctId=" + bankAcctId
				+ ", amount=" + amount + ", endDate=" + endDate+ ", targetPage=" + targetPage
				+ ", pageSize=" + pageSize  + "]";
	}


	
}
