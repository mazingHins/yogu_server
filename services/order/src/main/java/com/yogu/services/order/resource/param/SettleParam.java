package com.yogu.services.order.resource.param;

import javax.ws.rs.FormParam;

import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.language.OrderMessages;

/**
 * 订单预支付API接收参数
 * 
 * @author Hins
 * @date 2015年8月7日 下午7:59:14
 *
 * @Deprecated by ten 2016/2/23 已经完全废弃不用
 *
 */
@Deprecated
public class SettleParam {

	/**
	 * 寄送地址ID
	 */
	@FormParam("addressId")
	private long addressId;
	
	/**
	 * 寄送时间（毫秒数）
	 */
	@FormParam("deliveryTime")
	private long deliveryTime;
	
	/**
	 * 购买商品id，用英文逗号分隔 
	 */
	@FormParam("dishId") @NotEmpty(message = "订单商品不能为空", mkey = OrderMessages.ORDER_SETTLE_DISHID_EMPTY)
	private String dishId;
	
	/**
	 * 购买商品数量，用英文逗号分隔，跟dishId对应
	 */
	@FormParam("num") @NotEmpty(message = "订单商品数量不能为空", mkey = OrderMessages.ORDER_SETTLE_NUM_EMPTY)
	private String num;
	
	/**
	 * 地址到商家的直线距离(单位米)
	 */
	@FormParam("distance")
	private int distance;

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDishId() {
		return dishId;
	}

	public void setDishId(String dishId) {
		this.dishId = dishId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
}
