package com.mod.loan.common.model;

/**
 * 
 * @author wugy 2018年4月26日 下午2:56:54
 */
public class RequestBean {

	private Long uid;// 用户id
	private Long requestTime; // 请求时间
	private String token; // 用户token
	private String ip; // ip
	private String ua; // 浏览器版本
	private String host;// 请求来源
	private String merchant;// 商户id


	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Long requestTime) {
		this.requestTime = requestTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

}
