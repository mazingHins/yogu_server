package com.yogu.services.user.resource.base;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.SysType;
import com.yogu.core.base.BaseParams;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.context.SecurityContext;

/**
 * 用户登出控制器
 * 
 * @author sky
 * @date 2015/08/20 17:09:20
 */

@Named
@Path("p")
@Singleton
@Produces("application/json;charset=UTF-8")
public class LogoutResource {

	private static final Logger logger = LoggerFactory.getLogger(LogoutResource.class);

	@Inject
	private LoginInfoService loginInfoService;

	@GET
	@Path("v1/user/logout")
	public RestResult<Integer> logout() {
		Long uid = SecurityContext.getUserId();
		// 用户是否是 登陆状态校验
		if(null == uid || 0 >= uid){
			logger.error("user#logout | 用户没有登录 | uid: {}", uid);
			return new RestResult<>(1);
		}

		try {
			logger.info("user#logout | before logout | uid: {}", uid);

			// 获取当前用户的token数据
			String userToken = SecurityContext.getUserToken();

			if (StringUtils.isBlank(userToken)) {
				logger.error("user#logout | 用户没有登录 | ut: {}", userToken);
				return new RestResult<>(1);
			}
			BaseParams params = SecurityContext.getBaseParams();
			String sysName = params.getSysName();
			SysType sys = SysType.getSysType(sysName);
			if(sys == null)
				sys = SysType.IOS;
			
			// 清除用户token信息
			logger.info("user#logout | 调用接口清除用户token信息 | uid: {}", uid);
			// LoginInfoStore session = new LoginInfoStore();
			// session.clearTokenCache(uid, userToken);// 清除该登录设备 token缓存
			// session.clearCurrLoginUserDeviceCache(uid);// 清除该登录设备 设备信息缓存
			// 改成使用两层缓存的实现；jfan 2016-04-07
			loginInfoService.clearCurrDeviceToken(uid);

			logger.info("user#logout | after logout | uid: {}, time: {} ", uid,
					DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD_HH_mm_ss));
		} catch (Exception e) {
			// 退出登录任何错误都catch住, 不会往外抛
			logger.error("user#logout | 退出登录出错", e);
		}

		return new RestResult<Integer>(1);

	}

}
