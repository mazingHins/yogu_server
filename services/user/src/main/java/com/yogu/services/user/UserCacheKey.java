/**
 * 
 */
package com.yogu.services.user;

import com.yogu.commons.cache.aspect.AnnoCacheExtendAspecter;

/**
 * 整个商家模块的KEY定义 <br>
 * 这里提供方法直接获取key
 *
 * @author JFan 2015年8月18日 下午5:13:12
 */
public final class UserCacheKey {

//	/** 30分钟 */
//	public static final int TIME_30M = 30 * 60;
//	/** 1小时 */
//	public static final int TIME_1H = 60 * 60;
	/** 永久有效 */
	public static final int TIME_FOREVER = -1;
	/** 3小时 */
	public static final int TIME_3H = 3 * 60 * 60;

	/** 收货地址缓存前缀 */
	public static final String ADDRESS_PREFIX = "Address";

	/** 用户收货地址列表缓存前缀 */
	public static final String ADDRESS_LIST_PREFIX = "AddressList";
	
	/** 用户是否被封号缓存前缀 */
	public static final String IS_USER_BANNED_PREFIX = "IsUserBanned";
	
	/** 用户iOS push设备信息 缓存前缀 **/
	public static final String USER_IOS_PROFILE_PREFIX= "IosProfile";
	
	/** 用户android push设备信息 缓存前缀 **/
	public static final String USER_ANDROID_PROFILE_PREFIX= "AndroidProfile";

	

	// ####
	// ## 当你需要缓存某个东西的时候，例如：门店信息
	// ## 应当先查看现有的key能否满足，没有合适的再添加
	// ####

	/** 收货地址 Address对象的缓存 */
	public static String addressKey(long addressId) {
		return AnnoCacheExtendAspecter.toKey(ADDRESS_PREFIX, addressId);
	}
	
	/** 收货地址 Address对象的缓存 */
	public static String addressListKey(long uid) {
		return AnnoCacheExtendAspecter.toKey(ADDRESS_LIST_PREFIX, uid);
	}
	
	/** 用户是否被封号的缓存 */
	public static String isUserKey(long uid) {
		return AnnoCacheExtendAspecter.toKey(IS_USER_BANNED_PREFIX, uid);
	}
	
	/** 用户iOS push设备信息 缓存前缀 **/
	public static String userIosProfileKey(long uid){
		return AnnoCacheExtendAspecter.toKey(USER_IOS_PROFILE_PREFIX, uid);
	}
	
	/** 用户android push设备信息 缓存前缀 **/
	public static String userAndroidProfileKey(long uid){
		return AnnoCacheExtendAspecter.toKey(USER_IOS_PROFILE_PREFIX, uid);
	}
}
