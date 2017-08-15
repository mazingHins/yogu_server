package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 票 退款的 消息载体, 包括 申请退款、退款成功 等状态通知
 * 
 * @author sky 2017-03-07
 *
 */
public class TicketRefundBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2760037820856973783L;

	private long orderNo;// 退款订单号

	private String ticketIds;// 退款的ticketId串, 但类型为 ALL=整单退款 时, 该值可以为空

	private short refundType;// 1:整单退 2：部分退

	private short refundStatus;// 1：申请退款 2：退款成功

	private long uid;// 购买者uid

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public String getTicketIds() {
		return ticketIds;
	}

	public void setTicketIds(String ticketIds) {
		this.ticketIds = ticketIds;
	}

	public short getRefundType() {
		return refundType;
	}

	public void setRefundType(short refundType) {
		this.refundType = refundType;
	}

	public short getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(short refundStatus) {
		this.refundStatus = refundStatus;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public TicketRefundBO() {
	}

	TicketRefundBO(long orderNo, String ticketIds, short refundType, short refundStatus, long uid) {
		super();
		this.orderNo = orderNo;
		this.ticketIds = ticketIds;
		this.refundType = refundType;
		this.refundStatus = refundStatus;
		this.uid = uid;
	}

	public static TicketRefundBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private long orderNo;// 退款订单号

		private String ticketIds;// 退款的ticketId串, 但类型为 ALL=整单退款 时, 该值可以为空

		private short refundType;// 1:整单退 2：部分退

		private short refundStatus;// 1：申请退款 2：退款成功

		private long uid;// 购买者uid

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder setTicketIds(String ticketIds) {
			this.ticketIds = ticketIds;
			return this;
		}

		public Builder setRefundType(short refundType) {
			this.refundType = refundType;
			return this;
		}

		public Builder setRefundStatus(short refundStatus) {
			this.refundStatus = refundStatus;
			return this;
		}

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public TicketRefundBO build() {
			return new TicketRefundBO(orderNo, ticketIds, refundType, refundStatus, uid);
		}

		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.TICKET_REFUND,
					new TicketRefundBO(orderNo, ticketIds, refundType, refundStatus, uid));
		}

	}

}
