package com.yogu.services.backend.admin.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 管理员名单
 * 
 * <pre>
 *     自动生成代码: 表名 mz_admin_account, 日期: 2015-08-03
 *     uid <PK>              bigint(20)
 *     realname        varchar(60)
 *     operator        int(11)
 *     status          tinyint(4)
 *     mobile_no       varchar(30)
 *     default_data    tinyint(4)
 *     last_modify     datetime(19)
 *     create_time     datetime(19)
 * </pre>
 */
public class AdminAccountPO implements Serializable {

	private static final long serialVersionUID = -3074457346602579384L;

	/** 用户ID */
	private long uid;

	/** 真实姓名 */
	private String realname = "";

	/** 操作员ID */
	private int operator;

	/** 管理员状态，1-正常2-冻结 */
	private short status = 1;

	/** 移动电话 */
	private String mobileNo = "";

	/** 是否是基本用户，0-不是，1-是不可以删除 */
	private short defaultData = 0;

	/** 修改时间 */
	private Date lastModify;

	/** 创建时间 */
	private Date createTime;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealname() {
		return realname;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public int getOperator() {
		return operator;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setDefaultData(short defaultData) {
		this.defaultData = defaultData;
	}

	public short getDefaultData() {
		return defaultData;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
