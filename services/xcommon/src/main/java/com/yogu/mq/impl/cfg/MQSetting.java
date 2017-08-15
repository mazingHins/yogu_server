package com.yogu.mq.impl.cfg;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.cfg.EncryptionPropertyPlaceholderConfigurer;

/**
 * MQ配置
 * 
 * @author linyi 2015/6/27.
 */
public class MQSetting {

	private static final String cloud = GlobalSetting.getCloud();

	public static String getCloudAccessKey() {
		String key = cloud + "_AccessKey";

		return (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get(key);
		// ConfigRemoteService 是一个spring管理的一个bean, 静态获取会有问题
		// return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, key);
	}

	public static String getCloudAccessSecret() {
		// 改为从DB中读取
		String key = cloud + "_AccessSecret";

		// if (GlobalSetting.CLOUD_LOCAL.equals(cloud))
		return (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get(key);

		// return ConfigRemoteService.getConfig(ConfigGroupConstants.CLOUD, key);

	}

	/**
	 * 根据环境获取MQ生产者ID
	 * 
	 * @return
	 */
	public static String getQueueProducerId() {
		return (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get(cloud + "_queue_producer_id");
	}

	/**
	 * 根据环境获取MQ消费者ID
	 * 
	 * @return
	 */
	public static String getQueueConsumerId() {
		String key = cloud + "_queue_consumer_id";
		return (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get(key);

	}

	/**
	 * 根据环境获取MQ topic
	 * 
	 * @return
	 */
	public static String getQueueName() {
		return (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get(cloud + "_queue_name");
	}

	public static String get(String key) {
		return (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get(key);
	}
}
