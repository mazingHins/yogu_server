package com.yogu.services.order.pay.service;

import com.yogu.services.order.pay.dto.PayRecord;

/**
 * 支付记录表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-17
 */
public interface PayRecordService {

	/**
	 * 保存数据
	 * 
	 * @param dto对象
	 */
	public void save(PayRecord dto);

}
