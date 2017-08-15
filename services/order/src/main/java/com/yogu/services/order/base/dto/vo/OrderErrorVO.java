package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;

/**
 * 下单-错误信息VO
 * 
 * @author Hins
 * @date 2015年7月28日 上午10:16:45
 */
public class OrderErrorVO implements Serializable {

	private static final long serialVersionUID = 7266002710385673713L;

	/**
	 * 实际是存储 dishKey
	 * 2016/2/25 by ten
	 */
	private long dishId;
	
	private String message;

	/**
	 * 规格key
	 * 2016/2/25 by ten，在库存不足的时候，返回给客户端
	 */
	private long specKey;

	public long getDishId() {
		return dishId;
	}

	public void setDishId(long dishId) {
		this.dishId = dishId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getSpecKey() {
		return specKey;
	}

	public void setSpecKey(long specKey) {
		this.specKey = specKey;
	}
}
