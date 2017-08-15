/**
 * 
 */
package com.mazing.test.runtime.impl;

import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.mazing.test.runtime.CollectData;

/**
 * 解析CityCode
 * 
 * @author jfan 2016年10月13日 下午3:43:54
 */
public class CityCodeCollect extends CollectData {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void collect(String data) {
		List<String> list = JsonPath.read(data, "$.object.cityList[*].code");
		collectCityCode(list);
	}

}
