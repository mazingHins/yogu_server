package com.yogu.core.sms;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.concurrent.ExecutorFactory;
import com.yogu.core.remote.config.ConfigRemoteService;

/**
 * @author ten 2015/12/2.
 */
public class SmsServiceFactory {

	private final static Logger logger = LoggerFactory.getLogger(SmsServiceFactory.class);

	private static final long interval = 15000;// 刷新短信渠道的时间间隔 ms (15s)
	private static long lastTime = 0;// 最后一次刷新 短信渠道 的时间
	static {
		refreshChannel(800);
	}

	/**
	 * 启动线程刷新短信渠道商<br>
	 * 如果 timeout(毫秒)大于0，则在启动线程后尝试等待结果；否则不等待
	 */
	private static void refreshChannel(long timeout) {
		long time = System.currentTimeMillis();
		if (interval < (time - lastTime)) {
			lastTime = time;
			Future<?> future = ExecutorFactory.submit(new SmsInstanceCheckingThread());// 立即启动一个线程交由线程池运行
			if (0 < timeout) {
				try {
					future.get(timeout, TimeUnit.MILLISECONDS);
				} catch (Exception e) {
				}
			}
		}
	}

	// ############################################

	/**
	 * 创建发送短信的实例
	 * 
	 * @return
	 */
	public static SmsService getInstance() {
		refreshChannel(0);

		if (SmsConfig.currentServiceProvider == SmsConfig.SMS_INSTANCE_YUN_PIAN) {
			return new SmsServiceYunpianImpl();
		} else if (SmsConfig.currentServiceProvider == SmsConfig.SMS_INSTANCE_NETEASE) {
			return new SmsServiceNeteaseImpl();
		}
		throw new IllegalStateException("Not support sms sp: " + SmsConfig.currentServiceProvider);
	}

	/**
	 * 创建发送短信的实例
	 * 
	 * @author hins
	 * @date 2016年9月18日 下午7:42:56
	 * @return SmsService
	 */
	public static SmsService getCodeInstance() {
		
		//注意：如果该方法增加 或 去除了 某个短信运营商，请同时 修改 getAnotherCodeServiceInstance() 方法 //add by sky 2016-09-27
		
		String channel = ConfigRemoteService.getConfig(SmsConfig.SMS_SETTING_GROUP, SmsConfig.SMS_CODE_CHANNEL);
		if (StringUtils.isBlank(channel)) {
			logger.info("SmsServiceFactory#getCodeInstance | 当前获取发送短信的实例 运营商为  ALI DAYU | channel: {}", channel);
			
			return new SmsServiceAliImpl();
			
		} else if (channel.equals(String.valueOf(SmsConfig.SMS_INSTANCE_YUN_PIAN))) {
			logger.info("SmsServiceFactory#getCodeInstance | 当前获取发送短信的实例 运营商为  YUN PIAN | channel: {}", channel);
			
			return new SmsServiceYunpianImpl();
			
		} else if (channel.equals(String.valueOf(SmsConfig.SMS_INSTANCE_NETEASE))) {
			logger.info("SmsServiceFactory#getCodeInstance | 当前获取发送短信的实例 运营商为  YUN PIAN | channel: {}", channel);
			
			return new SmsServiceYunpianImpl();
			
		} else if (channel.equals(String.valueOf(SmsConfig.SMS_INSTANCE_ALI_DAYU))) {
			logger.info("SmsServiceFactory#getCodeInstance | 当前获取发送短信的实例 运营商为  ALI DAYU | channel: {}", channel);
			
			return new SmsServiceAliImpl();
			
		}
		
		logger.info("SmsServiceFactory#getCodeInstance | 当前获取发送短信的实例 运营商为  ALI DAYU | channel: {}", channel);
		
		return new SmsServiceAliImpl();
	}

	/**
	 * 获取与配置 不同的 另一个 短信服务商实例, 注意： 该方法仅用做动态实例切换
	 * 
	 * @return
	 * @author sky 2016-09-27
	 */
	public static SmsService getAnotherCodeServiceInstance() {

		String channel = ConfigRemoteService.getConfig(SmsConfig.SMS_SETTING_GROUP, SmsConfig.SMS_CODE_CHANNEL);
		if (StringUtils.isBlank(channel)) {
			logger.info("SmsServiceFactory#getAnotherCodeServiceInstance | 当前获取发送短信的实例 运营商为 YUN PIAN | channel: {}", channel);
			
			return new SmsServiceYunpianImpl();
			
		} else if (channel.equals(String.valueOf(SmsConfig.SMS_INSTANCE_YUN_PIAN))) {
			logger.info("SmsServiceFactory#getAnotherCodeServiceInstance | 当前获取发送短信的实例 运营商为 ALI DAYU | channel: {}", channel);
			
			return new SmsServiceAliImpl();
			
		} else if (channel.equals(String.valueOf(SmsConfig.SMS_INSTANCE_NETEASE))) {
			logger.info("SmsServiceFactory#getAnotherCodeServiceInstance | 当前获取发送短信的实例 运营商为 ALI DAYU | channel: {}", channel);
			
			return new SmsServiceAliImpl();
			
		} else if (channel.equals(String.valueOf(SmsConfig.SMS_INSTANCE_ALI_DAYU))) {
			logger.info("SmsServiceFactory#getAnotherCodeServiceInstance | 当前获取发送短信的实例 运营商为 YUN PIAN | channel: {}", channel);
			
			return new SmsServiceYunpianImpl();
			
		}
		logger.info("SmsServiceFactory#getAnotherCodeServiceInstance | 当前获取发送短信的实例 运营商为 YUN PIAN | channel: {}", channel);
		
		return new SmsServiceYunpianImpl();
	}

	/**
	 * 创建网易云信发送短信的实例<br>
	 * <strong>发送给客户经理的才是用此方法</strong>
	 */
	public static SmsService getYunxinInstance() {
		// return new SmsServiceNeteaseImpl();// 本来以为网易云信可以一个号码发送超过30条，结果还是一样。so ...
		return getInstance();
	}

}
