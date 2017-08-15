package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

public class LoadCouponBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7577500914497286691L;

	private long couponRuleId;

	public long getCouponRuleId() {
		return couponRuleId;
	}

	public void setCouponRuleId(long couponRuleId) {
		this.couponRuleId = couponRuleId;
	}

}
