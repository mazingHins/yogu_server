package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.enums.pay.PayType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 商家取消订单的MQ 处理 消息bean
 * @author felix
 */
public class StoreCancelOrderBO implements Serializable {

	private static final long serialVersionUID = -7429667878640023230L;

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
	
	/** 支付方式 */
	private short payType;
	
	/**
	 * 门店ID
	 */
	private long storeId;

	/**
	 * 报表日期yyyyMMdd格式
	 */
	private int serviceTime;

	/**
	 * 交易费用（分）
	 */
	private long orderFee;
	
	/** 推送区域 END***/
	
	public StoreCancelOrderBO() {}
	
	StoreCancelOrderBO(String uids, long orderNo, String storeName, short payType, long storeId, int serviceTime, long orderFee) {
		this.storeId = storeId;
		this.serviceTime = serviceTime;
		this.orderFee = orderFee;
		this.uids = uids;
		this.orderNo = orderNo;
		this.storeName = storeName;
		this.payType = payType;
	}
	
	/**
	 * 创建StoreCancelOrderBean的构造者
	 * @return StoreCancelOrderBean的构造者
	 */
	public static StoreCancelOrderBO.Builder builder() {
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
	
	public short getPayType() {
		return payType;
	}
	
	public long getStoreId() {
		return storeId;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public long getOrderFee() {
		return orderFee;
	}



	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 订单号 */
		private long orderNo;
		
		/** 餐厅名字 */
		private String storeName;
		
		/** 支付方式 */
		private short payType;
		
		/**
		 * 门店ID
		 */
		private long storeId;

		/**
		 * 报表日期yyyyMMdd格式
		 */
		private int serviceTime;

		/**
		 * 交易费用（分）
		 */
		private long orderFee;
		
		public Builder setPayType(short payType) {
			this.payType = payType;
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
		
		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		public Builder setServiceTime(int serviceTime) {
			this.serviceTime = serviceTime;
			return this;
		}

		public Builder setOrderFee(long orderFee) {
			this.orderFee = orderFee;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * @return
		 */
		public StoreCancelOrderBO build(){
			return new StoreCancelOrderBO(uids, orderNo, storeName, payType, storeId, serviceTime, orderFee);
		}
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.STORE_CANCEL_ORDER, new StoreCancelOrderBO(uids, orderNo, storeName, payType, storeId, serviceTime, orderFee));
		}
	}
}
