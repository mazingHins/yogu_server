package com.yogu.services.store.base.dto;

public class StoreServiceRangeVO extends StoreServiceRange {

	private static final long serialVersionUID = -6511648510428607305L;

	/**
	 * 顺丰配送使用运费的规则
	 */
	private SfExpressVO sfExpress;
	
	public SfExpressVO getSfExpress() {
		return sfExpress;
	}

	public void setSfExpress(SfExpressVO sfExpress) {
		this.sfExpress = sfExpress;
	}
	
}
