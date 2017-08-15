package com.yogu.services.store.order;

import java.util.Date;
import java.util.Map;

import com.yogu.services.store.base.dto.Store;
import com.yogu.services.store.base.dto.StoreServiceRange;
import com.yogu.services.store.base.dto.StoreServiceRangeVO;
import com.yogu.services.store.dish.dto.Dish;
import com.yogu.services.store.dish.dto.DishSpecVO;

/**
 * 创建订单流程中，store域相关操作<br>
 * 如验证美食，库存，配送范围，配送费，扣库存
 *
 * @date 2016年10月2日 上午11:22:47
 * @author hins
 */
public class StoreCreateOrderVO {
	
	/**
	 * 购买餐厅
	 */
	private Store store;
	
	/**
	 * 购买信息对应的规格，key：规格key、 value：规格信息
	 */
	private Map<Long, DishSpecVO> specMap;
	
	/**
	 * 购买信息对应的美食，key：美食key、 value：美食信息
	 */
	private Map<Long, Dish> dishMap;
	
	/**
	 * 配送信息
	 */
	private StoreServiceRangeVO range;
	
	/**
	 * 定位的服务日期，格式yyyyMMdd
	 */
	private int serviceDay;
	
	/**
	 * 最新的配送时间
	 */
	private Date deliveryTime;
	
	/**
	 * 扣除库存的结果，1：map.empty = true表示全部操作成功；2：操作失败才记录（key：菜品ID，value：剩余数量）
	 */
	private Map<Long, Integer> surpluss;
	
	/**
	 * 美食总价格
	 */
	private long goodsFee;
	
	/**
	 * 最大需要提前下单时间(分钟)
	 */
	private int advanceMinute;
	
	/**
	 * 美食信息组合。支付sdk需要
	 */
	private String body;
	
	/**
	 * 规格key对应购买数量集合，用于“锁”、“释放”库存
	 * 2016/2/23 by ten
	 */
	private Map<Long, Integer> specNumMap;
	
	/**
	 * 是否含有错误信息，影响最终下单。如美食下架
	 */
	private short hasError;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Map<Long, DishSpecVO> getSpecMap() {
		return specMap;
	}

	public void setSpecMap(Map<Long, DishSpecVO> specMap) {
		this.specMap = specMap;
	}

	public Map<Long, Dish> getDishMap() {
		return dishMap;
	}

	public void setDishMap(Map<Long, Dish> dishMap) {
		this.dishMap = dishMap;
	}

	public StoreServiceRangeVO getRange() {
		return range;
	}

	public void setRange(StoreServiceRangeVO range) {
		this.range = range;
	}

	public int getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(int serviceDay) {
		this.serviceDay = serviceDay;
	}

	public Map<Long, Integer> getSurpluss() {
		return surpluss;
	}

	public void setSurpluss(Map<Long, Integer> surpluss) {
		this.surpluss = surpluss;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public int getAdvanceMinute() {
		return advanceMinute;
	}

	public void setAdvanceMinute(int advanceMinute) {
		this.advanceMinute = advanceMinute;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<Long, Integer> getSpecNumMap() {
		return specNumMap;
	}

	public void setSpecNumMap(Map<Long, Integer> specNumMap) {
		this.specNumMap = specNumMap;
	}

	public short getHasError() {
		return hasError;
	}

	public void setHasError(short hasError) {
		this.hasError = hasError;
	}
	
}
