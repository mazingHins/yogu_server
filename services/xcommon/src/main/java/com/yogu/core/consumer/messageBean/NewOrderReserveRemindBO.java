package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.Date;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 有新单进入待接单列表时的MQ 消息bean
 * @author felix
 */
public class NewOrderReserveRemindBO implements Serializable {

	private static final long serialVersionUID = -4518772199514734684L;

	/** 公用区域 START***/
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;

	/** 公用区域 END***/

	/** 短信区域 START***/

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

	/** 短信区域 END***/

	/** 推送区域 START***/

	/** 是否在服务范围 */
	private boolean inServiceRange;

	/** 推送区域 END***/

	/**
	 * 订单配送费（单位分）
	 */
	private long deliveryFee;

	/**
	 * 支付类型
	 */
	private short payType;

	/**
	 * 优惠券ID
	 */
	private long couponId;

	/**
	 * 支付编号
	 */
	private long payNo;

	/**
	 * 订单商品金额（单位分）
	 */
	private long goodsFee;

	/**
	 * 下单人
	 */
	private long uid;

	/** 寄送时间 */
	private Date deliveryTime;

	public NewOrderReserveRemindBO() {
	}

	NewOrderReserveRemindBO(String uids, long orderNo, boolean inServiceRange, long storeId, int serviceTime, long orderFee, long payNo, long goodsFee,
			long deliveryFee, short payType, long couponId, long uid, Date deliveryTime) {
		this.uids = uids;
		this.orderNo = orderNo;
		this.inServiceRange = inServiceRange;
		this.storeId = storeId;
		this.serviceTime = serviceTime;
		this.orderFee = orderFee;
		this.payNo = payNo;
		this.goodsFee = goodsFee;
		this.deliveryFee = deliveryFee;
		this.payType = payType;
		this.couponId = couponId;
		this.uid = uid;
		this.deliveryTime = deliveryTime;
	}

	/**
	 * 创建NewOrderBean的构造者
	 * @return NewOrderBean的构造者
	 */
	public static NewOrderReserveRemindBO.Builder builder() {
		return new Builder();
	}

	public String getUids() {
		return uids;
	}

	public boolean isInServiceRange() {
		return inServiceRange;
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

	public long getDeliveryFee() {
		return deliveryFee;
	}

	public short getPayType() {
		return payType;
	}

	public long getCouponId() {
		return couponId;
	}

	public long getPayNo() {
		return payNo;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public long getUid() {
		return uid;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public static class Builder {
		private String uids;

		private long orderNo;

		/** 是否在服务范围 */
		private boolean inServiceRange;

		public Builder setInServiceRange(boolean inServiceRange) {
			this.inServiceRange = inServiceRange;
			return this;
		}

		private long storeId;

		private int serviceTime;

		private long orderFee;

		/**
		 * 订单配送费（单位分）
		 */
		private long deliveryFee;

		/**
		 * 支付类型
		 */
		private short payType;

		/**
		 * 优惠券ID
		 */
		private long couponId;

		/**
		 * 支付编号
		 */
		private long payNo;

		/**
		 * 订单商品金额（单位分）
		 */
		private long goodsFee;

		/**
		 * 下单人
		 */
		private long uid;

		/** 寄送时间 */
		private Date deliveryTime;

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

		public Builder setDeliveryFee(long deliveryFee) {
			this.deliveryFee = deliveryFee;
			return this;
		}

		public Builder setPayType(short payType) {
			this.payType = payType;
			return this;
		}

		public Builder setCouponId(long couponId) {
			this.couponId = couponId;
			return this;
		}

		public Builder setPayNo(long payNo) {
			this.payNo = payNo;
			return this;
		}

		public Builder setGoodsFee(long goodsFee) {
			this.goodsFee = goodsFee;
			return this;
		}

		public Builder setUid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setDeliveryTime(Date deliveryTime) {
			this.deliveryTime = deliveryTime;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * @return
		 */
		public NewOrderReserveRemindBO build() {
			return new NewOrderReserveRemindBO(uids, orderNo, inServiceRange, storeId, serviceTime, orderFee, payNo, goodsFee, deliveryFee, payType, couponId, uid,
					deliveryTime);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.NEW_ORDER_RESERVE_REMIND, new NewOrderReserveRemindBO(uids, orderNo, inServiceRange, storeId, serviceTime,
					orderFee, payNo, goodsFee, deliveryFee, payType, couponId, uid, deliveryTime));
		}
	}

}
