package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 进行中订单通知的消息订阅的bean
 * @author felix
 */
public class OnGoingOrderNotifyBO implements Serializable{
	
	private static final long serialVersionUID = -1837692336456210965L;

	private long storeId;
	
	private long buyerId;
	
	private long orderNo;
	
	private short type;

	public long getStoreId() {
		return storeId;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public long getOrderNo() {
		return orderNo;
	}
	
	public short getType() {
		return type;
	}
	
	public OnGoingOrderNotifyBO() {}
	
	OnGoingOrderNotifyBO(long storeId, long buyerId, long orderNo, short type) {
		this.storeId = storeId;
		this.buyerId = buyerId;
		this.orderNo = orderNo;
		this.type = type;
	}
	
	public static OnGoingOrderNotifyBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		
		private long storeId;
		
		private long buyerId;
		
		private long orderNo;
		
		private short type;
		
		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}
		
		public Builder setBuyerId(long buyerId) {
			this.buyerId = buyerId;
			return this;
		}
		
		public Builder setType(short type) {
			this.type = type;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * @return
		 */
		public OnGoingOrderNotifyBO build(){
			return new OnGoingOrderNotifyBO(storeId, buyerId, orderNo, type);
		}
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.ONGOING_ORDER_AUTO_NOTIFY, new OnGoingOrderNotifyBO(storeId, buyerId, orderNo, type));
		}
	}
	
}
