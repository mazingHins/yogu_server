package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

public class MazingPaySuccessBO implements Serializable {

	private static final long serialVersionUID = 3230941398250876626L;

	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;

	/** 订单号 */
	private long orderNo;

	/**
	 * 门店ID
	 */
	private long storeId;

	/**
	 * 门店名称
	 */
	private String storeName;

	/**
	 * 交易费用（分）
	 */
	private long orderFee;

	/**
	 * 报表日期yyyyMMdd格式
	 */
	private int serviceTime;

	/**
	 * 订单商品金额（单位分）
	 */
	private long goodsFee;

	/**
	 * 订单配送费（单位分）
	 */
	private long deliveryFee;

	/**
	 * 优惠券ID
	 */
	private long couponId;

	/**
	 * 支付编号
	 */
	private long payNo;

	/**
	 * 购买者ID
	 */
	private long uid;

	private String objectId;
	
	/** 下单的渠道, 暂时有iphone, android和H5(mobile) add by felix 2016-04-28 */
	private String orderChannel = "";

	public MazingPaySuccessBO() {
	}

	MazingPaySuccessBO(String uids, long orderNo, long storeId, String storeName, int serviceTime, long orderFee,
			long goodsFee, long deliveryFee, long couponId, long payNo, long uid, String objectId, String orderChannel) {
		this.uids = uids;
		this.orderNo = orderNo;
		this.storeId = storeId;
		this.storeName = storeName;
		this.serviceTime = serviceTime;
		this.orderFee = orderFee;
		this.couponId = couponId;
		this.goodsFee = goodsFee;
		this.deliveryFee = deliveryFee;
		this.payNo = payNo;
		this.uid = uid;
		this.objectId = objectId;
		this.orderChannel = orderChannel;
	}

	public String getUids() {
		return uids;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public long getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public long getOrderFee() {
		return orderFee;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public long getDeliveryFee() {
		return deliveryFee;
	}

	public long getCouponId() {
		return couponId;
	}

	public long getPayNo() {
		return payNo;
	}

	public long getUid() {
		return uid;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getOrderChannel(){
		return orderChannel;
	}
	
	/**
	 * 创建MazingPaySuccessBO的构造者
	 * 
	 * @return MazingPaySuccessBO的构造者
	 */
	public static MazingPaySuccessBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {

		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;

		/** 订单号 */
		private long orderNo;

		/**
		 * 门店ID
		 */
		private long storeId;

		private String storeName;

		/**
		 * 报表日期yyyyMMdd格式
		 */
		private int serviceTime;

		/**
		 * 交易费用（分）
		 */
		private long orderFee;

		/**
		 * 订单商品金额（单位分）
		 */
		private long goodsFee;

		/**
		 * 订单配送费（单位分）
		 */
		private long deliveryFee;

		/**
		 * 优惠券ID
		 */
		private long couponId;

		/**
		 * 支付编号
		 */
		private long payNo;

		/**
		 * 购买者ID
		 */
		private long uid;

		private String objectId;
		
		private String orderChannel;

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

		public Builder setStoreName(String storeName) {
			this.storeName = storeName;
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

		public Builder setGoodsFee(long goodsFee) {
			this.goodsFee = goodsFee;
			return this;
		}

		public Builder setDeliveryFee(long deliveryFee) {
			this.deliveryFee = deliveryFee;
			return this;
		}

		public Builder setPayNo(long payNo) {
			this.payNo = payNo;
			return this;
		}

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setCouponId(long couponId) {
			this.couponId = couponId;
			return this;
		}

		public Builder setObjectId(String objectId) {
			this.objectId = objectId;
			return this;
		}
		
		public Builder setOrderChannel(String orderChannel) {
			this.orderChannel = orderChannel;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public MazingPaySuccessBO build() {
			return new MazingPaySuccessBO(uids, orderNo, storeId, storeName, serviceTime, orderFee, goodsFee, deliveryFee,
					couponId, payNo, uid, objectId, orderChannel);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.MAZING_PAY_SUCCESS, new MazingPaySuccessBO(uids, orderNo,
					storeId, storeName, serviceTime, orderFee, goodsFee, deliveryFee, couponId, payNo, uid, objectId, orderChannel));
		}

	}

}
