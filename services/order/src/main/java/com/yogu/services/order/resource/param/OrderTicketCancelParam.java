package com.yogu.services.order.resource.param;

import javax.ws.rs.FormParam;

/**
 * 购票取消订单API接收参数
 * 
 * @author east
 * @date 2017年2月27日 下午2:56:43
 */
public class OrderTicketCancelParam {

	/**
	 * 订单编号
	 */
	@FormParam("orderNo")
	private long orderNo;

	/**
	 * 是否整体退款，1是0否
	 */
	@FormParam("isAll")
	private short isAll;

	/**
	 * 退款信息载体，isAll=0时，不为空
	 */
	@FormParam("tickInfo")
	private String tickInfo;// 购票信息集合

	/**
	 * 退款原因
	 */
	@FormParam("cancelReason")
	private String cancelReason;

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public short getIsAll() {
		return isAll;
	}

	public void setIsAll(short isAll) {
		this.isAll = isAll;
	}

	public String getTickInfo() {
		return tickInfo;
	}

	public void setTickInfo(String tickInfo) {
		this.tickInfo = tickInfo;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

}
