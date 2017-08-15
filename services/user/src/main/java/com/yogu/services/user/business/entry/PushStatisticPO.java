package com.yogu.services.user.business.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * push all 相关数据统计
 * 
 * <pre>
 *     自动生成代码: 表名 mz_push_statistic, 日期: 2016-04-11
 *     pid <PK>               bigint(20)
 *     msg              varchar(512)
 *     create_time      datetime(19)
 *     total            int(11)
 *     total_success    int(11)
 *     type             tinyint(4)
 *     admin_name              varchar(12)
 * </pre>
 */
public class PushStatisticPO implements Serializable {

	private static final long serialVersionUID = -3074457345207781173L;

	/** 主键id */
	private long pid;

	/** 推送内容 */
	private String msg;

	/** 推送时间 */
	private Date createTime;

	/** 推送总数 */
	private int total = 0;

	/** 发送成功总数 */
	private int totalSuccess = 0;

	/** 发送的系统类型 1:ios 2:android */
	private short type;

	private String adminName;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getPid() {
		return pid;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotalSuccess(int totalSuccess) {
		this.totalSuccess = totalSuccess;
	}

	public int getTotalSuccess() {
		return totalSuccess;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getType() {
		return type;
	}

}
