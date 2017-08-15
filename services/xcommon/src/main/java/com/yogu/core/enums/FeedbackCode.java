package com.yogu.core.enums;

/**
 * 用户评价订单的反馈定义
 * @author felix
 */
public enum FeedbackCode {
	DELIVERY(1, "配送原因"),
	
	QUALITY(2, "质量问题"),
	
	VALUE(3, "性价比"),
	
	TABLEWARE(4, "餐具"),
	
	SERVICE(5, "服务"),
	
	OTHERS(6, "其他");
	
	private int code;
	
	private String reason;
	
	private FeedbackCode(int code, String reason) {
		this.code = code;
		this.reason = reason;
	}

	public int getCode() {
		return code;
	}

	public String getReason() {
		return reason;
	}
	
	public static FeedbackCode getFeedback(int code) {
		if (code == DELIVERY.getCode()) {
			return DELIVERY;
		} else if (code == QUALITY.getCode()) {
			return QUALITY;
		} else if (code == VALUE.getCode()) {
			return VALUE;
		} else if (code == TABLEWARE.getCode()) {
			return TABLEWARE;
		} else if (code == SERVICE.getCode()) {
			return SERVICE;
		} else if (code == OTHERS.getCode()) {
			return OTHERS;
		} else {
			return null;
		}
	}
}	
