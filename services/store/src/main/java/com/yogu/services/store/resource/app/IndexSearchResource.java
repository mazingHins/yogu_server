package com.yogu.services.store.resource.app;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.services.store.Goods;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.resource.vo.GoodsVO;


/**
 * 商品查询相关api接口
 *
 */
@Named
@Path("p")
@Singleton
@Produces("application/json;charset=UTF-8")
public class IndexSearchResource {
	
	@Inject
	private GoodsService goodsService;
	
	private static final int MAX_SIZE = 20, DEFAULT_SIZE = 10, MIN_SIZE = 5;
	
	@GET
	@Path("v1/search/goods")
	public RestResult<List<GoodsVO>> goodsList(@QueryParam("keyword") String keyword, @QueryParam("pageSize") int pageSize, @QueryParam("lastTime") long lastTime) {
		Long uid = SecurityContext.getUserId();
		pageSize = PageUtils.limitSize(pageSize, MIN_SIZE, MAX_SIZE);
		List<Goods> goodsList = goodsService.listByName(keyword, uid, lastTime, pageSize);
		List<GoodsVO> result = new ArrayList<>(goodsList.size());
		for (Goods goods : goodsList) {
			GoodsVO vo = VOUtil.from(goods, GoodsVO.class);
			vo.setPrice(goods.getRetailPrice());
			result.add(vo);
		}
		return new RestResult<List<GoodsVO>>(result);
		
	}
	
	

}
