package com.mod.loan.pay.kuaiqian.dto.query;

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
@XmlType(name = "pay2bankSearchDetail", propOrder = {"applyDate","orderId","orderSeqId","bankName","branchName","creditName",
		"bankAcctId","payerMembercode","payerName","amount","fee","feeAction","status","errorCode","errorMsg","endDate"})  
public class Pay2bankSearchDetail { 
	
	
	/**
	 *申请日期
	 */
	@XmlElement(required = true) 
	private String applyDate="";  
	
	/**
	 * 订单号  必填
	 */
	@XmlElement(required = true) 
	private String orderId="";  
	
	/**
	 * 快钱交易号
	 */
	@XmlElement(required = true) 
	private String orderSeqId="";  

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
	 * 银行卡号  必填
	 */
	@XmlElement(required = true) 
	private String bankAcctId="";
	

	/**
	 * 付款方
	 */
	@XmlElement(required = true) 
	private String payerMembercode="";
	
	/**
	 * 付款方名称
	 */
	@XmlElement(required = true) 
	private String payerName="";
	
	/**
	 * 交易金额 必填
	 */
	@XmlElement(required = true) 
	private String amount="";
	
	/**
	 * fee
	 */
	@XmlElement(required = true) 
	private String fee="";
	
	/**
	 * feeaction
	 */
	@XmlElement(required = true) 
	private String feeAction="";
	
	/**
	 * 交易状态100：申请成功；101：支付中
111：支付成功；112：支付失败；114：已退票
	 */
	@XmlElement(required = true) 
	private String status="";

	/**
	 * errorCode
	 */
	@XmlElement(required = true) 
	private String errorCode="";
	
	/**
	 * errorMsg
	 */
	@XmlElement(required = true) 
	private String errorMsg="";
	
	/**
	 * 完成时间
	 */
	@XmlElement(required = true) 
	private String endDate="";



	public String getApplyDate() {
		return applyDate;
	}



	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}



	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public String getOrderSeqId() {
		return orderSeqId;
	}



	public void setOrderSeqId(String orderSeqId) {
		this.orderSeqId = orderSeqId;
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



	public String getBankAcctId() {
		return bankAcctId;
	}



	public void setBankAcctId(String bankAcctId) {
		this.bankAcctId = bankAcctId;
	}



	public String getPayerMembercode() {
		return payerMembercode;
	}



	public void setPayerMembercode(String payerMembercode) {
		this.payerMembercode = payerMembercode;
	}



	public String getPayerName() {
		return payerName;
	}



	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public String getFee() {
		return fee;
	}



	public void setFee(String fee) {
		this.fee = fee;
	}



	public String getFeeAction() {
		return feeAction;
	}



	public void setFeeAction(String feeAction) {
		this.feeAction = feeAction;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getErrorCode() {
		return errorCode;
	}



	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}



	public String getErrorMsg() {
		return errorMsg;
	}



	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	@Override
	public String toString() {
		return "pay2bankQueryParam [orderId=" + orderId + ", bankName="
				+ bankName + ", branchName=" + branchName
				+ ", creditName=" + creditName + ", applyDate="
				+ applyDate + ", bankAcctId=" + bankAcctId
				+ ", amount=" + amount + ", endDate=" + endDate+ ", errorCode=" + errorCode+ ", orderSeqId=" + orderSeqId
				+ ", status=" + status  + "]";
	}


	
}
