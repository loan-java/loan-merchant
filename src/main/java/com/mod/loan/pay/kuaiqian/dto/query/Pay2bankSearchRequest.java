package com.mod.loan.pay.kuaiqian.dto.query;

import javax.xml.bind.annotation.*;

/**
 * 报文实体
 * @author zan.liang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "pay2bankSearchRequest", propOrder = {"pay2bankSearchHead","searchRequestBody"})  
public class Pay2bankSearchRequest {

	@XmlElement(name = "pay2bankSearchHead")  
	private Pay2bankSearchHead pay2bankSearchHead;
	
	@XmlElement(name = "searchRequestBody")  
	private SearchRequestBody searchRequestBody;

	public Pay2bankSearchHead getPay2bankSearchHead() {
		return pay2bankSearchHead;
	}

	public void setPay2bankSearchHead(Pay2bankSearchHead pay2bankSearchHead) {
		this.pay2bankSearchHead = pay2bankSearchHead;
	}

	public SearchRequestBody getSearchRequestBody() {
		return searchRequestBody;
	}

	public void setSearchRequestBody(SearchRequestBody searchRequestBody) {
		this.searchRequestBody = searchRequestBody;
	}





	
}
