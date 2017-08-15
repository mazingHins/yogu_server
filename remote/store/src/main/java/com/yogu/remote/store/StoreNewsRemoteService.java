package com.yogu.remote.store;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
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
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.store.news.dto.NewsBlock;
import com.yogu.services.store.news.dto.NewsStoreinfo;

/**
 * 餐厅资讯 remote 接口
 * 
 * @author sky 2016-05-10
 *
 */
@Named
public class StoreNewsRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(StoreNewsRemoteService.class);
	private final String host = CommonConstants.STORE_DOMAIN;

	// 版本发布

	/**
	 * 发布版本, 该接口完成reload数据至缓存
	 * 
	 * @param cityCode
	 * @param adminId
	 * @return 成功返回true,失败抛出异常
	 * @author sky 2016-05-10
	 */
	public RestResult<Boolean> publishVersion(String cityCode, String langCode, long adminId) {

		logger.info("remote#storeNews#publishVersion | 发布正式版 | cityCode: {}, langCode: {},  adminId: {}", cityCode, langCode, adminId);

		Map<String, String> params = new HashMap<>(3);
		params.put("cityCode", cityCode);
		params.put("langCode", langCode);
		params.put("adminId", adminId + "");
		String json = HttpClientUtils.doPost(host + "/api/store/news/version/pub.do", params);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	// ----------------资讯推荐 块 block 操作接口 -----------------
	/**
	 * 根据cityCode获取 资讯推荐 块数据列表
	 * 
	 * @param cityCode 城市code
	 * @param langCode 语言code
	 * @return 块数据列表
	 * @author sky 2016-05-10
	 */
	public List<NewsBlock> listBlockByCity(String cityCode, String langCode, int pageIndex, int pageSize) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/news/block/list?cityCode=" + cityCode + "&langCode=" + langCode+ "&pageIndex=" + pageIndex
					+ "&pageSize=" + pageSize);
			RestResult<List<NewsBlock>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<NewsBlock>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeNews#block | listBlockByCity Error | cityCode: {}, langCode: {}, message: {}", cityCode, langCode, e.getMessage(), e);
		}

		return Collections.emptyList();
	}

	/**
	 * 获取指定的资讯推荐块信息
	 * 
	 * @param bid 块ID
	 * @author sky 2016-05-10
	 */
	public NewsBlock getBlock(long bid) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/news/block/get?bid=" + bid);
			RestResult<NewsBlock> result = JsonUtils.parseObject(json, new TypeReference<RestResult<NewsBlock>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeNews#block | getBlock Error | bid: {}, message: {}", bid, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 新增资讯推荐 块
	 * 
	 * @param block 需要添加的块数据
	 * @param adminId 管理员ID
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-05-10
	 */
	public RestResult<Long> addBlock(NewsBlock block, long adminId) {
		block.setAdminId(adminId);
		String cityCode = block.getCityCode();
		logger.info("remote#storeNews#block | 新增资讯推荐块 | adminId: {}, cityCode: {}, block:{}", adminId, cityCode,
				JsonUtils.toJSONString(block));

		Map<String, String> params;
		try {
			params = toMapString(block);
		} catch (Exception e) {
			logger.error("remote#storeNews#addBlock | 新增block, Object to map 数据转换时出错 | block: {}", JsonUtils.toJSONString(block));
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "block参数错误");
		}

		String json = HttpClientUtils.doPost(host + "/api/store/news/block/add.do", params);
		RestResult<Long> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Long>>() {
		});
		return result;
	}

	public int increaseReadCount(long bid) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/news/block/increaseReadCount?bid=" + bid);
			RestResult<Integer> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Integer>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeNews#block | increaseReadCount Error | bid: {}, message: {}", bid, e.getMessage(), e);
		}
		return 0;
	}

	/**
	 * 修改资讯推荐块的内容
	 * 
	 * @param block 需要修改的块数据内容（bid必须有值）
	 * @param adminId 管理员ID
	 * @author sky 2016-05-10
	 */
	public RestResult<Long> updateBlock(NewsBlock block, long adminId) {
		block.setAdminId(adminId);
		long bid = block.getBid();
		logger.info("remote#storeNews#block | 修改块标题 | adminId: {}, bid: {}, block:{}", adminId, bid, JsonUtils.toJSONString(block));

		Map<String, String> params;
		try {
			params = toMapString(block);
		} catch (Exception e) {
			logger.error("remote#storeNews#updateBlock | 修改block, Object to map 数据转换时出错 | block: {}", JsonUtils.toJSONString(block));
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "block参数错误");
		}

		String json = HttpClientUtils.doPost(host + "/api/store/news/block/update.do", params);
		RestResult<Long> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Long>>() {
		});
		return result;
	}

	/**
	 * 删除块
	 * 
	 * @param bid 块id
	 * @param adminId 管理员id
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-05-10
	 */
	public RestResult<Boolean> deleteBlock(long bid, long adminId) {

		logger.info("remote#storeNews#block | 删除资讯推荐块 | bid: {},  adminId: {}", bid, adminId);

		String json = HttpClientUtils.doGet(host + "/api/store/news/block/delete?bid=" + bid + "&adminId=" + adminId);
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
	 * @author sky 2016-05-10
	 */
	public RestResult<Boolean> mvBlock(long bid, int type, long adminId) {
		logger.debug("remote#storeNews#block | mvBlock | bid: {}, type: {}, adminId: {}", bid, type, adminId);

		String url = new StringBuilder(host).append("/api/store/news/block/move?bid=")//
				.append(bid).append("&type=").append(type).append("&adminId=").append(adminId).toString();
		String json = HttpClientUtils.doGet(url);
		logger.info("remote#storeNews#block | mvBlock | bid: {}, type: {}, adminId: {}, result: {}", bid, type, adminId, json);

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
	 * @author sky 2016-05-10
	 */
	public RestResult<Boolean> enableBlock(long bid, long adminId) {
		logger.info("remote#storeNews#block | 启用块 | bid: {},  adminId: {}", bid, adminId);

		String json = HttpClientUtils.doGet(host + "/api/store/news/block/enable?bid=" + bid + "&adminId=" + adminId);
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
	 * @author sky 2016-05-10
	 */
	public RestResult<Boolean> disableBlock(long bid, long adminId) {

		logger.info("remote#storeNews#block | 停用块 | bid: {},  adminId: {}", bid, adminId);

		String json = HttpClientUtils.doGet(host + "/api/store/news/block/disable?bid=" + bid + "&adminId=" + adminId);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	// ---------资讯推荐 餐厅详情 信息 storeInfo 操作接口--------------

	/**
	 * 获取全部的资讯餐厅详情信息列表
	 * 
	 * @author sky 2016-05-10
	 */
	public List<NewsStoreinfo> listAllStoreinfo(int pageIndex, int pageSize) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/news/storeinfo/list?pageIndex=" + pageIndex + "&pageSize=" + pageSize);
			RestResult<List<NewsStoreinfo>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<NewsStoreinfo>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeNews#listAllStoreinfo | Error | message: {}", e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 根据id获取餐厅详情信息
	 * 
	 * @param sinfoId 资讯餐厅id
	 * @return
	 * @author sky 2016-05-10
	 */
	public NewsStoreinfo getStoreinfo(long sinfoId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/news/storeinfo/get?sinfoId=" + sinfoId);
			RestResult<NewsStoreinfo> result = JsonUtils.parseObject(json, new TypeReference<RestResult<NewsStoreinfo>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeNews#getStoreinfo | Error | sinfoId: {}, message: {}", sinfoId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 新增 资讯餐厅
	 * 
	 * @param storeInfo 需要添加的资讯餐厅信息
	 * @param adminId 管理员ID
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-05-10
	 */
	public RestResult<Boolean> addStoreinfo(NewsStoreinfo storeInfo, long adminId) {
		logger.info("remote#storeNews#addStoreinfo | 新增资讯餐厅 | adminId: {}, storeInfo:{}", adminId, JsonUtils.toJSONString(storeInfo));

		Map<String, String> params;
		try {
			params = toMapString(storeInfo);
		} catch (Exception e) {
			logger.error("remote#storeNews#addStoreinfo | 新增资讯餐厅, Object to map 数据转换时出错 | block: {}", JsonUtils.toJSONString(storeInfo));
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "storeInfo参数错误");
		}

		params.put("adminId", adminId + "");

		String json = HttpClientUtils.doPost(host + "/api/store/news/storeinfo/add.do", params);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 修改资讯餐厅内容
	 * 
	 * @param storeInfo 需要修改的资讯餐厅信息
	 * @param adminId 管理员ID
	 * @author sky 2016-05-10
	 */
	public RestResult<Boolean> updateStoreinfo(NewsStoreinfo storeInfo, long adminId) {
		logger.info("remote#storeNews#updateStoreinfo | 更新资讯餐厅 | adminId: {}, storeInfo:{}", adminId, JsonUtils.toJSONString(storeInfo));

		Map<String, String> params;
		try {
			params = toMapString(storeInfo);
		} catch (Exception e) {
			logger.error("remote#storeNews#updateStoreinfo | 更新资讯餐厅, Object to map 数据转换时出错 | block: {}", JsonUtils.toJSONString(storeInfo));
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "storeInfo参数错误");
		}

		params.put("adminId", adminId + "");

		String json = HttpClientUtils.doPost(host + "/api/store/news/storeinfo/update.do", params);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	/**
	 * 删除资讯餐厅
	 * 
	 * @param sinfoId 资讯餐厅id
	 * @param adminId 管理员id
	 * @return true/false , true:成功; false:失败; 或者抛出异常
	 * @author sky 2016-05-10
	 */
	public RestResult<Boolean> deleteStoreinfo(long sinfoId, long adminId) {

		logger.info("remote#storeNews#deleteStoreinfo | 删除资讯餐厅| sinfoId: {},  adminId: {}", sinfoId, adminId);

		String json = HttpClientUtils.doGet(host + "/api/store/news/storeinfo/delete?sinfoId=" + sinfoId + "&adminId=" + adminId);
		RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
		});
		return result;
	}

	// ------------------------------

	// 获取统计数据

	/**
	 * 获取用户吃过的资讯餐厅总数
	 * 
	 * @return key = uid, value = 总数 的集合
	 * @author sky 2016-05-12
	 */
	public Map<Long, Integer> getUserEatenCountStatistics() {

		try {
			String json = HttpClientUtils.doGet(host + "/api/store/news/user/fav/statistics");
			RestResult<Map<Long, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Integer>>>() {
			});

			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeNews#getUserEatenCountStatistics | Error |  message: {}", e.getMessage(), e);
		}

		return Collections.emptyMap();
	}

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
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = (getter != null ? getter.invoke(object) : null);
			if (value != null) {
				if (value instanceof Date) {
					// map.put(key, sdf.format((Date) value));
					map.put(key, String.valueOf(((Date) value).getTime()));
				} else {
					map.put(key, String.valueOf(value));
				}
			}
		}

		return map;
	}
}
