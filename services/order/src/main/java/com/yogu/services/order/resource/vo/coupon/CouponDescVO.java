package com.yogu.services.order.resource.vo.coupon;

import java.util.List;

/**
 * 优惠券说明VO
 * 
 * @author Hins
 * @date 2015年12月23日 下午8:27:25
 */
public class CouponDescVO {

	/**
	 * 优惠券显示字体枚举
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午8:41:17
	 */
	public enum Type {
		/**
		 * 默认字体
		 */
		DEFAULT((short) 1),

		/**
		 * 强调字体
		 */
		EMPHASIZE((short) 2);

		private short value;

		private Type(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
	}

	/**
	 * 优惠券使用说明子项，如满88元以上才可使用
	 */
	private String desc;

	/**
	 * 使用哪种字体展示 1: 默认字体 2: 强调字体
	 */
	private short type;

	/**
	 * 优惠券适用对象列表，可无
	 */
	private List<CouponItemDescVO> items;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public List<CouponItemDescVO> getItems() {
		return items;
	}

	public void setItems(List<CouponItemDescVO> items) {
		this.items = items;
	}

}
