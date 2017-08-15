/**
 * 
 */
package com.yogu.core.web.context;

import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.core.base.ApiReqinfoType;
import com.yogu.core.base.BaseParams;
import com.yogu.core.base.Point;
import com.yogu.core.remote.config.AppLanguage;
import com.yogu.core.server.exception.AuthenticationException;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.StoreErrorCode;
import com.yogu.core.web.UserErrorCode;
import com.yogu.core.web.exception.ServiceException;

/**
 * 权限相关工具类、常量 ： 能取到值，应该是验证已登录之后 <br>
 * 例如：获取当前已登录用户的信息<br>
 * 
 * put数据，由合适的地方，直接调用ThreadLocalContext进行设置
 * 
 * JFan 2014年12月25日 上午10:55:31
 */
public final class SecurityContext {

	private SecurityContext() {
	}

	// ApiReqinfoType
	public static final String API_TYPE = "sc:apiReqinfoType";
	// BaseParams
	public static final String BASE_PARAMS = "sc:baseParams";
	// AppLanguage
	public static final String BASE_APP_LANGUAGE = "sc:appLanguage";
	// Point
	public static final String BASE_POINT = "sc:basePoint";

	// long
	public static final String USER_ID = "sc:userId";
	// String
	public static final String USER_TOKEN = "sc:userToken";
	// String
	public static final String CITY_CODE = "sc:cityCode";

	// long
	public static final String STAFF_STORE_ID = "sc:staffStoreId";

	/** 存储m域登录信息的webToken值，注意：只有m域才能使用该值 */
	public static final String MOBILE_WEB_TOKEN = "m:webToken";
	/** 存储m域用户的微信OPEN ID（如果有），注意：只有m域才能使用该值 */
	public static final String MOBILE_OPEN_ID = "m:wechat:openId";

	/**
	 * 获取当前正在请求的 API 类型
	 */
	public static ApiReqinfoType getApiType() {
		Object v = ThreadLocalContext.getThreadValue(API_TYPE);
		return (null == v ? null : (ApiReqinfoType) v);
	}

	/**
	 * 获取baseParams对象，没有则抛异常
	 */
	public static BaseParams getBaseParams() {
		Object v = ThreadLocalContext.getThreadValue(BASE_PARAMS);
		if (null == v)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "lack base parameters.");
		return ((BaseParams) v);
	}

	/**
	 * 获取APP的语言，如果app没指定则默认使用中文<br>
	 * 该方法返回一定不会为null
	 */
	public static AppLanguage getAppLanguage() {
		Object v = ThreadLocalContext.getThreadValue(BASE_APP_LANGUAGE);
		if (null != v)
			return ((AppLanguage) v);
		return AppLanguage.zh;
	}

	/**
	 * 是否切换语言至 英语 AppLanguage.en
	 */
	public static boolean change2English() {
		return AppLanguage.en.equals(getAppLanguage());
	}

	/**
	 * 获取用户选择的坐标，可以为null
	 */
	public static Point getPoint() {
		Object v = ThreadLocalContext.getThreadValue(BASE_POINT);
		return (null == v ? null : (Point) v);
	}

	/**
	 * 获取用户选择的坐标，为null时抛缺少参数异常
	 */
	public static Point getUserPoint() {
		Point point = getPoint();
		if (null == point)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "lack coordinate parameters.");
		return point;
	}

	/**
	 * 获取uid，可以为null
	 */
	public static Long getUserId() {
		Object v = ThreadLocalContext.getThreadValue(USER_ID);
		return (null == v ? null : (Long) v);
	}

	/**
	 * 获取uid，为null时抛异常
	 */
	public static long getUid() {
		Long uid = getUserId();
		if (null == uid)
			throw new AuthenticationException(UserErrorCode.USER_NOT_LOGIN, "user not login.");
		return uid;
	}

	/**
	 * 获取当前用户userToken
	 */
	public static String getUserToken() {
		return ThreadLocalContext.getThreadValue(USER_TOKEN);
	}

	/**
	 * 返回用户当前查看的城市CODE
	 */
	public static String getCityCode() {
		return ThreadLocalContext.getThreadValue(CITY_CODE);
	}

	/**
	 * 获取mobile登录的用户的webTokne信息 <strong>只有m域才能调用</strong>
	 */
	public static String getMobileWebToken() {
		return ThreadLocalContext.getThreadValue(MOBILE_WEB_TOKEN);
	}

	/**
	 * 获取mobile登录的用户的 微信OpenID 信息 <strong>只有m域才能调用</strong>
	 */
	public static String getWechatOpenId() {
		return ThreadLocalContext.getThreadValue(MOBILE_WEB_TOKEN);
	}

	// ####
	// ## /s/ 类接口专享方法
	// ####

	/**
	 * 检验当前用户是否是‘指定门店’的员工<br>
	 * 这里只校验当前用户是否是店内员工，不是则抛出异常<br>
	 * 判定是否具备某个操作的权限，请参考 SOperation 枚举<br>
	 * <br>
	 * 一下情况会抛出异常：<br>
	 * 1：用户没有登录<br>
	 * 2：用户不是任何门店的员工<br>
	 * 3：用户所属的门店不是指定的门店<br>
	 * 
	 * @param storeId 门店ID
	 */
	public static void accessStore(long storeId) {
		getUid();
		long sid = getStaffStoreId();
		if (sid != storeId)
			throw new ServiceException(ResultCode.UNAUTHORIZED_ACCESS, "你没有权限修改这家餐厅的数据");
	}

	/**
	 * 当前用户所属的门店ID，在s类接口中调用
	 */
	public static long getStaffStoreId() {
		Long storeId = ThreadLocalContext.getThreadValue(STAFF_STORE_ID);
		if (null == storeId)
			throw new AuthenticationException(StoreErrorCode.NOT_BELONG, "not belong store.");
		return storeId;
	}

}
