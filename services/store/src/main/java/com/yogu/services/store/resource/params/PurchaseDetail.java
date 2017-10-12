package com.yogu.services.store.resource.params;

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
    private long goodsKey;

    /**
     * 购买数量
     */
    private int purchaseNum;
    
    public long getGoodsKey() {
		return goodsKey;
	}

	public void setGoodsKey(long goodsKey) {
		this.goodsKey = goodsKey;
	}

	public int getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(int purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
