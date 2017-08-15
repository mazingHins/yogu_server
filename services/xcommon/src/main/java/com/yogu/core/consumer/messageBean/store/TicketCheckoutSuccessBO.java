package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 票核销成功 时 通知的 信息载体
 * 
 * @author sky
 *
 */
public class TicketCheckoutSuccessBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657301055264094792L;

	private long orderNo;// 订单编号
	private long storeId;// 商家id
	private long ticketId;// 票id
	private String ticketNo;// 票码
	private long uid;
	private long eventId;

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public long getUid() {
		return uid;
	}

	public long getEventId() {
		return eventId;
	}

	public TicketCheckoutSuccessBO() {
	}

	TicketCheckoutSuccessBO(long orderNo, long storeId, long ticketId, String ticketNo, long uid, long eventId) {
		super();
		this.orderNo = orderNo;
		this.storeId = storeId;
		this.ticketId = ticketId;
		this.ticketNo = ticketNo;
		this.uid = uid;
		this.eventId = eventId;
	}

	public static TicketCheckoutSuccessBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private long orderNo;// 订单编号
		private long storeId;// 商家id
		private long ticketId;// 票id
		private String ticketNo;// 票码
		private long uid;
		private long eventId;
		
		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		public Builder setTicketId(long ticketId) {
			this.ticketId = ticketId;
			return this;
		}

		public Builder setTicketNo(String ticketNo) {
			this.ticketNo = ticketNo;
			return this;
		}

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}
		
		public Builder setEventId(long eventId) {
			this.eventId = eventId;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public TicketCheckoutSuccessBO build() {
			return new TicketCheckoutSuccessBO(orderNo, storeId, ticketId, ticketNo, uid, eventId);
		}

		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.TICKET_CHECKOUT_SUCCESS,
					new TicketCheckoutSuccessBO(orderNo, storeId, ticketId, ticketNo, uid, eventId));
		}

	}
}
