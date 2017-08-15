/**
 * 
 */
package com.yogu.core.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yogu.cfg.GlobalSetting;

/**
 * Service (spring 容器) 测试基类 <br>
 * 
 * @author JFan 2015年7月25日 下午2:13:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = BaseServiceTest.LOCATIONS)
public class BaseServiceTest {

	public static final String LOCATIONS = "classpath*:META-INF/mazing-applicationContext.xml";

	static {
		System.setProperty("modul", "user");
		GlobalSetting.cloud = "local";
		GlobalSetting.idc = "home";
		System.setProperty("PROJENV", "dev");
	}

}
