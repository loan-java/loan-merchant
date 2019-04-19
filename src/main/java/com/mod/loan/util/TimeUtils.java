package com.mod.loan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.joda.time.DateTime;

public class TimeUtils {

	public final static String dateformat1 = "yyyy-MM-dd HH:mm:ss";
	public final static String dateformat2 = "yyyy-MM-dd";
	public final static String dateformat3 = "EEE MMM dd HH:mm:ss zzzZZZ yyyy";
	public final static String dateformat4 = "yyyyMMdd";
	public final static String dateformat5 = "yyyyMMddHHmmss";
	public final static String dateformat6 = "yyyyMMddHHmmssSSS";
	public final static String STARTTIME = "startTime";
	public final static String ENDTIME = "endTime";

	private TimeUtils() {
		throw new Error("can't instance this tool class");
	}

	public static DateTime getDateTime() {
		return new DateTime();
	}

	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat1);
		return sdf.format(new Date());
	}

	public static String getNowString() {
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat2);
		return sdf.format(new Date());
	}

	public static String parseTime(Date date, String dateformat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		return sdf.format(date);
	}

	/**
	 * 判断当前传入时间是不是今年
	 *
	 * @param date
	 * @return
	 */
	public static boolean checkThisYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int i = calendar.get(Calendar.YEAR);
		calendar.setTime(new Date());
		int j = calendar.get(Calendar.YEAR);
		return i == j;
	}

	/**
	 * 格式化这种类型的时间Wed Feb 01 00:00:00 CST+0800 2012
	 *
	 * @param str
	 * @return
	 */
	public static String parseTimeCST(String str, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(dateformat3, Locale.US);
		Date d = null;
		try {
			d = sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parseTime(d, dateformat2);
	}

	/**
	 * 格式化这种类型的时间20170111
	 *
	 * @param str
	 * @return
	 */
	public static Date parseTime(String str) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		Date d = null;
		try {
			d = sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static Date parseTime(String str, String dateformat) {
		SimpleDateFormat sf = new SimpleDateFormat(dateformat);
		Date d = null;
		try {
			d = sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 得到一年之前的时间
	 *
	 * @param str
	 * @return
	 */
	public static Date lastYearTime() {
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -1);
		String time = format2.format(calendar.getTime());
		try {
			date = format2.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 得到当前的时间yyyyMMdd格式
	 *
	 * @param str
	 * @return
	 */
	public static Date getNowDate() {
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		String time = format2.format(calendar.getTime());
		try {
			date = format2.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 默认定时开始关闭时间
	public static Date defaultTime(String time) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if ("startTime".equals(time)) {
			return date;
		} else {
			calendar.add(Calendar.DATE, 365);
			date = calendar.getTime();
			return date;
		}
	}

	public static int getTimeDiff(String startTime, String endTime) {
		if (startTime == null || startTime.isEmpty()) {
			return -1;
		}
		if (endTime == null || endTime.isEmpty()) {
			return -1;
		}
		long days = DateTime.parse(endTime).getMillis() - DateTime.parse(startTime).getMillis();
		Long time = days / (1000 * 60 * 60 * 24);
		return time.intValue();
	}
	
}
