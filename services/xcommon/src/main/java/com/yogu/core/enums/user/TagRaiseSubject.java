package com.yogu.core.enums.user;

/**
 * 标签数据孕育日志科目
 *
 * @date 2016年12月27日 上午10:52:41
 * @author hins
 */
public enum TagRaiseSubject {

	/**
	 * 餐厅加入用户的随变列表
	 */
	SUIBIAN(10),
	
	/**
	 * cblog被查看
	 */
	VIEW_CBLOG(20),
	
	/**
	 * 餐厅被收藏
	 */
	FAV_STORE(30),
	
	/**
	 * 餐厅被取消收藏
	 */
	CANCEL_FAV_STORE(40),
	
	/**
	 * 餐厅被线上订餐
	 */
	ONLINE_ORDER(50),
	
	/**
	 * 餐厅被米星付
	 */
	MAZING_PAY_ORDER(60),
	
	/**
	 * 没有操作，被扣分
	 */
	NO_OPREATION(70);
	
	public static TagRaiseSubject valueOf(int value) {
		switch (value) {
		case 10:
			return SUIBIAN;
		case 20:
			return VIEW_CBLOG;
		case 30:
			return FAV_STORE;
		case 40:
			return CANCEL_FAV_STORE;
		case 50:
			return ONLINE_ORDER;
		case 60:
			return MAZING_PAY_ORDER;
		case 70:
			return NO_OPREATION;
		default:
			return null;
		}
	}
	
	/**
	 * 用户主动操作
	 * @param value
	 * @author hins
	 * @date 2016年12月27日 下午4:08:10
	 * @return TagRaiseSubject
	 */
	public static TagRaiseSubject active(int value){
		switch (value) {
		case 20:
			return VIEW_CBLOG;
		case 30:
			return FAV_STORE;
		case 40:
			return CANCEL_FAV_STORE;
		case 50:
			return ONLINE_ORDER;
		case 60:
			return MAZING_PAY_ORDER;
		default:
			return null;
		}
	}
	
	private int value;
	
	private TagRaiseSubject(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}
