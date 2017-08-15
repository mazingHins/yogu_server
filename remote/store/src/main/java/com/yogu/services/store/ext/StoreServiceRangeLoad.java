/**
 * 
 */
package com.yogu.services.store.ext;

import com.yogu.services.store.ServiceRangeLoad;

/**
 * 门店服务信息的装载 <br>
 * 比ServiceRangeLoad多了门店ID、门店坐标
 *
 * @author JFan 2015年9月8日 上午9:59:31
 */
public interface StoreServiceRangeLoad extends ServiceRangeLoad {

	/**
	 * 返回门店ID
	 */
	public long getStoreId();

	/**
	 * 返回门店经度
	 */
	public double getLng();

	/**
	 * 返回门店纬度
	 */
	public double getLat();

}
