package com.mod.loan.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 签名工具类
 * </p>
 *
 * @author wanghui
 * @version $Id: SignUtil.java, v 0.1 2014-6-17 下午4:26:06 wanghui Exp $
 */
public class SignUtil {

	public static String[] RSA_ARR;

	static {
		RSA_ARR = new String[] { "real_name", "cert_no", "verify_entity", "bank_account_no", "account_name", "phone_no",
				"validity_period", "verification_value", "telephone", "email", "organization_no", "legal_person",
				"legal_person_phone", "agent_name", "license_no", "agent_mobile" };
		Arrays.sort(RSA_ARR);
	}

	/**
	 * 创建http post发送数据的url连接
	 *
	 * @param params
	 *            转换数据 map
	 * @param encode
	 *            是否做urlencode
	 * @return url
	 */
	public static String createLinkString(Map<String, String> params, boolean encode) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		String charset = params.get("_input_charset");
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (encode) {
				try {
					value = URLEncoder.encode(URLEncoder.encode(value, charset), charset);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (i == keys.size() - 1) {
				prestr = prestr + key.trim() + "=" + value;
			} else {
				prestr = prestr + key.trim() + "=" + value + "&";
			}
		}

		return prestr;
	}

	public static Map<String, String> getParameterMap(HttpServletRequest request, boolean isFilter) {
		// 参数Map
		Map properties = request.getParameterMap();

		// 返回值Map
		Map<String, String> returnMap = new HashMap<String, String>();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			if (isFilter) {
				if (!name.equals("sign") && !name.equals("sign_type") && !name.equals("sign_version")) {
					Object valueObj = entry.getValue();
					if (null == valueObj) {
						value = "";
					} else if (valueObj instanceof String[]) {
						String[] values = (String[]) valueObj;
						for (int i = 0; i < values.length; i++) {
							value = values[i] + ",";
						}
						value = value.substring(0, value.length() - 1);
					} else {
						value = valueObj.toString();
					}
					returnMap.put(name, value);
				}
			} else {
				Object valueObj = entry.getValue();
				if (null == valueObj) {
					value = "";
				} else if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++) {
						value = values[i] + ",";
					}
					value = value.substring(0, value.length() - 1);
				} else {
					value = valueObj.toString();
				}
				returnMap.put(name, value);
			}
		}
		return returnMap;
	}

	/**
	 * 生成log跟踪号
	 *
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/*
	 * 交易类网关列表，用于判断请求网关地址
	 */
	public static String[][] get_trade_Interface_service() {
		String[][] trade_Interface_service = { { "create_hosting_collect_trade", "创建托管代收交易" },
				{ "create_single_hosting_pay_trade", "创建托管代付交易" }, { "create_batch_hosting_pay_trade", "创建批量托管代付交易" },
				{ "pay_hosting_trade", "托管交易支付" }, { "query_pay_result", "支付结果查询" },
				{ "query_hosting_trade", "托管交易查询" }, { "query_hosting_batch_trade", "托管交易批次查询" },
				{ "create_hosting_refund", "托管退款" }, { "query_hosting_refund", "托管退款查询" },
				{ "create_hosting_deposit", "托管充值" }, { "query_hosting_deposit", "托管充值查询" },
				{ "create_hosting_withdraw", "托管提现" }, { "query_hosting_withdraw", "托管提现查询" },
				{ "create_hosting_transfer", "转账接口" }, { "advance_hosting_pay", "支付推进" }, { "create_bid_info", "标的录入" },
				{ "query_bid_info", "标的信息查询" }, { "create_single_hosting_pay_to_card_trade", "创建单笔代付到提现卡交易" },
				{ "create_batch_hosting_pay_to_card_trade", "创建批量代付到提现卡交易" }, { "finish_pre_auth_trade", "代收完成" },
				{ "cancel_pre_auth_trade", "代收撤销" }, { "query_fund_yield", "5.1存钱罐基金收益率查询" } };
		return trade_Interface_service;
	}

	public static void toEncryptParam(String public_key, Map<String, String> params) throws Exception {
		Set<String> keySet = params.keySet();
		for (String name : keySet) {
			String value = params.get(name);
			if (Arrays.binarySearch(RSA_ARR, name) >= 0) {
				byte[] value_encrypt = RSA.encryptByPublicKey(value.trim().getBytes("utf-8"), public_key);
				String base64_value_encrypt = Base64.encodeBase64String(value_encrypt);
				// 将加密好的value放到map中替换原有值
				params.put(name, base64_value_encrypt);
			}
		}
	}

	public static String sign(String content, String signType, String signKey, String charset) throws Exception {
		if ("MD5".equalsIgnoreCase(signType)) {
			return MD5.toMD5(content + signKey, charset);
		} else if ("RSA".equalsIgnoreCase(signType)) {
			return RSA.sign(content, signKey, charset);
		}
		return "";
	}

	public static void main(String[] args) {
		String s = "{\"gmt_create\":\"20170807170456\",\"gmt_payment\":\"20170807170456\",\"notify_time\":\"20170807170937\",\"outer_trade_no\":\"201708071707059671911\",\"_input_charset\":\"UTF-8\",\"sign\":\"zPdZtG2IvarkWHgkFEMZkmRyXGo9HkbeOrukwFb4tlqMPvHHv+PkMqBI3ucw4HS2wejPCBq1cnau+fH6QMXdBJey5HttOcekKlq1X+c1/vDV+QakhaeiT/kUfdkoQlVZoiJsaezageiITZjPQmqc9N++u3yFaG3FxrRYMHVWmoc=\",\"pay_method\":\"balance^102.41^SAVING_POT\",\"version\":\"1.0\",\"notify_id\":\"201708070074856181\",\"notify_type\":\"trade_status_sync\",\"trade_amount\":\"102.41\",\"inner_trade_no\":\"111502096696231236460\",\"trade_status\":\"TRADE_FINISHED\",\"sign_type\":\"RSA\"}";
		Map<String, String> requestMap = JSON.parseObject(s, HashMap.class);
		String p = createLinkString(requestMap, true);
		System.out.println(p);
	}

	/**
	 * JuHeXinYong 签名加密后
	 *
	 * @return sign
	 */
	public static String getSign(Map<String, Object> paramMap) {
		String sign = getSignStr(paramMap);
		return MD5Cap.toMD5(sign);
	}

	/**
	 * JuHeXinYong 签名
	 *
	 * @return sign
	 */
	private static String getSignStr(Map<String, Object> paramMap) {
		//排序
		paramMap = sortMapByKey(paramMap);

		StringBuilder param = new StringBuilder();
		for (String key : paramMap.keySet()) {
			if (!"key".equals(key) && !"sign".equals(key) && StringUtils.isNotBlank(paramMap.get(key).toString())) {
				param.append(key).append("=").append(paramMap.get(key).toString()).append("&");
			}
		}
		param.append("key" + "=").append("e83fgehkJuHeXinYongContosedce9ff");
		return param.toString();
	}

	/**
	 * 使用 Map按key进行排序
	 *
	 * @param map 需要排序的Map
	 * @return 排序完成的Map
	 */
	private static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, Object> sortMap = new TreeMap<>(
				new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}

	/**
	 * 比较器类
	 */
	static class MapKeyComparator implements Comparator<String> {

		@Override
		public int compare(String str1, String str2) {

			return str1.compareTo(str2);
		}
	}
}
