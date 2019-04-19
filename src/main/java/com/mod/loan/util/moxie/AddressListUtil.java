package com.mod.loan.util.moxie;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.mod.loan.model.moxie.Linkmans;


/**
 * 通讯录辅助类
 */
public class AddressListUtil {
	
	//要过滤靠前显示的关键字
	public static String[] keyArr = new String[] { "爸", "妈", "爹", "娘", "父", "母", "公", "婆", "媳妇", "爱人", "太", "爷", "奶", "儿", "女", "兄", "弟", "姐", "妹", "嫂", "叔", "舅", "伯", "侄", "婶", "姨", "姑", "孙", "外甥", "贷", "中介", "口子", "还钱", "黑户", "代办", "养卡", "欠钱", "催", "金融", "假证", "套现", "钱", "借", "财富", "现金", "普惠", "宜信", "平安", "挖财", "呗", "51", "分期", "达飞", "信而富" };
	
	public static List<Linkmans> getLinkmans(String json) {
		return JSON.parseArray(json, Linkmans.class);
	}
	
	/**
	 * 获取通讯录手机号/手机号 
	 * @param addressLists
	 * @return
	 */
	public static Map<String, String> getMap(List<Linkmans>... addressLists) {
		Map<String, String> map=new HashMap<>(500);
		if (addressLists==null) {
			return map;
		}
		for (List<Linkmans> addressList : addressLists) {
			if (addressList==null) {
				continue;
			}
			for (Linkmans linkmans : addressList) {
				if (map.containsKey(linkmans.getP())) {
					map.put(linkmans.getP(),map.get(linkmans.getP())+"|"+ linkmans.getN());
				}else {
					map.put(linkmans.getP(),linkmans.getN());
				}
			}
		}
		return map;
	}
	
	/**
	 * 通讯录联系人关键字过滤排名靠前
	 * 
	 * @param json
	 * @return 排序之后的联系人信息集合
	 */
	public static List<Linkmans> getList(List<Linkmans> addressList) {
		List<Linkmans> cunzailist = new ArrayList<Linkmans>(200);
		List<Linkmans> bucunzailist = new ArrayList<Linkmans>(400);
		for (Linkmans model : addressList) {
			if (model != null) {
				int no = checkKeyWord(model.getN());
				model.setNo(no);
				if (no >= 0) {
					cunzailist.add(model);
				} else {
					bucunzailist.add(model);
				}
			}
		}
		// 将存在关键字的通讯录信息中的联系人姓名进行排序
		Collections.sort(cunzailist, new Comparator<Linkmans>() {
			public int compare(Linkmans a, Linkmans b) {
				int one = a.getNo();
				int two = b.getNo();
				return one - two;
			}
		});
		cunzailist.addAll(bucunzailist);
		return cunzailist;
	}

	private static int checkKeyWord(String name) {
		for (int i = 0; i < keyArr.length; i++) {
			if (name.contains(keyArr[i])) {
				return i;
			}
		}
		return -1;
	}

}
