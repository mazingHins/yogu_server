package com.yogu.cfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.StringUtils;

import javax.annotation.PostConstruct;
import javax.inject.Named;

/**
 * 全局配置，在系统环境变量设置<strong>例子</strong>： <br/>
 * 
 * <pre>
 * export PROJCLOUD=ali
 * export PROJENV=test
 * -------------可选----------
 * export PROJIDC=sz
 * export PROJNO=mazing
 * </pre>
 * 
 * @author linyi 2015/5/28.
 */
@Named
public class GlobalSetting {

	private static final Logger logger = LoggerFactory.getLogger(GlobalSetting.class);

	/** 云提供商：阿里云 */
	public static final String CLOUD_ALIYUN = "ali";
	/** 云提供商：AWS */
	public static final String CLOUD_AWS = "aws";
	/** 云提供商：本地系统，仅用于测试 */
	public static final String CLOUD_LOCAL = "local";

	/** 项目环境：内网环境 */
	public static final String PROJENV_INTERNAL = "internal";
	/** 项目环境：开发 */
	public static final String PROJENV_DEV = "dev";
	/** 项目环境：测试 */
	public static final String PROJENV_TEST = "test";
	/** 项目环境：线上 */
	public static final String PROJENV_PROD = "prod";
	/** 项目环境：预发布 */
	public static final String PROJENV_PUBPRE = "pubpre";

	// ------------------------------------------------------------------------

	/** 机房序号，两位字母或数字，比如SZ（深圳），HZ（杭州） */
	public static String idc = "hz";
	/** 项目名称 */
	public static String projno = "mazing";
	
	/** 云提供商 */
	public static String cloud = CLOUD_ALIYUN;
	/** 项目环境 */
	public static String projenv = PROJENV_TEST;

	public static String getCloud() {
		iniaial1();
		return GlobalSetting.cloud;
	}

	public static String getProjenv() {
		iniaial1();
		return projenv;
	}

	public static String getIdc() {
		iniaial1();
		return idc;
	}

	public static String getProjno() {
		iniaial1();
		return projno;
	}
	
	// ####
	// ## initial
	// ####

	// 指示是否已经在其他地方set过值了，如果set过了则initial将不在重复set
	private static boolean init = false, setCloud = false, setIdc = false, setProjenv = false, setProjno = false;

	@PostConstruct
	public void initial() {
		iniaial1();
	}

	private static void iniaial1() {
		if (!init)
			initial0();
	}

	private static void initial0() {
		// set cloud config
		if (!(setCloud)) {
			String cloud = getSysCongfig("PROJCLOUD");
			if (StringUtils.isBlank(cloud))
				logger.warn("setting#global#cloud | PROJCLOUD config isBlank");
			else
				setCloud(cloud);
		}

		// set idc config
		if (!(setIdc)) {
			String idc = getSysCongfig("PROJIDC");
			if (StringUtils.isBlank(idc))
				logger.warn("setting#global#idc | PROJIDC config isBlank");
			else
				setIdc(idc);
		}

		// set idc config
		if (!(setProjenv)) {
			String projenv = getSysCongfig("PROJENV");
			if (StringUtils.isBlank(projenv))
				logger.warn("setting#global#projenv | PROJENV config isBlank");
			else
				setProjenv(projenv);
		}

		// set idc config
		if (!(setProjno)) {
			String projno = getSysCongfig("PROJNO");
			if (StringUtils.isBlank(projno))
				logger.warn("setting#global#projno | PROJNO config isBlank");
			else
				setProjno(projno);
		}
		
		init = true;
	}

	/**
	 * 读取系统参数
	 */
	public static String getSysCongfig(String key) {
		String value = System.getProperty(key);// 优先读取 启动war包时 -D指定的参数
		if (null == value)
			value = System.getenv(key);// 次要读取 OS指定的环境参数
		return value;
	}

	private static void setCloud(String cloud) {
		logger.info("setting#global#cloud | PROJCLOUD | value: '{}'", cloud);
		GlobalSetting.cloud = cloud;
		setCloud = true;
	}

	private static void setIdc(String idc) {
		logger.info("setting#global#idc | PROJIDC | value: '{}'", idc);
		GlobalSetting.idc = idc;
		setIdc = true;
	}

	private static void setProjenv(String env) {
		// ####
		// ## CheckUtil.isProdEnv() 中也有用到 env 环境变量
		// ####
		logger.info("setting#global#projenv | PROJENV | value: '{}'", env);
		GlobalSetting.projenv = env;
		setProjenv = true;
	}

	private static void setProjno(String projno) {
		logger.info("setting#global#projno | PROJNO | value: '{}'", projno);
		GlobalSetting.projno = projno;
		setProjno = true;
	}
	

}
