package com.yogu.core;

import com.yogu.commons.utils.StringUtils;

/**
 * 发起请求设备的系统 枚举定义<br>
 * 从PushUtil（sky）中内部类提取出来
 *
 * @author JFan 2016年2月26日 下午3:28:43
 */
public enum SysType {

	/**
	 * ios系统
	 */
	IOS((short) 1),

	/**
	 * Android系统
	 */
	Android((short) 2),

	/**
	 * os
	 */
	OS_X((short) 3),

	/**
	 * Windows系统
	 */
	WIN((short) 4);

	private short value;

	private SysType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public static SysType getSysType(String sysName) {

		if (StringUtils.isBlank(sysName)) {
			return null;
		} else {

			sysName = sysName.toLowerCase();

			if (sysName.contains("ios") || sysName.contains("iphone") || sysName.contains("ipad"))
				return IOS;
			if (sysName.contains("android"))
				return Android;
			if (sysName.contains("os_x"))
				return OS_X;
			if (sysName.contains("win"))
				return WIN;
		}

		return null;
	}

	public static SysType valueOf(short value) {
		switch (value) {
		case 1:
			return IOS;
		case 2:
			return Android;
		case 3:
			return OS_X;
		case 4:
			return WIN;
		default:
			return null;
		}
	}

	/**
	 * 是否为 支持的 系统类型， 目前只有iOS和 android
	 * 
	 * @param sysType 系统类型
	 * @return
	 * @author sky 2016-12-19
	 */
	public static boolean isSupportSysType(short sysType) {
		if (sysType == IOS.getValue() || sysType == Android.getValue())
			return true;
		else
			return false;
	}

}