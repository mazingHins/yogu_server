package com.yogu.core.utils;

import org.apache.commons.lang3.StringUtils;

import com.yogu.core.base.BaseParams;

/**
 * 订单渠道的工具类
 * 
 * @author felix
 * @date 2016-04-28
 */
public class OrderChannelUtils {
	/**
	 * 获取用户的下单渠道
	 * @param params 接口基础参数, 主要使用aname
	 * @return
	 */
	public static String getOrderChannel(BaseParams params) {
		String channel = "";
		if (null != params) {
			if (StringUtils.isNotBlank(params.getAppName())) {
				if (params.getAppName().contains("_")) {
					channel = params.getAppName().split("_")[0];
				} else{
					channel = params.getAppName();
				}
			}
		}
		return channel;
	}
	
	public static void main(String[] args) {
		BaseParams base = new BaseParams();
		base.setAppName("android_mx");
		System.out.println(getOrderChannel(base));
		
		base.setAppName("iphone_mixing");
		System.out.println(getOrderChannel(base));
		
		base.setAppName("mobile");
		System.out.println(getOrderChannel(base));
		
		base.setAppName("");
		System.out.println(getOrderChannel(base));
		
		base = null;
		System.out.println(getOrderChannel(base));
	}
}
