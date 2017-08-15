/**
 * 
 */
package com.yogu.core.base;

/**
 * 每次请求都必须带有的基础参数 <br>
 * 
 * <br>
 * JFan - 2015年6月6日 下午12:28:02
 */
public class BaseParams {

	/** 系统名称 */
	private String sysName;
	/** 系统版本 */
	private String sysVersion;
	/** APP名称（CODE） */
	private String appName;
	/** APP版本 */
	private String appVersion;
	/** 设备ID */
	private String did;
	/** appKey（请求密匙的KEY） */
	private String appKey;
	/** 用于区分ios的线上版本及org版本，只有iOS才有这个参数 **/
	private String target;

	/**
	 * @return sysName
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * @param sysName 要设置的 sysName
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * @return sysVersion
	 */
	public String getSysVersion() {
		return sysVersion;
	}

	/**
	 * @param sysVersion 要设置的 sysVersion
	 */
	public void setSysVersion(String sysVersion) {
		this.sysVersion = sysVersion;
	}

	/**
	 * @return appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName 要设置的 appName
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return appVersion
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * @param appVersion 要设置的 appVersion
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * @return did
	 */
	public String getDid() {
		return did;
	}

	/**
	 * @param did 要设置的 did
	 */
	public void setDid(String did) {
		this.did = did;
	}

	/**
	 * @return appKey
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * @param appKey 要设置的 appKey
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * @return target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target 要设置的 target
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "sysName=" + sysName + ", sysVersion=" + sysVersion + ", appName=" + appName + ", appVersion=" + appVersion + ", target="
				+ target;
	}
}
