package com.yogu.remote.order.vo;

import java.io.Serializable;

/**
 * 订单数量统计VO，用于报表展示
 * 
 * @author Hins
 * @date 2015年9月17日 下午7:42:23
 */
public class OrderChartReportVO implements Serializable {

	private static final long serialVersionUID = -9013974307612110027L;
	
	/**
	 * 报表日期格式MM/dd
	 */
	private String dayFormat;

	/**
	 * 统计时间，格式yyyyMMdd
	 */
	private int day;
	
	/**
	 * 报表内的数据（可能是金额，可能是订单数）
	 */
	private long number;

	public String getDayFormat() {
		return dayFormat;
	}

	public void setDayFormat(String dayFormat) {
		this.dayFormat = dayFormat;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
}
