package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

/**
 * 统一的短信通知 messageBean(producer 中 传递的 message body, consumer 获取该message body做相关业务操作)<br>
 * 
 * 用于MQ
 * 
 * @author felix
 */
public class CommonSmsMsgBean implements Serializable{

	private static final long serialVersionUID = -16345743279141662L;
	
	/**
	 * 用户ID, 多个用英文逗号分隔
	 */
	private String uids;
	
	/**
	 * 发送的短信消息
	 */
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

}
