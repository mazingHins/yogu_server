package com.yogu;

import com.yogu.commons.utils.StringUtils;
import com.yogu.core.SysType;

/**
 * 客户端包名标识 target 值 常量类 </br>
 * 
 * 注意：4.0 版本 iOS、android 的 正式版target 都有传值 并 且 值相同， 4.0 之前 android是没有传target值的
 * 
 * @author sky
 *
 */
public class TargetConstants {
	/**
	 * iOS org 测试版本
	 */
	public static final String ORG = "com.mazing.tastyOrg";

	/**
	 * iOS 线上正式版本
	 */
	public static final String ONLINE = "com.mazing.tasty";

	/**
	 * iOS app store 审核中版本
	 */
	public static final String CHECKING = "com.mazing.tastyAppStore";

	// ----------------------4.0 版本target-------------------------
	/**
	 * 4.0 新版 target， iOS、android 都是相同的
	 */
	public static final String ONLINE_NEW = "com.mazing.amazing";
	/**
	 * 4.0 iOS ORG 版本新的 target值
	 */
	public static final String ORG_NEW = "com.mazing.amazingOrg";

	// ----------------------4.0 版本target-------------------------

	/**
	 * 是否是用APNS 来发push
	 * 
	 * @param sysType 系统类型
	 * @param target 客户端target参数值
	 * @return true,调用APNS push; false, 不调用APNS push,用极光
	 */
	public static boolean needApnsPush(short sysType, String target) {
		// 4.0前的 iOS 系统 用的是APNS push

		if (sysType == SysType.IOS.getValue())// 是否是iOS系统
			if (isOldTarget(target))// 是否 是 4.0前的版本
				return true;

		return false;
	}

	/**
	 * 客户端所传target值是否为 4.0 前版本的target(4.0 前的版本只有iOS 传了target值， android 没传， 4.0 版本开始 iOS、android 都传了新的target值)
	 * 
	 * @param target 客户端所传target
	 * @return true, 相对于4.0 是老版本; false, 不是老版本
	 * @author sky 2016-12-15
	 */
	public static boolean isOldTarget(String target) {
		if (StringUtils.isBlank(target) || ORG.equals(target) || ONLINE.equals(target) || CHECKING.equals(target))
			return true;
		else
			return false;
	}

}
