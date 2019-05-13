package com.mod.loan.kuaiqian.dto.query;

import javax.xml.bind.annotation.*;

/**
 * 报文实体
 * @author zhiwei.ma
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "pay2bankSearchResponse", propOrder = {"pay2bankSearchHead","searchResponseBody"})  
public class Pay2bankSearchResponse {

	@XmlElement(name = "pay2bankSearchHead")  
	private Pay2bankSearchHead pay2bankSearchHead;
	
	@XmlElement(name = "searchResponseBody")  
	private SearchResponseBody searchResponseBody;

	public Pay2bankSearchHead getPay2bankSearchHead() {
		return pay2bankSearchHead;
	}

	public void setPay2bankSearchHead(Pay2bankSearchHead pay2bankSearchHead) {
		this.pay2bankSearchHead = pay2bankSearchHead;
	}

	public SearchResponseBody getSearchResponseBody() {
		return searchResponseBody;
	}

	public void setSearchResponseBody(SearchResponseBody searchResponseBody) {
		this.searchResponseBody = searchResponseBody;
	}




	
	
}
