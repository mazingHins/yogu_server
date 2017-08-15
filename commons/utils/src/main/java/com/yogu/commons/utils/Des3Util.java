package com.yogu.commons.utils;
//package com.vip.commons.utils;
//
//import java.security.Key;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESedeKeySpec;
//
///**
// * Des3 Utility
// * @author kevin.xu
// *
// */
//public class Des3Util {    
//    
//    private static final String UTF_8 = "UTF-8";
//
//    /**
//     * 
//     * @param plainData
//     * @param key
//     * @return
//     * @throws Exception
//     */
//    public static String encodeECB(String plainData, String key) throws Exception {
//        
//        byte[] dataBuffer = plainData.getBytes(UTF_8);
//        // byte[] keyByte = new BASE64Decoder().decodeBuffer(key);
//        byte[] keyByte = Base64.decode(key.getBytes());
//
//        DESedeKeySpec spec = new DESedeKeySpec(keyByte);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        Key deskey = keyfactory.generateSecret(spec);
//
//        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, deskey);
//        
//        byte[] byteOut = cipher.doFinal(dataBuffer);
//
//        // return new BASE64Encoder().encode(byteOut);
//        byte[] encode = Base64.encode(byteOut);
//        return new String(encode, UTF_8);
//    }
//
//    /**
//     * 
//     * @param dataToDecode
//     * @param key
//     * @return
//     * @throws Exception
//     */
//    public static String decodeECB(String dataToDecode, String key) throws Exception {
//        
//        // byte[] dataBuffers = new BASE64Decoder().decodeBuffer(dataToDecode);
//        byte[] dataBuffers = Base64.decode(dataToDecode.getBytes());
//        // byte[] keyBuffers = new BASE64Decoder().decodeBuffer(key);
//        byte[] keyBuffers = Base64.decode(key.getBytes());
//        
//        DESedeKeySpec spec = new DESedeKeySpec(keyBuffers);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        Key deskey = keyfactory.generateSecret(spec);        
//        
//        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, deskey);
//        
//        byte[] byteOut = cipher.doFinal(dataBuffers);
//
//        return new String(byteOut, UTF_8);
//    }
//    
//    public static void main(String[] args) throws Exception {
//        final String key = "SUNz1CDr5SigXxCM6MDvTGecJKgrYJJioNuQbhz0vLjQ7Dfcmyq57ud5OWEyY9kL";
//
//        // this func
//        long offset = System.currentTimeMillis();
//        for (int i = 0; i < 1; i++) {
//            String encoded = encodeECB("01234567890123456789", key);
//            System.out.println("encoded: " + encoded);
//            // System.out.println(System.currentTimeMillis() - offset);
//            System.out.println("decoded: " + decodeECB(encoded, key));
//            System.out.println(System.currentTimeMillis() - offset);
//        }
//        System.out.println(System.currentTimeMillis() - offset);
//        
//        // DigestUtil func
//        offset = System.currentTimeMillis();
//        for (int i = 0; i < 1; i++) {
//            String encoded = DigestUtil.encryptECB("01234567890123456789", key);
//            System.out.println("encoded: " + encoded);
//            // System.out.println(System.currentTimeMillis() - offset);
//            System.out.println("decoded: " + DigestUtil.decryptECB(encoded, key));
//            System.out.println(System.currentTimeMillis() - offset);
//        }
//        System.out.println(System.currentTimeMillis() - offset);
//    }
//}
