package com.yogu.core.enums.config;

/**
 * 发现-挑选栏目配置类型定义
 *
 * @date 2016年12月26日 下午4:25:55
 * @author hins
 */
public enum FindPickType {
	
	/** 场景 */
	SCENE((short)1),
	
	/** 时段 */
	TIME_INTERVAL((short)2),
	
	/** 区域 */
	AREA((short)3),
	
	/** 菜系 */
	DISH((short)4);
	
	private short value;
	
	private FindPickType(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return this.value;
	}

}
