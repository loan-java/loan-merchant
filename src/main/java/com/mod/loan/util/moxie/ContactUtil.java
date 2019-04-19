package com.mod.loan.util.moxie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.mod.loan.model.moxie.CallInContactList;
import com.mod.loan.model.moxie.CallOutContactList;
import com.mod.loan.model.moxie.ContactList;

/**
 * 通讯录辅助类
 */
public class ContactUtil {

	public static final Logger logger = LoggerFactory.getLogger(ContactUtil.class);

	/**
	 * 获取呼入通话记录，并按照通话次数排序
	 * 
	 * @param json
	 * @return
	 */
	public static List<CallInContactList> getCallInContactList(List<ContactList> listContactList) {
		List<CallInContactList> callInList = new ArrayList<CallInContactList>();

		// 循环遍历通话详情信息
		if (null != listContactList) {
			for (int i = 0; i < listContactList.size(); i++) {
				ContactList c = (ContactList) listContactList.get(i);
				if (null != c) {
					// 判断呼入次数和呼入时长
					if (c.getCall_in_cnt() > 0 || c.getCall_in_len() > 0.0) {
						CallInContactList callIn = new CallInContactList();
						BeanUtils.copyProperties(c, callIn);
						callInList.add(callIn);
					}
				}
			}
			// 按照通话次数比较
			Collections.sort(callInList, new Comparator<CallInContactList>() {
				public int compare(CallInContactList a, CallInContactList b) {
					int one = a.getCall_in_cnt();
					int two = b.getCall_in_cnt();
					return two - one;
				}
			});
		}
		return callInList;
	}

	/**
	 * 获取通话记录，并按照通话次数排序
	 * 
	 * @param json
	 * @return
	 */
	public static List<CallOutContactList> getCallOutContactList(List<ContactList> listContactList) {
		List<CallOutContactList> callOutList = new ArrayList<CallOutContactList>();
		// 循环遍历通话详情信息
		if (null != listContactList) {
			for (int i = 0; i < listContactList.size(); i++) {
				ContactList c = listContactList.get(i);
				if (null != c) {
					// 判断呼入次数和呼入时长
					if (c.getCall_out_cnt() > 0 || c.getCall_out_len() > 0.0) {
						CallOutContactList callOut = new CallOutContactList();
						BeanUtils.copyProperties(c, callOut);
						callOutList.add(callOut);
					}
				}
			}
			Collections.sort(callOutList, new Comparator<CallOutContactList>() {
				public int compare(CallOutContactList a, CallOutContactList b) {
					int one = a.getCall_out_cnt();
					int two = b.getCall_out_cnt();
					return two - one;
				}
			});
		}
		return callOutList;
	}

	/**
	 * 获取被叫信息
	 * 
	 * @param addressList
	 * @param contact_list
	 * @return
	 */
	public static List<CallInContactList> getCallInContactList(Map<String, String> addressList, List<ContactList> listContactList) {
		List<CallInContactList> callInList = getCallInContactList(listContactList);
		for (int i = 0; i < callInList.size(); i++) {
			CallInContactList callIn = callInList.get(i);
			String name = addressList.get(callIn.getPhone_num());// 姓名
			callIn.setName(name == null ? "" : name);
		}
		return callInList;
	}

	/**
	 * 
	 * @param addressList
	 * @param contact_list
	 * @return
	 */
	public static List<CallOutContactList> getCallOutContactList(Map<String, String> addressList, List<ContactList> listContactList) {
		List<CallOutContactList> callOutList = getCallOutContactList(listContactList);
		for (int i = 0; i < callOutList.size(); i++) {
			CallOutContactList callout = callOutList.get(i);
			String name = addressList.get(callout.getPhone_num());// 姓名
			callout.setName(name == null ? "" : name);
		}
		return callOutList;
	}

}
