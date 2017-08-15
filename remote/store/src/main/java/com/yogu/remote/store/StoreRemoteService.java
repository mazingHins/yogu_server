package com.yogu.remote.store;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.store.audit.dto.StoreAuditStatus;
import com.yogu.services.store.base.dto.Store;
import com.yogu.services.store.base.dto.StorePayAccount;
import com.yogu.services.store.express.dto.SfFeeConfig;
import com.yogu.services.store.express.dto.StoreSf;
import com.yogu.services.store.news.dto.NewsStoreinfo;
import com.yogu.services.store.tag.NewsStoreinfoTag;
import com.yogu.services.store.tag.StoreTagMp;

/**
 * 对门店操作的远程服务类 <br>
 * 
 * @author JFan 2015年7月20日 下午12:33:30
 */
@Named
public class StoreRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(StoreRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	// 访问接口 timeout 时间（ms）
	private static final int API_DEFAULT_TIMEOUT = 5000;

	/**
	 * 根据门店id获取门店内容
	 * 
	 * @param storeId 门店id
	 * @return 门店信息
	 */
	public Store getStoreBySid(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/get?storeId=" + storeId);
			logger.debug("remote#store#getStoreBySid | response | storeId: {}, json: {}", storeId, json);
			RestResult<Store> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Store>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#getStoreBySid | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据餐厅ID获取餐厅所有的信息：基本信息、图片信息、详细信息（StoreDetail）、认证进度，等等。 用于餐厅分享。
	 * 
	 * @param storeId 餐厅ID
	 * @return 返回餐厅的详细信息
	 * @author ten 2015/12/12
	 */
	public Map<String, Object> getStoreAllInfo(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/allInfo?storeId=" + storeId);
			logger.debug("remote#store#getStoreAllInfo | response | storeId: {}, json: {}", storeId, json);
			RestResult<Map<String, Object>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<String, Object>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#getStoreAllInfo | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据餐厅ID获取餐厅所有的信息，用于 APP 界面或 HTML5 界面展示。
	 * 
	 * @param storeId 餐厅ID
	 * @return 返回餐厅的详细信息
	 * @author ten 2016/4/13
	 */
	public Map<String, Object> getStoreDetails(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/details?storeId=" + storeId);
			logger.debug("remote#store#getStoreDetails | response | storeId: {}, json: {}", storeId, json);
			RestResult<Map<String, Object>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<String, Object>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#getStoreDetails | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据门店id获取门店内容
	 * 
	 * @param storeIds 门店id列表
	 * @return 门店信息
	 */
	public List<Store> getStoreBySids(long... storeIds) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/list?storeIds=" + StringUtils.join(storeIds, ','));
			logger.debug("remote#store#getStoreBySids | response | storeIds: {}, json: {}", storeIds, json);
			RestResult<List<Store>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Store>>>() {
			});
			return (List<Store>) (result.getObject() == null ? Collections.emptyList() : result.getObject());
		} catch (Exception e) {
			logger.error("remote#store#getStoreBySids | Error | storeIds: {}, message: {}", storeIds, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 根据餐厅id，批量获取餐厅信息。多个餐厅id用英文逗号分隔
	 * 
	 * @param storeIds - 餐厅id
	 * @author hins
	 * @date 2016年11月23日 下午6:28:38
	 * @return 餐厅信息列表，若无/异常，返回empty list
	 */
	public List<Store> listStoreByIds(String storeIds) {
		logger.info("remote#store#getStoreBySids | 批量查询餐厅信息 | storeIds: {}", storeIds);
		if (StringUtils.isBlank(storeIds)) {
			return Collections.emptyList();
		}
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/list?storeIds=" + storeIds);
			RestResult<List<Store>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Store>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#getStoreBySids | 批量查询餐厅信息出现异常 | storeIds: {}, message: {}", storeIds, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 对门店评论数进行+1操作
	 * 
	 * @param storeId 门店ID
	 */
	public boolean additiveCommentNum(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/comment/additiveNum?id=" + storeId);
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#additiveCommentNum | error | stroeId: {}", storeId, e);
		}
		return false;
	}

	/**
	 * 对门店评论数进行-1操作
	 * 
	 * @param storeId 门店ID
	 * @author hins
	 * @date 2016年7月13日 下午4:35:16
	 * @return boolean
	 */
	public boolean reduceCommentNum(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/comment/reduceNum?id=" + storeId);
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#reduceCommentNum | error | stroeId: {}", storeId, e);
		}
		return false;
	}

	/**
	 * 远程查询符合条件的商店数据。仅用于测试
	 * 
	 * @param keyword 关键字，可以为null，暂时不起作用
	 * @param page 第几页，最小值为1
	 * @param pageSize 每页多少条数据，最小值1为1
	 * @return 返回符合条件的数据，如果没有数据，返回size=0的List
	 * @author ten 2015/9/1
	 * @see {@link #adminQueryStore}
	 */
	@Deprecated
	public List<Store> queryStore(String keyword, int page, int pageSize) {
		Map<String, String> params = new HashMap<>();
		params.put("keyword", keyword);
		params.put("page", page + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/query", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Store>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Store>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#queryStore | 查询门店数据错误 | keyword: {}", keyword, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 返回门店的详细信息，包括：基本信息、认证信息、菜品列表、员工列表。<br/>
	 * 成功返回相应的详细信息，失败抛出 ServiceException <strong>注：后台管理系统使用</strong>，用于展示门店的信息。
	 * 
	 * <pre>
	 * {
	 *     store: 商家基本信息,
	 *     detail: 商家额外的信息,
	 *     staffList: 员工列表,
	 *     dishList: 菜品列表,
	 *     storeAudit: 商家认证的状态,
	 *     userAudit: 用户认证的资料,
	 *     rangeList: 配送范围,
	 *     storeSf: 顺丰配送配置,
	 * }
	 * </pre>
	 * 
	 * @param storeId 门店ID
	 * @return 成功返回相应的详细信息，失败抛出 ServiceException
	 */
	public Map<String, Object> adminGetStoreDetail(long storeId) {
		Map<String, String> params = new HashMap<>(2);
		params.put("storeId", storeId + "");
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/detail", API_DEFAULT_TIMEOUT, params);
			RestResult<Map<String, Object>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<String, Object>>>() {
			});
			if (result.isSuccess())
				return result.getObject();
			throw new ServiceException(result.getCode(), result.getMessage());
		} catch (Exception e) {
			logger.error("remote#store#adminGetStoreDetail | 查询门店详细数据错误 | storeId: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员对门店封号<br/>
	 * <strong>注：后台管理系统使用</strong>
	 * 
	 * @param uid 管理员ID
	 * @param storeId 门店ID
	 * @param reason 封号原因
	 * @return result.success=true为封号成功
	 */
	public RestResult<Object> adminBanStore(long uid, long storeId, String reason) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("reason", reason);
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/ban.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminBanStore | 管理员对门店封号错误 | uid: {}, storeId: {}, reason: {}", uid, storeId, reason, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员对门店封号<br/>
	 * <strong>注：后台管理系统使用</strong>
	 * 
	 * @param uid 管理员ID
	 * @param storeId 门店ID
	 * @return result.success=true为封号成功
	 */
	public RestResult<Object> adminUnbanStore(long uid, long storeId) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/unban.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminUnbanStore | 管理员对门店封号错误 | uid: {}, storeId: {}", uid, storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 对门店修改状态，成功时 result.success=true
	 * 
	 * @param uid 管理员ID
	 * @param storeId 门店ID
	 * @param status 新的状态
	 * @return result.success=true为成功
	 * @author ten 2015/10/5
	 * @param remark
	 */
	public RestResult<Object> adminModifyStatus(long uid, long storeId, short status, String remark) {
		Map<String, String> params = new HashMap<String, String>(4);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("status", status + "");
		params.put("remark", remark);
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/modifyStatus.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminModifyStatus | 管理员对门店修改状态错误 | uid: {}, storeId: {}, newStatus: {}", uid, storeId, status, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员修改餐厅mazing pay状态(开启/关闭)
	 * 
	 * @param uid 管理员ID
	 * @param storeId 门店ID
	 * @param status mazing pay状态
	 * @return
	 * @author sky 2016-06-14
	 */
	public RestResult<Object> adminModifyMazingPayStatus(long uid, long storeId, short status) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("status", status + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/modifyMazingPayStatus.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminModifyStatus | 管理员对门店修改mazing pay状态错误 | uid: {}, storeId: {}, newStatus: {}", uid, storeId,
					status, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员修改餐厅 米星配送（顺丰配送）状态(开启/关闭)
	 * 
	 * @param storeId 门店ID
	 * @param status 顺丰配送状态
	 * @param uid 管理员ID
	 * @return
	 * @author sky 2016-09-30
	 */
	public RestResult<Object> modifySupportSfStatus(long uid, long storeId, short status) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("status", status + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/sf/modifySupportSfStatus.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#modifySupportSfStatus | 管理员对门店修改顺丰配送状态错误 | uid: {}, storeId: {}, newStatus: {}", uid, storeId,
					status, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员修改餐厅 顺丰餐点类型
	 * 
	 * @param uid 管理员uid
	 * @param storeId 门店ID
	 * @param cuisineType 餐点类型
	 * @return
	 * @author sky 2016-09-30
	 */
	public RestResult<Object> modifyStoreSfCuisineType(long uid, long storeId, short cuisineType) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("cuisineType", cuisineType + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/sf/modifyStoreSfCuisineType.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#modifyStoreSfCuisineType | 管理员修改餐厅 顺丰餐点类型 错误 | uid: {}, storeId: {}, newCuisineType: {}", uid,
					storeId, cuisineType, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员修改餐厅 米星配送（顺丰配送）具体承担费用 信息配置
	 * 
	 * @param uid 管理员ID
	 * @param storeId 门店ID
	 * @param userBear 用户承担费用
	 * @param mzBear 米星承担费用
	 * @param merchantBear 商家承担费用
	 * @param cuisineType 商家承担费用
	 * @return
	 * @author sky 2016-09-30
	 */
	public RestResult<Object> modifySfFeeConfig(long uid, long storeId, int userBear, int mzBear, int merchantBear, short cuisineType) {
		Map<String, String> params = new HashMap<>(7);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("userBear", userBear + "");
		params.put("mzBear", mzBear + "");
		params.put("merchantBear", merchantBear + "");
		params.put("cuisineType", cuisineType + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/sf/modifySfFeeConfig.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error(
					"remote#store#modifySfFeeConfig | 管理员对门店修改顺丰配送费用信息错误 | uid: {}, storeId: {}, userBear: {}, mzBear: {}, merchantBear: {}",
					uid, storeId, userBear, mzBear, merchantBear, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 修改餐厅温馨提示(如特殊取货提醒)
	 * 
	 * @param uid 管理员id
	 * @param storeId 门店id
	 * @param warmTipZh 中文温馨提示
	 * @param warmTipEn 英文温馨提示
	 * @return
	 * @author sky 2016-11-03
	 */
	public RestResult<Object> modifyWarmTip(long uid, long storeId, String warmTipZh, String warmTipEn) {
		Map<String, String> params = new HashMap<>(5);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("warmTipZh", warmTipZh);
		params.put("warmTipEn", warmTipEn);
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/modifyWarmTip.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#modifyWarmTip | 管理员对门店修改 温馨提示信息错误 | uid: {}, storeId: {}, warmTipZh: {}, warmTipEn: {}", uid,
					storeId, warmTipZh, warmTipEn, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 修改餐厅 推荐首页展示logo图
	 * @param uid
	 * @param storeId
	 * @param indexShowImg
	 * @return
	 * @author sky 2016-12-16
	 */
	public RestResult<Object> modifyIndexShowImg(long uid, long storeId, String indexShowImg) {
		Map<String, String> params = new HashMap<>(5);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("indexShowImg", indexShowImg);
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/modifyIndexShowImg.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#modifyIndexShowImg | 管理员对门店修改 推荐首页展示logo错误 | uid: {}, storeId: {}, indexShowImg: {}", uid, storeId,
					indexShowImg, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 根据餐点类型获取 顺丰配送基本信息
	 * 
	 * @param cuisineType 餐点类型
	 * @param request
	 * @return
	 * @author sky 2016-10-08
	 */
	public RestResult<SfFeeConfig> getSfFeeConfigById(short cuisineType) {
		Map<String, String> params = new HashMap<>(2);
		params.put("cuisineType", cuisineType + "");
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/sf/getSfFeeConfig", API_DEFAULT_TIMEOUT, params);
			RestResult<SfFeeConfig> result = JsonUtils.parseObject(json, new TypeReference<RestResult<SfFeeConfig>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#getSfFeeConfigById | 根据餐点类型获取顺丰配送基本信息失败 |  cuisineType: {}", cuisineType, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取米星餐厅-顺丰配送 关联关系实体信息 <br>
	 * 该实体信息包含了米星餐厅与顺丰配送的配置信息，包含费用承担、餐点类型、配送时间、重量、配送距离范围等
	 * 
	 * @param storeId 米星餐厅id
	 * @return
	 * @author sky 2016-10-08
	 */
	public StoreSf getStoreSf(long storeId) {
		Map<String, String> params = new HashMap<>(2);
		params.put("storeId", storeId + "");
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/sf/getStoreSfByStoreId", API_DEFAULT_TIMEOUT, params);
			RestResult<StoreSf> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreSf>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#getSfFeeConfigById | 根据餐点类型获取顺丰配送基本信息失败 |  storeId: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 清除门店的缓存，包括菜品的缓存
	 * 
	 * @param adminId 管理员ID
	 * @param storeId 门店ID
	 * @return result.success=true为成功
	 * @author ten 2015/10/19
	 */
	public RestResult<Object> adminCleanCache(long adminId, long storeId) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("uid", adminId + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/cleanCache.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminCleanCache | 管理员对门店清除缓存错误 | adminId: {}, storeId: {}", adminId, storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员修改门店名称（不影响门店自身的 更改条件【也就是如果门店自己没有修改过，那么他自己还是可以修改的】）
	 * 
	 * @param adminId 管理员ID
	 * @param storeId 门店ID
	 * @param newName 新的门店名称
	 * @return result.success=true为成功
	 * @author JFan 2015-10-31
	 */
	public RestResult<Object> adminUpdateName(long adminId, long storeId, String newName) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("uid", adminId + "");
		params.put("newName", newName);
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/updateName.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminUpdateName | 管理员修改门店名称时发生错误 | adminId: {}, storeId: {}, newName: {}", adminId, storeId,
					newName, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员修改餐厅客户经理信息
	 * 
	 * @param adminId 管理员ID
	 * @param storeId 门店ID
	 * @param csmName 新的门店客户经理名称
	 * @param csmPhone 新的门店客户经理电话
	 * @return result.success=true为成功
	 * @author JFan 2015-11-30
	 */
	public RestResult<Object> adminUpdateCsm(long adminId, long storeId, String csmName, String csmPhone) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("adminId", adminId + "");
		params.put("csmName", csmName);
		params.put("csmPhone", csmPhone);
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/updateCsm.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminUpdateCsm | 管理员修改餐厅客户经理信息发生错误 | adminId: {}, storeId: {}, csmName: {}, csmPhone: {}"//
					, adminId, storeId, csmName, csmPhone, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 添加员工
	 * 
	 * @param storeId 门店id
	 * @param uid 员工uid
	 * @param name 员工姓名
	 * @param roleIds 角色id串,多个id用英文','分隔
	 * @param imId 员工imId
	 * @return
	 * @author jack 2016/3/23
	 */
	public RestResult<Object> adminSaveStaff(long storeId, long uid, String name, String roleIds, long imId) {
		Map<String, String> params = new HashMap<>(6);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("name", name);
		params.put("roleIds", roleIds + "");
		params.put("imId", imId + "");

		try {
			String json = HttpClientUtils.doPost(host + "/api/store/adminSaveStaff.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminSaveStaff | 管理员添加员工信息发生错误 | storeId: {}, uid: {}, name: {}, roleIds: {}, imId : {}", storeId,
					uid, name, roleIds, imId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 修改员工信息
	 * 
	 * @param storeId 门店id
	 * @param uid 员工uid
	 * @param name 员工新的姓名
	 * @param newRoleIds 新的角色id串,多个id用英文','分隔
	 * @param imId 员工的imId
	 * @return
	 * @author jack 2016/3/24
	 */
	public RestResult<Object> adminUpdateStaff(long storeId, long uid, String name, String newRoleIds, long imId) {
		Map<String, String> params = new HashMap<>(6);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("name", name);
		params.put("newRoleIds", newRoleIds);
		params.put("imId", imId + "");

		try {
			String json = HttpClientUtils.doPost(host + "/api/store/adminUpdateStaff.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminUpdateStaff | 管理员修改员工信息发生错误 | storeId: {}, uid: {}, name: {}, newRoleIds: {}, imId : {}",
					storeId, uid, name, newRoleIds, imId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 门店转让
	 * 
	 * @param adminId 管理员ID
	 * @param storeId 门店ID
	 * @param uid 门店要转让的目标用户ID
	 * @param realName 转给者真实姓名
	 * @param identity 转给者证件号
	 * @param transferAudit 是否转让个人认证信息，一般不要转让
	 * @return success=true为成功
	 * @author ten 2015/10/22
	 */
	public RestResult<Object> adminStoreTransfer(long adminId, long storeId, long uid, String realName, String identity,
			boolean transferAudit) {
		Map<String, String> params = new HashMap<>(6);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("adminId", adminId + "");
		params.put("transferAudit", transferAudit + "");

		params.put("realName", realName);
		params.put("identity", identity);

		try {
			String json = HttpClientUtils.doPost(host + "/api/store/storeTransfer.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminStoreTransfer | 管理员进行门店转让错误 | adminId:{}, uid: {}, storeId: {}, transferAudit: {}", adminId,
					uid, storeId, transferAudit, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 清除所有的餐厅的缓存
	 * 
	 * @param adminId 管理员ID
	 * @return success=true为成功
	 * @author ten 2015/10/30
	 */
	public RestResult<Object> adminClearAllStoreCache(long adminId) {
		Map<String, String> params = new HashMap<>(4);
		params.put("adminId", adminId + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/cleanAllStoreCache.do", 25000, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminClearAllStoreCache | 清除所有的餐厅的缓存错误 | adminId:{}", adminId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 远程查询符合条件的商店数据
	 * 
	 * @param uid 用户ID
	 * @param keyword 关键字，可以为null
	 * @param excludeFrost 是否排除被封号的数据
	 * @param storeStatus 餐厅状态，若不指定，传0
	 * @param page 第几页，最小值为1
	 * @param pageSize 每页多少条数据，最小值1为1
	 * @return 返回符合条件的数据，如果没有数据，返回size=0的List
	 * @author ten 2015/11/12
	 */
	public List<Store> adminQueryStore(long uid, String keyword, boolean excludeFrost, short storeStatus, int page, int pageSize) {
		Map<String, String> params = new HashMap<>();
		params.put("uid", uid + "");
		params.put("keyword", StringUtils.trimToEmpty(keyword));
		// modify by hins 2016-01-23 新增查询指定餐厅状态条件
		params.put("storeStatus", storeStatus + "");
		params.put("page", page + "");
		params.put("pageSize", pageSize + "");
		params.put("excludeStatus", excludeFrost ? "1" : "0");
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/queryByType", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Store>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Store>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#queryStore | 查询门店数据错误 | uid: {}, keyword: {}", uid, keyword, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员更新门店瀑布流
	 * 
	 * @param storeId 门店ID
	 * @param path 图片路径
	 * @return
	 */
	public Map<String, String> adminUpdateTopicImg(long storeId, String path) {
		Map<String, String> params = new HashMap<String, String>(2);
		params.put("storeId", storeId + "");
		params.put("path", path);
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/topicImg/upload.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Map<String, String>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<String, String>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#adminUpdateTopicImg | 管理员更新门店瀑布流首图 | storeId: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 远程查询符合条件的待审核商店列表数据
	 * 
	 * @param keyword 关键字，可以为null，暂时不起作用
	 * @param page 第几页，最小值为1
	 * @param pageSize 每页多少条数据，最小值1为1
	 * @return 返回符合条件的数据，如果没有数据，返回size=0的List
	 * @author ben 2015－12-03
	 */
	public List<StoreAuditStatus> adminQueryStoreAudit(String keyword, int page, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("keyword", keyword);
		params.put("page", page + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/queryStoreAudit", API_DEFAULT_TIMEOUT, params);
			RestResult<List<StoreAuditStatus>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<StoreAuditStatus>>>() {
					});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#queryStore | 查询待审核商店列表错误 | {}, keyword: {}", keyword, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 修改餐厅的地址
	 * 
	 * @param storeId 餐厅ID
	 * @param newAddress 新地址
	 * @param newLng 经度
	 * @param newLat 纬度
	 * @return
	 */
	public RestResult<Object> updateAddress(long storeId, String newAddress, double newLng, double newLat) {
		Map<String, String> params = new HashMap<>(8);
		params.put("storeId", storeId + "");
		params.put("newAddress", newAddress);
		params.put("newLng", newLng + "");
		params.put("newLat", newLat + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/updateAddress.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#updateAddress | 查询门店数据错误 | storeId: {}, newAddress: {}, newLng: {}, newLat: {}", storeId,
					newAddress, newLng, newLat, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 
	 * 更改认证状态
	 *
	 * @author ben
	 * @date 2015年11月30日 下午4:33:14
	 * @param storeId 门店id （更改用户实名认证时为－1）
	 * @param uid 用户id（只有在更改用户实名认证的时候才会有，其他情况为－1）
	 * @param type 要更新的认证类型：实名认证 － user ； 场所认证 －facility ； 营业执照认证 － businessLicense ； 资质证明认证 － cateringCertification
	 * @param status 要更新至的状态，只有两种： 2 － 审核通过 ； 3 － 审核拒绝
	 * @return
	 */
	public RestResult<Object> updateAuditStatus(long storeId, long uid, long hid, String type, short status) {
		Map<String, String> params = new HashMap<>(8);
		params.put("storeId", storeId + "");
		params.put("uid", uid + "");
		params.put("hid", hid + "");
		params.put("type", type);
		params.put("status", status + "");
		try {
			String json = HttpClientUtils.doPost(host + "/api/store/updateAuditStatus.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#updateAuditStatus | 修改认证状态错误 | storeId: {}, uid: {}, type: {}, status: {}", storeId, uid, type,
					status, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 更新门店评分(定时任务调度)
	 *
	 * @author ben
	 * @date 2015年11月12日 上午11:26:54
	 * @param starJson json字符串 : 里面包含一个门店id-评分的map ｜ key － store_id , value - star
	 * @return
	 */
	public RestResult<Object> updateStoreStar(String starJson) {
		try {
			Map<String, String> params = new HashMap<>(1);
			params.put("starJson", starJson);
			String json = HttpClientUtils.doPost(host + "/api/store/updateStoreStar.do", API_DEFAULT_TIMEOUT, params);
			// String json = HttpClientUtils.doPost("http://localhost:8080/api/store/updateStoreStar.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			logger.debug("remote#store#updateStoreStar | response |  json: {} , params{}", json, params);
			return result;
		} catch (Exception e) {
			logger.error("remote#store#updateStoreStar | error | storeJson{} , e", starJson, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 更新门店评论总数(定时任务调度)
	 *
	 * @author ben
	 * @date 2015年11月12日 上午11:26:54
	 * @param countJson json字符串 : 里面包含一个门店id-评论数量 ｜ key － store_id , value - count
	 * @return
	 */
	public RestResult<Object> updateStoreCommentCount(String countJson) {
		try {
			Map<String, String> params = new HashMap<>(1);
			params.put("countJson", countJson);
			String json = HttpClientUtils.doPost(host + "/api/store/updateStoreCommentCount.do", API_DEFAULT_TIMEOUT, params);
			// String json = HttpClientUtils.doPost("http://localhost:8080/api/store/updateStoreCommentCount.do", API_DEFAULT_TIMEOUT,
			// params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			logger.debug("remote#store#updateStoreCommentCount | response |  json: {} , params{}", json, params);
			return result;
		} catch (Exception e) {
			logger.error("remote#store#updateStoreCommentCount | error | countJson{} , e", countJson, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 远程查询所有门店ID
	 * 
	 * @author Hins
	 * @date 2015年11月28日 下午12:18:42
	 * 
	 * @return
	 */
	public String adminQueryAllStoreId() {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/getAllStoreIds", API_DEFAULT_TIMEOUT);
			RestResult<String> result = JsonUtils.parseObject(json, new TypeReference<RestResult<String>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#adminQueryAllStoreId | 远程查询所有门店ID错误 | e: {}", e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 远程查询所有餐厅正在营业、休业、结业等状态的数量<br>
	 * 返回map，key-餐厅状态值，参照StoreStatus枚举，value-当前状态对应的数量
	 * 
	 * @author Hins
	 * @date 2016年1月22日 下午4:42:21
	 * 
	 * @return
	 */
	public Map<Short, Integer> adminStatStoreStatus() {
		logger.info("remote#store#adminStatStoreStatus | 远程查询所有餐厅每个状态的数量");
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/statByStatus", API_DEFAULT_TIMEOUT);
			RestResult<Map<Short, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Short, Integer>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#store#adminStatStoreStatus | 远程查询所有餐厅每个状态的数量错误 | e: {}", e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理后台删除标签后 1, 删除门店中关联此tag的项 2, 更新阿里云开放搜索
	 * 
	 * @param tagId 标签ID
	 * @return
	 */
	public RestResult<Object> adminDeleteStoreTag(int tagId) {
		try {
			Map<String, String> params = new HashMap<>(2);
			params.put("tagId", tagId + "");
			String json = HttpClientUtils.doPost(host + "/api/store/tag/delete.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminDeleteStoreTag | 远程删除门店TAG关联项错误 | e: {}", e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 根据餐厅id，获取该餐厅所有的标签信息
	 * 
	 * @param storeId - 餐厅id
	 * @author hins
	 * @date 2017年1月5日 下午4:12:45
	 * @return 标签列表，如果出现异常，返回empty list
	 */
	public List<StoreTagMp> listTagsByStoreId(long storeId){
		ParameterUtil.assertGreaterThanZero(storeId, "餐厅id不合法");
		String url = CommonConstants.STORE_DOMAIN + "/api/store/tag/list?storeId=" + storeId;
		
		
        try {
        	String json = HttpClientUtils.doGet(url, 3000);

        	RestResult<List<StoreTagMp>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<StoreTagMp>>>() {
    		});
            if (result.isSuccess()) {
                return result.getObject();
            }
            logger.error("remote#tag#listTagsByStoreId | 根据storeId读取标签失败 | storeId:{}, code: {}, message: {}", storeId, result.getCode(), result.getMessage());
        } catch (Exception e) {
        	logger.error("remote#tag#listTagsByStoreId | 根据storeId读取标签失败", e);
        }
        return Collections.emptyList();
	}

	/**
	 * 根据餐厅id，获取该cblog餐厅所有的标签信息
	 * @param sinfoId
	 * @return 标签列表，如果出现异常，返回empty list
	 * @author east
	 * @date 2017年2月14日 下午6:52:24
	 */
	public List<NewsStoreinfoTag> listTagsBySinfoId(long sinfoId){
		ParameterUtil.assertGreaterThanZero(sinfoId, "餐厅id不合法");
		String url = CommonConstants.STORE_DOMAIN + "/api/storeinfo/tag/listBySinfoId";
		
        try {
        	Map<String, String> params = new HashMap<String, String>();
        	params.put("sinfoId", sinfoId+"");
        	
        	String json = HttpClientUtils.doGet(url, params);
        	RestResult<List<NewsStoreinfoTag>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<NewsStoreinfoTag>>>() {
    		});
            if (result.isSuccess()) {
                return result.getObject();
            }
            logger.error("remote#tag#listTagsBySinfoId | 根据sinfoId读取标签失败 | sinfoId:{}, code: {}, message: {}", sinfoId, result.getCode(), result.getMessage());
        } catch (Exception e) {
        	logger.error("remote#tag#listTagsBySinfoId | 根据sinfoId读取标签失败", e);
        }
        return Collections.emptyList();
	}
	
	/**
	 * 根据storeId 查询关联的cblog餐厅
	 * @param storeId
	 * @return    
	 * @author east
	 * @date 2017年2月20日 下午4:51:12
	 */
	public NewsStoreinfo getSinfoByStoreId(long storeId){
		ParameterUtil.assertGreaterThanZero(storeId, "餐厅id不合法");
		String url = CommonConstants.STORE_DOMAIN + "/api/store/news/get/sinfoId";
		
        try {
        	Map<String, String> params = new HashMap<String, String>();
        	params.put("storeId", storeId+"");
        	
        	String json = HttpClientUtils.doGet(url, params);
        	RestResult<NewsStoreinfo> result = JsonUtils.parseObject(json, new TypeReference<RestResult<NewsStoreinfo>>() {
    		});
            if (result.isSuccess()) {
                return result.getObject();
            }
            logger.error("storeRemote#getSinfoIdByStoreId | 根据storeId获取关联的cblog餐厅id | storeId:{}, code: {}, message: {}", storeId, result.getCode(), result.getMessage());
        } catch (Exception e) {
        	logger.error("remote#getSinfoIdByStoreId | 根据sinfoId读取标签失败", e);
        }
        return null;
	}
	
	/**
	 * 根据用户id和支付方式，获取商家是否签约了直连
	 * @param uid
	 * @param payMode
	 * @return    
	 * @author east
	 * @date 2017年4月26日 下午4:40:09
	 */
	public StorePayAccount getStorePayAccount(long uid, short payMode){
		try {
        	Map<String, String> params = new HashMap<String, String>();
        	params.put("uid", uid+"");
        	params.put("payMode", payMode+"");
        	
        	String json = HttpClientUtils.doGet(CommonConstants.STORE_DOMAIN + "/api/get/store/pay/account", params);
        	RestResult<StorePayAccount> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StorePayAccount>>() {
    		});
            if (result.isSuccess()) {
                return result.getObject();
            }
            logger.info("storeRemote#getStorePayAccount | 根据用户id和支付方式，获取商家的直连信息 | uid:{}, payMode: {}", uid, payMode);
        } catch (Exception e) {
        	logger.error("storeRemote#getStorePayAccount | 根据用户id和支付方式，获取商家的直连信息error | uid:{}, payMode: {}", uid, payMode, e);
        }
        return null;
	}
}
