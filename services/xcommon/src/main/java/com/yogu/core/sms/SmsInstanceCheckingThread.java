package com.yogu.core.sms;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.server.context.MainframeBeanFactory;

/**
 * 检查 SMS SP（短信提供商）的配置是否有发生变化。 SP 的配置从 redis 读取，如果发生切换，实时切换SP。
 * 
 * @author ten 2015/12/2.
 */
public class SmsInstanceCheckingThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(SmsInstanceCheckingThread.class);

	@Override
	public void run() {
		String currentName = SmsConfig.getCurrentSpName();
		logger.info("sms#instance#checking | 检测短信渠道供应商是否变动 | 当前渠道: {}", currentName);
		try {
			Redis redis = getRedis();
			if (redis != null) {
				String value = redis.get(SmsConfig.SMS_INSTANCE_KEY);
				if (StringUtils.isNotBlank(value)) {
					int sp = NumberUtils.toInt(value, SmsConfig.SMS_INSTANCE_NETEASE);
					int old = SmsConfig.currentServiceProvider;
					if (sp != old) {
						String newName = SmsConfig.getSpName(sp);
						logger.info("sms#instance#checking | 更换短信供应商 | old: {}, new: {}", currentName, newName);
						SmsConfig.currentServiceProvider = sp;
					}
				}
			}
		} catch (Exception e) {
			logger.error("sms#instance#checking | 检查SMS SP 线程出错 | message: {}", e.getMessage(), e);
		}
	}

	private Redis getRedis() {
		Redis redis = (Redis) MainframeBeanFactory.getBean("redis");
		if (redis == null) {
			logger.error("sms#instance#checking | Redis(name='redis')实例为空");
		}
		return redis;
	}

}
