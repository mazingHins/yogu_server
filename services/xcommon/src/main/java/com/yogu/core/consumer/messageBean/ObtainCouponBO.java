package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 领取优惠券的MQ处理bean
 * 
 * @author felix
 * @date 2016-02-27
 */
public class ObtainCouponBO implements Serializable {

	private static final long serialVersionUID = 4020862935370232505L;

	public ObtainCouponBO() {
	}

	ObtainCouponBO(String phone, String couponName, String faceValue, Long uid, long couponRuleId) {
		this.phone = phone;
		this.couponName = couponName;
		this.faceValue = faceValue;
		this.uid = uid;
		this.couponRuleId = couponRuleId;
	}

	public Long uid;

	/**
	 * 电话号码, 暂时支持单个 (被LogUtil加密过的)
	 */
	private String phone;

	/**
	 * 优惠券/卡包名
	 */
	private String couponName;

	/**
	 * 面值
	 */
	private String faceValue;

	private long couponRuleId;

	public Long getUid() {
		return uid;
	}

	public String getFaceValue() {
		return faceValue;
	}

	public String getPhone() {
		return phone;
	}

	public String getCouponName() {
		return couponName;
	}

	public long getCouponRuleId() {
		return couponRuleId;
	}

	/**
	 * 创建ObtainCouponBO的构造者
	 * 
	 * @return ObtainCouponBO的构造者
	 */
	public static ObtainCouponBO.Builder builder() {
		return new Builder();
	}

	public static class Builder {
		public Long uid;

		/**
		 * 电话号码, 暂时支持单个
		 */
		private String phone;

		/**
		 * 优惠券/卡包名
		 */
		private String couponName;

		/**
		 * 面值
		 */
		private String faceValue;
		
		private long couponRuleId;

		public Builder setUid(Long uid) {
			this.uid = uid;
			return this;
		}

		public Builder setFaceValue(String faceValue) {
			this.faceValue = faceValue;
			return this;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder setCouponName(String couponName) {
			this.couponName = couponName;
			return this;
		}

		public Builder setCouponRuleId(long couponRuleId) {
			this.couponRuleId = couponRuleId;
			return this;
		}
		
		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public ObtainCouponBO build() {
			return new ObtainCouponBO(phone, couponName, faceValue, uid, couponRuleId);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.OBTAIN_COUPON_OR_BAG,
					new ObtainCouponBO(phone, couponName, faceValue, uid, couponRuleId));
		}

	}
}
