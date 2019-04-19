package com.mod.loan.common.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mod.loan.common.enums.ResponseEnum;

/**
 * 消息返回
 * 
 * @author wugy 2016年7月11日下午5:03:22
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultMessage {

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 状态消息
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private Object data;
	/**
	 * 分页
	 */
	private Page page;

	public ResultMessage() {
		this(null, null, null, null);
	}

	public ResultMessage(String status) {
		this(status, null, null, null);
	}

	public ResultMessage(String status, String message) {
		this(status, message, null, null);
	}

	public ResultMessage(String status, Object data) {
		this(status, null, data, null);
	}

	public ResultMessage(String status, String message, Object data) {
		this(status, message, data, null);
	}

	public ResultMessage(String status, Object data, Page page) {
		this(status, null, data, page);
	}

	public ResultMessage(String status, String message, Object data, Page page) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.page = page;
	}

	public ResultMessage(ResponseEnum responseEnum) {
		this(responseEnum.getCode(), responseEnum.getMessage(), null, null);
	}

	public ResultMessage(ResponseEnum responseEnum, Object data) {
		this(responseEnum.getCode(), responseEnum.getMessage(), data, null);
	}

	public ResultMessage(ResponseEnum responseEnum, Object data, Page page) {
		this(responseEnum.getCode(), responseEnum.getMessage(), data, page);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
