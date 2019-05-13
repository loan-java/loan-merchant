package com.mod.loan.baofoo.base.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("trans_reqDatas")
public class TransReqDatas<T> {
	
	//为空则不显示
	private String count;
	
	//去掉<trans_reqData>父节点<trans_reqDatas>
	@XStreamImplicit
	private List<T> trans_reqDatas;
	
	public TransReqDatas(){
		
	}
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}


	public List<T> getTrans_reqDatas() {
		return trans_reqDatas;
	}

	public void setTrans_reqDatas(List<T> trans_reqDatas) {
		this.trans_reqDatas = trans_reqDatas;
	}

}