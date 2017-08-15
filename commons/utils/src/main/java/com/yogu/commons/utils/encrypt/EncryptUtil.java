package com.yogu.commons.utils.encrypt;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加密类
 * 
 * @author Fentid Peng 2007-2-7 下午07:16:28
 */
public class EncryptUtil {
	public static String getMD5(String str) {
		return encode(str, "MD5");
	}

	public static String getMD5(byte[] content) {
		return encode(content, "MD5");
	}

	public static String getSHA1(String str) {
		return encode(str, "SHA-1");
	}

	public static String getLittleMD5(String str) {
		String estr = encode(str, "MD5");
		return estr.substring(0, 20);
	}

	public static String getLittleSHA1(String str) {
		String estr = encode(str, "SHA-1");
		return estr.substring(0, 20);
	}

	private static String encode(String str, String type) {
		try {
			return encode(str.getBytes("UTF-8"), type);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("Not supported encoding: UTF-8", e);
		}
	}

	private static String encode(byte[] bytes, String type) {
		try {
			MessageDigest alga = java.security.MessageDigest.getInstance(type);
			alga.update(bytes);
			byte[] digest = alga.digest();
			return byte2hex(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Not supported: " + type, e);
		}
	}

	public static String uuid(String[] strs) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			for (int i = 0; i < strs.length; i++) {
				if (StringUtils.isNotEmpty(strs[i])) {
					md.update(strs[i].getBytes());
				}
			}
			byte[] bs = md.digest();
			return byte2hex(bs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String byte2hex(byte[] b) {
        StringBuilder buf = new StringBuilder(32);
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				buf.append("0").append(stmp);
			else
				buf.append(stmp);
		}
		return buf.toString().toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 16进制转换成二进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**将二进制转换成16进制
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

	/**
	 * 异或加密
	 * 
	 * @param str
	 * @param key
	 * @return
	 */
	public static String xorEncrypt(String str, String key) {
		BigInteger strbi = new BigInteger(str.getBytes());
		BigInteger keybi = new BigInteger(key.getBytes());
		BigInteger encryptbi = strbi.xor(keybi);

		return new String(encryptbi.toByteArray());
	}

	/**
	 * 异或解密
	 * 
	 * @param encryptStr
	 * @param key
	 * @return
	 */
	public static String xorDecrypt(String encryptStr, String key) {
		BigInteger encryptbi = new BigInteger(encryptStr.getBytes());
		BigInteger keybi = new BigInteger(key.getBytes());
		BigInteger decryptbi = encryptbi.xor(keybi);
		return new String(decryptbi.toByteArray());
	}

	// 将 s 进行 BASE64 编码
	public static String base64Encode(String s) {
		if (s == null)
			return null;
		return (new BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String base64Decode(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * DES加密
	 * 
	 * @param datasource
	 * @param password
	 * @return
	 */
	public static byte[] desEncrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * DES解密
	 * 
	 * @param src
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] desDecrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}

	/**
	 * AES 加密
	 *
	 * @param content 需要加密的内容
	 * @param password  加密密码
	 * @return 返回加密后的字符串
	 * @see <pre>http://www.cnblogs.com/freeliver54/archive/2011/10/08/2202136.html</pre>
	 */
	public static String AESEncrypt(String content, String password) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(password.getBytes("utf-8")));
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		byte[] byteContent = content.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return byte2hex(result); // 加密
	}

	/**
	 * AES 解密
	 * @param str  待解密内容
	 * @param password 解密密钥
	 * @return 返回解密后的字符串
	 */
	public static String AESDecrypt(String str, String password) throws Exception {
		byte[] content = parseHexStr2Byte(str);
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(password.getBytes("utf-8")));
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(content);
		return new String(result, "utf-8");
	}

	public static void main(String[] args) throws Exception {
        System.out.println(getMD5("sk@#2usjdkf"));
        System.out.println("f8aeabd0cba6efdf28d65e9d805c837c".equalsIgnoreCase(getMD5("sk@#2usjdkf")));

		String content = "9123456789";
		String key = "sk@#2usjdkf";

		System.out.println("content=" + content);
		String aesTmp = AESEncrypt(content, key);
		System.out.println("aes: " + aesTmp + ", len=" + aesTmp.length());
		System.out.println("aes decrypt: " + AESDecrypt(aesTmp, key).equals(content));

		// 测试一下性能
		// 热身一下code
		for (int i=0; i < 10000; i++) {
			AESEncrypt(content, key);
		}
		// 真正测试开始
		long t1 = System.currentTimeMillis();
		for (int i=0; i < 10000; i++) {
			AESEncrypt(content, key);
		}
		long time = System.currentTimeMillis() - t1;
		System.out.println("time=" + time);
	}
}
