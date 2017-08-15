package com.yogu.services.user.business.entry;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 推送相关信息表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_android_profile, 日期: 2015-11-18
 *     pid <PK>            int(11)
 *     uid           bigint(20)
 *     dtype         tinyint(4)
 *     did           varchar(128)
 *     createtime    datetime(19)
 *     updatetime    datetime(19)
 *     channel       varchar(32)
 *     version       varchar(12)
 *     lang          varchar(8)
 *     target        varchar(32)
 * </pre>
 */
public class AndroidProfilePO implements Serializable {

	private static final long serialVersionUID = -3074457347114860511L;

	/** 主键id， 自增长 */
	private int pid;

	/** 用户id */
	private long uid;

	/** 设备类型 1：android phone 2: android pad */
	private short dtype;

	/** 设备唯一标识 */
	private String did;

	/** 创建时间 */
	private Date createtime;

	/** 更新时间 */
	private Date updatetime;

	/** 渠道号 */
	private String channel;

	/** 版本号 */
	private String version;

	/** 用户系统语言代码 **/
	private String lang = "";

	/** 城市编码 */
	private String city = "";

	/** 客户端app包名标识target参数，android从4.0开始传该参数 **/
	private String target;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPid() {
		return pid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setDtype(short dtype) {
		this.dtype = dtype;
	}

	public short getDtype() {
		return dtype;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getDid() {
		return did;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
