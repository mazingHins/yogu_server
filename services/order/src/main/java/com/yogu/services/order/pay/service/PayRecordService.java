package com.yogu.services.order.pay.service;

import com.yogu.services.order.pay.dto.PayRecord;
import com.yogu.services.order.pay.service.params.UpdataPayRecordMode;

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
	
	/**
	 * 根据内部平台订单号读取记录<br>
	 * 如果有多条记录，抛出异常！！！
	 * 
	 * @author Hins
	 * @date 2015年10月6日 下午5:01:05
	 * 
	 * @param insideTradeNo - 内部平台订单号
	 * @return 支付记录，若无，返回null
	 */
	public PayRecord getByOrderNo(long orderNo);
	
	/**
	 * 根据支付流水号读取记录
	 * 
	 * @param payNo - 支付流水号
	 * @return 支付记录，若无，返回null
	 */
	public PayRecord getByPayNo(long payNo);
	
	/**
	 * 更换支付方式,是否使用优惠券，实际支付金额，用户支付第三方配送费<br>
	 * 如果实付金额=0，修改支付记录支付状态=成功，否则修改支付记录状态=未支付<br>
	 * 该方法使用条件：只能应用于没有支付成功的记录，如果记录成功，则会抛异常<br>
	 * 此方法只提供给生成支付信息service使用
	 * 
	 * @author Hins
	 * @date 2016年2月19日 下午6:32:31
	 * 
	 * @param payNo - 支付编号
	 * @param payMode - 新的支付方式
	 * @param totalFee - 新的实际支付金额
	 * @param hasDiscount - 新的是否使用优惠券
	 * @param deliveryFee - 用户支付第三方配送费
	 */
	@Deprecated
	public void changePayMode(long payNo, UpdataPayRecordMode params);
	
	/**
	 * 支付平台异步回调成功时，调动该方法，将pay域的支付记录修改成支付成功，并记录对应的回调记录id
	 * 
	 * @param payNo - 支付编号
	 * @param notifyId - 回调记录ID
	 */
	public void paySuccess(long payNo, long notifyId);
	
	/**
	 * 支付平台异步回调成功时，调动该方法，将pay域的支付记录修改成支付失败，并记录对应的回调记录id<br>
	 * 不保存失败原因，失败原因由回调通知记录维护
	 * 
	 * @author Hins
	 * @date 2016年2月2日 下午3:41:37
	 * 
	 * @param payNo
	 * @param notifyId
	 */
	public void payFail(long payNo, long notifyId);
	
	

}
