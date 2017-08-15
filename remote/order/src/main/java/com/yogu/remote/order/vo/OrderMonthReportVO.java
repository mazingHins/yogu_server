package com.yogu.remote.order.vo;

import java.util.List;

/**
 * 按月份的报表数据（订单报表）
 * @author Hins
 * @date 2015年9月19日 下午5:03:22
 */
public class OrderMonthReportVO {
	
	/**
	 * 月份取消单数
	 */
	private int cancelOrder;

	/**
	 * 月份的总成交量
	 */
	private int tradeTotal;
	
	/**
	 * 报表月份
	 */
	private int month;

	/**
	 * 月份的订单数量报表
	 */
	private List<OrderChartReportVO> items;

	public int getCancelOrder() {
		return cancelOrder;
	}

	public void setCancelOrder(int cancelOrder) {
		this.cancelOrder = cancelOrder;
	}

	public int getTradeTotal() {
		return tradeTotal;
	}

	public void setTradeTotal(int tradeTotal) {
		this.tradeTotal = tradeTotal;
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public List<OrderChartReportVO> getItems() {
		return items;
	}

	public void setItems(List<OrderChartReportVO> items) {
		this.items = items;
	}
	
}
