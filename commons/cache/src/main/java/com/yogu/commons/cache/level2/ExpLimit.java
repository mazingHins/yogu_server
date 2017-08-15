/**
 * 
 */
package com.yogu.commons.cache.level2;

/**
 * 两层缓存时效转换器 <br>
 * amassExp 2 localExp <br>
 * 
 * @author JFan - 2014年10月30日 下午12:32:19
 */
public interface ExpLimit {

	/**
	 * amass Exp 2 local Exp <br>
	 * 'local' must be less than 'amass'
	 */
	public int localExp(int amassExp);

}
