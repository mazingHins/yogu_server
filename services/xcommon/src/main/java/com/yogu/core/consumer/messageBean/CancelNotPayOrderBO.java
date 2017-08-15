package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 取消超时订单
 * @author felix
 */
public class CancelNotPayOrderBO implements Serializable {

	private static final long serialVersionUID = -7779062194400517399L;

	/** 短信区域 START***/
	
	/** 短信区域 END***/
	
	/** 推送区域 START***/
	
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 餐厅名字 */
	private String storeName;
	
	/** 订单号 */
	private long orderNo;
	/** 推送区域 END***/
	
	public CancelNotPayOrderBO(){}
	
	CancelNotPayOrderBO(String uids, String storeName, long orderNo) {
		this.uids = uids;
		this.storeName = storeName;
		this.orderNo = orderNo;
	}
	
	/**
	 * 创建CancelNotPayOrderBean的构造者
	 * @return CancelNotPayOrderBean的构造者
	 */
	public static CancelNotPayOrderBO.Builder builder() {
		return new Builder();
	}

	public String getUids() {
		return uids;
	}
	
	public String getStoreName() {
		return storeName;
	}

	public long getOrderNo() {
		return orderNo;
	}
	
	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 餐厅名字 */
		private String storeName;
		
		/** 订单号 */
		private long orderNo;
		
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
		public CancelNotPayOrderBO build(){
			return new CancelNotPayOrderBO(uids, storeName, orderNo);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.CANCEL_NOT_PAY_ORDER, new CancelNotPayOrderBO(uids, storeName, orderNo));
		}
	}
	
}
