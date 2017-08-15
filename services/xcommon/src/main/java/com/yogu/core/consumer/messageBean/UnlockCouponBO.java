package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 释放使用中优惠券的 MQ 消息实体
 * 
 * @author sky
 *
 */
public class UnlockCouponBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 617433999145129869L;

	private long uid;
	private long couponId;
	private long storeId;
	private long orderNo;
	private long payNo;
	private long totalFee;
	private long goodsFee;
	private long deliveryFee;
	private short payType;
	private short backStatus;

	public UnlockCouponBO() {
	}

	UnlockCouponBO(long uid, long couponId, long storeId, long orderNo, long payNo, long totalFee, long goodsFee, long deliveryFee,
			short payType, short backStatus) {
		super();
		this.uid = uid;
		this.couponId = couponId;
		this.storeId = storeId;
		this.orderNo = orderNo;
		this.payNo = payNo;
		this.totalFee = totalFee;
		this.goodsFee = goodsFee;
		this.deliveryFee = deliveryFee;
		this.payType = payType;
		this.backStatus = backStatus;
	}

	public static UnlockCouponBO.Builder builder() {
		return new Builder();
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public long getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(long deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public short getBackStatus() {
		return backStatus;
	}

	public void setBackStatus(short backStatus) {
		this.backStatus = backStatus;
	}

	public static class Builder {

		private long uid;
		private long couponId;
		private long storeId;
		private long orderNo;
		private long payNo;
		private long totalFee;
		private long goodsFee;
		private long deliveryFee;
		private short payType;
		private short backStatus;

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setCouponId(long couponId) {
			this.couponId = couponId;
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

		public Builder setTotalFee(long totalFee) {
			this.totalFee = totalFee;
			return this;
		}

		public Builder setGoodsFee(long goodsFee) {
			this.goodsFee = goodsFee;
			return this;
		}

		public Builder setDeliveryFee(long deliveryFee) {
			this.deliveryFee = deliveryFee;
			return this;
		}

		public Builder setPayType(short payType) {
			this.payType = payType;
			return this;
		}

		public Builder setBackStatus(short backStatus) {
			this.backStatus = backStatus;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public UnlockCouponBO build() {
			return new UnlockCouponBO(uid, couponId, storeId, orderNo, payNo, totalFee, goodsFee, deliveryFee, payType, backStatus);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.COUPON_UNLOCK,
					new UnlockCouponBO(uid, couponId, storeId, orderNo, payNo, totalFee, goodsFee, deliveryFee, payType, backStatus));
		}
	}
}
