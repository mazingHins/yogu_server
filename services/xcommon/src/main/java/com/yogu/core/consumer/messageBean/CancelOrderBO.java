package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.enums.ActionOrderSource;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 取消订单的MQ 消息bean
 * @author felix
 */
public class CancelOrderBO implements Serializable {

	private static final long serialVersionUID = -6778492700102202175L;
	
	/** 短信区域 START***/
	
	
	/** 短信区域 END***/
	
	/** 推送区域 START***/
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 餐厅名字 */
	private String storeName;
	
	/** 订单号 */
	private long orderNo;
	
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
	
	/**
	 * 订单编号
	 */
	private String serialNumber;
	
	/**
	 * 取消来源(用户, 商家, 定时任务, 管理员)
	 */
	private short cancelSource;
	
	/** 推送区域 END***/
	
	public CancelOrderBO(){}
	
	CancelOrderBO(String uids, String storeName, long orderNo, long storeId, int serviceTime, long orderFee, short cancelSource, String serialNumber) {
		this.uids = uids;
		this.storeName = storeName;
		this.orderNo = orderNo;
		this.storeId = storeId;
		this.serviceTime = serviceTime;
		this.orderFee = orderFee;
		this.cancelSource = cancelSource;
		this.serialNumber = serialNumber;
	}
	
	/**
	 * 创建CancelOrderBean的构造者
	 * @return CancelOrderBean的构造者
	 */
	public static CancelOrderBO.Builder builder() {
		return new Builder();
	}

	public String getUids() {
		return uids;
	}
	
	public short getCancelSource() {
		return cancelSource;
	}
	
	public String getStoreName() {
		return storeName;
	}

	public long getOrderNo() {
		return orderNo;
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
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 餐厅名字 */
		private String storeName;
		
		/** 订单号 */
		private long orderNo;
		
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
		
		/**
		 * 订单编号
		 */
		private String serialNumber = "";
		
		/**
		 * 取消来源(用户, 商家, 定时任务, 管理员)
		 */
		private short cancelSource;
		
		public Builder setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
			return this;
		}
		
		public Builder userCancel() {
			this.cancelSource = ActionOrderSource.USER.getValue();
			return this;
		}
		
		public Builder storeCancel() {
			this.cancelSource = ActionOrderSource.STORE.getValue();
			return this;
		}
		
		public Builder timerCancel() {
			this.cancelSource = ActionOrderSource.TIMER.getValue();
			return this;
		}
		
		public Builder adminCancel() {
			this.cancelSource = ActionOrderSource.ADMIN.getValue();
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
		public CancelOrderBO build(){
			return new CancelOrderBO(uids, storeName, orderNo, storeId, serviceTime, orderFee, cancelSource, serialNumber);
		}
		
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.CANCEL_ORDER,
					new CancelOrderBO(uids, storeName, orderNo, storeId, serviceTime, orderFee, cancelSource, serialNumber));
		}

		public Builder setStoreName(String storeName) {
			this.storeName = storeName;
			return this;
		}
	}
}
