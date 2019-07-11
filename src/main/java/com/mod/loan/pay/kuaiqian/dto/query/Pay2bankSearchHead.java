package com.mod.loan.pay.kuaiqian.dto.query;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "pay2bankSearchHead", propOrder = {"version", "memberCode"})  
public class Pay2bankSearchHead {

	@XmlElement(required = true) 
	private String version;
	
	@XmlElement(required = true) 
	private String memberCode;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
}
