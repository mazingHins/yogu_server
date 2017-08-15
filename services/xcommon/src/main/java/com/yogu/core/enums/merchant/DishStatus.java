/**
 * 
 */
package com.yogu.core.enums.merchant;

/**
 * 美食状态枚举 <br>
 * 从Dish内部类Status移动到此位置，创建人hins
 *
 * @author JFan 2015年12月16日 下午3:27:25
 */
public enum DishStatus {

	/**
	 * 上架
	 */
	ON_SHELF((short) 1),

	/**
	 * 下架
	 */
	OFF_SHELF((short) 2);

	private short value;

	private DishStatus(short value) {
		this.value = value;
	}

	/**
	 * 是否可以被搜索
	 */
	public static boolean canSearch(short status) {
		return (ON_SHELF.getValue() == status);
	}

	/**
	 * 是否可以被搜索
	 */
	public static boolean canSearch(short status, short storeStatus) {
		return (canSearch(status) && StoreStatus.canSearch(storeStatus));
	}

	public short getValue() {
		return value;
	}

}
