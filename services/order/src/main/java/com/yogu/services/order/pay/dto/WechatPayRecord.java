package com.yogu.services.order.pay.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * 微信支付请求记录表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_wechat_pay_record, 日期: 2016-01-30
 *     pay_id <PK>               bigint(20)
 *     nonce_str           varchar(32)
 *     spbill_create_ip    varchar(16)
 *     trade_type          tinyint(4)
 *     prepay_id           varchar(64)
 *     create_time         datetime(19)
 * </pre>
 */
public class WechatPayRecord implements Serializable {
	
	private static final long serialVersionUID = -6215870614526597569L;

	/** 支付记录ID */
	private long payId;

	/** 请求参数-随机字符串 */
	private String nonceStr;

	/** 请求参数-终端IP */
	private String spbillCreateIp;

	/** 预支付交易会话标识 */
	private String prepayId;
	
	/** 公众号预支付交易会话标识(m域h5支付) */
	private String mpPrepayId;
	
	/** 时间撮 */
	private long timestamp;

	/** 创建时间 */
	private Date createTime;

	public void setPayId(long payId) {
		this.payId = payId;
	}

	public long getPayId() {
		return payId;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getPrepayId() {
		return prepayId;
	}
	
	public String getMpPrepayId() {
		return mpPrepayId;
	}

	public void setMpPrepayId(String mpPrepayId) {
		this.mpPrepayId = mpPrepayId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
