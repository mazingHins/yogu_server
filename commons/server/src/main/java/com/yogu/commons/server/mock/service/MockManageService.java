package com.yogu.commons.server.mock.service;

import java.util.Map;

/**
 * mock数据管理器 <br>
 * 
 * @author JFan 2015年7月17日 下午3:41:26
 */
public interface MockManageService {

	/**
	 * 是否启用：<br>
	 * JVM中指定启动配置，并且成功装载，即为启动
	 */
	public boolean isAvailable();

	/**
	 * 根据key查看mock内容
	 */
	public String result(String key);

	// manage

	/**
	 * ant表达式搜索内容
	 */
	public Map<String, String> select(String select);

	/**
	 * 新增
	 */
	public void add(String key, String value);// or update

	/**
	 * 删除
	 */
	public void delete(String key);

	/**
	 * 重载
	 */
	public void reload();

}
