package com.yogu.services.backend.admin.resources.store;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.RestResult;
import com.yogu.remote.store.GoodsRemoteService;
import com.yogu.services.backend.admin.resources.store.vo.GoodsVO;
import com.yogu.services.store.Goods;



@Controller
@RequestMapping("/admin/goods/")
@Menu(name = "商品详情", parent = "商家管理", sequence = 3100000)
public class GoodsDetailResource {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsDetailResource.class);
	
	@Autowired
	private GoodsRemoteService goodsRemoteService;
	
	 /**
     * 门店列表主页，xhtm 仅用于展示页面，ajax 调用 接口获取参数
     * @return
     */
    @RequestMapping("detail.xhtm")
    @MenuResource("商品主页")
    public String index() {
        return ("/store/goods_detail");
    }
	
	@ResponseBody
	@MenuResource("查看商品详情")
	@RequestMapping(value = "detail")
	public RestResult<GoodsVO> detail(@RequestParam(value = "goodsKey") long goodsKey) {

		logger.info("admin#goodsListResource#query | 查看商品详情 |  goodsKey: {}", goodsKey);

		Goods goods = goodsRemoteService.getGoodsByKey(goodsKey);
		GoodsVO result = VOUtil.from(goods, GoodsVO.class);
		String[] imgArray = goods.getContent().split(",");
		List<String> imgs = new ArrayList<>();
		for(String img : imgArray){
			imgs.add(img);
		}
		result.setContentImgs(imgs);
		return new RestResult<>(result);
	}

	

}
