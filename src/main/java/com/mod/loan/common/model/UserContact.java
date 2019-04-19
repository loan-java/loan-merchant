package com.mod.loan.common.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;

public class UserContact {

	private String c;//关系
	
	private String n;//名字
	
	private String p;//手机

	
	
	public UserContact() {
		super();
	}

	
	public UserContact(String c, String n, String p) {
		super();
		this.c = c;
		this.n = n;
		this.p = p;
	}


	public String getC() {
		return c;
	}


	public void setC(String c) {
		this.c = c;
	}



	public String getN() {
		return n;
	}


	public void setN(String n) {
		this.n = n;
	}


	public String getP() {
		return p;
	}


	public void setP(String p) {
		this.p = p;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserContact other = (UserContact) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return   p + ":" + n + ":" +c ;
	}


	public static void main(String[] args) {
		UserContact relationPhone1 = new UserContact("父子", "wuaaa", "15867130607");
		UserContact relationPhone2 = new UserContact("父子", "wuaaa", "15867130607");
		UserContact relationPhone3 = new UserContact("父子", "wuaaa", "15867130607");
		UserContact relationPhone4 = new UserContact("父子", "wuaaa", "15867130607");
		UserContact relationPhone5 = new UserContact("父子", "wuaaa", "15867130607");
		UserContact relationPhone6 = new UserContact("父子", "wuaaa", "15867130607");
		
		
		ArrayList<UserContact> list=new ArrayList<>();
		list.add(relationPhone1);
		list.add(relationPhone2);
		list.add(relationPhone3);
		list.add(relationPhone4);
		list.add(relationPhone5);
		list.add(relationPhone6);
		String str=JSON.toJSONString(list);
		System.out.println("1:"+JSON.toJSONString(relationPhone1));
		System.out.println("2:"+str);
		
		List<UserContact> jsonArray = JSON.parseArray(str, UserContact.class);
		Set<UserContact> contacts=new LinkedHashSet<>();
		for (UserContact userContact : jsonArray) {
			contacts.add(userContact);
		}
		System.out.println(JSON.toJSON(contacts));
	}
}
