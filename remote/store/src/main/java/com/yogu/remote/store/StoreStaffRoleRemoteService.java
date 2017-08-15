package com.yogu.remote.store;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.enums.Role;
import com.yogu.core.web.RestResult;
import com.yogu.services.store.base.dto.StoreStaffRole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.ws.rs.QueryParam;

import java.util.*;

/**
 * 对门店员工操作的远程服务类 <br>
 * 
 * @author Felix 2015年9月6日
 */
@Named
public class StoreStaffRoleRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(StoreStaffRoleRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	// 访问接口 timeout 时间（ms）
	private static final int API_DEFAULT_TIMEOUT = 5000;

	/**
	 * 根据门店id获取门店员工信息
	 * 
	 * @param storeId 门店id
	 * @return 门店信息
	 */
	public List<StoreStaffRole> listStaffByStoreId(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/list?storeId=" + storeId);
			logger.debug("remote#store#listStaffByStoreId | response | storeId: {}, json: {}", storeId, json);
			RestResult<List<StoreStaffRole>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<StoreStaffRole>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#listStaffByStoreId | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 根据门店id和用户Id 获取用户权限列表
	 * 
	 * @param storeId 门店id
	 * @param uid 用户id
	 * @return 用户权限(可能多个)
	 */
	public List<Role> getStaffRoles(long storeId, long uid) {
		try {
			// HttpStoreProviderImpl.whetherStoreStaff() 中有请求此URL
			String json = HttpClientUtils.doGet(host + "/api/store/staff/roles?storeId=" + storeId + "&uid=" + uid);
			logger.debug("remote#store#staff#getStaffRoles | response | storeId: {}, uid: {} json: {}", storeId, uid, json);
			RestResult<List<Role>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Role>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#staff#getStaffRoles | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 返回用户在商家的角色，后台管理使用。
	 * @param uid 用户ID
	 * @return 用户的角色列表，不会返回null
	 */
	public List<StoreStaffRole> listUserRoles(long uid) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/listUserRoles?uid=" + uid);
			logger.debug("remote#store#staff#listUserRoles | response | uid: {} json: {}", uid, json);
			RestResult<List<StoreStaffRole>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<StoreStaffRole>>>() {
			});
			if (result.isSuccess())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#listUserRoles | Error | uid: {}", uid, e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * 获取门店下的接单员或店长
	 * 
	 * @param storeId 门店ID
	 * @return
	 */
	public List<Long> getAccepterOrKeeper(long storeId){
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/getAccepterOrKeeper?storeId=" + storeId);
			logger.debug("remote#store#staff#getAccepterOrKeeper | response | storeId: {}, json: {}", storeId, json);
			RestResult<List<Long>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Long>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#staff#getAccepterOrKeeper | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * 如果餐厅存在接单员和店长，则返回到店长的UID, 其他情况一律返回null或空列表
	 * 
	 * @param storeId 门店ID
	 * @return
	 */
	public List<Long> getKeeperOrNull(long storeId){
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/getKeeperOrNull?storeId=" + storeId);
			logger.info("remote#store#staff#getKeeperOrNull | response | storeId: {}, json: {}", storeId, json);
			RestResult<List<Long>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Long>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#staff#getKeeperOrNull | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

//	/**
//	 * 分页并去重 获取所有 B端(商家端)的用户数据
//	 *
//	 * @param page
//	 * @param pageSize
//	 * @return
//	 * @author sky
//	 */
//	public List<StoreStaffRole> getAllStaffUser(int page, int pageSize) {
//		Map<String, String> params = new HashMap<>();
//		params.put("page", page + "");
//		params.put("pageSize", pageSize + "");
//		try {
//
//			String json = HttpClientUtils.doGet(host + "/api/store/staff/listAllUser", API_DEFAULT_TIMEOUT, params);
//
//			logger.debug("remote#storeStaff#getAllStaffUser | response | page: {}, pageSize: {}, json: {}", page, pageSize, json);
//
//			RestResult<List<StoreStaffRole>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<StoreStaffRole>>>() {
//			});
//			return result.getObject();
//		} catch (Exception e) {
//			logger.error("remote#storeStaff#getAllStaffUser | Error |page: {}, pageSize: {}, msg: {}", page, pageSize, e.getMessage(), e);
//		}
//		return Collections.emptyList();
//	}
	
	
	/**
	 * 检测B端用户是否应该被轮询任务通知有新单
	 * 1, 若餐厅有接单员, 且用户是接单员 - 通知
	 * 2, 若餐厅无接单员, 但有店长, 且用户是店长 - 通知
	 * 3, 若餐厅无接单员和店长, 但用户是店主 - 通知
	 * 4, 其他 - 不通知
	 * 
	 * @param uid 用户ID
	 * @param storeId 门店ID
	 * @return
	 * 
	 * @author felix 
	 */
	public boolean ifNotify(long uid, long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/ifNotify?storeId=" + storeId + "&uid=" + uid);
			logger.info("remote#store#staff#ifNotify | response | storeId: {}, uid: {}, json: {}", storeId, uid, json);
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#staff#ifNotify | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * 获取门店的所有员工, 包括店主
	 * 
	 * @param storeId 门店ID
	 * @return
	 */
	public List<StoreStaffRole> listStoreAllStaff(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/listAllByStoreId?storeId=" + storeId);
			logger.info("remote#store#staff#listStoreAllStaff | response | storeId: {}, json: {}", storeId, json);
			RestResult<List<StoreStaffRole>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<StoreStaffRole>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#listStoreAllStaff | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 *  若有店长, 获取所有店长, 若无, 则返回店主 (ID)
	 * 
	 * @param storeId
	 * @return
	 */
	public List<Long> listKeepersOrOwnerId(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/getKeepersOrOwner?storeId=" + storeId);
			logger.info("remote#store#staff#listKeepersOrOwnerId | response | storeId: {}, json: {}", storeId, json);
			RestResult<List<Long>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Long>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#listKeepersOrOwnerId | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * 分页获取配送员列表<br>
	 * 如果配送员不在，则获取店长，如果店长不在，则获取店主
	 * @param storeId - 餐厅id
	 * @param pageSize - 每页大小
	 * @param pageIndex - 页码
	 * @author hins
	 * @date 2016年10月16日 下午7:27:37
	 * @return 配送员列表，若无/异常，返回empty list
	 */
	public List<StoreStaffRole> listStoreDelivery(long storeId, int pageIndex, int pageSize) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/listDeliveryByStoreId?storeId=" + storeId + "&pageIndex=" + pageIndex + "&pageSize=" + pageSize);
			logger.info("remote#store#staff#listStoreDelivery | response | storeId: {}, json: {}", storeId, json);
			RestResult<List<StoreStaffRole>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<StoreStaffRole>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#listStoreDelivery | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return Collections.emptyList();
	}
	
}
