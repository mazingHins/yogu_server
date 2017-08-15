package com.yogu.core.enums.pay;

/**
 * 微信“查询退款”api接口-退款状态对应的定义
 *
 * @date 2017年1月10日 上午10:25:01
 * @author hins
 */
public enum WechatApiRefundStatus {

	 /**
     * 退款成功
     */
	SUCCESS((short)1, "退款成功"),
	
	/**
     * 退款失败
     */
	FAIL((short)2, "退款失败"),
	
	/**
     * 退款处理中
     */
	PROCESSING((short)3, "退款处理中"),
	
	/**
     * 转入代发
     */
	CHANGE((short)4, "转入代发"),
	
	
	NO_DEFINITION((short)0, "没定义的状态");

    private short value;
    
    private String name;

    private WechatApiRefundStatus(short value, String name) {
        this.value = value;
        this.name = name;
    }

    public short getValue() {
        return value;
    }
    
    public String getName(){
    	return name;
    }
    
    public WechatApiRefundStatus valueOf(short value){
    	switch (value) {
		case 1:
			return SUCCESS;
		case 2:
			return FAIL;
		case 3:
			return PROCESSING;
		case 4:
			return CHANGE;
		default:
			return NO_DEFINITION;
		}
    }
	
}
