package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.yogu.core.web.ParameterUtil;

/**\
 * 新的推送message bean
 * @author felix
 *
 */
public class MessagePushBean implements Serializable {
	private static final long serialVersionUID = -1172656007941440593L;

	/**
	 * 用户ID, 多个用英文逗号分隔
	 */
	private final String uids;
	
	/**
	 * 推送消息的自定义参数
	 */
	private final Map<String, Object> customFields;
	
	/**
	 * 推送消息
	 */
	private final String msg;
	
	/**
	 * 推送标题
	 */
	private final String title;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUids() {
		return uids;
	}

	public Map<String, Object> getCustomFields() {
		return customFields;
	}

	public String getMsg() {
		return msg;
	}

	public String getTitle() {
		return title;
	}

	MessagePushBean(String uids, Map<String, Object> customFields, String msg, String title) {
		this.uids = uids;
		this.customFields = customFields;
		this.msg = msg;
		this.title = title;
	}
	
	public static class Builder{
		/**
		 * 用户ID, 多个用英文逗号分隔
		 */
		private String uids;
		
		/**
		 * 推送消息的推送类型
		 */
		private short type;
		
		/**
		 * 推送消息的msgId 暂时无用
		 */
		private long msgId = 0;
		
		/**
		 * 推送消息
		 */
		private String msg;
		
		/**
		 * 推送标题
		 */
		private String title;

		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setType(short type) {
			this.type = type;
			return this;
		}

		public Builder setMsgId(long msgId) {
			this.msgId = msgId;
			return this;
		}

		public Builder setMsg(String msg) {
			this.msg = msg;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public MessagePushBean build(){
			ParameterUtil.assertNotBlank(uids, "用户ID参数不合法");
			ParameterUtil.assertGreaterThanOrEqual(type, 0, "推送类型");
			ParameterUtil.assertNotBlank(msg, "消息内容不能为空");
			ParameterUtil.assertGreaterThanZero(type, "操作类型不能为空");
			ParameterUtil.assertNotBlank(title, "推送标题不能为空");
			
			Map<String, Object> paramsMap = new HashMap<String, Object>(2);
			paramsMap.put("msgId", msgId);
			paramsMap.put("type", type);
			
			return new MessagePushBean(uids, paramsMap, msg, title);
		}
		
	}
	
	
}
