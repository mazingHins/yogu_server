package com.yogu.commons.utils.cfg;

import com.yogu.commons.utils.encrypt.DES3;

/**
 * DES实现
 * @author tendy
 *
 */
public class DesPropertiesEncoder implements PropertiesEncoder {
    
    /**
     * the key
     */
    private static final String KEY = "(.7dcez#328%f-zkl;-xct3-{}32savc345,@%$)";

    @Override
    public String encode(String str) {
        String s = "";
		try {
			s = DES3.encrypt(str, KEY);
		} catch (Exception e) {
			throw new RuntimeException("加密错误", e);
		}
        return s;
    }

    @Override
    public String decode(String str) {
        String s = "";
		try {
			s = DES3.decrypt(str, KEY);
		} catch (Exception e) {
			throw new RuntimeException("解密错误: " + str, e);
		}
        return s;
    }

    public static void main(String[] args) {
    	DesPropertiesEncoder ed = new DesPropertiesEncoder();
        System.out.println(ed.decode("6d15rpNP2nXXUsY1lsICLKhigcZib+5zwBZpGhIhMMKb5n8eqyipqN8ThSiC 0bYouwRgH+fFcvIyvjp4oBhJhBnMDqFF8i2kUrUYEQ3Wt5PhdPZ0rG4+Gclw lNJyMD0m2PpI3W+Y188="));	// prod
        System.out.println(ed.encode("127.0.0.1:6379 # 27.0.0.1:16379"));	// org
        System.out.println(ed.decode("6d15rpNP2nXXUsY1lsICLB+c0/4T/6V66qcwOYBgCwV8YZorfCWUBoko8ZlY XBYZ"));	// org
        System.out.println(ed.decode("kg7znayXH8TXUsY1lsICLKrgQatQp+i9"));	// org
        System.out.println(ed.decode("eTRBNnv7FvxVVRLSOxEQi2OLoyCMaGzweTRBNnv7FvxVVRLSOxEQiztzCOO6 6794"));	// org
        System.out.println(ed.decode("eTRBNnv7FvxVVRLSOxEQi1D5Uu9lWL9ZeTRBNnv7FvxVVRLSOxEQi4tiEQWm YM+B"));	// org
        
//        System.out.println(ed.decode("0l32Osie2EAeXFHXWES6OgtFPzawvK9q"));
    }
}
