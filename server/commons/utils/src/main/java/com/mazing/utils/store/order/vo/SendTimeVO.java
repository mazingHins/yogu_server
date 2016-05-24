package com.mazing.utils.store.order.vo;

import java.io.Serializable;

/**
 * 当天可选送达时间
 * @author hins
 *
 */
public class SendTimeVO implements Serializable {
	
	private static final long serialVersionUID = -3122191470019383161L;

	/** 显示文本 */
	private String name;
	
	/**
	 * 选中后的时间内容
	 */
	private String title;

	/** 时间毫秒数(格林威治时间) */
	private long timestamp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
