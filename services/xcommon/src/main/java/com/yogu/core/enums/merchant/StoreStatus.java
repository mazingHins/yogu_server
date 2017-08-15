package com.yogu.core.enums.merchant;

import com.yogu.language.StoreMessages;

/**
 * 门店营业状态
 * 
 * @author sky
 * @date 2015-09-21 20:51:52
 */
public enum StoreStatus {

	// 1：正常、2：休业（例如外出旅游等）、3：结业（不展示、不接单）、 4: 审核中(新创建的门店)
	// 20：冻结（不展示、无法下单等）可结算、21：冻结（不展示、无法下单等）不可结算；；
	// ；20以内的状态（不含）门店自行维护，其他状态由平台管理。

	/**
	 * 正常营业
	 */
	IN_BUSSINESS((short) 1),
	/**
	 * 休业
	 */
	IN_REST((short) 2),
	/**
	 * 结业
	 */
	CLOSED((short) 3),

	/**
	 * 审核中
	 */
	CHECKING((short) 4), // add by sky 2015-10-08 20:00:00

	/**
	 * 即将开业
	 */
	COMING_SOON((short) 5),

	/**
	 * 冻结
	 */
	FROST((short) 20);

	private short value;

	private StoreStatus(short value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public short getValue() {
		return value;
	}

	public static StoreStatus from(short status) {
		for (StoreStatus ss : StoreStatus.values()) {
			if (ss.getValue() == status) {
				return ss;
			}
		}
		throw new IllegalArgumentException("Cannot find status: " + status);
	}

	/**
	 * 是否可以在首页中展示
	 */
	public static boolean canNewIndexShow(short status) {
		return (IN_BUSSINESS.getValue() == status);
	}

	/**
	 * 是否可被下单
	 * 
	 * @param status 门店状态
	 * @return
	 * @author sky 2016-02-24
	 */
	public static boolean canBeOrdered(short status) {
		return (IN_BUSSINESS.getValue() == status);
	}

	/**
	 * 是否可以在瀑布流中展示
	 */
	public static boolean canIndexShow(short status) {
		return (IN_BUSSINESS.getValue() == status || COMING_SOON.getValue() == status);
	}

	/**
	 * 是否可以被搜索
	 */
	public static boolean canSearch(short status) {
		return (IN_BUSSINESS.getValue() == status || COMING_SOON.getValue() == status || IN_REST.getValue() == status);
	}

	public static String switchValue(StoreStatus status) {
		switch (status) {
		case IN_BUSSINESS:
			return "正常营业";
		case IN_REST:
			return "休业";
		case CLOSED:
			return "结业";
		case CHECKING:
			return "审核中";
		case COMING_SOON:
			return "即将开业";
		case FROST:
			return "冻结";
		default:
			break;
		}

		return "";
	}
	
	/**
	 * @Description:根据lang值，美食列表/详情页面‘+’代替为以下文字
	 * @param status
	 * @return     
	 * @author east
	 * @date 2016年11月2日
	 */
	public static String getStatusDesc(short status) {
		StoreStatus storeStatus = from(status);
		switch (storeStatus) {
			case COMING_SOON:
				return StoreMessages.STORE_STATUS_COMING_SOON();
			case IN_REST:
				return StoreMessages.STORE_STATUS_CLOSED_NOW();
			case CLOSED:
				return StoreMessages.STORE_STATUS_CLOSED();
			case FROST:
				return StoreMessages.STORE_STATUS_CLOSED();
			default:
				break;
		}
		return "";
	}
}
