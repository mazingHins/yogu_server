package com.yogu.services.order.base.service.param;

import java.io.Serializable;

/**
 * @Description: 购买信息（美食ID,购买数量）
 * @author Hins
 * @date 2015年8月20日 上午10:56:47
 */
public class BuyParam implements Serializable {

	private static final long serialVersionUID = 6798893554202983509L;

	/**
	 * 菜品ID
	 */
	private long dishId;
	
	/**
	 * 购买数量
	 */
	private int num;

	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
