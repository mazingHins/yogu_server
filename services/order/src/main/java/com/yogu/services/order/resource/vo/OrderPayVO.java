package com.yogu.services.order.resource.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yogu.services.order.resource.vo.coupon.OrderCouponVO;
import com.yogu.services.order.resource.vo.pay.PayVO;

/**
 * 
 * @Description: 下单结果信息
 * @author Hins
 * @date 2015年8月7日 下午5:54:43
 */
public class OrderPayVO implements Serializable {

	private static final long serialVersionUID = 7479694557687410659L;

	/**
	 * 下单结果：1-下单成功；-1：库存不足；-2：菜品下架
	 */
	private short result;

	/**
	 * pay返回的支付信息数据
	 */
	private PayVO pay;

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
	 * 客户端可用优惠券button标题( 2015-12-26 新增 )
	 */
	private String optionalCouponTitle;
	
	/**
	 * 默认选中的支付方式
	 */
	private short defaultPayMode;
	
	/**
	 * 可用优惠券列表( 2015-12-24 新增 )
	 */
	private List<OrderCouponVO> couponItems;

	public short isResult() {
		return result;
	}

	public void setResult(short result) {
		this.result = result;
	}

	public short getResult() {
		return result;
	}

	public PayVO getPay() {
		return pay;
	}

	public void setPay(PayVO pay) {
		this.pay = pay;
	}

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
	
	public String getOptionalCouponTitle() {
		return optionalCouponTitle;
	}

	public void setOptionalCouponTitle(String optionalCouponTitle) {
		this.optionalCouponTitle = optionalCouponTitle;
	}
	
	public short getDefaultPayMode() {
		return defaultPayMode;
	}

	public void setDefaultPayMode(short defaultPayMode) {
		this.defaultPayMode = defaultPayMode;
	}

	public List<OrderCouponVO> getCouponItems() {
		return couponItems;
	}

	public void setCouponItems(List<OrderCouponVO> couponItems) {
		this.couponItems = couponItems;
	}
	
}
