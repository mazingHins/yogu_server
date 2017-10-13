package com.yogu.services.store.resource.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.utils.ComputeUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.language.StoreMessages;
import com.yogu.services.store.Goods;
import com.yogu.services.store.GoodsOrderVO;
import com.yogu.services.store.StoreSettleOrderVO;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.business.service.GoodsTrackService;
import com.yogu.services.store.resource.params.PurchaseDetail;

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
	
	/**
	 * 预下单接口，有关store域的操作<br>
	 * 包括：装载餐厅、美食、配送费（包括顺丰送）、可选配送时间、库存<br>
	 * 方法同时会验证美食是否存在
	 * 
	 * @param reqParams
	 * @param request
	 * @author hins
	 * @date 2016年10月1日 上午11:03:43
	 * @return RestResult<Object>
	 */
	@POST
	@Path("v1/store/order/settle.do")
	public RestResult<StoreSettleOrderVO> settleOrder(@FormParam("purchaseDetail") String purchaseDetail, @FormParam("uid") long uid, @Context HttpServletRequest request) {
		logger.info("api#LoaclOrderApiResource#settleOrder | 预下单接口，有关store域的操作start | purchaseDetail: {}", JsonUtils.toJSONString(purchaseDetail));

		ParameterUtil.assertNotBlank(purchaseDetail, StoreMessages.STORESERVICE_UPDATETOPICIMG_DISH_NOT_EXIST());
		List<PurchaseDetail> list = JsonUtils.parseArray(purchaseDetail, PurchaseDetail.class);
		
		// 1. 查询美食信息
		List<Long> goodsKeys = new ArrayList<Long>(list.size());
		for(PurchaseDetail detail : list){
			goodsKeys.add(detail.getGoodsKey());
		}
		List<Goods> goodsList = goodsService.listBykeys(uid, goodsKeys);
		Map<Long, Goods> goodsMap = new HashMap<Long, Goods>( goodsList.size() * 4 / 3 + 2);
		for(Goods goods : goodsList){
			goodsMap.put(goods.getGoodsKey(), goods);
		}
 		
		// 2. 装载返回结果
		long goodsFee = 0;	// 总商品价格
		List<GoodsOrderVO> goList = new ArrayList<GoodsOrderVO>(goodsList.size());
		for(PurchaseDetail detail : list){
			Goods goods = goodsMap.get(detail.getGoodsKey());
			if(null == goods){
				continue;
			}
			GoodsOrderVO vo = VOUtil.from(goods, GoodsOrderVO.class);
			
			long tmp = ComputeUtils.multCeiling(vo.getPrice(), detail.getPurchaseNum());// 单个商品总价
			vo.setTotalFee(tmp);
			goodsFee = goodsFee + tmp;
			goList.add(vo);
		}
		
		StoreSettleOrderVO result = new StoreSettleOrderVO();
		result.setGoodsFee(goodsFee);
		result.setList(goList);
		return new RestResult<StoreSettleOrderVO>(result);

	}
	
	

}
