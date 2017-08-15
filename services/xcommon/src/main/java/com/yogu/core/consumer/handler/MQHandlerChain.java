package com.yogu.core.consumer.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.consumer.MQHandler;

/**
 * MQ cunsumer 实际调用的处理方法, 是一个链表, 里面有多个处理逻辑, 按顺序执行
 * @author felix
 * @date 2015-11-06
 */
public class MQHandlerChain implements MQHandler, Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(MQHandlerChain.class);
	
	private static final long serialVersionUID = 4519581604240124178L;
	
	private List<MQHandler> handlers;
	
	@Override
	public void execute(Map<String, Object> params) {
		for (MQHandler handler : getHandlers()) {
			try {
				handler.execute(params);
			} catch (Exception e) {
				logger.error("MQHandlerChain | 有任务执行失败", e);
			}
			
		}
	}
	
	/**
	 * 获取MQ处理的列表
	 * @return
	 */
	public List<MQHandler> getHandlers(){
		if (null == handlers) {
			handlers = new ArrayList<MQHandler>();
		}
		return handlers;
	}

}
