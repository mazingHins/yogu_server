package com.yogu.services.store.tag;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 餐厅的tag
 * @author ten 2016/1/18.
 */
public class StoreTagMp implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 餐厅ID */
    private long storeId;

    /** 餐厅标签ID */
    private int tagId;

    /** 创建时间 */
    private Date createTime;

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
