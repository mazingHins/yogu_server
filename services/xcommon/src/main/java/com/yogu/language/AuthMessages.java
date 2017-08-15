package com.yogu.language;

/**
 * 权限校验filter的提示信息
 * 
 * @author sky 2016-04-21
 *
 */
public class AuthMessages {

	/**
	 * 没有装载到任何权限校验器
	 */
	public static final String AUTH_OAUTH_INIT_EMPTY = "auth.OAuth.init.empty";

	/**
	 * 没有装载到任何权限校验器
	 */
	public static String AUTH_OAUTH_INIT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUTH_OAUTH_INIT_EMPTY);
	}

	/**
	 * 访问频率过快, 请稍后再试
	 */
	public static final String AUTH_FREQUENCY_LIMIT_OVER = "auth.frequency.limit.over";

	/**
	 * 访问频率过快, 请稍后再试
	 */
	public static String AUTH_FREQUENCY_LIMIT_OVER() {
		return MultiLanguageAdapter.fetchMessage(AUTH_FREQUENCY_LIMIT_OVER);
	}

	/**
	 * 没有正确获得门店Id
	 */
	public static final String AUTH_OPERAUTH_FILTER_STOREID_ILLEGAL = "auth.operAuth.filter.storeId.illegal";

	/**
	 * 没有正确获得门店Id
	 */
	public static String AUTH_OPERAUTH_FILTER_STOREID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(AUTH_OPERAUTH_FILTER_STOREID_ILLEGAL);
	}

	/**
	 * 注解参数不能为空
	 */
	public static final String AUTH_OPERAUTH_FILTER_ROLES_EMPTY = "auth.operAuth.filter.roles.empty";

	/**
	 * 注解参数不能为空
	 */
	public static String AUTH_OPERAUTH_FILTER_ROLES_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUTH_OPERAUTH_FILTER_ROLES_EMPTY);
	}

	/**
	 * 餐厅已被封号, 请联系米星管理员
	 */
	public static final String AUTH_OPERAUTH_FILTER_STORE_BANNED = "auth.operAuth.filter.store.banned";

	/**
	 * 餐厅已被封号, 请联系米星管理员
	 */
	public static String AUTH_OPERAUTH_FILTER_STORE_BANNED() {
		return MultiLanguageAdapter.fetchMessage(AUTH_OPERAUTH_FILTER_STORE_BANNED);
	}

	/**
	 * 您没有该操作的权限
	 */
	public static final String AUTH_OPERAUTH_FILTER_AUTHORITY_NONE = "auth.operAuth.filter.authority.none";

	/**
	 * 您没有该操作的权限
	 */
	public static String AUTH_OPERAUTH_FILTER_AUTHORITY_NONE() {
		return MultiLanguageAdapter.fetchMessage(AUTH_OPERAUTH_FILTER_AUTHORITY_NONE);
	}

	/**
	 * 您没有权限进行当前的操作，请向餐厅负责人申请更高权限
	 */
	public static final String AUTH_SAPIAUTH_AUTH_AUTHORITY_NONE = "auth.SApiAuth.auth.authority.none";

	/**
	 * 您没有权限进行当前的操作，请向餐厅负责人申请更高权限
	 */
	public static String AUTH_SAPIAUTH_AUTH_AUTHORITY_NONE() {
		return MultiLanguageAdapter.fetchMessage(AUTH_SAPIAUTH_AUTH_AUTHORITY_NONE);
	}

	/**
	 * 找不到美食，请重新刷新
	 */
	public static final String AUTH_SAPIAUTH_AUTH_DISHID_NOTEXIST = "auth.SApiAuth.auth.dishId.notexist";

	/**
	 * 找不到美食，请重新刷新
	 */
	public static String AUTH_SAPIAUTH_AUTH_DISHID_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(AUTH_SAPIAUTH_AUTH_DISHID_NOTEXIST);
	}

	/**
	 * 你不能操作不属于自己的餐厅的美食
	 */
	public static final String AUTH_SAPIAUTH_AUTH_DISH_AUTHORITY_NONE = "auth.SApiAuth.auth.dish.authority.none";

	/**
	 * 你不能操作不属于自己的餐厅的美食
	 */
	public static String AUTH_SAPIAUTH_AUTH_DISH_AUTHORITY_NONE() {
		return MultiLanguageAdapter.fetchMessage(AUTH_SAPIAUTH_AUTH_DISH_AUTHORITY_NONE);
	}

	/**
	 * 找不到对应的发货设置
	 */
	public static final String AUTH_SAPIAUTH_AUTH_RANGEID_NOTEXIST = "auth.SApiAuth.auth.rangeId.notexist";

	/**
	 * 找不到对应的发货设置
	 */
	public static String AUTH_SAPIAUTH_AUTH_RANGEID_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(AUTH_SAPIAUTH_AUTH_RANGEID_NOTEXIST);
	}

	/**
	 * 你不能修改不属于自己的餐厅的发货设置
	 */
	public static final String AUTH_SAPIAUTH_AUTH_RANGE_AUTHORITY_NONE = "auth.SApiAuth.auth.range.authority.none";

	/**
	 * 你不能修改不属于自己的餐厅的发货设置
	 */
	public static String AUTH_SAPIAUTH_AUTH_RANGE_AUTHORITY_NONE() {
		return MultiLanguageAdapter.fetchMessage(AUTH_SAPIAUTH_AUTH_RANGE_AUTHORITY_NONE);
	}

	/**
	 * 登录状态已失效
	 */
	public static final String AUTH_APIAUTH_CHECK_USERTOKEN_EMPTY = "auth.apiAuth.check.userToken.empty";

	/**
	 * 登录状态已失效
	 */
	public static String AUTH_APIAUTH_CHECK_USERTOKEN_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUTH_APIAUTH_CHECK_USERTOKEN_EMPTY);
	}

	/**
	 * 无法获取您所在的城市，请检查定位是否开启或处于可定位的地方
	 */
	public static final String AUTH_APIAUTH_CHECK_BASEPARAM_CITYCODE_EMPTY = "auth.apiAuth.check.baseParam.citycode.empty";

	/**
	 * 无法获取您所在的城市，请检查定位是否开启或处于可定位的地方
	 */
	public static String AUTH_APIAUTH_CHECK_BASEPARAM_CITYCODE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUTH_APIAUTH_CHECK_BASEPARAM_CITYCODE_EMPTY);
	}

	/**
	 * 请确保您的手机时间和北京时间同步: {0}
	 */
	public static final String AUTH_APIAUTH_CHECK_BASEPARAM_TIME_ILLEGAL = "auth.apiAuth.check.baseParam.time.illegal";

	/**
	 * 请确保您的手机时间和北京时间同步: {0}
	 */
	public static String AUTH_APIAUTH_CHECK_BASEPARAM_TIME_ILLEGAL(String now) {
		return MultiLanguageAdapter.fetchMessage(AUTH_APIAUTH_CHECK_BASEPARAM_TIME_ILLEGAL, now);
	}

	/**
	 * 系统繁忙，请稍后再试。
	 */
	public static final String EXCEPTION_RESPONSE_SYSTEM_BUSY = "exception.response.system.busy";

	/**
	 * 系统繁忙，请稍后再试。
	 */
	public static String EXCEPTION_RESPONSE_SYSTEM_BUSY() {
		return MultiLanguageAdapter.fetchMessage(EXCEPTION_RESPONSE_SYSTEM_BUSY);
	}

	/**
	 * 访问的地址不存在，请检查代码。
	 */
	public static final String EXCEPTION_RESPONSE_URL_NOTEXIST = "exception.response.url.notexist";

	/**
	 * 访问的地址不存在，请检查代码。
	 */
	public static String EXCEPTION_RESPONSE_URL_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(EXCEPTION_RESPONSE_URL_NOTEXIST);
	}

}
