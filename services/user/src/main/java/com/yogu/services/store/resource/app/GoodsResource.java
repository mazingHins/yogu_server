package com.yogu.services.store.resource.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.services.store.Goods;
import com.yogu.services.store.GoodsVO;
import com.yogu.services.store.base.dto.GoodsTag;
import com.yogu.services.store.base.service.GoodsTagService;
import com.yogu.services.store.business.service.GoodsService;

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
	
	@Inject
	private GoodsTagService goodsTagService;
	
	@GET
	@Path("v1/goods/details")
	public RestResult<GoodsVO> details(@QueryParam("goodsKey") long goodsKey) {
		Long uid = SecurityContext.getUserId();
		Goods goods = goodsService.getByKey(goodsKey, uid);
		if(null == goods){
			return new RestResult<GoodsVO>(null);
		}
		
		GoodsVO result = VOUtil.from(goods, GoodsVO.class);
		result.setPrice(goods.getRetailPrice());
		if (StringUtils.isNotBlank(goods.getContent())) {
			String[] content = goods.getContent().trim().split(",");
			result.setContent(content);
		}
		return new RestResult<GoodsVO>(result);
	}
	
	
	/**
	 * 
	 * @author qiujun
	 * @date 2017年10月9日 上午11:23:06 
	 * 
	 * @param tagId - 标签id
	 * @param sort - 1-默认排序；2-按照价格倒序；3-按照价格升序
	 * @param pageIndex - 页码，首页传1
	 * @param pageSize - 每页数据大小
	 * @return
	 */
	@GET
	@Path("v1/goods/listByTag")
	public RestResult<List<GoodsVO>> listByTag(@QueryParam("tagId") long tagId, @QueryParam("sort") int sort, 
			@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
		
		GoodsTag tag = goodsTagService.getById(tagId);
		if(tag == null){
			return new RestResult<List<GoodsVO>>(Collections. <GoodsVO>emptyList());
		}
		
		// 获取符合的商品列表
		Long uid = SecurityContext.getUserId();
		List<Goods> goodsList = null;
		if (sort == 2) {
			goodsList = goodsService.listByTagIdOrderByPriceDesc(tagId, uid, pageIndex, pageSize);
		} else if (sort == 3) {
			goodsList = goodsService.listByTagIdOrderByPriceAsc(tagId, uid, pageIndex, pageSize);
		} else {
			goodsList = goodsService.listByTagId(tagId, uid, pageIndex, pageSize);
		}

		// 装载结果
		List<GoodsVO> result = new ArrayList<>(goodsList.size());
		for (Goods goods : goodsList) {
			GoodsVO vo = VOUtil.from(goods, GoodsVO.class);
			vo.setPrice(goods.getRetailPrice());
			result.add(vo);
		}
		return new RestResult<List<GoodsVO>>(result);
		
	}
		

}
