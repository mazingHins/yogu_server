package com.yogu.commons.utils;

import java.util.Calendar;
import java.util.Date;

import com.yogu.commons.utils.DateUtils;

/**
 * T+N结算入账相关工具类
 *
 * @date 2016年8月12日 下午2:40:02
 * @author hins
 */
public class WithdrawableTimeUtils {
	
	public static final String TIME_UNIT = "second";
	
	public static void main(String[] args) {
		System.out.println(DateUtils.formatDate(getWithdrawableStampStart("86400", "86400"), DateUtils.YYYY_MM_DD_HH_mm_ss));
		System.out.println(DateUtils.formatDate(getWithdrawableStampEnd("86400"), DateUtils.YYYY_MM_DD_HH_mm_ss));
		Date end = WithdrawableTimeUtils.getWithdrawableStampEnd("86400");
		Date beforeMinute = DateUtils.beforeMinute(end, 4 * 60);
		System.out.println(DateUtils.formatDate(beforeMinute, DateUtils.YYYY_MM_DD_HH_mm_ss));
	}
	
	/**
	 * 获取T+N结算入账查询条件的开始时间
	 * 
	 * @param buffer - 用户确认订单到商家可提现的缓冲时间,从config获取
	 * @param rangeTime - 待入账账户金钱转到可提现账户的定时任务处理的流水时间范围,从config获取
	 * @author hins
	 * @date 2016年8月12日 下午2:42:51
	 * @return void
	 */
	public static Date getWithdrawableStampStart(String buffer, String range) {
		Calendar calendar = DateUtils.getCalendar();
		calendar.setTime(new Date());

		// 获得结束时间
		int unit;
		switch (TIME_UNIT) {
		case "second":
			unit = Calendar.SECOND;
			break;
		case "minute":
			unit = Calendar.MINUTE;
			break;
		case "hour":
			unit = Calendar.HOUR;
			break;
		case "day":
			unit = Calendar.DAY_OF_MONTH;
			break;
		default:
			unit = Calendar.SECOND;
			break;
		}
		

		calendar.add(unit, Integer.parseInt("-" + buffer));
		
		// 获得起始时间
		calendar.add(unit, Integer.parseInt("-" + range));
		Date start = calendar.getTime();
		return start;
	}
	
	/**
	 * 获取T+N结算入账查询条件的结束时间
	 * 
	 * @param buffer - 用户确认订单到商家可提现的缓冲时间,从config获取
	 * @author hins
	 * @date 2016年8月12日 下午2:55:26
	 * @return Date
	 */
	public static Date getWithdrawableStampEnd(String buffer){
		Calendar calendar = DateUtils.getCalendar();
		calendar.setTime(new Date());

		// 获得结束时间
		int unit;
		switch (TIME_UNIT) {
		case "second":
			unit = Calendar.SECOND;
			break;
		case "minute":
			unit = Calendar.MINUTE;
			break;
		case "hour":
			unit = Calendar.HOUR;
			break;
		case "day":
			unit = Calendar.DAY_OF_MONTH;
			break;
		default:
			unit = Calendar.SECOND;
			break;
		}
		

		calendar.add(unit, Integer.parseInt("-" + buffer));
		Date end = calendar.getTime();
		
		return end;
	}
	

}
