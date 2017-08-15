package com.yogu.commons.cache.level2.impl;

import com.yogu.commons.cache.level2.ExpLimit;

/**
 * 固定时效策略 <br>
 * <br>
 * 
 * JFan 2014年12月5日 下午5:23:36
 */
public class FixExpLimit implements ExpLimit {

	private int localExp;

	public FixExpLimit(int localExp) {
		this.localExp = localExp;
	}

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public int localExp(int amassExp) {
		return localExp;
	}

}
