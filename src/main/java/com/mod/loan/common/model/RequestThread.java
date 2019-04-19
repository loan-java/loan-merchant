package com.mod.loan.common.model;

/**
 * requestBean的ThreadLocal Created by lijy on 2017/7/20.
 */
public class RequestThread {
	private static final ThreadLocal<RequestBean> requestThread = new ThreadLocal<RequestBean>();

	public static RequestBean get() {
		RequestBean s = requestThread.get();
		if (s == null) {
			s = new RequestBean();
			requestThread.set(s);
		}
		return s;
	}

	public static void remove() {
		requestThread.remove();
	}
}
