package com.yogu.commons.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DigestUtil {

	private DigestUtil() {
	}

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static final Logger logger = LoggerFactory.getLogger(DigestUtil.class);
	private static final String UTF_8 = "UTF-8";

	private static MessageDigest getMDigest(String algorithm) throws NoSuchAlgorithmException {
		String key = "DU_MD_" + algorithm;
		MessageDigest messageDigest = ThreadLocalContext.getGlobalValue(key);
        if (null == messageDigest) {
            messageDigest = ThreadLocalContext.getGlobalValue(key);
            if (null == messageDigest) {
                messageDigest = MessageDigest.getInstance(algorithm);
                ThreadLocalContext.setGlobalValue(key, messageDigest);
            }
        }
		return messageDigest;
	}

//    private static SecretKeyFactory getSecretKeyFactory(String algorithm) throws NoSuchAlgorithmException {
//        String key = "DU_SKF_" + algorithm;
//        SecretKeyFactory factory = ThreadLocalContext.getGlobalValue(key);
//        if (null == factory) {
//            factory = ThreadLocalContext.getGlobalValue(key);
//            if (null == factory) {
//                factory = SecretKeyFactory.getInstance(algorithm);
//                ThreadLocalContext.setGlobalValue(key, factory);
//            }
//        }
//        return factory;
//    }

	public static String sha1(String decript) {
		try {
			MessageDigest digest = getMDigest("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2)
					hexString.append(0);
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String sha(String decript) {
		try {
			MessageDigest digest = getMDigest("SHA");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2)
					hexString.append(0);
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String md5(String input) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = getMDigest("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(input.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < md.length; i++) {
				String shaHex = Integer.toHexString(md[i] & 0xFF);
				if (shaHex.length() < 2)
					hexString.append(0);
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 可逆，加密
	 * 
	 * @param content 需要加密的内容
	 * @param password 加密密码
	 * @return
	 */
	public static byte[] encryptAES(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 可逆，解密
	 * 
	 * @param content 待解密内容
	 * @param password 解密密钥
	 * @return
	 */
	public static byte[] decryptAES(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 加密数据
     * 
     * @param encryptString 注意：这里的数据长度只能为8的倍数
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptDES(byte[] encryptbyte, byte[] encryptKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKey(encryptKey), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(encryptbyte);
        return encryptedData;
    }

    /***
     * 解密数据
     * 
     * @param decryptString
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptDES2(String decryptString, byte[] decryptKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKey(decryptKey), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte decryptedData[] = cipher.doFinal(hexStringToByte(decryptString));
        return decryptedData;
    }

    /**
     * ECB加密
     */
    public static String encryptECB(String plainData, String key) throws Exception {
        byte[] dataBuffer = plainData.getBytes(UTF_8);
        // byte[] keyByte = new BASE64Decoder().decodeBuffer(key);
        byte[] keyByte = Base64.decode(key.getBytes());

        DESedeKeySpec spec = new DESedeKeySpec(keyByte);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);

        byte[] byteOut = cipher.doFinal(dataBuffer);

        // return new BASE64Encoder().encode(byteOut);
        byte[] encode = Base64.encode(byteOut);
        return new String(encode, UTF_8);
    }

    /**
     * ECB解密
     */
    public static String decryptECB(String dataToDecode, String key) throws Exception {
        // byte[] dataBuffers = new BASE64Decoder().decodeBuffer(dataToDecode);
        byte[] dataBuffers = Base64.decode(dataToDecode.getBytes());
        // byte[] keyBuffers = new BASE64Decoder().decodeBuffer(key);
        byte[] keyBuffers = Base64.decode(key.getBytes());

        DESedeKeySpec spec = new DESedeKeySpec(keyBuffers);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);

        byte[] byteOut = cipher.doFinal(dataBuffers);

        return new String(byteOut, UTF_8);
    }

	// ###############################
	// ############################### 从原来的 MD5.java 文件中copy过来
	// ###############################

	public static String md5(byte[] source) {
		String s = null;
		try {
			MessageDigest md = getMDigest("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception e) {
			logger.error("error msg : ", e);
		}
		return s;
	}

	/**
	 * 生成md5校验码
	 * 
	 * @param srcContent 需要加密的数据
	 * @return 加密后的md5校验码。出错则返回null。
	 */
	public static String makeMd5Sum(byte[] srcContent) {
		if (srcContent == null)
			return null;
		String strDes = null;
		try {
			MessageDigest md5 = getMDigest("MD5");
			md5.update(srcContent);
			strDes = bytes2Hex(md5.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			logger.error("error msg : ", e);
			return null;
		}
		return strDes;
	}

	/**
	 * 对流进行Md5
	 * 
	 * @param in 需要加密的流
	 * @return
	 */
	public static String makeStreamHash(InputStream in) {
		BufferedInputStream bfs = null;
		String hash = null;
		byte[] buffer = new byte[1024];
		int readNum = 0;
		try {
			bfs = new BufferedInputStream(in);
			MessageDigest md5 = getMDigest("MD5");
			while ((readNum = bfs.read(buffer)) > 0) {
				md5.update(buffer, 0, readNum);
			}
			bfs.close();
			hash = bytes2Hex(md5.digest());
		} catch (Exception e) {
			logger.error("error msg : ", e);
			return null;
		}
		return hash;
	}

	/**
	 * bytes2Hex方法
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String bytes2Hex(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (byteArray[i] >= 0 && byteArray[i] < 16)
				strBuf.append("0");
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}

    /**
     * 自定义一个key
     * 
     * @param string
     */
    public static byte[] getKey(byte[] keyRule) {
        Key key = null;
        byte[] keyByte = keyRule;
        // 创建一个空的八位数组,默认情况下为0
        byte[] byteTemp = new byte[8];
        // 将用户指定的规则转换成八位数组
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
            byteTemp[i] = keyByte[i];
        }
        key = new SecretKeySpec(byteTemp, "DES");
        return key.getEncoded();
    }

    /**
     * 把16进制字符串转换成字节数组
     * 
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

}
