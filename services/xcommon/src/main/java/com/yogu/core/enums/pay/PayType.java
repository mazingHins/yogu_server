package com.yogu.core.enums.pay;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单支付类型
 * @author Hins
 * @date 2015年9月9日 上午10:33:31
 */
public enum PayType {
	
	/**
	 * 未选择 (未支付)
	 */
	NONE((short)0),
	
	/**
     * 线上支付
     */
    ONLINE((short) 1),

    /**
     * 货到付款
     */
    CASH((short) 2),
    
    /**
     * 米星支付
     */
    MAZING_PAY((short) 3),
    
    /**
     * 购票支付
     */
    TICKET_PAY((short) 4),
    
    /**
     * 购票支付
     */
    TEAM_PAY((short) 5);
    
    private short value;

    private PayType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static PayType valueOf(int value) {
        switch (value) {
            case 1:
                return ONLINE;
            case 2:
                return CASH;
            case 3:
                return MAZING_PAY;
            case 4:
                return TICKET_PAY;
            case 5:
            	return TEAM_PAY;
            default:
                return null;
        }
    }
    
    /**
     * 获取米星付订单支付类型<br>
     * 用于查看米星付订单类型的查询条件
     * 
     * @author hins
     * @date 2016年6月15日 下午5:48:33
     * @return List<Short>
     */
    public static List<Short> mazingPay(){
    	List<Short> list = new ArrayList<>(1);
    	list.add(MAZING_PAY.getValue());
    	return list;
    }
    
    /**
     * 获取非米星付订单支付类型<br>
     * 用于查看米星付订单类型的查询条件
     * 
     * @author hins
     * @date 2016年6月15日 下午5:49:53
     * @return List<Short>
     */
    public static List<Short> notMazingPay(){
    	
    	List<Short> list = new ArrayList<>(3);
    	list.add(NONE.getValue());
    	list.add(ONLINE.getValue());
    	list.add(CASH.getValue());
    	return list;
    	
    }
    
    /**
     * 获取线上支付和货到付款的订单支付类型
     * 
     * @author hins
     * @date 2016年6月17日 上午11:06:15
     * @return List<Short>
     */
    public static List<Short> cashAndOnline(){
    	
    	List<Short> list = new ArrayList<>(2);
    	list.add(ONLINE.getValue());
    	list.add(CASH.getValue());
    	return list;
    	
    }
    
    public static List<Short> notNone(){
    	List<Short> list = new ArrayList<>(3);
    	list.add(ONLINE.getValue());
    	list.add(CASH.getValue());
    	list.add(MAZING_PAY.getValue());
    	return list;
    }
    
    /**
     * 获取需要支付的支付类型
     * @author hins
     * @date 2016年9月19日 下午6:16:57
     * @return List<Short>
     */
    public static List<Short> needPay(){
    	List<Short> list = new ArrayList<>(2);
    	list.add(ONLINE.getValue());
    	list.add(MAZING_PAY.getValue());
    	return list;
    }
    
    /**
     * 获取购票支付的支付类型
     * @return    
     * @author east
     * @date 2017年3月14日 上午10:37:11
     */
    public static List<Short> ticketPay(){
    	List<Short> list = new ArrayList<>(1);
    	list.add(TICKET_PAY.getValue());
    	return list;
    }

}
