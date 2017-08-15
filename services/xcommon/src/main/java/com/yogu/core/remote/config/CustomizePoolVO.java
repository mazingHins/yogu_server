package com.yogu.core.remote.config;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户定制标签选择池vo-内部使用
 *
 * @date 2016年12月16日 上午11:24:31
 * @author hins
 */
public class CustomizePoolVO implements Serializable {

	private static final long serialVersionUID = 2789680632670301559L;

	/** 定制id */
	private long customizeId = 0;

	/** 城市code */
	private String cityCode = "";

	/** 中文名称 */
	private String cnName = "";

	/** 英文名称 */
	private String enName = "";

	/** 序号 */
	private int sequence = 0;

	/** 被用户选中数量 */
	private int useNumber = 0;

	/** 状态：1-起效；0-无效 */
	private short status = 0;

	/** 创建时间 */
	private Date createTime;

	/** 该配置下含有的餐厅标签列表 */
	private List<Integer> tagList;

	/** 客户端显示中文 */
	private short showZh;

	/** 客户端显示英文 */
	private short showEn;

	public long getCustomizeId() {
		return customizeId;
	}

	public void setCustomizeId(long customizeId) {
		this.customizeId = customizeId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getUseNumber() {
		return useNumber;
	}

	public void setUseNumber(int useNumber) {
		this.useNumber = useNumber;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Integer> getTagList() {
		return tagList;
	}

	public void setTagList(List<Integer> tagList) {
		this.tagList = tagList;
	}

	public short getShowZh() {
		return showZh;
	}

	public void setShowZh(short showZh) {
		this.showZh = showZh;
	}

	public short getShowEn() {
		return showEn;
	}

	public void setShowEn(short showEn) {
		this.showEn = showEn;
	}

}
