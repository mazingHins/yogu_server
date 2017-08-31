package com.yogu.services.order.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.pay.dto.WechatPayRecord;
import com.yogu.services.order.pay.entry.WechatPayRecordPO;

/**
 * yg_wechat_pay_record 表的操作接口
 * 
 * @author Mazing 2016-01-30
 */
@TheDataDao
public interface WechatPayRecordDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(WechatPayRecordPO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("payId") long payId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(WechatPayRecordPO po);

	/**
	 * 根据主键读取记录
	 */
	public WechatPayRecordPO getById(@Param("payId") long payId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<WechatPayRecord> listAll();

}
