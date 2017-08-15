package com.yogu.remote.store;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.constant.RecordMoveTypeConstants;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.store.recommend.dto.RecommendBlock;
import com.yogu.services.store.recommend.dto.RecommendItem;
import com.yogu.services.store.recommend.dto.RecommendVersion;

@Named
public class RecommendRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(RecommendRemoteService.class);
	private final String host = CommonConstants.STORE_DOMAIN;

	// ------version start --------------
	/**
	 * 查询版本列表数据
	 */
	public List<RecommendVersion> listVersionByCity(String cityCode, String langCode, int pageIndex, int pageSize) {
		try {
			String url = new StringBuilder(host).append("/api/store/recommend/version/list?cityCode=").append(cityCode).append("&langCode=")
					.append(langCode).append("&pageIndex=").append(pageIndex).append("&pageSize=").append(pageSize).toString();
			String json = HttpClientUtils.doGet(url);
			RestResult<List<RecommendVersion>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<RecommendVersion>>>() {
					});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#recommend#listVersion | Error | cityCode: {}, message: {}", cityCode, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 回滚cityCode的正式版本, 可以指定回滚至的版本,若不指定,默认回滚至最近发布的一条历史版本<br>
	 * <br>
	 * 
	 * 回滚 动作-->回滚至的版本置为正式版,被回滚的版本置为历史版, 回滚所有配置 <br>
	 * <br>
	 * 
	 * 回滚成功,将刷新正式版缓存数据<br>
	 * <br>
	 * 
	 * 该操作只支持正式版的回滚,没有测试版的回滚
	 * 
	 * @param cityCode 城市code
	 * @param adminId 管理员id
	 * @param vid 回滚至的版本id, 可以为null(也就是可以指定回滚至的版本,也可以不指定)
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-01-18
	 */
	public RestResult<Boolean> rollbackVersion(long adminId, int vid) {
		logger.info("remote#recommend#rollback | 回滚版本 | adminId: {}, vid: {}", adminId, vid);

		Map<String, String> params = new HashMap<>(3);
		params.put("adminId", "" + adminId);
		params.put("vid", "" + vid);
		String json = HttpClientUtils.doGet(host + "/api/store/recommend/version/rollback", 10000, params);

		logger.info("remote#recommend#rollback | 回滚版本结果 | adminId: {}, vid: {}, result: '{}'", adminId, vid, json);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 发布测试版(预览版)
	 * 
	 * @return 成功返回true,失败抛出异常
	 * @author sky 2016-01-15
	 */
	public RestResult<Boolean> pubBetaVersion(String cityCode, String langCode, long adminId) {
		logger.info("remote#recommend#pubBetaVersion | 发布测试版 | cityCode: {}, langCode: {}, adminId: {}", cityCode, langCode, adminId);

		Map<String, String> params = new HashMap<>(2);
		params.put("cityCode", cityCode);
		params.put("langCode", langCode);
		params.put("adminId", adminId + "");
		String json = HttpClientUtils.doGet(host + "/api/store/recommend/version/pubBeta", params);

		logger.info("remote#recommend#pubBetaVersion | 发布测试版 结果 | cityCode: {}, langCode: {}, adminId: {}, result: '{}'"//
				, cityCode, langCode, adminId, json);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 发布 正式版
	 * 
	 * @return 成功返回true,失败抛出异常
	 * @author sky 2016-01-15
	 */
	public RestResult<Boolean> pubReleaseVersion(long adminId, int vid) {
		logger.info("remote#recommend#pubReleaseVersion | 发布正式版 | adminId: {}, vid: {}", adminId, vid);

		Map<String, String> params = new HashMap<>();
		params.put("adminId", "" + adminId);
		params.put("vid", "" + vid);
		String json = HttpClientUtils.doGet(host + "/api/store/recommend/version/pubRelease", params);

		logger.info("remote#recommend#pubReleaseVersion | 发布正式版结果 | adminId: {}, vid: {}, result: '{}'", adminId, vid, json);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	// -----------version end ----------------

	// ------------block start -------------------

	/**
	 * 复制一个Block配置（包括其下全部Item）到新的布局中（城市+语言）
	 */
	public RestResult<Boolean> copyBlock(long sourceBid, String destCityCode, String destLangCode, String newTitle, long adminId) {
		logger.info("remote#recommend#copyBlock | 复制Block | adminId: {}, sourceBid: {}, destCityCode: {}, destLangCode: {}, newTitle: '{}'"//
				, adminId, sourceBid, destCityCode, destLangCode, newTitle);

		StringBuilder url = new StringBuilder(host).append("/api/store/recommend/block/copy.do?adminId=").append(adminId);
		url.append("&sourceBid=").append(sourceBid).append("&destCityCode=").append(destCityCode).append("&destLangCode=")
				.append(destLangCode);
		Map<String, String> body = new HashMap<>();
		body.put("newTitle", newTitle);

		String json = HttpClientUtils.doPost(url.toString(), body);
		logger.info("remote#recommend#copyBlock | 复制Block结果 | {}", json);

		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 校验块数据的开始时间、结束时间是否重叠多次（同一个时间，生效的自动推送块，不能超过2个）
	 */
	public RestResult<Boolean> valiStartEndMinute(String cityCode, String langCode, long bid, int startMinute, int endMinute) {
		StringBuilder url = new StringBuilder(host);
		url.append("/api/store/recommend/block/valiStartEndMinute").append("?cityCode=").append(cityCode).append("&langCode=")
				.append(langCode).append("&startMinute=").append(startMinute).append("&endMinute=").append(endMinute);
		if (0 <= bid)
			url.append("&bid=").append(bid);

		String json = HttpClientUtils.doGet(url.toString());
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 修改块的标题
	 * 
	 * @param block 需要修改的块数据内容（bid必须有值）
	 * @param adminId 管理员ID
	 */
	public RestResult<Boolean> updateBlock(RecommendBlock block, long adminId) {
		block.setAdminId(adminId);
		long bid = block.getBid();
		logger.info("remote#recommend#block | 修改块标题 | adminId: {}, bid: {}, block:{}", adminId, bid, JsonUtils.toJSONString(block));

		Map<String, String> params;
		try {
			params = toMapString(block);
		} catch (Exception e) {
			logger.error("remote#recommend#updateBlock | 修改block, Object to map 数据转换时出错 | block: {}", JsonUtils.toJSONString(block));
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "block参数错误");
		}

		String json = HttpClientUtils.doPost(host + "/api/store/recommend/block/update.do", params);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 新增 块
	 * 
	 * @param block 需要添加的块数据
	 * @param adminId 管理员ID
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-01-14
	 */
	public RestResult<Boolean> addBlock(RecommendBlock block, long adminId) {
		block.setAdminId(adminId);
		String cityCode = block.getCityCode();
		String langCode = block.getLangCode();
		logger.info("remote#recommend#block | 新增块 | adminId: {}, cityCode: {}, langCode: {}, block:{}"//
				, adminId, cityCode, langCode, JsonUtils.toJSONString(block));

		Map<String, String> params;
		try {
			params = toMapString(block);
		} catch (Exception e) {
			logger.error("remote#recommend#addBlock | 新增block, Object to map 数据转换时出错 | block: {}", JsonUtils.toJSONString(block));
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "block参数错误");
		}

		String json = HttpClientUtils.doPost(host + "/api/store/recommend/block/add.do", params);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 获取指定的块信息
	 * 
	 * @param bid 块ID
	 */
	public RecommendBlock getBlock(long bid) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/recommend/get/block?bid=" + bid);
			RestResult<RecommendBlock> result = JsonUtils.parseObject(json, new TypeReference<RestResult<RecommendBlock>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#recommend#block | getBlock Error | bid: {}, message: {}", bid, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据cityCode获取 推荐 块数据列表
	 * 
	 * @param cityCode 城市code
	 * @return 块数据列表
	 * @author sky 2016-01-14
	 */
	public List<RecommendBlock> listBlockByCity(String cityCode, String langCode) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/recommend/list/block?cityCode=" + cityCode + "&langCode=" + langCode);
			RestResult<List<RecommendBlock>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<RecommendBlock>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#recommend#block | listBlockByCity Error | cityCode: {}, langCode: {}, message: {}"//
					, cityCode, langCode, e.getMessage(), e);
		}

		return Collections.emptyList();
	}

	/**
	 * 删除块
	 * 
	 * @param bid 块id
	 * @param adminId 管理员id
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-01-14
	 */
	public RestResult<Boolean> deleteBlock(long bid, long adminId) {

		logger.info("remote#recommend#block | 删除块 | bid: {},  adminId: {}", bid, adminId);

		String json = HttpClientUtils.doGet(host + "/api/store/recommend/deleteBlock?bid=" + bid + "&adminId=" + adminId);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 移动块的位置 ,具体移动操作的类型请参看 @see {@link RecordMoveTypeConstants}<br>
	 * 如果已经是无法移动的位置（例如：上移，但是已经是第一个了）仍然返回true
	 * 
	 * @param bid 块ID
	 * @param type 移动操作的类型 1:上移 2:下移 3:置顶 4:置底
	 * @param adminId 管理员ID
	 * @return true/false
	 * @author sky 2016-01-16
	 */
	public RestResult<Boolean> mvBlock(long bid, int type, long adminId) {
		logger.debug("remote#recommend#block | mvBlock | bid: {}, type: {}, adminId: {}", bid, type, adminId);

		String url = new StringBuilder(host).append("/api/store/recommend/block/mvBlock?bid=")//
				.append(bid).append("&type=").append(type).append("&adminId=").append(adminId).toString();
		String json = HttpClientUtils.doGet(url);
		logger.info("remote#recommend#block | mvBlock | bid: {}, type: {}, adminId: {}, result: {}", bid, type, adminId, json);

		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 启用块
	 * 
	 * @param bid 块id
	 * @param adminId 管理员id
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-01-14
	 */
	public RestResult<Boolean> enableBlock(long bid, long adminId) {
		logger.info("remote#recommend#block | 启用块 | bid: {},  adminId: {}", bid, adminId);

		String json = HttpClientUtils.doGet(host + "/api/store/recommend/enableBlock?bid=" + bid + "&adminId=" + adminId);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 停用块
	 * 
	 * @param bid 块id
	 * @param adminId 管理员id
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-01-14
	 */
	public RestResult<Boolean> disableBlock(long bid, long adminId) {

		logger.info("remote#recommend#block | 停用块 | bid: {},  adminId: {}", bid, adminId);

		String json = HttpClientUtils.doGet(host + "/api/store/recommend/disableBlock?bid=" + bid + "&adminId=" + adminId);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	// ------------block end -------------------

	// ------------item start -------------------

	/**
	 * 获取指定的数据项
	 */
	public RecommendItem getItem(int itemId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/recommend/get/item?itemId=" + itemId);
			RestResult<RecommendItem> result = JsonUtils.parseObject(json, new TypeReference<RestResult<RecommendItem>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#recommend#getItem | Error | itemId: {}, message: {}", itemId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取块下面的全部项数据
	 * 
	 * @param bid 块ID
	 */
	public List<RecommendItem> listItemByBlock(int bid) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/recommend/list/item?bid=" + bid);
			RestResult<List<RecommendItem>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<RecommendItem>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#recommend#listItemByBlock | Error | bid: {}, message: {}", bid, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 移动项的位置（一次只移动一个位置）<br>
	 * 如果已经是无法移动的位置（例如：上移，但是已经是第一个了）仍然返回true
	 * 
	 * @param itemId 项ID
	 * @param type 上移还是下移；1：上移、其他：下移
	 * @param adminId 管理员ID
	 */
	public RestResult<Boolean> mvItem(int itemId, int type, long adminId) {
		logger.info("remote#recommend#mvItem | mvItem | itemId: {}, type: {}, adminId: {}", itemId, type, adminId);

		String url = new StringBuilder(host).append("/api/store/recommend/item/mvItem?itemId=").append(itemId).append("&type=").append(type)
				.append("&adminId=").append(adminId).toString();
		String json = HttpClientUtils.doGet(url);
		logger.info("remote#recommend#mvItem | mvItem | itemId: {}, type: {}, adminId: {}, result: {}", itemId, type, adminId, json);

		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 新增 item子项
	 * 
	 * @param item 子项item
	 * @author sky 2016-01-15
	 */
	public RestResult<Boolean> addItem(RecommendItem item) {

		logger.info("remote#recommend#addItem | 新增块下的item子项  | item: {}", item);

		Map<String, String> params;
		try {
			params = toMapString(item);
		} catch (Exception e) {
			logger.error("remote#recommend#addItem | 新增item, Object to map 数据转换时出错 | item: {}", item);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "item参数错误");
		}

		String json = HttpClientUtils.doPost(host + "/api/store/recommend/item/add.do", params);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;

	}

	/**
	 * 删除块下的item子项
	 * 
	 * @param itemId 子项id
	 * @param adminId 管理员id
	 * @return true/false/异常
	 * @author sky 2016-01-18
	 */
	public RestResult<Boolean> deleteItem(int itemId, long adminId) {
		logger.info("remote#recommend#deleteItem | 删除块下的item子项  | itemId: {}, adminId: {}", itemId, adminId);

		String json = HttpClientUtils.doGet(host + "/api/store/recommend/item/delete?itemId=" + itemId + "&adminId=" + adminId);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 更新item, 更新的字段, 包括{img, target, targetId, name, description, logo, price, originalPrice, url, canShare,
	 * targetParams,shareImg, shareTitle, shareContent}
	 * 
	 * 注意：item所有的字段的数据都需要回传-->改动的以及 没改动的 都需要回传
	 * 
	 * @param item
	 * @return
	 * @author sky 2016-01-19
	 */
	public RestResult<Boolean> updateItem(RecommendItem item, long adminId) {

		logger.info("remote#recommend#updateItem | 更新item子项  | item: {}, adminId: {}", item, adminId);

		if (item == null)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "需要更新的item为空");

		item.setAdminId(adminId);

		Map<String, String> params;
		try {
			params = toMapString(item);
		} catch (Exception e) {
			logger.error("remote#recommend#addItem | 更新item, Object to map 数据转换时出错 | item: {}", item);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "item参数错误");
		}

		String json = HttpClientUtils.doPost(host + "/api/store/recommend/item/update.do", params);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;

	}

	/**
	 * 启用项
	 * 
	 * @param itemId 项id
	 * @param adminId 管理员id
	 */
	public RestResult<Boolean> enableItem(long itemId, long adminId) {
		logger.info("remote#recommend#item | 启用项 | itemId: {},  adminId: {}", itemId, adminId);
		String json = HttpClientUtils.doGet(host + "/api/store/recommend/enableItem?itemId=" + itemId + "&adminId=" + adminId);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 停用项
	 * 
	 * @param itemId 项id
	 * @param adminId 管理员id
	 */
	public RestResult<Boolean> disableItem(long itemId, long adminId) {
		logger.info("remote#recommend#item | 停用项 | itemId: {},  adminId: {}", itemId, adminId);
		String json = HttpClientUtils.doGet(host + "/api/store/recommend/disableItem?itemId=" + itemId + "&adminId=" + adminId);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	// ------------item end -------------------

	/**
	 * 把 Object 转成 map，浅层转换
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	private static Map<String, String> toMapString(Object object) throws Exception {
		Map<String, String> map = new HashMap<>();

		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_mm_ss);
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = (getter != null ? getter.invoke(object) : null);
			if (value != null) {
				if (value instanceof Date) {
					map.put(key, sdf.format((Date) value));
				} else {
					map.put(key, String.valueOf(value));
				}
			}
		}

		return map;
	}
}
