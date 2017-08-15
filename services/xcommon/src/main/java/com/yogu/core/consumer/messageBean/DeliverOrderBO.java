package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 开始配送订单的MQ 消息bean
 * @author felix
 */
public class DeliverOrderBO implements Serializable {

	private static final long serialVersionUID = -5238878139249593630L;

	/** 公用区域 START***/
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 订单号 */
	private long orderNo;
	
	/** 公用区域 END***/
	
	/** 短信区域 START***/
	/** 短信区域 END***/
	
	/** 推送区域 START***/
	
	/** 餐厅名字 */
	private String storeName;
	
	private long storeId;
	
	public long getStoreId() {
		return storeId;
	}

	/** 推送区域 END***/
	
	public DeliverOrderBO() {}
	
	DeliverOrderBO(String uids, long orderNo, String storeName, long storeId) {
		this.uids = uids;
		this.orderNo = orderNo;
		this.storeName = storeName;
		this.storeId = storeId;
	}
	
	/**
	 * 创建DeliverOrderBean的构造者
	 * @return DeliverOrderBean的构造者
	 */
	public static DeliverOrderBO.Builder builder() {
		return new Builder();
	}

	public String getUids() {
		return uids;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public String getStoreName() {
		return storeName;
	}
	
	
	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 订单号 */
		private long orderNo;
		
		/** 餐厅名字 */
		private String storeName;
		
		private long storeId;
		
		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}
		
		public Builder setStoreName(String storeName) {
			this.storeName = storeName;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public DeliverOrderBO build(){
			return new DeliverOrderBO(uids, orderNo, storeName, storeId);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.DELIVER_ORDER, new DeliverOrderBO(uids, orderNo, storeName, storeId));
		}
	}
}
