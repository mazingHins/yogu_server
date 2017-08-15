package com.yogu.core.enums.pay;

/**
 * 商户类型
 * @author east
 * @date 2017年4月26日 下午2:31:07
 */
public enum MerchantType {
	
    /**
     * 普通公众号商户
     */
    ORDINARY_PROVIDER((short) 0),
    
    /**
     * 服务商
     */
    SERVICE_PROVIDER((short) 1),
	
	/**
     * 小程序商户
     */
    WECHAT_APP_PROVIDER((short) 2);

    private short value;

    private MerchantType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
    
    public static MerchantType valueOf(short value){
    	MerchantType[] values = MerchantType.values();
    	for(MerchantType merchantType : values){
    		if(merchantType.getValue() == value)
    			return merchantType;
    	}
    	return null;
    }
}