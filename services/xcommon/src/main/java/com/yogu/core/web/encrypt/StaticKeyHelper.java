package com.yogu.core.web.encrypt;


import com.yogu.commons.utils.encrypt.DES3;

/**
 * 对系统所有涉及密码的地方加密。
 * 用固定的key加密
 * @author tendy
 * 2014/2/17
 */
public class StaticKeyHelper {

    /**
     * the key
     */
    private static final String KEY = "lkDoo@t$@pmx(.xcj_-=#328%f-++32-xctxxx32savcz3,@*($)";
    
    // private static final String KEY = "D5EFAEE2D37B423AAA43AC67B5EDC3FB";
    
    /**
     * 加密
     * @param key
     * @return
     */
	public static String encryptKey(String key) {
		String s = "";
		try {
			s = DES3.encrypt(key, KEY);
		} catch (Exception e) {
			throw new RuntimeException("加密错误", e);
		}
        return s;
	}
	
	/**
	 * 解密
	 * @param data
	 * @return
	 */
	public static String descryptKey(String data) {
		String s = "";
		try {
			s = DES3.decrypt(data, KEY);
		} catch (Exception e) {
			throw new RuntimeException("解密错误", e);
		}
        return s;
	}
	
	public static void main(String[] args) {
		//System.out.println(encryptKey("123456789").length());
				//System.out.println(descryptKey("9KlB5Cc5QExpYc7PGAEM7tG/y3u75/eqWsgVNM/Kq1E="));
		//System.out.println(descryptKey("9KlB5Cc5QExPcbTOYoC9urKs0DASdE16gksfkqrzDRM="));
		
		
		System.out.println(encryptKey("13924132367"));
		System.out.println(descryptKey("XmMkq3pLXutq9AGR6J2c+k6oG/93Zj4y"));
	}
}
