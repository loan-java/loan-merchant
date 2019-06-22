package com.mod.loan.common.enums;

/**
 * 返回内容枚举类 Created by lijy on 2017/7/18.
 */
public enum ResponseEnum {
	M80000("80000", "业务异常"),

	M2000("2000", "成功。"), M4000("4000", "失败。"), M4001("4001", "旧密码不能为空。"), M4002("4002", "新密码不能为空。"), M4003("4003", "旧密码错误。"), M4004("4004", "无效的操作。"), M4005("4005", "验证码错误。"), M5001("5001", "需要短信验证码。"), M5002("5002", "不存在的商户。");

	private String code;

	private String message;

	ResponseEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
