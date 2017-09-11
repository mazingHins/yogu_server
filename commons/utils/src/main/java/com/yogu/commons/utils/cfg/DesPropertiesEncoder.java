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
        System.out.println(ed.encode("127.0.0.1:6379"));	// org
        
//        System.out.println(ed.decode("0l32Osie2EAeXFHXWES6OgtFPzawvK9q"));
    }
}
