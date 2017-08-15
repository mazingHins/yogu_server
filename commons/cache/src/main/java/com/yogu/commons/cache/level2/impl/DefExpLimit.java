package com.yogu.commons.cache.level2.impl;

import com.yogu.commons.cache.level2.ExpLimit;

/**
 * 默认时效策略 <br>
 * <br>
 * amassExp大于1分钟时（>60s），localExp为1分钟 <br>
 * amassExp小于等于1分钟时，localExp为amassExp的一半（/） <br>
 * amassExp小于等于10秒，则localExp==amassExp
 * 
 * @author JFan - 2014年10月30日 下午1:47:32
 */
public class DefExpLimit implements ExpLimit {

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public int localExp(int amassExp) {
		if (10 >= amassExp)
			return amassExp;
		if (60 >= amassExp)
			return amassExp / 2;
		return 60;
	}

}
