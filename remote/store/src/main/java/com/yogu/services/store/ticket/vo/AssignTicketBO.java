package com.yogu.services.store.ticket.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 预下单扣库存时，通知分配ticket的 信息载体
 * 
 * @author sky
 *
 */
public class AssignTicketBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4224529149532748269L;
	private long uid;// 购买者用户uid
	private long storeId;// 餐厅id
	private long orderNo;// 订单号
	private long actId;// 活动id
	private List<TicketInfo> list;// 购票信息集合

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getActId() {
		return actId;
	}

	public void setActId(long actId) {
		this.actId = actId;
	}

	public List<TicketInfo> getList() {
		return list;
	}

	public void setList(List<TicketInfo> list) {
		this.list = list;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
