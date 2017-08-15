package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.enums.ActionOrderSource;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 确认收货消息bean
 * 
 * @author Hins
 * @date 2015年12月15日 下午3:59:57
 */
public class ConfirmReceiptBO implements Serializable {

	private static final long serialVersionUID = 8280850260566249459L;
	
	/** 报表 START***/
	/**
	 * 门店ID
	 */
	private long storeId;

	/**
	 * 报表日期yyyyMMdd格式
	 */
	private int serviceTime;

	/**
	 * 线上支付费用
	 */
	private long onlineFee;
	
	/**
	 * 货到付款费用
	 */
	private long cashFee;
	
	private long buyerId;
	
	/**
	 * 订单编号
	 */
	private long orderNo;
	
	/**
	 * 下单人id
	 */
	private long uid;
	
	public long getOrderNo() {
		return orderNo;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public short getConfirmSource() {
		return confirmSource;
	}
	
	private short confirmSource;
	
	/** 报表区域 END***/
	
	public ConfirmReceiptBO() {}
	
	ConfirmReceiptBO(long storeId, int serviceTime, long onlineFee, long cashFee, long buyerId, short confirmSource, long orderNo, long uid) {
		this.storeId = storeId;
		this.serviceTime = serviceTime;
		this.onlineFee = onlineFee;
		this.cashFee = cashFee;
		this.buyerId = buyerId;
		this.confirmSource = confirmSource;
		this.orderNo = orderNo;
		this.uid = uid;
	}
	
	/**
	 * 创建ConfirmReceiptBO的构造者
	 * @return ConfirmReceiptBO的构造者
	 */
	public static ConfirmReceiptBO.Builder builder() {
		return new Builder();
	}
	
	public long getStoreId() {
		return storeId;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public long getOnlineFee() {
		return onlineFee;
	}

	public long getCashFee() {
		return cashFee;
	}
	
	public long getUid() {
		return uid;
	}

	public static class Builder{
		/**
		 * 门店ID
		 */
		private long storeId;

		/**
		 * 报表日期yyyyMMdd格式
		 */
		private int serviceTime;

		/**
		 * 线上支付费用
		 */
		private long onlineFee;
		
		/**
		 * 货到付款费用
		 */
		private long cashFee;
		
		private long buyerId;
		
		/**
		 * 确认收货者
		 */
		private short confirmSource;
		
		/**
		 * 订单编号
		 */
		private long orderNo;
		
		/**
		 * 下单人id
		 */
		private long uid;
		
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
		
		public Builder userConfirm() {
			this.confirmSource = ActionOrderSource.USER.getValue();
			return this;
		}
		
		public Builder storeConfirm() {
			this.confirmSource = ActionOrderSource.STORE.getValue();
			return this;
		}
		
		public Builder timerConfirm() {
			this.confirmSource = ActionOrderSource.TIMER.getValue();
			return this;
		}
		
		public Builder adminConfirm() {
			this.confirmSource = ActionOrderSource.ADMIN.getValue();
			return this;
		}

		public Builder setServiceTime(int serviceTime) {
			this.serviceTime = serviceTime;
			return this;
		}

		public Builder setOnlineFee(long onlineFee) {
			this.onlineFee = onlineFee;
			return this;
		}

		public Builder setCashFee(long cashFee) {
			this.cashFee = cashFee;
			return this;
		}
		
		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * @return
		 */
		public ConfirmReceiptBO build(){
			return new ConfirmReceiptBO(storeId, serviceTime, onlineFee, cashFee, buyerId, confirmSource, orderNo, uid);
		}
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.CONFIRM_RECEIPT, new ConfirmReceiptBO(storeId, serviceTime, onlineFee, cashFee, buyerId, confirmSource, orderNo, uid));
		}
	}


}
