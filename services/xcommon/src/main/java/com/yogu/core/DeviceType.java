package com.yogu.core;

import com.yogu.commons.utils.StringUtils;

/**
 * 请求设备的枚举类型定义 <br>
 * 从PushUtil（sky）中内部类提取出来
 *
 * @author JFan 2016年2月26日 下午3:27:01
 */
public enum DeviceType {

	/**
	 * 手机
	 */
	PHONE((short) 1, "phone"),

	/**
	 * pad
	 */
	PAD((short) 2, "pad"),

	/**
	 * pc
	 */
	PC((short) 3, "pc"),

	/**
	 * web
	 */
	WEB((short) 4, "web"),

	/**
	 * mobile (mazing webMobile sys)
	 */
	MOBILE((short) 5, "mobile")

	;

	private short value;
	private String name;

	private DeviceType(short value, String name) {
		this.value = value;
		this.name = name;
	}

	public short getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static DeviceType getDeviceType(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		} else {
			name = name.toLowerCase();
			if (name.contains("phone") || name.contains("android") || name.contains("wm"))
				return PHONE;
			if (name.contains("pad"))
				return PAD;
			if (name.contains("pc"))
				return PC;
			if (name.contains("web"))
				return WEB;
			if (name.contains("mobile"))
				return MOBILE;
		}
		return null;
	}

	public static DeviceType valueOf(short value) {
		switch (value) {
		case 1:
			return PHONE;
		case 2:
			return PAD;
		case 3:
			return PC;
		case 4:
			return WEB;
		case 5:
			return MOBILE;
		default:
			return null;
		}
	}

	/**
	 * 根据请求的基础参数（SecurityContext.getBaseParams().getAppName()）判定是那种设备
	 */
	public static DeviceType valueOfAppName(String appName) {
		String device = "";
		if (appName != null)
			device = appName.split("_")[0].toLowerCase();
		return getDeviceType(device);
	}

}