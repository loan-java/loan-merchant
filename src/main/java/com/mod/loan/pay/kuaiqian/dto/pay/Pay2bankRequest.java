package com.mod.loan.pay.kuaiqian.dto.pay;

import javax.xml.bind.annotation.*;

/**
 * 报文实体
 * @author zan.liang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "pay2bankRequest", propOrder = {"pay2bankHead","requestBody"})  
public class Pay2bankRequest {

	@XmlElement(name = "pay2bankHead")  
	private Pay2bankHead pay2bankHead;
	
	@XmlElement(name = "requestBody")  
	private RequestBody requestBody;


	public RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}

	public Pay2bankHead getPay2bankHead() {
		return pay2bankHead;
	}

	public void setPay2bankHead(Pay2bankHead pay2bankHead) {
		this.pay2bankHead = pay2bankHead;
	}

	
}
