package com.yogu.core.consumer.handler.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yogu.core.enums.MessagePushType;

/**
 * MQ处理推送 消息 bean
 * @author felix
 */
public class MsgPushBean implements Serializable {
	
	private static final long serialVersionUID = -7320966958403509357L;
	
	/** 用户ID 多个用引文逗号分隔 */
	private final String uids;
	
	/** 推送模板 */
	private final String msg;
	
	/** 推送的提示音 */
	private final String sound;

	/** 模板的拼接参数 */
	private final List<String> msgTemplateParams;
	
	/** 推送标题 */
	private final String title;
	
	/** 推送消息类型, 供客户端处理 */
	private final MessagePushType msgType;
	
	/** 消息ID 暂无用 */
	private final long msgId;
	
	/** 额外的自定义参数 */
	private final Map<String, Object> msgParams;
	
	MsgPushBean(String uids, String msg, String sound, List<String> msgTemplateParams, String title, MessagePushType msgType, long msgId, Map<String, Object> msgParams) {
		this.uids = uids;
		this.msg = msg;
		this.sound = sound;
		this.msgTemplateParams = msgTemplateParams;
		this.title = title;
		this.msgType = msgType;
		this.msgId = msgId;
		this.msgParams = msgParams;
	}
	
	public static MsgPushBean.Builder builder() {
		return new Builder();
	}

	public List<String> getMsgTemplateParams() {
		return msgTemplateParams;
	}

	public String getUids() {
		return uids;
	}

	public String getMsg() {
		return msg;
	}

	public String getTitle() {
		return title;
	}
	
	public String getSound() {
		return sound;
	}

	public MessagePushType getMsgType() {
		return msgType;
	}

	public long getMsgId() {
		return msgId;
	}

	public Map<String, Object> getMsgParams() {
		return msgParams;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * @author felix
	 *
	 */
	public static class Builder{
		private String uids;
		
		private String msg;
		
		private List<String> msgTemplateParams;

		private String title;
		
		private String sound = "";
		
		private MessagePushType msgType;
		
		private long msgId = 0;
		
		private Map<String, Object> msgParams;

		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}
		
		public Builder setSound(String sound) {
			this.sound = sound;
			return this;
		}

		public Builder setMsg(String msg) {
			this.msg = msg;
			return this;
		}
		
		/**
		 * 添加推送模板的参数值
		 * @param value 参数值
		 * @return
		 */
		public Builder setMsgTemplateParams(String value) {
			if (null == msgTemplateParams)
				msgTemplateParams = new ArrayList<String>();
			msgTemplateParams.add(value);
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setMsgType(MessagePushType msgType) {
			this.msgType = msgType;
			return this;
		}

		public Builder setMsgId(long msgId) {
			this.msgId = msgId;
			return this;
		}

		
		/**
		 * 添加自定义参数
		 * @param key 参数键
		 * @param value 参数值
		 * @return
		 */
		public Builder setMsgParams(String key, Object value) {
			if (null == msgParams)
				msgParams = new HashMap<String, Object>();
			msgParams.put(key, value);
			return this;
		}
		
		/**
		 * 构建MsgPushBean
		 * @return
		 */
		public MsgPushBean build() {
			return new MsgPushBean(uids, msg, sound, msgTemplateParams, title, msgType, msgId, msgParams);
		}
		
	}
	
}
