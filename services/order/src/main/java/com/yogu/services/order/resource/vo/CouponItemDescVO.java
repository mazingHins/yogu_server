package com.yogu.services.order.resource.vo;
/**
 * 优惠券适用对象VO
 * 
 * @author Hins
 * @date 2015年12月23日 下午8:24:43
 */
public class CouponItemDescVO {

	/**
	 * 适用对象类型枚举
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午9:54:24
	 */
	public enum Type {
		/**
		 * 餐厅
		 */
		STORE((short) 1),

		/**
		 * 美食
		 */
		DISH((short) 2),
		
		/**
		 * h5链接
		 */
		LINK_URL((short) 3);

		private short value;

		private Type(short value) {
			this.value = value;
		}

		public short getValue() {
			return value;
		}
	}

	/**
	 * 内容说明，如餐厅名字
	 */
	private String desc;

	/**
	 * 类型：1- 餐厅ID；2-美食ID
	 */
	private short type;

	/**
	 * 餐厅的ID，如果没有，值=0
	 */
	private long storeId;
	
	/**
	 * 美食id，如果type=2，才有值
	 * 2016-11-23 add by hins
	 */
	private long dishKey;
	
	/**
	 * 网页链接，如果type=3，才有值
	 * 2016-11-24 add by hins
	 */
	private String linkUrl;

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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getDishKey() {
		return dishKey;
	}

	public void setDishKey(long dishKey) {
		this.dishKey = dishKey;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
}
