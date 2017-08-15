package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 用户票过期的通知 信息载体, 包括 票可退款、不可退款
 * 
 * @author sky 2017-03-20
 *
 */
public class TicketExpireNotifyBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1206220398610447574L;

	private long orderNo;// 订单编号
	private long storeId;// 商家id
	private long ticketId;// 票id
	private long actId;// 活动id
	private short canRefund;// 是否可退款 1：是 其他：否

	public long getActId() {
		return actId;
	}

	public void setActId(long actId) {
		this.actId = actId;
	}

	public short getCanRefund() {
		return canRefund;
	}

	public void setCanRefund(short canRefund) {
		this.canRefund = canRefund;
	}

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

	public TicketExpireNotifyBO() {
	}

	TicketExpireNotifyBO(long orderNo, long storeId, long ticketId, long actId, short canRefund) {
		super();
		this.orderNo = orderNo;
		this.storeId = storeId;
		this.ticketId = ticketId;
		this.actId = actId;
		this.canRefund = canRefund;
	}

	public static TicketExpireNotifyBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private long orderNo;// 订单编号
		private long storeId;// 商家id
		private long ticketId;// 票id
		private long actId;// 活动id
		private short canRefund;// 是否可退款 1：是 其他：否

		public Builder setActId(long actId) {
			this.actId = actId;
			return this;
		}

		public Builder setCanRefund(short canRefund) {
			this.canRefund = canRefund;
			return this;
		}

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

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public TicketExpireNotifyBO build() {
			return new TicketExpireNotifyBO(orderNo, storeId, ticketId, actId, canRefund);
		}

		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.TICKET_EXPIRE_NOTIFY,
					new TicketExpireNotifyBO(orderNo, storeId, ticketId, actId, canRefund));
		}

	}

}
