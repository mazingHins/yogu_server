package com.yogu.commons.utils.encrypt;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC_MD5算法
 *
 * @date 2016年7月25日 上午10:59:28
 * @author hins
 */
public class HMacMD5 {
	
	private static final String HMAC_MD5 = "HmacMD5";
	
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 使用HMAC_MD5算法生成签名数据
	 * 
	 * @param data - 待加密的数据
	 * @param secret - 加密使用的key
	 * @author hins
	 * @date 2016年7月25日 上午11:04:31
	 * @return String
	 */
	public static String getSignature(String data, String secret){
		
		// 生成字节流
		byte[] rawHmac = encryptHMAC(data, secret);
		
		// 转成十六进制
		return byte2hex(rawHmac);
		
	}
	
	/**
	 * 使用HMAC_MD5算法进行加密
	 * 
	 * @param data - 待加密的数据
	 * @param secret - 加密使用的key
	 * @throws IOException
	 * @author hins
	 * @date 2016年7月25日 上午11:01:33
	 * @return byte[]
	 */
	private static byte[] encryptHMAC(String data, String secret){
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(DEFAULT_CHARSET), HMAC_MD5);
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(DEFAULT_CHARSET));
		} catch (GeneralSecurityException gse) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return bytes;
	}
	
	/**
	 * 把字节流转换为十六进制表示方式。
	 * 
	 * @param bytes - 字节流，一般是encryptHMAC方法后得到的数据
	 * @author hins
	 * @date 2016年7月25日 上午11:03:02
	 * @return String
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

}
