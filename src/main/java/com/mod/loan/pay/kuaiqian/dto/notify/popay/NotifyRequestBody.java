package com.mod.loan.pay.kuaiqian.dto.notify.popay;

import com.mod.loan.pay.kuaiqian.dto.common.SealDataType;

import javax.xml.bind.annotation.*;

/**
 * 报文实体
 * @author zan.liang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "notifyRequestBody", propOrder = {"sealDataType"})  
public class NotifyRequestBody {

	
	@XmlElement(name = "sealDataType")  
	private SealDataType sealDataType;

	public SealDataType getSealDataType() {
		return sealDataType;
	}

	public void setSealDataType(SealDataType sealDataType) {
		this.sealDataType = sealDataType;
	}


	
}
