package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
/**
 * 订单按周查询返回的VO类
 * @author jack
 *
 */
public class OrderByDateVO implements Serializable{

	private static final long serialVersionUID = 1229826117189494407L;

	//日期
	private String day;
	//订单金额
	private long totalFee;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	@Override
	public String toString() {
		return "OrderByDateVO [day=" + day + ", totalFee=" + totalFee + "]";
	}

}
