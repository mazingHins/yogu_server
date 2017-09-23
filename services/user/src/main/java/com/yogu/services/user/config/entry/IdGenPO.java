package com.yogu.services.user.config.entry;

import java.io.Serializable;

/**
 * ID生成表，所有数字ID由ID服务统一生成
 * 
 * <pre>
 *     自动生成代码: 表名 mz_id_gen, 日期: 2015-07-13
 *     id_name <PK>           varchar(32)
 *     next_start_id    bigint(20)
 *     fetch_len        int(11)
 *     id_desc          varchar(64)
 * </pre>
 */
public class IdGenPO implements Serializable {

	private static final long serialVersionUID = -3074457343593270937L;

	/** ID名称，比如user_id */
	private String idName;

	/** 下一个开始的ID */
	private long nextStartId = 1;

	/** 每次取的长度，比如1000 */
	private int fetchLen = 1000;

	/** 描述 */
	private String idDesc;

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getIdName() {
		return idName;
	}

	public void setNextStartId(long nextStartId) {
		this.nextStartId = nextStartId;
	}

	public long getNextStartId() {
		return nextStartId;
	}

	public void setFetchLen(int fetchLen) {
		this.fetchLen = fetchLen;
	}

	public int getFetchLen() {
		return fetchLen;
	}

	public void setIdDesc(String idDesc) {
		this.idDesc = idDesc;
	}

	public String getIdDesc() {
		return idDesc;
	}

}
