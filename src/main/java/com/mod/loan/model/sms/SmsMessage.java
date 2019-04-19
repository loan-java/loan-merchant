package com.mod.loan.model.sms;

public class SmsMessage {

	/**
	 * 客户端别名
	 */
	private String clientAlias;
	
	/**
	 * 短信事件
	 * 1001.注册验证码，1002修改密码验证码，3-
	 */
	private String type;
	/**
	 * 手机号
	 */
	private String phone;	
	
	/**
	 * 参数值，多个参数值用"|"隔开
	 */
	private String params;
	
	public SmsMessage(String clientAlias, String type, String phone, String params) {
		super();
		this.clientAlias = clientAlias;
		this.type = type;
		this.phone = phone;
		this.params = params;
	}

	public String getClientAlias() {
		return clientAlias;
	}

	public void setClientAlias(String clientAlias) {
		this.clientAlias = clientAlias;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	
	
}
