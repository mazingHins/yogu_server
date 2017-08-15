package com.yogu.core.push;

import java.io.Serializable;

/**
 * push 相关设备信息及系统信息、版本信息的封装
 * 
 * @author sky
 *
 */
public class PushInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5775321627659840112L;

	private short deviceType;// 设备类型
	private short sysType;// 系统类型
	private String target;// ios 版本类型

	private String version;// 客户端版本号 add by Sky 2016-12-19

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public short getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(short deviceType) {
		this.deviceType = deviceType;
	}

	public short getSysType() {
		return sysType;
	}

	public void setSysType(short sysType) {
		this.sysType = sysType;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "sysType=" + sysType + ", deviceType=" + deviceType + ",target=" + target + ", version=" + version;
	}
}
