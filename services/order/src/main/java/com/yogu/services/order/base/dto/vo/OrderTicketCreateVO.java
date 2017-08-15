package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description: 下单结果信息
 * @author Hins
 * @date 2015年8月7日 下午5:54:43
 */
public class OrderTicketCreateVO implements Serializable {

	private static final long serialVersionUID = 7479694557687410659L;

	/**
	 * 订单金额
	 */
	private long totalFee;

	/**
	 * 订单编号
	 */
	private long orderNo;

	/**
	 * 下单时间
	 */
	private Date createTime;

	/**
	 * 可选支付方式
	 */
	private String optionalPayMode;

	/**
	 * 默认选中的支付方式
	 */
	private short defaultPayMode;

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOptionalPayMode() {
		return optionalPayMode;
	}

	public void setOptionalPayMode(String optionalPayMode) {
		this.optionalPayMode = optionalPayMode;
	}

	public short getDefaultPayMode() {
		return defaultPayMode;
	}

	public void setDefaultPayMode(short defaultPayMode) {
		this.defaultPayMode = defaultPayMode;
	}

}
