package com.yogu.merchant.log.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.consumer.BussinessType;
import com.yogu.core.enums.merchant.MerchantLogModule;
import com.yogu.core.enums.merchant.MerchantLogType;
import com.yogu.core.web.ParameterUtil;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 商家操作日志的
 * @author felix
 */
public class MQMerchantLogInfo {
	private static final Logger logger = LoggerFactory.getLogger(MQMerchantLogInfo.class);
	
	/** 用户ID */
	private final long uid;
	
	/** 门店ID */
	private final long storeId;
	
	/** 操作类型 INSERT/UPDATE/DELETE */
	private final short type;
	
	/** 操作模块 STORE/DISH/ORDER/MONEY */
	private final short module;
	
	/** 操作内容 */
	private final String content;
	
	MQMerchantLogInfo(final long uid, final long storeId, final short type, final short module, final String content) {
		this.uid = uid;
		this.storeId = storeId;
		this.type = type;
		this.module = module;
		this.content = content;
	}
	
	/**
	 * 调用MQ去记录商家操作日志
	 */
	public void log() {
		// 记录商家日志 Felix 2015-11-18
		try {
			Map<String, Object> params = new HashMap<String, Object>(8);
			params.put("uid", getUid());
			params.put("storeId", getStoreId());
			params.put("type", getType());
			params.put("module", getModule());
			params.put("content", getContent());
			CommandMQProducer.get().sendJSON(BussinessType.MERCHANT_LOG, params);
		} catch (Exception e) {
			logger.error("调用商家操作日志MQ错误", e);
		}
	}
	public static MQMerchantLogInfo.Builder newLog(){
		return new Builder();
	}
	
	public long getUid() {
		return uid;
	}
	public long getStoreId() {
		return storeId;
	}
	public short getType() {
		return type;
	}
	public short getModule() {
		return module;
	}
	public String getContent() {
		return content;
	}
	
	public static class Builder{
		/** 用户ID */
		private long uid;

		/** 门店ID */
		private long storeId;
		
		/** 操作类型 INSERT/UPDATE/DELETE */
		private short type;
		
		/** 操作模块 STORE/DISH/ORDER/MONEY */
		private short module;
		
		/** 操作内容 */
		private String content;
		

		public Builder uid(long uid) {
			this.uid = uid;
			return this;
		}

		public Builder storeId(long storeId) {
			this.storeId = storeId;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}
		
		public Builder insert(){
			this.type = MerchantLogType.INSERT.getValue();
			return this;
		}
		
		public Builder update(){
			this.type = MerchantLogType.UPDATE.getValue();
			return this;
		}
		
		public Builder delete(){
			this.type = MerchantLogType.DELETE.getValue();
			return this;
		}
		
		public Builder store(){
			this.module = MerchantLogModule.STORE.getValue();
			return this;
		}
		
		public Builder dish(){
			this.module = MerchantLogModule.DISH.getValue();
			return this;
		}
		
		public Builder order(){
			this.module = MerchantLogModule.ORDER.getValue();
			return this;
		}
		
		public Builder money(){
			this.module = MerchantLogModule.MONEY.getValue();
			return this;
		}
		
		public MQMerchantLogInfo build(){
			ParameterUtil.assertGreaterThanZero(uid, "用户ID参数不合法");
			ParameterUtil.assertGreaterThanZero(storeId, "用户ID参数不合法");
			ParameterUtil.assertNotBlank(content, "日志内容不能为空");
			ParameterUtil.assertGreaterThanZero(type, "操作类型不能为空");
			ParameterUtil.assertGreaterThanZero(module, "操作模块不能为空");
			
			return new MQMerchantLogInfo(uid, storeId, type, module, content);
		}
	}
}
