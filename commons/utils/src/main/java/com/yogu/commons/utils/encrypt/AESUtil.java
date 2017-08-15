package com.yogu.commons.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES加密解密工具类
 * 
 * @author sky
 * 
 */
public class AESUtil {

	/**
	 * 密钥
	 */
	//private static String APP_SECRET = "D5EFAEE2D37B423AAA43AC67B5EDC3FB";
	//private static final String APP_KEY = "appKey";

	/**
	 * default charset
	 */
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 加密
	 * 
	 * 
	 * @param key 密钥
	 * @param src 需要加密的源
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key, String src) throws Exception {
		byte[] rawKey = getRawKey(key.getBytes("UTF-8"));
		byte[] encrypted = encrypt(rawKey, src.getBytes(CHARSET_NAME));
		return parseByte2HexStr(encrypted);
	}

	/**
	 * 解密
	 * 
	 * @param key 密钥
	 * @param src 需要解密的源
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String key, String src) throws Exception {
		byte[] encrypted = parseHexStr2Byte(src);
		byte[] rawKey = getRawKey(key.getBytes("UTF-8"));
		byte[] result = decrypt(rawKey, encrypted);
		return new String(result, CHARSET_NAME);
	}

	/**
	 * 获取128位的加密密钥
	 * 
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	private static byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");

		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
		// 256 bits or 128 bits,192bits
		kgen.init(128, sr);
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		return raw;
	}

	/**
	 * 真正的加密过程
	 * 
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(src);
		return encrypted;
	}

	/**
	 * 真正的解密过程
	 * 
	 * @param key
	 * @param encrypted
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] key, byte[] encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	/**
	 * 16进制转换成二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		/**
		 * test key
		 */
		String key = "abcde###%";
		/**
		 * test value
		 */
		String value = "1234567890";
		System.out.println("key:" + key);
		
//		value = "123asdfasdf4fd55asdfasdf566666a";
		
		System.out.println("value:" + value);
		String encrypted = encrypt(key, value).toLowerCase();

		System.out.println("encrypted:" + encrypted+", length="+encrypted.length());

		String decrypted = decrypt("!4i$G-po|YU^&dx?", "c7606040df2430c74529eb0b3be9399e");

		System.out.println("decrypted:" + decrypted);

	}

}