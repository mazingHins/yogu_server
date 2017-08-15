package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 用户下单餐厅当前不在营业时间内，用户下单即通知用户
 * @author east
 * @date 2016年11月23日
 */
public class StoreDeliveredBO implements Serializable{
	private static final long serialVersionUID = -3095740971878897024L;
	
	private long storeId;
	
	private long buyerId;
	
	public long getStoreId() {
		return storeId;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public long getOrderNo() {
		return orderNo;
	}

	private long orderNo;
	
	public StoreDeliveredBO() {}
	
	StoreDeliveredBO(long storeId, long buyerId, long orderNo) {
		this.storeId = storeId;
		this.buyerId = buyerId;
		this.orderNo = orderNo;
	}
	
	public static StoreDeliveredBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private long storeId;
		
		private long buyerId;
		
		private long orderNo;
		
		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		public Builder setBuyerId(long buyerId) {
			this.buyerId = buyerId;
			return this;
		}

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * @return
		 */
		public StoreDeliveredBO build(){
			return new StoreDeliveredBO(storeId, buyerId, orderNo);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.STORE_NOT_IN_BUSINESS_NOTICE_USER, new StoreDeliveredBO(storeId, buyerId, orderNo));
		}
	}
	
}
