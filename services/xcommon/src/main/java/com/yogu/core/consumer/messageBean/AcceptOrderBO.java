package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 商家接单的MQ处理的消息bean
 * @author felix
 */
public class AcceptOrderBO implements Serializable {

	private static final long serialVersionUID = -2067627327952040949L;

	/** 公用区域 START***/
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 订单号 */
	private long orderNo;
	
	/** 支付编号 */
	private long payNo;
	
	/** 公用区域 END***/
	
	/** 短信区域 START***/
	/** 短信区域 END***/
	
	/** 推送区域 START***/
	
	/** 餐厅名字 */
	private String storeName;
	
	private long storeId;
	
	/** 推送区域 END***/
	
	public AcceptOrderBO() {}
	
	public long getStoreId() {
		return storeId;
	}

	AcceptOrderBO(String uids, long orderNo, long payNo, String storeName, long storeId) {
		this.uids = uids;
		this.orderNo = orderNo;
		this.storeName = storeName;
		this.storeId = storeId;
		this.payNo = payNo;
	}
	
	/**
	 * 创建AcceptOrderBean的构造者
	 * @return AcceptOrderBean的构造者
	 */
	public static AcceptOrderBO.Builder builder() {
		return new Builder();
	}

	public String getUids() {
		return uids;
	}

	public long getOrderNo() {
		return orderNo;
	}
	
	public long getPayNo() {
		return payNo;
	}

	public String getStoreName() {
		return storeName;
	}
	
	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 订单号 */
		private long orderNo;
		
		/** 支付编号 */
		private long payNo;
		
		private long storeId;
		

		/** 餐厅名字 */
		private String storeName;
		
		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setStoreId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		public Builder setOrderNo(long orderNo) {
			this.orderNo = orderNo;
			return this;
		}
		
		public Builder setPayNo(long payNo) {
			this.payNo = payNo;
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
		public AcceptOrderBO build(){
			return new AcceptOrderBO(uids, orderNo, payNo, storeName, storeId);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.ACCEPT_ORDER, new AcceptOrderBO(uids, orderNo, payNo, storeName, storeId));
		}
	}
	
}
