package com.yogu.services.order.resource.param;

import javax.ws.rs.FormParam;

/**
 * 购票预订单预支付API接收参数
 * 
 * @author east
 * @date 2017年2月27日 下午2:56:43
 */
public class OrderTicketSettleParam {

	@FormParam("eventId")
	private long eventId;

	/**
	 * 购买信息载体
	 */
	@FormParam("tickInfo")
	private String tickInfo;// 购票信息集合

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getTickInfo() {
		return tickInfo;
	}

	public void setTickInfo(String tickInfo) {
		this.tickInfo = tickInfo;
	}

}
