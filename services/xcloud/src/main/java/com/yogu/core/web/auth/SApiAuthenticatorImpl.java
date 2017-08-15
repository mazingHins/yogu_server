/**
 * 
 */
package com.yogu.core.web.auth;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.core.base.ApiReqinfoType;
import com.yogu.core.remote.store.provider.StoreProvider;
import com.yogu.core.remote.store.provider.impl.HttpStoreProviderImpl;
import com.yogu.core.server.auth.Authenticator;
import com.yogu.core.server.exception.AuthenticationException;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.StoreErrorCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.language.AuthMessages;

/**
 * 专门校验/s/类型的请求 <br>
 * ApiAuthenticatorImpl中已经校验过 ut 了，这里根据uid转载用户归属的门店
 *
 * @author JFan 2015年8月17日 下午6:31:42
 */
@Priority(Priorities.AUTHORIZATION)
public class SApiAuthenticatorImpl implements Authenticator {

	private static final Logger logger = LoggerFactory.getLogger(SApiAuthenticatorImpl.class);

	private StoreProvider storeProvider;

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.core.server.container.auth.Authenticator#authenticate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void authenticate(HttpServletRequest request) throws AuthenticationException {
		ApiReqinfoType type = SecurityContext.getApiType();
		if (null != type && ApiReqinfoType.S == type) {
			// 用户准备操作的门店ID
			String sid = request.getParameter("storeId");
			long storeId = toId(sid, "storeId");

			StoreProvider provider = getProvider();

			// 获取当前用户，必须已登录
			long uid = SecurityContext.getUid();
			// 获取当前用户 店家身份
			Boolean staff = provider.whetherStoreStaff(storeId, uid);
			if (null == staff || !(staff)) {
				logger.warn("sapi#interceptor | 可疑的请求，用户准备操作不属于自己的门店 | uid: {}, storeId: {}", uid, storeId);
				throw new AuthenticationException(StoreErrorCode.NOT_BELONG_STAFF, AuthMessages.AUTH_SAPIAUTH_AUTH_AUTHORITY_NONE());
			}

			// 记录用户正在操作的门店ID
			ThreadLocalContext.putThreadValue(SecurityContext.STAFF_STORE_ID, storeId);

			// ####
			// ## 附带校验
			// ####

			// 如果参数中含有 dishId，那么校验这个菜品是否 属于门店之下
			String did = request.getParameter("dishId");// >>>>>> 约定了/s/接口管理菜品是用dishId
			if (StringUtils.isNotBlank(did) && did.indexOf(",") < 0) {
				// 转成ID
				long dishId = toId(did, "dishId");
				// 判定
				if (0 < dishId) {
					Long dishStoreId = provider.getDishStoreId(dishId);
					if (null == dishStoreId)
						throw new AuthenticationException(StoreErrorCode.DISH_NOT_EXIST, AuthMessages.AUTH_SAPIAUTH_AUTH_DISHID_NOTEXIST());
					// 非法的操作
					if (storeId != dishStoreId) {
						logger.warn("sapi#interceptor | 可疑的请求，用户准备操作不属于自己门店的菜品 | uid: {}, storeId: {}, dishId: {}", uid, storeId, dishId);
						throw new AuthenticationException(ResultCode.UNAUTHORIZED_ACCESS, AuthMessages.AUTH_SAPIAUTH_AUTH_DISH_AUTHORITY_NONE());
					}
				}
			}

			// 如果参数中含有 rangeId，那么校验这个配送范围 是否属于门店之下
			String rid = request.getParameter("rangeId");
			if (StringUtils.isNotBlank(rid)) {
				// 转成ID
				long rangeId = toId(rid, "rangeId");
				// 判定
				if (0 < rangeId) {
					Long rangeStoreId = provider.getRangeStoreId(rangeId);
					if (null == rangeStoreId) {
						logger.warn("sapi#interceptor | 可疑的请求，配送范围找不到 | uid: {}, storeId: {}, rangeId: {}", uid, storeId, rangeId);
						throw new AuthenticationException(StoreErrorCode.SERVICE_RANGE_NOT_EXIST, AuthMessages.AUTH_SAPIAUTH_AUTH_RANGEID_NOTEXIST());
					}

					// 非法的操作
					if (storeId != rangeStoreId) {
						logger.warn("sapi#interceptor | 可疑的请求，用户准备操作不属于自己门店的配送信息 | uid: {}, storeId: {}, rangeId: {}", uid, storeId,
								rangeId);
						throw new AuthenticationException(ResultCode.UNAUTHORIZED_ACCESS, AuthMessages.AUTH_SAPIAUTH_AUTH_RANGE_AUTHORITY_NONE());
					}
				}
			}
		}
	}

	/**
	 * 将参数转换成long
	 */
	private long toId(String str, String name) {
		// if (StringUtils.isBlank(str)) // 外面已经检测过 isNotBlank 了
		// throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "'" + name + "' must not be empty.");
		// 校验storeId
		if (!(StringUtils.isNumeric(str)))
			throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "Parameter '" + name + "' is illegal.");
		// 2 long
		long id = Long.parseLong(str);
		// if (0 >= id) // 有传 0 的情况
		// throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "'" + name + "' abnormal parameters.");

		return id;
	}

	// 使用时装载service
	private StoreProvider getProvider() {
		if (null == storeProvider) {
			// 装载configProvider对象
			logger.debug("Loading 'StoreProvider' ......");

			// ServiceLoader<StoreProvider> load = ServiceLoader.load(StoreProvider.class);
			// Iterator<StoreProvider> iterator = load.iterator();
			// storeProvider = iterator.next();

			// 因ServiceLoader返回的对象是new出来的，所以不会经过spring aop
			// 改成从spring容器中获取实例
			StoreProvider provider = MainframeBeanFactory.getBeanOneOfType(StoreProvider.class);
			if (null == provider) {
				logger.warn("Loading 'StoreProvider' Get Null, use HttpImpl.");
				provider = new HttpStoreProviderImpl();
			}
			storeProvider = provider;

			logger.info("Loading 'StoreProvider' is {}", storeProvider.getClass());
		}
		return storeProvider;
	}

}
