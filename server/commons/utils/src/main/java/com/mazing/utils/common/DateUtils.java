package com.mazing.utils.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author hins
 *
 */
public class DateUtils {
	
	private DateUtils() {
	}

	public static final String YYMMDD = "yyMMdd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYMMDDHHMM = "yyMMddHHmm";
	public static final String YYYY_MM_DD_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String YYYYMMDDHHMM_CH = "yyyy年MM月dd日hh:mm";
	public static final String YYYYMMDDHHMM_CH_24 = "yyyy年MM月dd日HH:mm";
	public static final String DD = "dd";
	public static final String YYYYMM = "yyyyMM";
	public static final String MM_DD = "MM月dd日";
	public static final String HH_MM = "HH:mm";
	public static final String YYYY_MM_DD = "yyyy.MM.dd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String MM_DD_HH_MM = "MM月dd日 HH:mm";

	public static SimpleDateFormat getDateFormat(String pattern) {
		String key = "DateUtils_" + pattern;
		SimpleDateFormat format = ThreadLocalContext.getGlobalValue(key);
		if (null == format) {
			format = new SimpleDateFormat(pattern);
			// format = new SimpleDateFormat(pattern, Locale.US);
			// format.setTimeZone(TimeZone.getTimeZone("GMT"));
			ThreadLocalContext.setGlobalValue(key, format);
		}
		return format;
	}

	public static Calendar getCalendar() {
		return getCalendar(new Date());
	}

	public static Calendar getCalendar(Date time) {
		// String key = "DateUtils_Calendar";
		// Calendar c = ThreadLocalContext.getGlobalValue(key);
		// if (null == c) {
		// c = Calendar.getInstance();
		// ThreadLocalContext.setGlobalValue(key, c);
		// }
		// if (null != time)
		// c.setTime(time);
		// return c;

		Calendar c = Calendar.getInstance();
		if (null != time)
			c.setTime(time);
		return c;
	}

	/**
	 * 在一个线程里，返回同一个当前时间。 有时候在 Service、DAO 及其他的地方，需要取得同一个当前时间，记录到数据库，使这些地方的 new Date() 是一致的。
	 *
	 * @return 当前时间
	 * @author ten 2015/12/17
	 */
	public static Date getUniformCurrentTimeForThread() {
		String key = "DateUtils.getUniformCurrentTime";
		Date time = ThreadLocalContext.getGlobalValue(key);
		if (null == time) {
			time = new Date();
			ThreadLocalContext.putThreadValue(key, time);
		}
		return time;
	}

	/**
	 * 将当前时间（年月日）数字化<br>
	 * now() -> 20150821
	 */
	public static int currentTimeDay() {
		return currentTimeDay(new Date());
	}

	/**
	 * 将指定时间（年月日）数字化<br>
	 * date -> 20150821
	 */
	public static int currentTimeDay(Date date) {
		Args.notNull(date, "'date'");
		Calendar c = getCalendar(date);
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		int d = c.get(Calendar.DAY_OF_MONTH);
		return (y * 10000) + (m * 100) + (d);
	}

	/**
	 * 将指定时间（年月）数字化<br>
	 * date -> 201602
	 */
	public static int getCurrTimeMonth(Date date) {
		Args.notNull(date, "'date'");
		Calendar c = getCalendar(date);
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		return (y * 10000) + (m * 100);
	}

	/**
	 * currentTimeSeconds
	 */
	public static long currentTimeSeconds() {
		return timeSeconds(null);
	}

	/**
	 * 指定时间的秒数（date为空则返回当前时间）
	 */
	public static long timeSeconds(Date date) {
		long millis = (null != date ? date.getTime() : System.currentTimeMillis());
		return (millis / 1000L);
	}


	/**
	 * Formats the given date according to the specified pattern. The pattern must conform to that used by the {@link SimpleDateFormat
	 * simple date format} class.
	 * 
	 * @param date The date to format.
	 * @param pattern The pattern to use for formatting the date.
	 * @return A formatted date string.
	 * 
	 * @throws IllegalArgumentException If the given date pattern is invalid.
	 * 
	 * @see SimpleDateFormat
	 */
	public static String formatDate(Date date, String pattern) {
		Args.notNull(date, "'date'");
		Args.notNull(pattern, "'pattern'");

		SimpleDateFormat formatter = getDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * 计算指定时间到现在时间经过的天数
	 * 
	 * @param time - 指定时间
	 * @return 相隔天数
	 */
	public static int countDay(Date time) {
		Date now = new Date();
		int day = (int) (now.getTime() - time.getTime()) / (1000 * 60 * 60 * 24);// 转化day
		return day;
	}

	/**
	 * 计算2个时间段内经过的天数
	 * 
	 * @param beginTime - 开始日期时间
	 * @param endTime - 结束日期时间
	 * @return 相隔天数
	 */
	public static int countDay(Date beginTime, Date endTime) {
		int day = (int) (endTime.getTime() - beginTime.getTime()) / (1000 * 60 * 60 * 24);// 转化day
		return day;
	}

	/**
	 * 计算指定时间到现在时间经过的分钟数。<br>
	 * 时间向上取整。不足一分钟返回一分钟，超过分钟数整数，则取下一分钟（如4分01秒，则返回5分钟）
	 * 
	 * @param time - 指定时间
	 * @return
	 */
	public static int countMinute(Date time) {
		Date now = new Date();
		double second = now.getTime() - time.getTime();
		int minute = (int) (Math.ceil(second / (1000 * 60)));// 转化minute
		return minute;
	}

	/**
	 * 计算指定时间到现在时间经过的秒数
	 * 
	 * @param time -指定时间
	 * @return
	 */
	public static int countSecond(Date time) {
		Date now = new Date();
		int second = (int) (now.getTime() - time.getTime()) / (1000);// 转化second
		return second;
	}

	/**
	 * 计算2个时间经过的描述
	 * 
	 * @param beginTime - 开始时间
	 * @param endTime - 结束时间
	 * @return
	 */
	public static int countSecond(Date beginTime, Date endTime) {
		return (int) (endTime.getTime() - beginTime.getTime()) / (1000);// 转化second
	}

	/**
	 * 判断当前时间是否在指定的时间范围内（闭区间）<br>
	 * 如果begin、end两个时间都为空，那么直接返回false<br>
	 * 如果只传递了一个时间，则只判断[大于begin]或者[小于end]
	 * 
	 * @param begin 时间范围的起始时间（允许空）
	 * @param end 时间范围的结束时间（允许空）
	 * @author jfan
	 */
	public static boolean nowInRange(Date begin, Date end) {
		if (null == begin && null == end)
			return false;
		long now = System.currentTimeMillis();
		if (null != begin && now < begin.getTime())
			return false;
		if (null != end && now > end.getTime())
			return false;
		return true;
	}

	/**
	 * 返回指定日期的下一天
	 * 
	 * @param current 指定日期
	 * @return 返回下一天的Date
	 * @author ten 2015/8/21
	 */
	public static Date nextDay(Date current) {
		Calendar c = getCalendar(current);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 * 返回指定日期的后total天，total=1表示当前日期的后一天，也就是明天
	 * @param current 指定日期
	 * @return 返回后total天的Date
	 * @author sky 2016-02-29
	 */
	public static Date nextDay(Date current, int total) {
		Calendar c = getCalendar(current);
		c.add(Calendar.DAY_OF_MONTH, total);
		return c.getTime();
	}
	
	/**
	 * 返回指定日期的前day天
	 * 
	 * @author Hins
	 * @date 2015年9月17日 下午6:01:55
	 * 
	 * @param current 指定日期
	 * @param day 前几天的数量
	 * @return 返回上一天的Date
	 */
	public static Date beforeDay(Date current, int day) {
		Calendar c = getCalendar(current);
		c.add(Calendar.DAY_OF_MONTH, -day);
		return c.getTime();
	}

	/**
	 * 返回指定日期前month个月的第一天
	 * 
	 * @author Hins
	 * @date 2015年9月17日 下午6:01:55
	 * 
	 * @param current 指定日期
	 * @param month 前N个月
	 * @return 返回上一天的Date
	 */
	public static Date beforeMonthFirstDay(Date current, int month) {
		Calendar c = getCalendar(current);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MONTH, -month);
		return c.getTime();
	}

	/**
	 * 返回指定日期的月份
	 * 
	 * @param time - 指定时间
	 * @return 返回月份
	 */
	public static int getMonth(Date time) {
		Calendar c = getCalendar(time);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取指定日期一天的开始时间
	 * 
	 * @author Hins
	 * @date 2016年1月9日 下午6:56:45
	 * 
	 * @param time
	 * @return
	 */
	public static Date oneDayStartTime(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定日期一天的结束时间
	 * 
	 * @author Hins
	 * @date 2016年1月30日 上午11:45:54
	 * 
	 * @param time
	 * @return
	 */
	public static long oneDayEndTime(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTimeInMillis();
	}

	/**
	 * 
	 * 
	 * @author Hins
	 * @date 2016年1月9日 下午6:03:24
	 * 
	 * @param minute - 分钟数
	 * @return
	 */
	public static String minuteToHhMm(int minute) {
		int hour = minute / 60;
		int m = minute % 60;
		return (hour < 10 ? ("0" + hour) : hour) + ":" + (m < 10 ? ("0" + m) : m);
	}

	/**
	 * 计算两个日期的差, 以 X天X小时X分钟形式的字符串输出 # 请调用者保证 end > start, 否则输出空字符串
	 * 
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 */
	public static String getDateDiff(Date start, Date end) {
		long l = end.getTime() - start.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

		StringBuilder sb = new StringBuilder();
		boolean hasPrefix = false;
		if (day > 0) {
			sb.append(day).append("天");
			hasPrefix = true;
		}

		if (hour > 0 || hasPrefix) {
			sb.append(hour).append("小时");
			hasPrefix = true;
		}

		if (min > 0 || hasPrefix) {
			sb.append(min).append("分钟");
			hasPrefix = true;
		}

		return sb.toString();
	}


}
