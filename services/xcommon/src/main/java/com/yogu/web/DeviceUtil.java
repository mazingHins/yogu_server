package com.yogu.web;

import com.yogu.core.DeviceType;
import com.yogu.core.web.context.SecurityContext;

/**
 * 设备工具类
 * @author linyi 2015/6/19.
 */
public class DeviceUtil {

    /**
     * 返回当前登录的设备名，不会返回null
     * @return 返回 iphone,ipad,aphone,apad，如果没有登录设备名，返回""
     * @deprecated 建议使用枚举类型DeviceType，根据其方法valueOfAppName(..)来判断类型
     */
    public static String getRequestDevice() {
        String appName = SecurityContext.getBaseParams().getAppName();
        String device = "";
        if (appName != null) {
            device = appName.split("_")[0].toLowerCase();
        }
        return device;
    }

    /**
     * 判断请求设备是否为iphone
     * @return
     */
    public static boolean isIPhone() {
        String appName = SecurityContext.getBaseParams().getAppName();
        return (appName != null && appName.toLowerCase().indexOf("iphone") >= 0);
    }

    /**
     * 判断请求设备是否为ipad
     * @return
     */
    public static boolean isIPad() {
        String appName = SecurityContext.getBaseParams().getAppName();
        return (appName != null && appName.toLowerCase().indexOf("ipad") >= 0);
    }

    /**
     * 判断请求设备是否为 android phone
     * @return
     */
    public static boolean isAndroidPhone() {
        String appName = SecurityContext.getBaseParams().getAppName();
        return (appName != null && appName.toLowerCase().indexOf("aphone") >= 0);
    }

    /**
     * 判断请求设备是否为 android pad
     * @return
     */
    public static boolean isAndroidPad() {
        String appName = SecurityContext.getBaseParams().getAppName();
        return (appName != null && appName.toLowerCase().indexOf("apad") >= 0);
    }
}
