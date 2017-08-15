package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;

/**
 * 
 * @Description: 配送费信息VO
 * @author Hins
 * @date 2015年7月28日 上午10:50:23
 */
public class SendInfoVO implements Serializable {

	private static final long serialVersionUID = 9052730810835643600L;

	/** 配送费（分） */
	private long fee;

	/** 配送费说明 */
	private String content;
	
	/** 配送费标题,如：配送费 */
	private String title;
	
	/** 配送渠道，如顺丰专送 */
	private String channel;

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	

}
