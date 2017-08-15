package com.yogu.core.consumer;

import java.util.Map;

/**
 * 定义MQ consumer 处理的方法接口
 * @author felix
 * @date 2015-11-06
 */
public interface MQHandler {
	/**
	 * 在MQ consumer里面被调用的方法
	 * @param params 执行所需参数
	 */
	public void execute(Map<String, Object> params);
}
