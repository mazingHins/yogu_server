package com.yogu.services.order.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * @Description: 新增订单评论接口接收参数
 * @author Hins
 * @date 2015年8月21日 下午2:52:29
 */
public class OrderCommentParam {

	/**
	 * 星级
	 */
	@FormParam("star")
	private short star;

	/**
	 * 订单编号
	 */
	@FormParam("orderNo")
	private long orderNo;

	/**
	 * 评价内容
	 */
	@FormParam("content")
	@DefaultValue("")
	private String content;

	/**
	 * 反馈code
	 */
	@FormParam("feedbackCode")
	private short feedbackCode;
	
	/**
	 * 反馈人联系号码  2017-06-25 add by hins：开发时间不足，只能在接口做了
	 */
	@FormParam("feedbackCode")
	private String mobile;

	public short getStar() {
		return star;
	}

	public void setStar(short star) {
		this.star = star;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public short getFeedbackCode() {
		return feedbackCode;
	}

	public void setFeedbackCode(short feedbackCode) {
		this.feedbackCode = feedbackCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
