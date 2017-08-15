package com.mazing.mq;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.cfg.EncryptionPropertyPlaceholderConfigurer;
import com.yogu.core.test.BaseServiceTest;

/**
 * 测试mq的配置
 * 
 * @author linyi 2015/6/27.
 */
public class TestMQSettings extends BaseServiceTest {

	// @Inject
	// private EncryptionPropertyPlaceholderConfigurer configurer;

	@Test
	public void test() {
		String value = (String) EncryptionPropertyPlaceholderConfigurer.getConfig().get(GlobalSetting.getCloud() + "_AccessKey");
		assertTrue(StringUtils.isNotBlank(value));
	}

}
