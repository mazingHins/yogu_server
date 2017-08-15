package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * ClassName: SFExpressDistributedBO 
 * @Description: 顺丰接单的MQ处理消息bean
 * @author east
 * @date 2016年10月26日
 */
public class SFExpressDistributedBO implements Serializable {

	private static final long serialVersionUID = -2067627327952040949L;

	/** 公用区域 START***/
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 商家id **/
	private long storeId;
	
	/** 订单号 */
	private long orderNo;
	
	/** 顺丰配送员姓名 */
	private String userName;
	
	/** 顺丰配送员手机  **/
	private String mobile;
	
	public SFExpressDistributedBO(){};
	
	public SFExpressDistributedBO(String uids, long storeId, long orderNo, String userName, String mobile) {
		this.uids = uids;
		this.storeId = storeId;
		this.orderNo = orderNo;
		this.userName = userName;
		this.mobile = mobile;
	}

	/**
	 * 创建SFExpressDistributedBO的构造者
	 * @return SFExpressDistributedBO的构造者
	 */
	public static SFExpressDistributedBO.Builder builder() {
		return new Builder();
	}
	
	public String getUids() {
		return uids;
	}

	public long getStoreId(){
		return storeId;
	}
	
	public long getOrderNo() {
		return orderNo;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getMobile() {
		return mobile;
	}

	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 商家Id **/
		private long storeId;
		
		/** 订单号 */
		private long orderNo;
		
		/** 顺丰配送员姓名 */
		private String userName;
		
		/** 顺丰配送员手机  **/
		private String mobile;
		
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

		public Builder setUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder setMobile(String mobile) {
			this.mobile = mobile;
			return this;
		}

		
		/**
		 * 构造MQ Bean
		 * @return
		 */
		public SFExpressDistributedBO build(){
			return new SFExpressDistributedBO(uids, storeId, orderNo, userName, mobile);
		}

		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.SF_EXPRESS_DISTRIBUTED, new SFExpressDistributedBO(uids, storeId, orderNo, userName, mobile));
		}
	}
	
}
