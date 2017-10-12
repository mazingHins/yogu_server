package com.yogu.services.store;

import java.util.Map;

/**
 * 创建订单流程中，store域相关操作<br>
 * 如验证美食，库存，配送范围，配送费，扣库存
 *
 * @date 2016年10月2日 上午11:22:47
 * @author hins
 */
public class StoreCreateOrderVO {
	
	
	/**
	 * 购买信息对应的美食，key：美食key、 value：美食信息
	 */
	private Map<Long, GoodsOrderVO> goodsMap;
	
	/**
	 * 美食总价格
	 */
	private long goodsFee;
	
	/**
	 * 美食信息组合。支付sdk需要
	 */
	private String body;
	
	/**
	 * 是否含有错误信息，影响最终下单。如美食下架
	 */
	private short hasError;

	public Map<Long, GoodsOrderVO> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(Map<Long, GoodsOrderVO> goodsMap) {
		this.goodsMap = goodsMap;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public short getHasError() {
		return hasError;
	}

	public void setHasError(short hasError) {
		this.hasError = hasError;
	}
	
}
