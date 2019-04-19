package com.mod.loan.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 获取支付编号
	 *
	 * @return
	 */
	public static String getPayNumber(String prefix) {
		// TODO 目前生成规则为：日期精确到毫秒
		return prefix + TimeUtils.parseTime(new Date(), TimeUtils.dateformat6) + RandomUtils.generateRandomNum(4);
	}

	/**
	 * 银行卡保留后四位
	 * 
	 * @param bankCard
	 *            银行卡号
	 * @return
	 */
	public static String bankTailNo(String bankCard) {

		String pattern = "(?<=\\d{0})\\d(?=\\d{4})";

		if (bankCard == null || bankCard.isEmpty()) {
			return null;
		} else {
			return bankCard.replaceAll(pattern, "");
		}
	}

	/**
	 * 整数金额处理
	 * 
	 * @param money
	 * @return
	 */
	public static String moneyFormat(BigDecimal money) {
		return new DecimalFormat(".00").format(money);
	}

	/**
	 * 校验密码，必须由6~18位数字字母组成
	 * 
	 * @param password
	 * @return
	 */
	public static boolean verifyPassword(String password) {
		String pattern = "^(?!^[0-9]+$)(?!^[a-zA-Z]+$)(?!^[!@#$%&*]+$).{6,18}$";
		if (password == null || password.isEmpty()) {
			return false;
		} else {
			return Pattern.matches(pattern, password);
		}
	}

}
