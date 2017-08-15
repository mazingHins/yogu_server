package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;

/**
 * 
 * @Description: 订单轨迹说明关联内容
 * @author Hins
 * @date 2015年8月3日 上午11:32:38
 */
public class OrderTrackContentVO implements Serializable {

	private static final long serialVersionUID = -199021806701409335L;

	/** 联系号码 */
	private String phone;
	
	/** 关联人员 */
	private String username;
	
	/** 文本内容 */
	private String text;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
