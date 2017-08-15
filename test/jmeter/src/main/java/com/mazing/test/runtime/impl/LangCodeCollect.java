/**
 * 
 */
package com.mazing.test.runtime.impl;

import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.mazing.test.runtime.CollectData;

/**
 * 解析LangCode
 * 
 * @author jfan 2016年10月13日 下午4:37:39
 */
public class LangCodeCollect extends CollectData {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void collect(String data) {
		List<String> list = JsonPath.read(data, "$.object.languages");
		collectLangCode(list);
	}

}
