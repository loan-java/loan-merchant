package com.mod.loan.pay.kuaiqian.dto.common;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "sealDataType", propOrder = {"originalData", "signedData", "encryptedData", "digitalEnvelope"})  
public class SealDataType {

	//原始报文
	@XmlElement(required = true) 
	private String originalData;
	//签名数据
	@XmlElement(required = true) 
	private String signedData;
	//加密数据
	@XmlElement(required = true) 
	private String encryptedData;	
	//数字信封
	@XmlElement(required = true) 
	private String digitalEnvelope;
	
	public String getOriginalData() {
		return originalData;
	}
	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
	public String getSignedData() {
		return signedData;
	}
	public void setSignedData(String signedData) {
		this.signedData = signedData;
	}
	public String getEncryptedData() {
		return encryptedData;
	}
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	public String getDigitalEnvelope() {
		return digitalEnvelope;
	}
	public void setDigitalEnvelope(String digitalEnvelope) {
		this.digitalEnvelope = digitalEnvelope;
	}	

}
