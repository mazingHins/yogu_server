package com.yogu.services.user.base.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户配置表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_user_setting, 日期: 2015-08-17
 *     uid <PK>                     bigint(20)
 *     default_city_code      varchar(10)
 *     default_language_id    varchar(10)
 *     default_pay_mode       tinyint(4)
 *     is_push                tinyint(4)
 *     create_time            datetime(19)
 * </pre>
 */
public class UserSettingPO implements Serializable {

	private static final long serialVersionUID = -3074457344098493962L;

	/** 用户id */
	private long uid;

	/** 默认城市code */
	private String defaultCityCode;

	/** 默认语言id */
	private String defaultLanguageId;

	/** 默认支付方式，1-支付宝；2-微信支付 */
	private short defaultPayMode = 1;

	/** 是否推送通知，0-是，1-否 */
	private short isPush = 0;

	/** 创建时间 */
	private Date createTime;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setDefaultCityCode(String defaultCityCode) {
		this.defaultCityCode = defaultCityCode;
	}

	public String getDefaultCityCode() {
		return defaultCityCode;
	}

	public void setDefaultLanguageId(String defaultLanguageId) {
		this.defaultLanguageId = defaultLanguageId;
	}

	public String getDefaultLanguageId() {
		return defaultLanguageId;
	}

	public void setDefaultPayMode(short defaultPayMode) {
		this.defaultPayMode = defaultPayMode;
	}

	public short getDefaultPayMode() {
		return defaultPayMode;
	}

	public void setIsPush(short isPush) {
		this.isPush = isPush;
	}

	public short getIsPush() {
		return isPush;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
