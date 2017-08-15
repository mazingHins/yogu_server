package com.yogu.core.consumer.handler.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * MQ处理短信发送 消息 bean
 * @author felix
 */
public class SmsNotifyBean implements Serializable {
	
	private static final long serialVersionUID = -5309565015358146282L;
	
	/** 用户ID 多个用引文逗号分隔 */
	private final String uids;
	
	/** 短信类型, 用于从config域获取短信模板 */
	private final String smsType;
	
	/** 短信参数, 用于拼接至模板 (严格按照顺序拼接) */
	private final List<String> smsParams;
	
	SmsNotifyBean(String uids, String smsType, List<String> smsParams) {
		this.uids = uids;
		this.smsType = smsType;
		this.smsParams = smsParams;
	}
	
	public static SmsNotifyBean.Builder builder(){
		return new Builder();
	}
	

	public String getUids() {
		return uids;
	}

	public String getSmsType() {
		return smsType;
	}

	public List<String> getSmsParams() {
		return smsParams;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * @author felix
	 *
	 */
	public static class Builder{
		/** 用户ID 多个用引文逗号分隔 */
		private String uids;
		
		/** 短信类型, 用于从config域获取短信模板 */
		private String smsType;
		
		/** 短信参数, 用于拼接至模板 (严格按照顺序拼接) */
		private List<String> smsParams;

		public Builder setUids(String uids) {
			this.uids = uids;
			return this;
		}

		public Builder setSmsType(String smsType) {
			this.smsType = smsType;
			return this;
		}
		
		/**
		 * 插入参数, 会使用MessageFormat将参数插入模板中, Consumer内请注意格式和先后顺序
		 * @param value 值
		 * @return
		 */
		public Builder addFormatValue(String value) {
			if (null == smsParams) 
				smsParams = new ArrayList<String>();
			smsParams.add(value);
			return this;
		}
		
		/**
		 * 批量给短信参数列表赋值 (不会替换原有参数, 而是追加到尾部)
		 * @param smsParams
		 * @return
		 */
		public Builder setSmsParams(List<String> smsParams) {
			if (null == this.smsParams) 
				this.smsParams = new ArrayList<String>();
			this.smsParams.addAll(smsParams);
			return this;
		}
		
		/**
		 * 构建SmsNotifyBean
		 * @return
		 */
		public SmsNotifyBean build() {
			return new SmsNotifyBean(uids, smsType, smsParams);
		}
	}

}
