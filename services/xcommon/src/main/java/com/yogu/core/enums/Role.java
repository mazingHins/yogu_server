package com.yogu.core.enums;

/**
 * 门店工作人员角色定义 (目前只保留店主，店长，接单员，配送员角色 modify by sky 2015/11/04)
 * 
 * @author Felix 2015年9月9日
 */
public enum Role {
	/**
	 * 店主
	 */
	BOSS((short) 1),
	/**
	 * 店长
	 */
	SHOPKEEPER((short) 2),
	/**
	 * 管理员
	 */
	// ADMINISTRATOR((short) 3),
	/**
	 * 客服
	 */
	CUSTOMER_SERVICE((short) 4),
	/**
	 * 接单员
	 */
	ORDER_ACCEPTER((short) 5),
	/**
	 * 配菜员
	 */
	// DISH_PREPARATOR((short) 6),
	/**
	 * 厨房管理员
	 */
	// KITCHEN_ADMIN((short) 7),
	/**
	 * 打包员
	 */
	// PACKER((short) 8),
	/**
	 * 配送员
	 */
	DELIVER((short) 9),
	/**
	 * 厨师
	 */
	// COOK((short) 10),
	/**
	 * 未赋予角色
	 */
	NON_AUTHROIZED((short) 20);

	private short value;

	private Role(short value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public short getValue() {
		return value;
	}

	public static String getRoleName(short value) {
		switch (value) {
		case 1:
			return "店主";
		case 2:
			return "店长";
		case 3:
			return "管理员";
		case 4:
			return "客服";
		case 5:
			return "接单员";
		case 6:
			return "配菜员";
		case 7:
			return "厨房管理员";
		case 8:
			return "打包员";
		case 9:
			return "配送员";
		case 10:
			return "厨师";
		default:
			return "";
		}
	}

	public static Role valueOf(short value) {
		switch (value) {
		case 1:
			return BOSS;
		case 2:
			return SHOPKEEPER;
			// case 3:
			// return ADMINISTRATOR;
		case 4:
			 return CUSTOMER_SERVICE;
		case 5:
			return ORDER_ACCEPTER;
			// case 6:
			// return DISH_PREPARATOR;
			// case 7:
			// return KITCHEN_ADMIN;
			// case 8:
			// return PACKER;
		case 9:
			return DELIVER;
			// case 10:
			// return COOK;
		case 20:
			return NON_AUTHROIZED;
		default:
			return null;
		}
	}
}
