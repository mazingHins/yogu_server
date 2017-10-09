package com.yogu.services.store.resource.api;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.RestResult;
import com.yogu.services.store.Goods;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.business.service.GoodsTrackService;

/**
 * 菜品相关的 本地API，提供给集群内部使用的 <br>
 * 
 * @author hins 2017年10月9日 下午12:38:27
 */
@Named
@Singleton
@Path("api")
@Produces("application/json;charset=UTF-8")
public class GoodsApiResource {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsApiResource.class);
	
	@Inject
	private GoodsService goodsService;
	
	@Inject
	private GoodsTrackService goodsTrackService;
	
	/**
	 * 根据ID查询商品信息
	 */
	@GET
	@Path("goods/get")
	public RestResult<Goods> getGoodsById(@QueryParam("goodsId") long goodsId) {
		logger.debug("api#goods#getGoodsById | get goods | goodsId: {}", goodsId);
		Goods goods = goodsService.getById(goodsId);
		return new RestResult<>(goods);
	}
	
	/**
	 * 根据ID查询商品快照信息
	 */
	@GET
	@Path("dishTrack/get")
	public RestResult<Goods> getGoodsTrackById(@QueryParam("goodsId") long goodsId) {
		logger.debug("api#goods#getGoodsTrack | get goods | goodsId: {}", goodsId);
		Goods dish = goodsTrackService.getTrackById(goodsId);
		return new RestResult<>(dish);
	}
	
	

}
