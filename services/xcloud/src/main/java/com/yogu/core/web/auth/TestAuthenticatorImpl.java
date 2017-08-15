package com.yogu.core.web.auth;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;

import org.apache.commons.lang3.StringUtils;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.core.base.BaseParams;
import com.yogu.core.base.Point;
import com.yogu.core.server.auth.Authenticator;
import com.yogu.core.server.exception.AuthenticationException;
import com.yogu.core.test.ApiReq;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.UserErrorCode;
import com.yogu.core.web.context.SecurityContext;

/**
 * 以test模式启动时，屏蔽掉了验签部分 <br>
 * 然而该部分，会装载BaseParam对象，程序中会用到 <br>
 * 这里直接构建一个模拟的信息
 * 
 * @author JFan 2015年7月24日 下午6:49:02
 */
@Priority(Priorities.AUTHENTICATION - 100)
public class TestAuthenticatorImpl implements Authenticator {

	// @Inject // ServiceLoad 加载的，无法ioc
	private LoginInfoService loginInfoService;

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.core.server.container.auth.Authenticator#authenticate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void authenticate(HttpServletRequest request) throws AuthenticationException {
		if (!(Boolean.parseBoolean(System.getProperty("test"))))// BaseResourceTest 中会设置 test=true
			return;

		BaseParams base = new BaseParams();
		// base.setT(System.currentTimeMillis() / 1000);
		base.setDid(ApiReq.did);
		base.setAppName(ApiReq.aname);
		base.setSysName(ApiReq.sname);
		base.setAppVersion(ApiReq.aver);
		base.setSysVersion(ApiReq.sver);
		base.setAppKey(ApiReq.defAppKey);
		ThreadLocalContext.putThreadValue(SecurityContext.BASE_POINT, new Point(113.344759, 23.131461));
		ThreadLocalContext.putThreadValue(SecurityContext.BASE_PARAMS, base);
		ThreadLocalContext.putThreadValue(SecurityContext.CITY_CODE, "020");

		if (null != ApiReq.nowUt) {
			String userToken = ApiReq.nowUt;
			// LoginInfoStore store = new LoginInfoStore();
			String secret = loginInfoService().getSecretByToken(userToken);
			if (StringUtils.isNotBlank(secret)) {
				long uid = loginInfoService().getUidByToken(userToken, 0L);
				if (uid <= 0)
					throw UserErrorCode.notLogin();
				ThreadLocalContext.putThreadValue(SecurityContext.USER_ID, uid);// 读取请使用SecurityContext
				ThreadLocalContext.putThreadValue(SecurityContext.USER_TOKEN, userToken);
			}
		}
	}

	/**
	 * 使用时装载
	 */
	private LoginInfoService loginInfoService() {
		if (null == loginInfoService)
			loginInfoService = MainframeBeanFactory.getBean(LoginInfoService.class);
		return loginInfoService;
	}

}
