package com.yogu.services.order.pay.service;

import java.util.List;

import com.yogu.services.order.pay.dto.WechatPayRecord;

/**
 * 微信支付请求记录表 <br>
 * 的操作接口
 * 
 * @author Mazing 2016-01-30
 */
public interface WechatPayRecordService {

	/**
	 * 保存数据
	 * 
	 * @param dto 对象
	 */
	public void save(WechatPayRecord dto);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param dto - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(WechatPayRecord dto);

	/**
	 * 根据主键读取记录
	 */
	public WechatPayRecord getById(long payId);

}
