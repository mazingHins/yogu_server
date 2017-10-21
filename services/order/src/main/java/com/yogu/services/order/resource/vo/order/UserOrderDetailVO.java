package com.yogu.services.order.resource.vo.order;

import java.io.Serializable;
import java.util.List;

import com.yogu.services.store.GoodsOrderVO;

/**
 * @Description: 用户端订单详情接口VO
 * @author Hins
 * @date 2015年8月19日 上午10:13:13
 */
public class UserOrderDetailVO extends UserOrderVO implements Serializable {

	private static final long serialVersionUID = -9055355293991770682L;


	/** 商品明细 */
	private List<GoodsOrderVO> goodsList;


	public List<GoodsOrderVO> getGoodsList() {
		return goodsList;
	}


	public void setGoodsList(List<GoodsOrderVO> goodsList) {
		this.goodsList = goodsList;
	}
	
}
