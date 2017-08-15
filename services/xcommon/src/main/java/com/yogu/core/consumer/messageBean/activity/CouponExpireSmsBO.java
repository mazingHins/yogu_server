package com.yogu.core.consumer.messageBean.activity;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 优惠券过期提醒短信sms BO对象(为什么不是push、sms同用一个对象， 产品说要灵活配置，可以单独发一种 or 两种都发)
 * 
 * @author sky
 *
 */
public class CouponExpireSmsBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7910835354573824749L;
	
	/** 公用区域 START ***/
	/** 用户ID, 多个用英文逗号分隔 */
	private String uids;

	private int step;//几天后过期

	/** 推送区域 END ***/

	public CouponExpireSmsBO() {
	}

	CouponExpireSmsBO(String uids, int step) {
		this.uids = uids;
		this.step = step;
	}

	/**
	 * 创建AcceptOrderBean的构造者
	 * 
	 * @return AcceptOrderBean的构造者
	 */
	public static CouponExpireSmsBO.Builder builder() {
		return new Builder();
	}

	public String getUids() {
		return uids;
	}

	public int getStep() {
		return step;
	}

	public static class Builder {
		/** 用户ID, 多个用英文逗号分隔 */
		private String uids;

		private int step;

		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setStep(int step) {
			this.step = step;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * 
		 * @return
		 */
		public CouponExpirePushBO build() {
			return new CouponExpirePushBO(uids, step);
		}

		/**
		 * 发送MQ请求
		 */
		public void request() {
			CommandMQProducer.get().sendJSON(BussinessType.COUPON_EXPIRE_NOTIFY_SMS, new CouponExpirePushBO(uids, step));
		}
	}

}
