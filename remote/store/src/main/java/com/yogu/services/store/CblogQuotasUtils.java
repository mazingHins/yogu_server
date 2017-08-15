package com.yogu.services.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.yogu.commons.utils.ArrayUtils;
import com.yogu.services.store.news.dto.NewsStoreinfo;

public class CblogQuotasUtils {
	/**
	 * 1=佳肴体验，2=服务品质，3=装饰布局，4=环境氛围，5=价值感受，从中获取分数最高的3个(分数最高的显示3个), 并返回icon的序号
	 * 例如: 1=7, 2=3,3=5,4=6,5=7, 最终返回1,1,1,5,4
	 * @param newsStoreinfo
	 * @return    
	 * @author east
	 * @date 2016年12月27日 下午6:56:57
	 */
	public static Integer[] sortQuotas(NewsStoreinfo newsStoreinfo){
		if(newsStoreinfo == null)
			return ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
		
		Map<Integer, Integer> iconMap = new HashMap<Integer, Integer>();
		iconMap.put(1, newsStoreinfo.getDelicious());
		iconMap.put(2, newsStoreinfo.getDecoration());
		iconMap.put(3, newsStoreinfo.getFeeling());
		iconMap.put(4, newsStoreinfo.getQuality());
		iconMap.put(5, newsStoreinfo.getSurround());
		
		//根据分数排序
		Integer[] quotas = new Integer[]{newsStoreinfo.getDelicious(), newsStoreinfo.getQuality(), newsStoreinfo.getDecoration(), newsStoreinfo.getSurround(), newsStoreinfo.getFeeling()};
		Arrays.sort(quotas, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 <= o2)  
					return 1;  
				return -1;  
			}
		});
		List<Integer> tmp = new ArrayList<Integer>();
		//只获取前3个icon
		for(int i = 0; i < 3; i++){
			int value = quotas[i];
			int key = getKey(iconMap, value);
			//第一个图标多插入2次
			if(i == 0){
				tmp.add(key);
				tmp.add(key);
			}
			tmp.add(key);
		}
		return tmp.toArray(new Integer[5]);
	}
	
	/**
	 * 根据value获取key
	 * @param map
	 * @param value
	 * @return    
	 * @author east
	 * @date 2017年1月10日 下午4:55:46
	 */
	private static int getKey(Map<Integer, Integer> map, int value){
		Set<Entry<Integer, Integer>> entrySet = map.entrySet();
		for(Entry<Integer, Integer> entry : entrySet){
			if(entry.getValue().intValue() == value ){
				map.remove(entry.getKey());
				return entry.getKey().intValue();
			}
		}
		return 0;
	}
}
