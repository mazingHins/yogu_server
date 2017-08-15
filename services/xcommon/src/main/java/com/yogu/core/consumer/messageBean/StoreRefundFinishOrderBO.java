package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.consumer.messageBean.StoreCancelOrderBO.Builder;
import com.yogu.core.enums.pay.PayType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 商家对已完成订单退款的MQ 处理 消息bean
 *
 * @date 2016年8月15日 下午4:04:48
 * @author hins
 */
public class StoreRefundFinishOrderBO implements Serializable {

	private static final long serialVersionUID = 1565523205965150855L;

	/** 公用区域 START ***/
	/** 用户ID */
	private long uid;

	/** 订单号 */
	private long orderNo;

	/** 公用区域 END ***/

	/** 推送区域 START ***/

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

	/** 推送区域 END ***/

	public StoreRefundFinishOrderBO() {
	}

	StoreRefundFinishOrderBO(long uid, long orderNo, short payType, long storeId, int serviceTime, long orderFee) {
		this.storeId = storeId;
		this.serviceTime = serviceTime;
		this.orderFee = orderFee;
		this.uid = uid;
		this.orderNo = orderNo;
		this.payType = payType;
	}

	/**
	 * 创建StoreRefundFinishOrderBO的构造者
	 * 
	 * @return StoreRefundFinishOrderBO的构造者
	 */
	public static StoreRefundFinishOrderBO.Builder builder() {
		return new Builder();
	}

	public long getUid() {
		return uid;
	}

	public long getOrderNo() {
		return orderNo;
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

	public static class Builder {
		/** 用户ID, 多个用英文逗号分隔 */
		private long uid;

		/** 订单号 */
		private long orderNo;

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

		public Builder setUid(long uid) {
			this.uid = uid;
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
		 * 
		 * @return
		 */
		public StoreRefundFinishOrderBO build() {
			return new StoreRefundFinishOrderBO(uid, orderNo, payType, storeId, serviceTime, orderFee);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.STORE_APPLY_REFUND_FINISH,
					new StoreRefundFinishOrderBO(uid, orderNo, payType, storeId, serviceTime, orderFee));
		}
	}

}
