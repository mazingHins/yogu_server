package com.yogu.services.user.config.service.impl;

import java.util.List;

import javax.annotation.Priority;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.core.remote.config.Config;
import com.yogu.core.remote.config.provider.ConfigProvider;
import com.yogu.services.user.config.service.ConfigService;

/**
 * 通过DB形式获取配置 <br>
 * <br>
 * 优先级为10（其他域下有个低优先级的http实现）
 *
 * @author JFan 2015年10月22日 下午4:19:50
 */
@Priority(10)
public class DBConfigProviderImpl implements ConfigProvider {

	private ConfigService configService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Config> getAllConfig() throws Exception {
		return configService().listAll();
	}

	// ####
	// ## private func
	// ####

	/**
	 * 已延迟的形式加载configService实例，只装载一次
	 */
	private ConfigService configService() {
		if (null == configService)
			configService = MainframeBeanFactory.getBean(ConfigService.class);
		return configService;
	}

}
