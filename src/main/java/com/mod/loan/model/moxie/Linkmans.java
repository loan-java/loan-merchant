package com.mod.loan.model.moxie;

public class Linkmans {

	private Integer no;
	private String c;
	private String n;
	private String p;

	public Linkmans() {
	}

	public Linkmans(Integer no, String c, String n, String p) {
		this.no = no;
		this.c = c;
		this.n = n;
		this.p = p;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
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

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}
}
