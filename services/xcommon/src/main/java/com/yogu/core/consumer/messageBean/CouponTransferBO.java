package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

/**
 * 优惠券转让出去时的通知
 * 
 * @author sky
 *
 */
public class CouponTransferBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1325961573279404329L;

	private long uid;
	private long couponId;

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

}
