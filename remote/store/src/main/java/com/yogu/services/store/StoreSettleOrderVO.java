package com.yogu.services.store;

import java.util.List;

/**
 * 预下单、结算service。所需store域的所有信息实体<br>
 * 包括：餐厅信息，美食信息，库存信息，配送费信息
 *
 * @date 2016年10月1日 上午10:18:23
 * @author hins
 */
public class StoreSettleOrderVO {
	
	/**
	 * 菜品明细信息
	 */
	private List<GoodsOrderVO> list;
	
	/**
	 * 美食总价
	 */
	private long goodsFee;

	public List<GoodsOrderVO> getList() {
		return list;
	}

	public void setList(List<GoodsOrderVO> list) {
		this.list = list;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}
	

	
}
