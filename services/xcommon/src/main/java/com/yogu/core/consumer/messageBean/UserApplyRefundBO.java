package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.enums.ActionOrderSource;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 用户申请退款MQ处理的消息bean
 * @author felix
 */
public class UserApplyRefundBO implements Serializable {

	private static final long serialVersionUID = -3149395969973329674L;
	
	/** 公用区域 START***/
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;
	
	/** 订单号 */
	private long orderNo;
	
	/** 公用区域 END***/
	
	/** 短信区域 START***/
	
	/** 订单总金额 */
	private String totalFee;
	
	/** 短信区域 END***/
	
	/** 推送区域 START***/
	
	/** 餐厅名字 */
	private String storeName;
	
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
	
	public UserApplyRefundBO() {}
	
	UserApplyRefundBO(String uids, long orderNo, String storeName, String totalFee, long storeId, int serviceTime, long orderFee, short cancelSource, String serialNumber) {
		this.uids = uids;
		this.orderNo = orderNo;
		this.storeName = storeName;
		this.totalFee = totalFee;
		this.storeId = storeId;
		this.serviceTime = serviceTime;
		this.orderFee = orderFee;
		this.cancelSource = cancelSource;
		this.serialNumber = serialNumber;
	}
	
	/**
	 * 创建UserApplyRefundBean的构造者
	 * @return UserApplyRefundBean的构造者
	 */
	public static UserApplyRefundBO.Builder builder() {
		return new Builder();
	}
	
	public String getUids() {
		return uids;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public String getStoreName() {
		return storeName;
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
	
	public short getCancelSource() {
		return cancelSource;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public static class Builder{
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;
		
		/** 订单号 */
		private long orderNo;
		
		/** 餐厅名字 */
		private String storeName;
		
		/** 订单总金额 */
		private String totalFee;
		
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
		
		public Builder userApply() {
			this.cancelSource = ActionOrderSource.USER.getValue();
			return this;
		}
		
		public Builder storeApply() {
			this.cancelSource = ActionOrderSource.STORE.getValue();
			return this;
		}
		
		public Builder timerApply() {
			this.cancelSource = ActionOrderSource.TIMER.getValue();
			return this;
		}
		
		public Builder adminApply() {
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
		
		public Builder setTotalFee(String totalFee) {
			this.totalFee = totalFee;
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
		public UserApplyRefundBO build(){
			return new UserApplyRefundBO(uids, orderNo, storeName, totalFee, storeId, serviceTime, orderFee, cancelSource, serialNumber);
		}
		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.USER_APPLY_REFUND,
					new UserApplyRefundBO(uids, orderNo, storeName, totalFee, storeId, serviceTime, orderFee, cancelSource, serialNumber));
		}
	}
}
