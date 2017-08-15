package com.yogu.core.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yogu.commons.utils.StringUtils;

/**
 * 标签工具类
 * @author east
 * @date 2017年1月16日 下午5:19:49
 */
public class TagUtil {
	
	/** tag每个分组的大小 */
    public static final int GROUP_LENGTH = 10000;
    
	/**
	 * 根据传入的标签进行分组
	 * @param str
	 * @return    
	 * @author east
	 * @date 2017年1月3日 上午11:16:31
	 */
	public static Map<Integer, List<Integer>> getTagMaps(String str){
		// 根据传入的标签进行分组
		Map<Integer, List<Integer>> tagsMap = new HashMap<Integer, List<Integer>>();
		if (StringUtils.isNotBlank(str)) {
			for (int tagId : ParameterUtil.str2ints(str)) {
				int index = tagId / GROUP_LENGTH;
				if (tagsMap.containsKey(index)) {
					tagsMap.get(index).add(tagId);
				} else {
					List<Integer> list = new ArrayList<Integer>();
					list.add(tagId);
					tagsMap.put(index, list);
				}
			}
		}
		return tagsMap;
	}
}
