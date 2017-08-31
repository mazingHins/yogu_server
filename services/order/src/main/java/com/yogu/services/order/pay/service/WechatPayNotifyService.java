package com.yogu.services.order.pay.service;

import java.util.List;

import com.yogu.services.order.pay.dto.WechatPayNotify;

/**
 * 微信支付回调通知记录表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-08-06
 */
public interface WechatPayNotifyService {

	/**
	 * 保存数据
	 * 
	 * @param dto 对象
	 */
	public void save(WechatPayNotify dto);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param dto - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(WechatPayNotify dto);

	/**
	 * 根据主键读取记录
	 */
	public WechatPayNotify getById(long notifyId);
	
	/**
	 * 创建微信支付回调记录，返回notifyId<br>
	 * 该方法只保存微信回调记录，如果参数dto.payId已存在对应的记录，会抛出异常！因为微信支付回调可能会多次回调<br>
	 * 
	 * 但此方法不兼容微信多次回调（无论第一次是失败，第二次成功。或者2次都成功）<br>
	 * 
	 * 方法不会校验支付记录等数据是否合法/存在，所以此方法是提供给处理微信支付回调的service方法使用
	 * 
	 * @author Hins
	 * @date 2016年2月2日 下午3:16:57
	 * 
	 * @param dto
	 * @return 回调通知id主键
	 */
	@Deprecated
	public long createNotify(WechatPayNotify dto);
	
	/**
	 * 根据支付记录id，获取微信支付通知列表<br>
	 * 该方法支持批量查询，不验证支付记录是否合法并存在
	 * 
	 * @author Hins
	 * @date 2016年2月4日 下午6:15:20
	 * 
	 * @param payIds - 支付记录idList
	 * @return 返回支付通知记录数据，如果没有数据，返回emtpy list
	 */
	public List<WechatPayNotify> queryByPayIds(List<Long> payIds);
	
	/**
	 * 通过支付记录编号获取微信支付回调记录<br>
	 * 如果支付记录不合法/该支付是支付宝，返回null
	 * 
	 * @param payNo - 支付编号
	 * @author hins
	 * @date 2017年1月9日 上午10:28:42
	 * @return 微信支付回调记录，如果不存在，返回null
	 */
	public WechatPayNotify getByPayNo(long payNo);

}
