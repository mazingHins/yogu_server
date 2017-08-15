package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 订单轨迹VO
 * @author Hins
 * @date 2015年8月19日 上午10:14:30
 */
public class OrderTrackVO implements Serializable {

	private static final long serialVersionUID = 8899195639131827364L;

	/** 订单状态：1-未付款；2-已付款；3-已接单；4-制作中；5-配送中；6-商家确认收货；7-买家确认收货 */
	private Short status;

	/** 当前状态消耗时长 */
	private String duration = "0";

	/**
	 * 当前状态名称
	 */
	private String statusName;

	/** 跟踪轨迹说明 */
	private String content;

	/** 操作人 */
	private Long oper;

	/** 创建时间 */
	private Date createTime;

	/** 跟踪轨迹说明 */
	private OrderTrackContentVO detail;

	/** 是否显示button */
	private short shouldShowButton = 1;

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getOper() {
		return oper;
	}

	public void setOper(Long oper) {
		this.oper = oper;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public OrderTrackContentVO getDetail() {
		return detail;
	}

	public void setDetail(OrderTrackContentVO detail) {
		this.detail = detail;
	}

	public short getShouldShowButton() {
		return shouldShowButton;
	}

	public void setShouldShowButton(short shouldShowButton) {
		this.shouldShowButton = shouldShowButton;
	}

}
