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

import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.RestResult;
import com.yogu.services.store.base.dto.GoodsCategory;
import com.yogu.services.store.base.service.GoodsCategoryService;
import com.yogu.services.store.business.dto.Goods;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.resource.vo.GoodsCategoryVO;
import com.yogu.services.store.resource.vo.GoodsVO;
import com.yogu.services.store.resource.vo.IndexAdvertisingVO;
import com.yogu.services.store.resource.vo.IndexRecommendVO;
import com.yogu.services.store.resource.vo.IndexVO;

/**
 * 首页相关api接口
 *
 */
@Named
@Path("p")
@Singleton
@Produces("application/json;charset=UTF-8")
public class IndexResource {
	
	@Inject
	private GoodsCategoryService goodsCategoryService;
	
	@Inject
	private GoodsService goodsService;
	
	private static final int MAX_SIZE = 20, DEFAULT_SIZE = 10, MIN_SIZE = 5;
	
	/**
	 * app首页接口，装载广告、分类、今日推荐内容
	 * 
	 * @author qiujun 
	 * @date 2017年9月13日 下午9:52:42 
	 */
	@GET
	@Path("v1/index")
	public RestResult<IndexVO> details() {
		
		IndexVO result = new IndexVO();
		
		List<GoodsCategory> categoryList = goodsCategoryService.listAll();
		result.setCategoryList(VOUtil.fromList(categoryList, GoodsCategoryVO.class));
		result.setAdList(Collections.<IndexAdvertisingVO> emptyList());
		result.setRecommendList(loadRecomend(1, DEFAULT_SIZE));
		return new RestResult<IndexVO>(result);
	}
	
	/**
	 * 分页装载今日推荐内容
	 * 
	 * @param pageNo - 页码，首页传1
	 * @param pageSize - 每页大小
	 * @author qiujun 
	 * @date 2017年9月13日 下午9:49:34 
	 * @return 进入推荐内容列表，若无，返回empty list
	 */
	private List<IndexRecommendVO> loadRecomend(int pageNo, int pageSize){
		pageSize = PageUtils.limitSize(pageSize, MIN_SIZE, MAX_SIZE);
		List<Goods> goodsList = goodsService.listByPage(1, pageSize);
		List<IndexRecommendVO> recommendList = new ArrayList<>(goodsList.size());
		for (Goods goods : goodsList) {
			IndexRecommendVO vo = VOUtil.from(goods, IndexRecommendVO.class);
			vo.setPrice(goods.getRetailPrice());
			recommendList.add(vo);
		}
		return recommendList;
	}

}
