package com.mod.loan.kuaiqian.dto.notify.popay;

import javax.xml.bind.annotation.*;

/**
 * 报文实体
 * @author zhiwei.ma
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "notifyResponse", propOrder = {"notifyHead","notifyResponseBody"})  
public class NotifyResponse {

	@XmlElement(name = "notifyHead")  
	private NotifyHead notifyHead;
	
	@XmlElement(name = "notifyResponseBody")  
	private NotifyResponseBody notifyResponseBody;

	public NotifyHead getNotifyHead() {
		return notifyHead;
	}

	public void setNotifyHead(NotifyHead notifyHead) {
		this.notifyHead = notifyHead;
	}

	public NotifyResponseBody getNotifyResponseBody() {
		return notifyResponseBody;
	}

	public void setNotifyResponseBody(NotifyResponseBody notifyResponseBody) {
		this.notifyResponseBody = notifyResponseBody;
	}



	
}
