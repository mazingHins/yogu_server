/**
 * 
 */
package com.mazing.test.runtime.impl;

import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.mazing.test.runtime.CollectData;

/**
 * 解析DishKey
 * 
 * @author jfan 2016年10月13日 下午4:40:25
 */
public class AllNewsBidCollect extends CollectData {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void collect(String data) {
		List<Integer> list = JsonPath.read(data, "$..bid");
		collectNewsBid(list);
	}

}
