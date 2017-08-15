package com.yogu.core.enums.merchant;

/**
 * 商家操作日志的操作模块
 * @author felix
 * @date 2015-11-06
 */
public enum MerchantLogModule {
	
	/** 美食模块 */
	DEFAULT((short)0),
	
	/** 餐厅模块 */
	STORE((short)1),
	
	/** 美食模块 */
	DISH((short)2),
	
	/** 订单模块 */
	ORDER((short)3),
	
	/** 余额模块 */
	MONEY((short)4);
	
	private short value;
	
	private MerchantLogModule(short value){
		this.value = value;
	}
	
	public short getValue(){
		return this.value;
	}
}
