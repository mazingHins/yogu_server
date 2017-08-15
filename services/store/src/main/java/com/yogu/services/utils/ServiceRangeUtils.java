package com.yogu.services.utils;

import java.util.List;

import com.yogu.core.enums.merchant.RangeMoneyType;
import com.yogu.services.store.base.dto.StoreServiceRange;

public class ServiceRangeUtils {
	
	/**
	 * 的士费*1的money值
	 */
	private static final int TAXI_MONEY = -1;

	/**
	 * 的士费*2的money值
	 */
	private static final int TAXI_DOUBLE_MONEY = -2;
	
	/**
	 * 解析配送费。 ---hin 2015/10/12 修改---<br>
	 * 配送费money的值=-1表示配送费类型为的士费*1<br>
	 * 配送费money的值=-2配送费类型为的士费*2<br>
	 * ---hin 2015/10/12 修改 end---
	 * 
	 * @author Hins
	 * @date 2015年10月29日 下午5:48:38
	 * 
	 * @param list
	 */
	public static void rangeToList(List<StoreServiceRange> list) {
		// hins 2015/10/12 修改了money的返回值
		if (!list.isEmpty()) {
			for (StoreServiceRange po : list) {
				// 配送费类型为的士费*1，则修改配送费money的值=-1
				// 配送费类型为的士费*2，则修改配送费money的值=-2
				if (po.getMoneyType() == RangeMoneyType.TAXI_FEE.getValue()) {
					po.setMoney(TAXI_MONEY);
				} else if (po.getMoneyType() == RangeMoneyType.TAXI_FEE_DOUBLE.getValue()) {
					po.setMoney(TAXI_DOUBLE_MONEY);
				}
			}
		}
		// hins end update
	}

}
