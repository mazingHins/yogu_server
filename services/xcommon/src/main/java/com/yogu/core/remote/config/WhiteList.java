package com.yogu.core.remote.config;

import java.io.Serializable;
import java.util.Date;

/**
 * 白名单
 * @author ten 2016/1/14.
 */
public class WhiteList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private long uid;

    /**
     * 手机号码
     */
    private String mobile;

	/**
	 * 用户最后一次登录app的did值<br>
	 * 不需要录入，consumer自动回填
	 */
	private String did;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 操作者ID
     */
    private long operatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
