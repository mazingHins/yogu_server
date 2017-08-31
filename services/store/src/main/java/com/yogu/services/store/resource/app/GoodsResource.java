package com.yogu.services.store.resource.app;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.RestResult;
import com.yogu.services.store.business.dto.Goods;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.resource.vo.GoodsVO;

/**
 * 商品相关API <br>
 * 
 * @author qiujun   
 * @date 2017年8月29日 下午8:28:47
 */
@Named
@Path("p")
@Singleton
@Produces("application/json;charset=UTF-8")
public class GoodsResource {
	
	@Inject
	private GoodsService goodsService;
	
	@GET
	@Path("v1/goods/details")
	public RestResult<GoodsVO> details(@QueryParam("goodsKey") long goodsKey) {
		
		Goods goods = goodsService.getByKey(goodsKey);
		if(null == goods){
			return new RestResult<GoodsVO>(null);
		}
		
		return new RestResult<GoodsVO>(VOUtil.from(goods, GoodsVO.class));
	}
	
	
	
		

}
