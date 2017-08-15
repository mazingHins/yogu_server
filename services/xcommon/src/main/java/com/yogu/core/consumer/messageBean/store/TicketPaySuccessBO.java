package com.yogu.core.consumer.messageBean.store;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * ticket 订单支付成功， 通知ticket更新ticket状态的信息载体
 * 
 * @author sky
 *
 */
public class TicketPaySuccessBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8712607338415967358L;

	private long orderNo;
	private long uid;
	private String eventName;
	private int num;
	private String tickets;
	private String time;
	private String address;
	private String phone;
	private String aftersalePhone;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public long getUid() {
		return uid;
	}

	public String getEventName() {
		return eventName;
	}

	public int getNum() {
		return num;
	}

	public String getTickets() {
		return tickets;
	}

	public String getTime() {
		return time;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getAftersalePhone() {
		return aftersalePhone;
	}

	public TicketPaySuccessBO() {
	}

	public TicketPaySuccessBO(long orderNo, long uid, String eventName, int num, String tickets, String time,
			String address, String phone, String aftersalePhone) {
		super();
		this.orderNo = orderNo;
		this.uid = uid;
		this.eventName = eventName;
		this.num = num;
		this.tickets = tickets;
		this.time = time;
		this.address = address;
		this.phone = phone;
		this.aftersalePhone = aftersalePhone;
	}

	public static TicketPaySuccessBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private long orderNo;// 退款订单号

		private long uid;// 购买者uid

		private String eventName;
		private int num;
		private String tickets;
		private String time;
		private String address;
		private String phone;
		private String aftersalePhone;

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setEventName(String eventName) {
			this.eventName = eventName;
			return this;
		}

		public Builder setNum(int num) {
			this.num = num;
			return this;
		}

		public Builder setTickets(String tickets) {
			this.tickets = tickets;
			return this;
		}

		public Builder setTime(String time) {
			this.time = time;
			return this;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder setAftersalePhone(String aftersalePhone) {
			this.aftersalePhone = aftersalePhone;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public TicketPaySuccessBO build() {
			return new TicketPaySuccessBO(orderNo, uid, eventName, num, tickets, time, address, phone, aftersalePhone);
		}

		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.TICKET_ORDER_SUCCESS,
					new TicketPaySuccessBO(orderNo, uid, eventName, num, tickets, time, address, phone, aftersalePhone));
		}

	}

}
