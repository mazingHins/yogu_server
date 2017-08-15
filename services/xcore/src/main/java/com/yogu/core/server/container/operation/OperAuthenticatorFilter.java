package com.yogu.core.server.container.operation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.cache.annotation.Cacher;
import com.yogu.commons.cache.aspect.AnnoCacheExtendAspecter;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.enums.Role;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.AuthMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于对/s/类接口权限的检查
 * 
 * @author Felix 2015年9月9日
 */

@Priority(Priorities.AUTHORIZATION + 100)
public class OperAuthenticatorFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(OperAuthenticatorFilter.class);
	private static final String storeHost = CommonConstants.STORE_DOMAIN;
	private static final String userHost = CommonConstants.USER_DOMAIN;
	
	/** 操作者在当前门店下的权限缓存前缀 */
	public static final String STORE_STAFF_ROLES_PREFIX = "StoreStaffRoles";
	
	/** 用户账户是否被封的缓存前缀, 权限验证的远程方法专用 */
	public static final String USER_BANNED_AUTH_PREFIX = "UserBannedAuth";
	
	/** 门店是否被封的缓存前缀, 权限验证的远程方法专用 */
	public static final String STORE_BANNED_AUTH_PREFIX = "StoreBannedAuth";
	
	
	private static final int time_out = 3000;
	
	
	private Role[] roles;
	
	public OperAuthenticatorFilter(Role[] roles){
		this.roles = roles;
	}
	
	/**
	 * 实际验证的方法
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		long uid = SecurityContext.getUid();
		long storeId = SecurityContext.getStaffStoreId();
		
		logger.info("core#operation | start checking user permission | uid: {}, storeId: {}", uid, storeId);
		if ((storeId <= 0)){
			throw new ServiceException(ResultCode.PARAMETER_ERROR, AuthMessages.AUTH_OPERAUTH_FILTER_STOREID_ILLEGAL());
		}
		
		if (null == roles || roles.length ==0){
			throw new ServiceException(ResultCode.PARAMETER_ERROR, AuthMessages.AUTH_OPERAUTH_FILTER_ROLES_EMPTY());
		}
		
		// 校验门店是否被封
		if (isStoreBanned(storeId)){
			logger.info("core#operation | store is banned | storeId: {}", storeId);
			throw new ServiceException(ResultCode.UNAUTHORIZED_ACCESS, AuthMessages.AUTH_OPERAUTH_FILTER_STORE_BANNED());
		}
		
//		if (isUserBanned(uid)){
//			logger.info("core#operation | user is banned | uid: {}", uid);
//			throw new ServiceException(ResultCode.UNAUTHORIZED_ACCESS, "用户账户已被停用, 请联系米星管理员");
//		}
		
		// 获得用户的所有操作权限
		List<Role> authList = getStaffRoles(storeId, uid);
		// 如果员工是店主, 则默认获得所有权限
		if (authList.contains(Role.BOSS)) {
			logger.info("core#operation | complete checking user permission | uid: {}, storeId: {}", uid, storeId);
			return;
		}
		
		// 检验权限
		for (int i = 0; i < roles.length; i++) {
			if (authList.contains(roles[i])) {
				logger.info("core#operation | complete checking user permission | uid: {}, storeId: {}", uid, storeId);
				return;
			}
		}
		logger.info("core#operation | user doesn't have the proper permission for this action | uid: {}, storeId: {}, permission: {}", 
								uid, storeId, JsonUtils.toJSONString(authList));
		throw new ServiceException(ResultCode.UNAUTHORIZED_ACCESS, AuthMessages.AUTH_OPERAUTH_FILTER_AUTHORITY_NONE());
	}
	
	
	/**
	 * 远程查看门店是否被封, 使用缓存
	 * @param storeId 门店ID
	 * @return
	 */
	@Cacher(value = STORE_BANNED_AUTH_PREFIX)
	public boolean isStoreBanned(long storeId){
		try {
			String url = new StringBuffer(storeHost).append("/api/store/isStoreBanned?storeId=").append(storeId).toString();
			String json = HttpClientUtils.doGet(url, time_out);
			logger.info("operAuth#isStoreBanned#remote | check if store is banned or not | storeId: {}, json: {}", storeId, json);
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("operAuth#isStoreBanned#remote | check if store is banned error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return true;
	}
	
	
	/**
	 * 远程查看用户账户是否被封, 使用缓存
	 * @param uid 用户ID
	 * @return
	 */
	@Cacher(value = USER_BANNED_AUTH_PREFIX)
	public boolean isUserBanned(long uid){
		try {
			String url = new StringBuffer(userHost).append("/api/user/isUserBanned?uid=").append(uid).toString();
			String json = HttpClientUtils.doGet(url, time_out);
			logger.info("operAuth#isUserBanned#remote | check if user is banned or not | uid: {}, json: {}", uid, json);
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("operAuth#isUserBanned#remote | check if user is banned error | uid: {}, message: {}", uid, e.getMessage(), e);
		}
		return true;
	}
	
	/**
	 * 根据门店id和用户Id 获取用户权限列表
	 * 
	 * @param storeId 门店id
	 * @param uid 用户id
	 * @return 用户权限(可能多个)
	 */
	@Cacher(value = STORE_STAFF_ROLES_PREFIX)
	public List<Role> getStaffRoles(long storeId, long uid) {
		try {
			String url = new StringBuffer(storeHost).append("/api/store/staff/roles?storeId=").append(storeId).append("&uid=").append(uid).toString();
			String json = HttpClientUtils.doGet(url, time_out);
			logger.info("operAuth#getStaffRoles#remote | getStaffRoles | storeId: {}, uid: {}, json: {}", storeId, uid, json);
			RestResult<List<Role>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Role>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("operAuth#getStaffRoles#remote | getStaffRoles Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return new ArrayList<Role>();
	}
	
	/** 门店员工角色的缓存, 用于权限校验
	 *  
	 *  @param storeId 门店ID
	 *  @param uid 用户ID
	 *  @author felix
	 *  */
	public static String storeStaffRolesKey(long storeId, long uid) {
		return AnnoCacheExtendAspecter.toKey(STORE_STAFF_ROLES_PREFIX, storeId, uid);
	}

}
