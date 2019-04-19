package com.mod.loan.util;

import java.util.List;

public class ArrayUtil {
	
	private ArrayUtil(){
		throw new Error("can't instance this tool class");
	}
	/**
	 * String to Long[] by type
	 * @description 
	 * @param ids
	 * @param splitType
	 * @return
	 */
	public static Long[] toLongArray(String ids,String splitType){
		
		String[] strValue = ids.split(splitType);
		Long[] longValue = new Long[strValue.length]; 
		if (strValue.length > 0) {
			for (int i = 0; i < strValue.length; i++) {
				longValue[i]=Long.parseLong(strValue[i]);
			}
		}
		return longValue;
	}
	
	public static Integer[] toIntegerArray(String ids,String splitType){
		
		String[] strValue = ids.split(splitType);
		Integer[] intValue = new Integer[strValue.length]; 
		if (strValue.length > 0) {
			for (int i = 0; i < strValue.length; i++) {
				intValue[i]=Integer.parseInt(strValue[i]);
			}
		}
		return intValue;
	}

	public static String join(List<String> stringList,String separator){
		if (stringList==null) {
			return null;
		}
		StringBuilder result=new StringBuilder();
		boolean flag=false;
		for (String string : stringList) {
			if (flag) {
				result.append(separator);
			}else {
				flag=true;
			}
			result.append(string);
		}
		return result.toString();
	}
	
}
