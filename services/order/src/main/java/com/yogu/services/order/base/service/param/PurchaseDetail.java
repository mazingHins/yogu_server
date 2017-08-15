package com.yogu.services.order.base.service.param;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 购买物品的详情
 * 增加规格后新增的
 * @author ten 2016/2/22.
 */
public class PurchaseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 美食key
     */
    private long dishKey;

    /**
     * 规格key
     */
    private long specKey;

    /**
     * 购买数量
     */
    private int purchaseNum;

    /**
     * 规格备注名, 以数组形式上传
     */
    private List<String> supplementName;

    public long getDishKey() {
        return dishKey;
    }

    public void setDishKey(long dishKey) {
        this.dishKey = dishKey;
    }

    public long getSpecKey() {
        return specKey;
    }

    public void setSpecKey(long specKey) {
        this.specKey = specKey;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public List<String> getSupplementName() {
        return supplementName;
    }

    public void setSupplementName(List<String> supplementName) {
        this.supplementName = supplementName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
