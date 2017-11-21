package com.yogu.services.backend.admin.resources.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.RestResult;
import com.yogu.remote.store.GoodsRemoteService;
import com.yogu.services.store.Goods;



@Controller
@RequestMapping("/admin/goods/")
@Menu(name = "商品列表", parent = "商家管理", sequence = 3100000)
public class GoodsListResource {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsListResource.class);
	
	@Autowired
	private GoodsRemoteService goodsRemoteService;
	
	 /**
     * 门店列表主页，xhtm 仅用于展示页面，ajax 调用 接口获取参数
     * @return
     */
    @RequestMapping("list.xhtm")
    @MenuResource("餐厅列表主页")
    public String index() {
        return ("/store/list_goods");
    }
	
	/**
	 * 查询符合条件的美食列表
	 * 
	 * @param keyword 关键字，可以为null
	 * @param page 第几页，最小是1
	 * @param pageSize 每页大小，最小是1
	 * @return 返回不为空的列表
	 */
	@ResponseBody
	@MenuResource("查询商品列表")
	@RequestMapping(value = "query")
	public RestResult<List<Goods>> query(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize) {

		logger.info("admin#goodsListResource#query | 查询美食列表 |  keyword: {}, page: {}, pageSize: {}", keyword, page, pageSize);

		String query = StringUtils.trimToEmpty(keyword);
		List<Goods> result = goodsRemoteService.query(query, page, pageSize);
		// 剔除null
		List<Goods> list = new ArrayList<Goods>();
		if (CollectionUtils.isNotEmpty(result))
			for (Goods goods : result)
				if (null != goods)
					list.add(goods);
		return new RestResult<>(list);
	}
	
	

	

}
