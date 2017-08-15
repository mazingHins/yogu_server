package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 商家接单, 制作完成, 商家配送, 商家送达时候的 messageBean(producer 中 传递的 message body, consumer 获取该message body做相关业务操作)<br>
 * 
 * @author Felix
 * @date 2015/9/28
 */
public class CommonMsgPushBean implements Serializable{

	private static final long serialVersionUID = -4261934486278433675L;
	
	/**
	 * 用户ID, 多个用英文逗号分隔
	 */
	private String uids;
	
	/**
	 * 推送消息的自定义参数
	 */
	private Map<String, Object> customFields = new HashMap<String, Object>();
	
	/**
	 * 推送消息
	 */
	private String msg;
	
	/**
	 * 推送标题
	 */
	private String title = "";

//	public short getType() {
//		return type;
//	}
//
//	public void setType(short type) {
//		this.type = type;
//	}

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

	public Map<String, Object> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, Object> customFields) {
		this.customFields = customFields;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
