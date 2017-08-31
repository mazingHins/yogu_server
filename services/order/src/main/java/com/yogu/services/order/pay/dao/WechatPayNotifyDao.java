package com.yogu.services.order.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.pay.entry.WechatPayNotifyPO;

/**
 * yg_wechat_pay_notify 表的操作接口
 * 
 * @author Mazing 2016-01-30
 */
@TheDataDao
public interface WechatPayNotifyDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(WechatPayNotifyPO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("notifyId") long notifyId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(WechatPayNotifyPO po);

	/**
	 * 根据主键读取记录
	 */
	public WechatPayNotifyPO getById(@Param("notifyId") long notifyId);

	/**
	 * 根据支付记录ID，批量获取通知结果，该方法不排序
	 * @author Hins
	 * @date 2016年02月04日 下午18:20:21
	 * 
	 * @param payIds - 支付记录ID集
	 * @return 返回符合的数据，如果没有数据，返回emtpy list
	 */
	public List<WechatPayNotifyPO> listByPayIds(@Param("list") List<Long> payIds);

}
