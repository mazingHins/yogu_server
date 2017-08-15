package com.yogu.remote.order.vo;

import java.util.List;

/**
 * 门店订单报表VO
 * 
 * @author Hins
 * @date 2015年9月19日 下午5:00:08
 */
public class StoreOrderVO {

	/**
	 * 订单总金额，单位分
	 */
	private long totalFee;

	/**
	 * 实际收入。订单总金额-商家优惠金额
	 */
	private long autualFee;

	/**
	 * 商家优惠金额
	 */
	private long storeCouponFee;
	
	/**
	 * 订单总数量
	 */
	private long orderNumber;

	/**
	 * 客单价
	 */
	private long pct;
	
	/**
	 * 订单总金额，单位元，精确到2位小数。用于h5页面展示
	 */
	private String totalFeeFormat;
	
	/**
	 * 实际收入，单位元，精确到2位小数。用于h5页面展示
	 */
	private String autualFeeFormat;
	
	/**
	 * 商家优惠金额，单位元，精确到2位小数。用于h5页面展示
	 */
	private String storeCouponFeeFormat;
	
	/**
	 * 客单价，单位元，精确到2位小数。用于h5页面展示
	 */
	private String pctFormat;

	/**
	 * 图表展示的报表
	 */
	private List<OrderChartReportVO> chartOrderItems;
	
	/**
	 * 按每天展示的报表
	 */
	private List<OrderChartReportVO> dayOrderItems;
	
	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getAutualFee() {
		return autualFee;
	}

	public void setAutualFee(long autualFee) {
		this.autualFee = autualFee;
	}

	public long getStoreCouponFee() {
		return storeCouponFee;
	}

	public void setStoreCouponFee(long storeCouponFee) {
		this.storeCouponFee = storeCouponFee;
	}

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getPct() {
		return pct;
	}

	public void setPct(long pct) {
		this.pct = pct;
	}

	public List<OrderChartReportVO> getChartOrderItems() {
		return chartOrderItems;
	}

	public void setChartOrderItems(List<OrderChartReportVO> chartOrderItems) {
		this.chartOrderItems = chartOrderItems;
	}

	public List<OrderChartReportVO> getDayOrderItems() {
		return dayOrderItems;
	}

	public void setDayOrderItems(List<OrderChartReportVO> dayOrderItems) {
		this.dayOrderItems = dayOrderItems;
	}

	public String getTotalFeeFormat() {
		return totalFeeFormat;
	}

	public void setTotalFeeFormat(String totalFeeFormat) {
		this.totalFeeFormat = totalFeeFormat;
	}

	public String getAutualFeeFormat() {
		return autualFeeFormat;
	}

	public void setAutualFeeFormat(String autualFeeFormat) {
		this.autualFeeFormat = autualFeeFormat;
	}

	public String getStoreCouponFeeFormat() {
		return storeCouponFeeFormat;
	}

	public void setStoreCouponFeeFormat(String storeCouponFeeFormat) {
		this.storeCouponFeeFormat = storeCouponFeeFormat;
	}

	public String getPctFormat() {
		return pctFormat;
	}

	public void setPctFormat(String pctFormat) {
		this.pctFormat = pctFormat;
	}
	

}
