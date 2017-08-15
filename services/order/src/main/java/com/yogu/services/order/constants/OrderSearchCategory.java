package com.yogu.services.order.constants;

public enum OrderSearchCategory {
	SERIAL_NUMBER((short)0),
	
	PHONE((short)1);
	
	private short value;

    private OrderSearchCategory(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
    
    public static String getCategoryName(short value){
    	
    	switch (value) {
        	case 0:
        		return SERIAL_NUMBER.toString();
        	case 1:
        		return PHONE.toString();
        	default:
        		return null;
    	}
//    	if (value == SERIAL_NUMBER.getValue()){
//    		return SERIAL_NUMBER.toString();
//    	}else if (value == PHONE.getValue()) {
//			return PHONE.toString();
//		}
//    	return null;
    }
    
    public static OrderSearchCategory valueOf(short value){
    	switch (value) {
    		case 0:
    			return SERIAL_NUMBER;
    		case 1:
    			return PHONE;
    		default:
    			return null;
    	}    	
    }
    
    
}
