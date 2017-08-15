package com.mazing.test.core.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * 3DES加密工具类。主要和 IOS 的加密保持一致。
 * 参考：http://blog.csdn.net/justinjing0612/article/details/8482689
 * 
 * @author linyi
 * @date 2015-6-8
 */
public class DES3 {
    // 向量
//    private final static String iv = "01234567";

    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 3DES加密
     * 
     * @param plainText
     *            普通文本
     * @param secretKey 密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText, String secretKey)
            throws Exception {

        DESedeKeySpec dks = new DESedeKeySpec(secretKey.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        byte[] b = cipher.doFinal(plainText.getBytes(encoding));
        return Base64.encode(b);
    }

    /**
     * 3DES解密
     * 
     * @param encryptText
     *            加密文本
     * @param secretKey 密钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptText, String secretKey)
            throws Exception {

        byte[] bytesrc = Base64.decode(encryptText);
        //--解密的key
        DESedeKeySpec dks = new DESedeKeySpec(secretKey.getBytes(encoding));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);

        //--Chipher对象解密
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte, encoding);

    }

    public static void main(String[] args) throws Exception {
        String message = "今天天气好吗？yes.";
        String key = "D5EFAEE2D37B423AAA43AC67B5EDC3FB";
        // faefd16@@93f1-54a849%844!ed3a2#9
        String s = encrypt(message, key);
        System.out.println(s);
        String t = decrypt(s, key);
        System.out.println(t);
    }
}
