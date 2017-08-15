package com.yogu.services.store;

import com.yogu.services.store.base.dto.ServiceRangeVO;


/**
 * 服务范围信息 的装载<br>
 * 含：距离目标信息
 *
 * @author JFan 2015年8月25日 下午3:10:20
 */
public interface ServiceRangeLoad {

	/**
	 * 设置距离
	 */
	public void setDistance(int distance);

	/**
	 * 设置距离（含单位）
	 */
	public void setDistanceStr(String distanceStr);

	/**
	 * 设置配送信息对象
	 */
	public void setService(ServiceRangeVO service);

}
