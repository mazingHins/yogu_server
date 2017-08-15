package com.yogu.core.push;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.TargetConstants;
import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.core.DeviceType;
import com.yogu.core.SysType;
import com.yogu.core.base.BaseParams;
import com.yogu.core.constant.DishSpecConstants;
import com.yogu.core.push.PushInfoBean;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.context.SecurityContext;

/**
 * Push功能相关的工具类<br>
 * 
 * 目前有IOS push, android push 的一个区分,在push信息时, 需要知道当前push的用户的登录系统类型(ios/android)、设备类型(phone/pad) <br>
 * 
 * <br>
 * 该工具类就是用来处理并返回当前推送所需要的必要信息的，返回的必要信息包括 设备信息系统类型, 设备类型 等
 * 
 * @author sky
 * @date 2015-11-17 19:51
 *
 */
public class PushUtil {

	private static final Logger logger = LoggerFactory.getLogger(PushUtil.class);

	public static void main(String[] args) {
		String appName = "iphone_mx";
		String appVersion = "1.2.0.65";// "68";

		BaseParams baseParam = new BaseParams();
		baseParam.setAppName(appName);
		baseParam.setAppVersion(appVersion);

		ThreadLocalContext.putThreadValue("sc:baseParams", baseParam);
		System.out.println(needTipForceUpdate());
	}

	/**
	 * 获取 当前用户 处于登录状态的设备 push 信息
	 * 
	 * @param uid 用户uid
	 * @author sky
	 * @return 返回key为设备类型 value为系统类型的键值对map
	 */
	public static List<PushInfoBean> getCurrUserLoginDevicePushInfo(long uid) {
		// LoginInfoStore session = new LoginInfoStore();
		LoginInfoService session = MainframeBeanFactory.getBean(LoginInfoService.class);
		List<Map<String, Object>> devicesList = session.getDevicesByUid(uid);

		List<PushInfoBean> list = new ArrayList<PushInfoBean>();

		if (CollectionUtils.isNotEmpty(devicesList)) {

			for (Map<String, Object> map : devicesList) {
				String sysName = (String) map.get("sysName");
				if (StringUtils.isBlank(sysName))// web登录的话，sysName是""
					continue;

				PushInfoBean bean = new PushInfoBean();

				String deviceName = (String) map.get("device");
				String target = (String) map.get("target");
				String version = (String) map.get("version");
				
				version = (StringUtils.isBlank(version) ? "" : version.trim());//客户端版本号

				// 首先判断系统类型, 不存在的系统类型直接跳过(新加了一个web类型的device,是没有系统类型的,也不在推送的范围)
				SysType sys = SysType.getSysType(sysName);
				if (sys == null) {
					logger.error("PushUtil#getUserCacheSysName | 获取用户缓存信息错误,客户端系统参数不存在 | uid: {}", uid);

					continue;// 找不到的忽略
				}

				short sysType = sys.getValue();

				// IOS 系统, 单独处理 线上、测试 版本区分
				if (sysType == SysType.IOS.getValue()) {
					if (StringUtils.isBlank(target))// 如果ios的这个字段为空, 则默认设置为 线上版本
						target = TargetConstants.ONLINE;
				}

				DeviceType device = DeviceType.getDeviceType(deviceName);
				if (device == null) {
					logger.error("PushUtil#getUserCacheDeviceName | 获取用户缓存信息错误,客户端 设备名称 不存在 | uid: {}", uid);
					device = DeviceType.PHONE;// 默认 手机端
				}

				short deviceType = device.getValue();

				// 封装
				bean.setDeviceType(deviceType);
				bean.setSysType(sysType);
				bean.setTarget(target);
				
				bean.setVersion(version);
				
				list.add(bean);

			}
		}

		logger.info("PushUtil#getCurrUserLoginDevicePushInfo | 获取当前用户登录的设备信息 | uid: {}, result: {}", uid, list);

		return list;
	}

	/**
	 * 是否需要提示强制更新,只针对ios 版本<br>
	 * 
	 * 目前为 build号小于66的 都需要提示强制更新
	 * 
	 * @return true,提示; false, 不需要提示
	 * 
	 * @author sky 2015-11-28
	 */
	public static boolean needTipForceUpdate() {

		// 当前build号
		int currIosBuildId = DishSpecConstants.DISH_SPEC_VERSION_IOS;
		
		int currAndroidBuildId = DishSpecConstants.DISH_SPEC_VERSION_ANDROID;

		BaseParams baseParam = SecurityContext.getBaseParams();

		String aname = baseParam.getAppName();
		String aver = baseParam.getAppVersion();

		if (StringUtils.isNotBlank(aname)) {

			if (aname.toLowerCase().contains("iphone")) {

				if (StringUtils.isNotBlank(aver)) {

					logger.info("PushUtil#needTipForceUpdate | 获取当前app的验签参数,判断是否需要提示强制更新 | baseParam: {}", baseParam);

					String[] arr = aver.trim().split("\\.");

					int length = arr.length;

					int buildId = Integer.valueOf(arr[length - 1]);

					if (buildId < currIosBuildId)
						return true;
				} else {
					logger.warn("PushUtil#needTipForceUpdate | 没有获取验签参数 aver | baseParam: {}", baseParam);
				}

			} else if (aname.toLowerCase().contains("android")) {
				if (StringUtils.isNotBlank(aver)) {

					logger.info("PushUtil#needTipForceUpdate | 获取当前app的验签参数,判断是否需要提示强制更新 | baseParam: {}", baseParam);

					String[] arr = aver.trim().split("\\.");

					int length = arr.length;

					int buildId = Integer.valueOf(arr[length - 1]);

					if (buildId < currAndroidBuildId)
						return true;
				} else {
					logger.warn("PushUtil#needTipForceUpdate | 没有获取验签参数 aver | baseParam: {}", baseParam);
				}
			} else {
				logger.info("PushUtil#needTipForceUpdate | 没有获取验签参数 aname | baseParam: {}", baseParam);
			}

		} else {
			logger.warn("PushUtil#needTipForceUpdate | 没有获取验签参数 aname | baseParam: {}", baseParam);
		}

		return false;

	}
}
