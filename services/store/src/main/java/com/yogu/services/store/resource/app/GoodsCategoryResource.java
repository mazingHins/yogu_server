package com.yogu.services.store.resource.app;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.RestResult;
import com.yogu.services.store.base.dto.GoodsCategory;
import com.yogu.services.store.base.dto.GoodsTag;
import com.yogu.services.store.base.service.GoodsCategoryService;
import com.yogu.services.store.base.service.GoodsTagService;
import com.yogu.services.store.resource.vo.GoodsCategoryVO;
import com.yogu.services.store.resource.vo.GoodsTagVO;

/**
 * 商品分类相关api接口
 *
 */
@Named
@Path("p")
@Singleton
@Produces("application/json;charset=UTF-8")
public class GoodsCategoryResource {
	
	@Inject
	private GoodsCategoryService goodsCategoryService;
	
	@Inject
	private GoodsTagService goodsTagService;
	
	@GET
	@Path("v1/store/category/list")
	public RestResult<List<GoodsCategoryVO>> listAll() {

		// 1. 获取所有的分类
		List<GoodsCategory> categoryList = goodsCategoryService.listAll();

		// 2. 遍历获取分类标签
		List<GoodsCategoryVO> result = VOUtil.fromList(categoryList, GoodsCategoryVO.class);
		for (GoodsCategoryVO category : result) {
			List<GoodsTag> tagList = goodsTagService.listByCategoryId(category.getCategoryId());
			category.setTagList(VOUtil.fromList(tagList, GoodsTagVO.class));
		}
		return new RestResult<List<GoodsCategoryVO>>(result);

	}
		

}
