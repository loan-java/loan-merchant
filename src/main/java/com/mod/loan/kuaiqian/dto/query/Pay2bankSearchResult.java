package com.mod.loan.kuaiqian.dto.query;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 
 * @ClassName: Order
 * @Description: 请求对象
 * @date 2017-3-30
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)   
@XmlRootElement   
@XmlType(name = "pay2bankSearchResult", propOrder = {"pay2bankSearchResponseParam","resultList"})  
public class Pay2bankSearchResult { 
	
	
	@XmlElement(name = "pay2bankSearchResponseParam")  
	private Pay2bankSearchResponseParam pay2bankSearchResponseParam;
	
	@XmlElement(name = "resultList")  
	private List<Pay2bankSearchDetail> resultList;


	public List<Pay2bankSearchDetail> getResultList() {
		return resultList;
	}

	public void setResultList(List<Pay2bankSearchDetail> resultList) {
		this.resultList = resultList;
	}

	public Pay2bankSearchResponseParam getPay2bankSearchResponseParam() {
		return pay2bankSearchResponseParam;
	}

	public void setPay2bankSearchResponseParam(
			Pay2bankSearchResponseParam pay2bankSearchResponseParam) {
		this.pay2bankSearchResponseParam = pay2bankSearchResponseParam;
	}



	
}
