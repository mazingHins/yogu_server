package com.yogu.remote.store;

import java.util.Collections;
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
import com.yogu.services.store.Goods;

/**
 * 对商品的远程操作服务类 <br>
 * 
 * @author qiujun 2018年10月09日 下午12:32:55
 */
@Named
public class GoodsRemoteService {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsRemoteService.class);
	
	private static final String host = CommonConstants.STORE_DOMAIN;

	
	/**
	 * 根据商品ID获取内容
	 * 
	 * @param dishId 菜品ID
	 */
	public Goods getGoods(long goodsId) {
		try {
			// HttpStoreProviderImpl.getDishStoreId() 也有call此URL
			String json = HttpClientUtils.doGet(host + "/api/goods/get?goodsId=" + goodsId);
			RestResult<Goods> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Goods>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#goods#getGoods | Error | goodsId: {}, message: {}", goodsId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据商品ID获取快照内容
	 * 
	 * @param dishId 菜品ID
	 */
	public Goods getGoodsTrack(long goodsId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/goodsTrack/get?goodsId=" + goodsId);
			RestResult<Goods> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Goods>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#goods#getGoodsTrack | Error | goodsId: {}, message: {}", goodsId, e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 根据菜品Id获取菜品快照信息(可多个菜品ID)
	 * 
	 * @param dishIds 菜品Id
	 * @return 菜品信息，若无，返回null
	 */
	public List<Goods> listGoodsTrackByIds(String goodsIds) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/goodsTrack/list?goodsIds=" + goodsIds);
			RestResult<List<Goods>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Goods>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishByIds | Error | goodsIds: {}, message: {}", goodsIds, e.getMessage(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * 搜索美食，分页形式返回
	 * 
	 * @param query 关键字
	 * @param pageIndex 第几页（1开始）
	 * @param pageSize 每页多少条数据
	 */
	public List<Goods> query(String query, int pageIndex, int pageSize) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("query", query);
			params.put("pageSize", pageSize + "");
			params.put("pageIndex", pageIndex + "");
			String json = HttpClientUtils.doGet(host + "/api/goods/query", 3000, params);
			RestResult<List<Goods>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Goods>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#goods#query | Error | query: {}, pageIndex: {}, pageSize: {}, message: {}"//
					, query, pageIndex, pageSize, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

}
