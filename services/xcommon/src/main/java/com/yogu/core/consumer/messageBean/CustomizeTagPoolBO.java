package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

import com.yogu.core.consumer.BussinessType;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 从标签池选择了标签进行查询的消息BO
 *
 * @date 2016年12月28日 上午11:46:51
 * @author hins
 */
public class CustomizeTagPoolBO implements Serializable {
	
	private static final long serialVersionUID = 5585554274416037493L;
	
	/**
	 * 城市code
	 */
	private String cityCode;
	
	/**
	 * 选择的用户定制标签id，多个用英文逗号分隔
	 */
	private String customizeIds;
	
	public CustomizeTagPoolBO() {}
	
	CustomizeTagPoolBO(String cityCode, String customizeIds) {
		this.cityCode = cityCode;
		this.customizeIds = customizeIds;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getCustomizeIds() {
		return customizeIds;
	}

	public static CustomizeTagPoolBO.Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		
		/**
		 * 城市code
		 */
		private String cityCode;
		
		/**
		 * 选择的用户定制标签id，多个用英文逗号分隔
		 */
		private String customizeIds;
		
		public Builder setCityCode(String cityCode) {
			this.cityCode = cityCode;
			return this;
		}

		public Builder setCustomizeIds(String customizeIds) {
			this.customizeIds = customizeIds;
			return this;
		}

		/**
		 * 构造MQ Bean
		 * @return
		 */
		public CustomizeTagPoolBO build(){
			return new CustomizeTagPoolBO(cityCode, customizeIds);
		}
		/**
		 * 发送MQ请求
		 */
		public void request(){
			CommandMQProducer.get().sendJSON(BussinessType.SELECT_CUSTOMIZE_TAG_POOL, new CustomizeTagPoolBO(cityCode, customizeIds));
		}
		
	}
	
}
