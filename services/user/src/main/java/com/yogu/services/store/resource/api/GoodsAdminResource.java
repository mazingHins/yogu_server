package com.yogu.services.store.resource.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.web.RestResult;
import com.yogu.services.store.Goods;
import com.yogu.services.store.GoodsTagsVO;
import com.yogu.services.store.base.dto.GoodsTag;
import com.yogu.services.store.base.dto.GoodsTagMp;
import com.yogu.services.store.base.service.GoodsTagMpService;
import com.yogu.services.store.base.service.GoodsTagService;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.resource.params.GoodsParam;

@Named
@Singleton
@Path("api")
@Produces("application/json;charset=UTF-8")
public class GoodsAdminResource {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsAdminResource.class);
	
	@Inject
	private GoodsService goodsService;
	
	@Inject
	private GoodsTagService goodsTagService;
	
	@Inject
	private GoodsTagMpService goodsTagMpService;
	
	/**
	 * 搜索美食数据，按分页形式返回
	 */
	@GET
	@Path("goods/query")
	public RestResult<List<Goods>> query(@QueryParam("query") String query, @QueryParam("pageIndex") int pageIndex,
			@QueryParam("pageSize") int pageSize) {
		logger.info("api#dish#query | 搜索美食列表 | query: {}, pageIndex: {}, pageSize: {}", query, pageIndex, pageSize);
		
		return new RestResult<>(goodsService.listByName(query, pageIndex, pageSize));
	}
	
	@GET
	@Path("goods/detail")
	public RestResult<Goods> detail(@QueryParam("goodsKey") long goodsKey) {
		logger.info("api#goods#detail | 查看商品详情 | goodsKey: {}", goodsKey);
		return new RestResult<>(goodsService.getByKey(goodsKey));
	}
	
	@POST
	@Path("goods/saveGoods.do")
	public RestResult<Integer> saveGoods(@Valid @BeanParam  GoodsParam params) {
		logger.info("api#goods#saveGoods | 新增商品 | params: {}", JsonUtils.toJSONString(params));
		
		Goods goods = VOUtil.from(params, Goods.class);
		goodsService.addGoods(goods);
		return new RestResult<>(1);
	}
	
	@POST
	@Path("goods/soldOut.do")
	public RestResult<Integer> soldOut(@FormParam("goodsId") long goodsId) {
		logger.debug("api#goods#soldOut | 下架商品 | goodsId: {}", goodsId);
		
		goodsService.setDishStatus(goodsId, BooleanConstants.FALSE);
		return new RestResult<>(1);
	}
	
	@POST
	@Path("goods/shelves.do")
	public RestResult<Integer> shelves(@FormParam("goodsId") long goodsId) {
		logger.debug("api#dish#shelves | 上架商品 | goodsId: {}", goodsId);
		goodsService.setDishStatus(goodsId, BooleanConstants.TRUE);
		return new RestResult<>(1);
	}
	
	@GET
	@Path("tag/listAll")
	public RestResult<List<GoodsTagsVO>> listAll(@QueryParam("goodsKey") long goodsKey) {
		logger.info("api#dish#listAll | 获取所有商品tag");
		List<GoodsTag> list = goodsTagService.listAll();
		if(goodsKey == 0){
			return new RestResult<>(VOUtil.fromList(list, GoodsTagsVO.class));
		}
		
		
		List<GoodsTagMp> mpList = goodsTagMpService.listByGoods(goodsKey);
		Set<Long> mpSet = new HashSet<>(mpList.size()*4/3+2);
		for(GoodsTagMp mp : mpList){
			mpSet.add(mp.getTagId());
		}
		
		List<GoodsTagsVO> result = new ArrayList<>(list.size());
		for(GoodsTag tag : list){
			GoodsTagsVO vo = VOUtil.from(tag, GoodsTagsVO.class);
			if(mpSet.contains(vo.getTagId())){
				vo.setIsCheck(BooleanConstants.TRUE);
			}
			result.add(vo);
		}
		return new RestResult<>(result);
		
	}
	
}
