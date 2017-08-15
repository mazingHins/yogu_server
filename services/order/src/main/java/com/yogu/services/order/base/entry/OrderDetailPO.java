package com.yogu.services.order.base.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单明细表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_order_detail, 日期: 2015-07-15
 *     order_id <PK>        bigint(20)
 *     object_id <PK>       bigint(20)
 *     number         tinyint(4)
 *     unit_fee       int(11)
 *     total_fee      int(11)
 *     create_time    datetime(19)
 * </pre>
 */
public class OrderDetailPO implements Serializable {

	private static final long serialVersionUID = -3074457345147860859L;

	/** 订单id */
	private long orderId;

	/** 菜品id */
	private long objectId;

	/** 商品数量 */
	private short number;

	/** 商品单价（精确到分） */
	private long unitFee;

	/** 总价 */
	private long totalFee;

	/** 创建时间 */
	private Date createTime;
	
	/** 规格id */
	private long specId;
	
	/** 规格名称 */
	private String specName;
	
	/** 规格备注, 多个用英文逗号分隔 */
	private String supplements;

	/**
	 * 美食key 2016/2/24 by ten
	 */
	private long dishKey;

	public long getSpecId() {
		return specId;
	}

	public void setSpecId(long specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSupplements() {
		return supplements;
	}

	public void setSupplements(String supplements) {
		this.supplements = supplements;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setNumber(short number) {
		this.number = number;
	}

	public short getNumber() {
		return number;
	}

	public void setUnitFee(long unitFee) {
		this.unitFee = unitFee;
	}

	public long getUnitFee() {
		return unitFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public long getDishKey() {
		return dishKey;
	}

	public void setDishKey(long dishKey) {
		this.dishKey = dishKey;
	}
}
