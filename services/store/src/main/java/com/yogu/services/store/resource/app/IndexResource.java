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

import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.services.store.Goods;
import com.yogu.services.store.base.dto.GoodsCategory;
import com.yogu.services.store.base.dto.GoodsRecommend;
import com.yogu.services.store.base.dto.IndexBannerAd;
import com.yogu.services.store.base.service.GoodsCategoryService;
import com.yogu.services.store.base.service.GoodsRecommendService;
import com.yogu.services.store.base.service.IndexBannerAdService;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.resource.vo.GoodsCategoryVO;
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
	
	@Inject
	private IndexBannerAdService indexBannerAdService;
	
	@Inject
	private GoodsRecommendService goodsRecommendService;
	
	private static final int MAX_SIZE = 20, DEFAULT_SIZE = 10, MIN_SIZE = 5;
	
	/**
	 * app首页接口，装载广告、分类、今日推荐内容
	 * 
	 * @author qiujun 
	 * @date 2017年9月13日 下午9:52:42 
	 */
	@GET
	@Path("v1/index")
	public RestResult<IndexVO> index() {
		
		IndexVO result = new IndexVO();
		
		List<GoodsCategory> categoryList = goodsCategoryService.listAll();
		result.setCategoryList(VOUtil.fromList(categoryList, GoodsCategoryVO.class));
		result.setAdList(loadAdvertising());
		result.setRecommendList(loadRecomend(1, DEFAULT_SIZE));
		return new RestResult<IndexVO>(result);
	}
	
	@GET
	@Path("v1/index/recommend")
	public RestResult<List<IndexRecommendVO>> goodsList(@QueryParam("pageSize") int pageSize, @QueryParam("lastTime") long lastTime) {
		return new RestResult<List<IndexRecommendVO>>(loadRecomend(lastTime, pageSize));
	}
	
	
	private List<IndexAdvertisingVO> loadAdvertising(){
		List<IndexBannerAd> list = indexBannerAdService.listEffectivve(5);
		if(list.isEmpty()){
			return Collections.emptyList();
		}
		
		List<IndexAdvertisingVO> result = new ArrayList<IndexAdvertisingVO>(list.size());
		for(IndexBannerAd ad : list){
			IndexAdvertisingVO vo = VOUtil.from(ad, IndexAdvertisingVO.class);
			if(ad.getAdType() == 1){
				IndexAdvertisingVO json = JsonUtils.parseObject(ad.getContent(), IndexAdvertisingVO.class);
				if(json!=null && json.getGoodsKey()>0){
					vo.setGoodsKey(json.getGoodsKey());
					result.add(vo);
				}
			}else if(ad.getAdType() == 2){
				IndexAdvertisingVO json = JsonUtils.parseObject(ad.getContent(), IndexAdvertisingVO.class);
				if(json!=null && StringUtils.isNoneBlank(json.getLinkUrl())){
					vo.setLinkUrl(json.getLinkUrl());
					result.add(vo);
				}
			}
		}
		
		return result;
		
		
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
	private List<IndexRecommendVO> loadRecomend(long lastTime, int pageSize){
		Long uid = SecurityContext.getUserId();
		pageSize = PageUtils.limitSize(pageSize, MIN_SIZE, MAX_SIZE);
		
		// 先获取推送列表
		List<GoodsRecommend> list = goodsRecommendService.listEffectivve(lastTime, pageSize);
		List<Long> goodsKeys  = new ArrayList<Long>(list.size());
		for(GoodsRecommend rm : list){
			goodsKeys.add(rm.getGoodsKey());
		}
		
		// 通过推荐列表批量查询商品信息
		List<Goods> goodsList = goodsService.listBykeys(uid, goodsKeys);
		List<IndexRecommendVO> recommendList = new ArrayList<>(goodsList.size());
		for (Goods goods : goodsList) {
			IndexRecommendVO vo = VOUtil.from(goods, IndexRecommendVO.class);
			vo.setPrice(goods.getRetailPrice());
			recommendList.add(vo);
		}
		return recommendList;
	}

}
