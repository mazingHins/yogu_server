package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户退款成功 的短信通知 messageBean
 * 
 * @author sky
 *
 */
public class UserRefundSuccessBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7371487167321176354L;

	private long refundId;//退款记录ID 
	
	private Date successTime;
	
	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public long getRefundId() {
		return refundId;
	}

	public void setRefundId(long refundId) {
		this.refundId = refundId;
	}
	

}
