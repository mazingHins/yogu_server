package com.yogu.services.order.base.dto.detail;

import com.yogu.services.order.base.dto.OrderDetail;
import com.yogu.services.order.base.dto.vo.OrderErrorVO;
import com.yogu.services.store.base.dto.Store;

import java.util.List;
import java.util.Map;

/**
 * 创建订单时的菜品信息
 * 
 * @author Hins
 * @date 2015年8月16日 下午11:10:13
 */
public class CreateOrderDetail {

	/** 订单明细列表 */
	private List<OrderDetail> detailList;

//	/** 菜品对应购买数量集合，用于锁库存 */
//	private Map<Long, Integer> dishNumMap;

	/**
	 * 规格key对应购买数量集合，用于所库存
	 * 2016/2/23 by ten
	 */
	private Map<Long, Integer> specNumMap;

	/** 错误信息列表(菜品下架列表) */
	private List<OrderErrorVO> errorList;

	/**
	 * 最大需要提前下单时间(分钟)
	 */
	private int advanceMinute;

	/** 菜品总价 */
	private long goodsFee;

	/** 菜品所属门店 */
	private Store store;

	/** 菜品名称组合，用逗号分隔 */
	private String body;

	public List<OrderDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetail> detailList) {
		this.detailList = detailList;
	}

//	public Map<Long, Integer> getDishNumMap() {
//		return dishNumMap;
//	}

//	public void setDishNumMap(Map<Long, Integer> dishNumMap) {
//		this.dishNumMap = dishNumMap;
//	}

	public Map<Long, Integer> getSpecNumMap() {
		return specNumMap;
	}

	public void setSpecNumMap(Map<Long, Integer> specNumMap) {
		this.specNumMap = specNumMap;
	}

	public List<OrderErrorVO> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<OrderErrorVO> errorList) {
		this.errorList = errorList;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getAdvanceMinute() {
		return advanceMinute;
	}

	public void setAdvanceMinute(int advanceMinute) {
		this.advanceMinute = advanceMinute;
	}

}
