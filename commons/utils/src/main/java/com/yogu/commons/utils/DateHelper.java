package com.yogu.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期辅助类
 * 
 * @author linyi 2014年2月26日
 */
public class DateHelper {

	// 返回日历
	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 获得 days 后的日期
	 * 
	 * @param date
	 *            - 指定时间
	 * @param days
	 *            - 多少天后，可以是负数
	 * @return days 后的日期
	 */
	public static Date getNextDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/**
	 * 获得 days 后的日期，以当前时间为基准
	 * 
	 * @param days
	 *            - 多少天后
	 * @return days 后的日期
	 */
	public static Date getNextDays(int days) {
		return getNextDays(new Date(), days);
	}

	/**
	 * 获得 seconds 后的时间
	 * 
	 * @param date
	 *            - 指定时间
	 * @param seconds
	 *            - 多少秒后，可以是负数
	 * @return seconds 后的时间
	 */
	public static Date getNextSeconds(Date date, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	/**
	 * 获得 minutes 后的时间
	 * 
	 * @param date
	 *            - 指定时间
	 * @param minutes
	 *            - 多少分钟后，可以是负数
	 * @return minutes 后的时间
	 */
	public static Date getNextMinutes(Date date, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}

	/**
	 * 返回当前日期对应的 00:00:00 ~ 23:59:59 的时间
	 * 
	 * @return
	 */
	public static Date[] getTodayRange() {
		return getDateRange(new Date());
	}

	/**
	 * 返回指定日期的00:00:00 ~ 23:59:59 的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getDateRange(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date beginTime = cal.getTime();

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		Date endTime = cal.getTime();
		return new Date[] { beginTime, endTime };
	}

	/**
	 * 返回上个月的1号00:00:00 ~ 该月最后一天23:59:59 的时间
	 * 
	 * @return date数组:date[0]上月的第一天；date[1]上月的最后一天
	 */
	public static Date[] getLastMonthDate() {
		return getLastMonthDate(new Date());
	}

	/**
	 * 返回上个月的1号00:00:00 ~ 该月最后一天23:59:59 的时间
	 * @param date - 指定日期的上个月
	 * @return date数组:date[0]上月的第一天；date[1]上月的最后一天
	 * @author linyi 2015/2/13
	 */
	public static Date[] getLastMonthDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);

		Date endTime = cal.getTime();

		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date beginTime = cal.getTime();
		return new Date[] { beginTime, endTime };
	}

	/**
	 * 返回指定日期的00:00:00 时间
	 * 
	 * @param date
	 *            需要修改的时间
	 * @return
	 */
	public static Date toDayStart(Date date) {
		return getDateRange(date)[0];
	}

	/**
	 * 返回指定日期的23:59:59时间
	 * 
	 * @param date
	 *            需要修改的时间
	 * @return
	 */
	public static Date toDayEnd(Date date) {
		return getDateRange(date)[1];
	}

	/**
	 * 获得 seconds 后的时间，以当前时间为基准
	 * 
	 * @param seconds
	 * @return
	 */
	public static Date getNextSeconds(int seconds) {
		return getNextSeconds(new Date(), seconds);
	}

	/**
	 * string类型转换为long类型
	 * 
	 * @param strTime
	 *            要转换的string类型的时间，
	 * @param formatType
	 *            要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 DateTimeFormat 里有定义
	 * @param idToSecond
	 *            true:转换成秒 false:返回毫秒
	 * @return
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	public static long stringToLong(String strTime, String formatType,
			boolean idToSecond) throws java.text.ParseException {
		Date date = stringToDate(strTime, formatType);
		if (date == null) {
			return 0;
		} else {
			long currentTime = date.getTime();
			return idToSecond ? currentTime / 1000 : currentTime;
		}
	}

	/**
	 * string类型转换为date类型
	 * 
	 * @param strTime
	 *            要转换的string类型的时间，
	 * @param formatType
	 *            要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 DateTimeFormat 里有定义
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date stringToDate(String strTime, String formatType)
			throws java.text.ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		Date date = null;
		date = formatter.parse(strTime);
		return date;
	}
	

	/**
	 * 
	 * @param date
	 *            要转换的date类型的时间，
	 * @param formatType
	 *            要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 DateTimeFormat 里有定义
	 * @return
	 * @throws java.text.ParseException
	 */
	public static String dateToString(Date date, String formatType) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		String str = null;
		str = formatter.format(date);
		return str;
	}

	/**
	 * 根据date获得年份
	 * 
	 * @param date
	 *            输入null的话，获得当前年份
	 * @return
	 */
	public static int getYearByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 根据date获得月份
	 * 
	 * @param date
	 *            输入null的话，获得当前月份
	 * @return
	 */
	public static int getMonthByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 返回从当月1号到date日期的天数 例如输入2014-04-18 则返回18
	 * 
	 * @param date
	 *            输入null的话，取当前日期
	 * @return
	 */
	public static int getDayByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		int day = cal.get(Calendar.DATE);
		return day;
	}

	/**
	 * 返回当月的第一天
	 * @param date - 指定的日期，如果不指定，默认为当前日期
	 * @return 当月的第一天
	 * @author linyi 2015/2/10
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 2天之间相差多少天，精确到日期，时、分、秒默认都设为0
     * 例如： 2014-11-06 23:59:59 与 2014-11-07 00:00:00 dayDiff的结果为 1
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int dayDiff(Date start, Date end) {
		Calendar startCalendar = getCalendar(start);
		Calendar endCalendar = getCalendar(end);
		startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);
		endCalendar.set(Calendar.HOUR_OF_DAY, 0);
		endCalendar.set(Calendar.MINUTE, 0);
		endCalendar.set(Calendar.SECOND, 0);
		endCalendar.set(Calendar.MILLISECOND, 0);
		long millSecDiff = endCalendar.getTimeInMillis()
				- startCalendar.getTimeInMillis();
		int dayDiff = Math.round((millSecDiff / (1000 * 60 * 60 * 24)));
		return dayDiff;
	}

    /**
     * 2个日期之间相隔多少秒
     * @param start
     * @param end
     * @return
     */
    public static int secondsBetweenDays(Date start, Date end){
        Calendar startCalendar = getCalendar(start);
        Calendar endCalendar = getCalendar(end);
        long millSecDiff = endCalendar.getTimeInMillis()
                - startCalendar.getTimeInMillis();
        return (int) (millSecDiff/1000);
    }

	/**
	 * 判断两个日期是否为同一天（只判断日期，不判断时，分，秒）
	 * 
	 * @param date1
	 *            需要判斷的日期
	 * @param date2
	 *            需要判断的日期
	 * @return
	 */
	public static boolean isTheSameDay(Date date1, Date date2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (date1 != null && date2 != null) {
			return StringUtils.equals(formatter.format(date1),
					formatter.format(date2));
		}
		return false;
	}

	/**
	 * 判断当前的时间是否在当天的statSpacingSecond秒内 例如statSpacingSecond为300秒，
	 * 如果现在时间是00:00:21 返回true 如果是00:05:01，返回false
	 * 
	 * @param statSpacingSecond
	 *            统计的间隔，单位秒 ，
	 * @return
	 */
	public static boolean isCompriseTime(int statSpacingSecond) {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		if (now.getTime() - cal.getTimeInMillis() <= statSpacingSecond * 1000) { // 统计昨天
			return true;
		} else {
			return false;
		}
	}

}
