package com.yogu.core.web.encrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.encrypt.HMacSHA1;

/**
 * 密码加密类
 * 
 * @date 2014-2-26
 * 
 */
public class PasswordHelper {

	private static final Logger log = LoggerFactory.getLogger(PasswordHelper.class);

	/**
	 * 加密秘钥
	 */
	private static final String KEY = "7vMJTtS1RtOdPmxuN39RE4QM6Aam332f";
	/**
	 * 随机字符串集
	 */
	private static final String CHARACTORS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789*-+/'~!@#$%^&()_=";

	/**
	 * 随机长度范围 6表示0~6
	 */
	private static final int RANDOM_LENGTH = 6;

	/**
	 * 加密密码
	 * 
	 * @param plain 明文
	 * @param salt 盐
	 * @return String 加密字符串
	 * @version V1.0
	 * @throws RuntimeException
	 */
	public static String old_encrypt(String plain, String salt) {
		if (StringUtils.isEmpty(salt)) {
			log.error("salt是空值");
			throw new IllegalArgumentException("salt是空值");
		}
		plain = plain.concat(salt);
		plain = StaticKeyHelper.encryptKey(plain);
		String s = HMacSHA1.getSignature(plain, KEY);
		salt = StaticKeyHelper.encryptKey(salt); // salt加密
		s = s.concat(salt); // 机密salt链接在加密密码之后
		return s;
	}

	/**
	 * 深度加密
	 * 
	 * <br>
	 * <p>
	 * 如果要做加密算法的迁移,目前想好的策略是:<br>
	 * 建一张user的关联表,用来标识用户密码是否已经升级,<br>
	 * 否：在用户登录、修改密码操作时, 得到salt后验证旧的加密密码正确后,调用新的加密算法,生成新的加密串,同时修改 关联表;<br>
	 * 是：调用新的加密算法做验证<br>
	 * 新注册的用户直接调用新的加密算法,可以依据时间节点调整逻辑
	 * 
	 * 
	 * @author sky
	 * @date 2015-08-28 14:34:33
	 * 
	 * @param plain 明文
	 * @param salt 盐
	 * @return String 加密字符串
	 * @version V1.0
	 * @throws Exception
	 */
	public static String encrypt(String plain, String salt) {
		if (StringUtils.isEmpty(salt)) {
			log.error("salt是空值");
			throw new IllegalArgumentException("salt是空值");
		}
		// 明文通过HMacSHA1 由动态key hash得到simple串
		String simple = HMacSHA1.getSignature(plain, plain);

		// hash + 加盐
		simple = simple.concat(salt);

		// 加盐simple串 通过HMacSHA1 由 固定KEY 再 hash得到complex串
		String complex = HMacSHA1.getSignature(simple, KEY);

		// complex串 由 HMacSHA1 经过动态key 再 hash得到 复杂的multiplex 串
		String multiplex = HMacSHA1.getSignature(complex, complex);

		// 对盐salt进行DES3加密
		String des_salt = StaticKeyHelper.encryptKey(salt);

		// multiplex 尾部拼接加密的盐 得到最终的结果串
		String result = multiplex.concat(des_salt); // 机密salt链接在加密密码之后

		return result;
	}

	/**
	 * 加密密码
	 * 
	 * @param plain 明文
	 * @return
	 * @throws
	 */
	public static String encrypt(String plain) {
		String salt = PasswordHelper.salt();
		String password = PasswordHelper.encrypt(plain, salt);
		return password;
	}

	/**
	 * @Description 生成经过加密处理的任意长度的盐
	 * @return String
	 * @throws
	 */
	public static String old_salt() {
		Random random = new Random();
		int len = CHARACTORS.length();
		StringBuilder builder = new StringBuilder();
		for (int i = 0, l = random.nextInt(6) + 5; i < l; i++) {
			int num = random.nextInt(len);
			builder.append(CHARACTORS.charAt(num));
		}
		return builder.toString();
	}

	/**
	 * <strong>深度随机 盐</strong><br>
	 * 
	 * <p>
	 * 该方法线程安全
	 * </p>
	 * 
	 * 经测试,调用一次该方法耗时几乎可以忽略,1000次 总耗时平均在40ms,10万次平均耗时在3.5s
	 * 
	 * 
	 * 
	 * @author sky
	 * @date 2015-08-29 11:11:11
	 * @return 一个随机字符串
	 */
	public static String salt() {
		Random random = new Random();
		// 随机字符串长度
		int length = random.nextInt(RANDOM_LENGTH) + 5;
		// 由给定chars串 生成随机长度为length的字符串
		String salt = RandomStringUtils.random(length, CHARACTORS);
		return salt;
	}

	/**
	 * 随机生成一个指定长度的key
	 * 
	 * @param keyLen - key的长度
	 * @return key
	 */
	public static String randomKey(int keyLen) {
		Random random = new Random();
		int len = CHARACTORS.length();
		StringBuilder builder = new StringBuilder(keyLen);
		for (int i = 0; i < keyLen; i++) {
			int num = random.nextInt(len);
			builder.append(CHARACTORS.charAt(num));
		}
		return builder.toString();
	}

	/**
	 * 解析加密+盐后的密码获取加密密码和盐
	 * 
	 * @param encrypt 加密+盐后的密码
	 * @return Map
	 * @throws
	 */
	public static Map<String, String> resolvePassword(String encrypt) {
		Map<String, String> params = new HashMap<String, String>();
		// 从字符串字段中解析出加密密码和盐，并对盐进行解密
		String persistent = encrypt.substring(0, 40);
		String salt = encrypt.substring(40);
		salt = StaticKeyHelper.descryptKey(salt);
		params.put("password", persistent);
		params.put("salt", salt);
		return params;
	}

	public static void main(String[] args) throws Exception {
		// String salt = PasswordHelper.salt();
		// System.out.println("salt=" + salt);
		//
		// String string = "88888888";
		// String first = PasswordHelper.encrypt(string, salt);
		// // String second = PasswordHelper.encrypt(string, salt);
		// // System.out.println(first.equals(second));
		// System.out.println("first: " + first + ", length=" + first.length());
		//
		// salt = resolvePassword(first).get("salt");
		// System.out.println("salt=" + salt);
		// String third = PasswordHelper.encrypt(string, salt);
		// System.out.println("third: " + third + ", length=" + third.length());

		// long starttime = System.currentTimeMillis();
		//
		// Set<String> set1 = new HashSet<String>();
		// for (int i = 0; i < 100000; i++) {
		//
		// long start = System.currentTimeMillis();
		// String salt = salt();
		// long end = System.currentTimeMillis();
		// System.out.println("deep_salt" + i + " = " + salt + ", use time =" + (end - start) + " ms");
		//
		// set1.add(salt);
		// }
		//
		// long endtime = System.currentTimeMillis();
		// System.out.println("####------------------####");
		// System.out.println("set1 size : " + set1.size() + ", use total time=" + (endtime - starttime) + "ms");

		//
		// for (int i = 0; i < 50; i++) {
		// long start = System.currentTimeMillis();
		// String salt = salt();
		// long end = System.currentTimeMillis();
		// System.out.println("salt"+i+" = "+salt +", use time ="+(end-start)+" ms");
		//
		// }
		//

		// test deep encrypted

		String password = "12345678";

		// for (int i = 0; i < 100; i++) {
		// String salt = PasswordHelper.salt();
		// long start = System.currentTimeMillis();
		// String encrypt_pw = PasswordHelper.encrypt(password, salt);
		// long end = System.currentTimeMillis();
		// System.out.println("2: encrypt_pw = " + encrypt_pw + ", length=" + encrypt_pw.length() + " use time =" + (end - start) + " ms");
		// }

		// String salt = PasswordHelper.salt();

		// String encrypt_pw = "ef29098cc37ee331f0d8c2b150b343327e3247b8OOmQHY1eCls=";//PasswordHelper.encrypt(password, salt);
		//
		// String salt2 = PasswordHelper.resolvePassword(encrypt_pw).get("salt");
		//
		// String en2 = PasswordHelper.encrypt(password, salt2);
		//
		// System.out.println("salt=" + salt2 + ", en1=" + encrypt_pw );
		// System.out.println("salt2=" + salt2 + ", en2="+ en2);

		for (int i = 0; i < 64; i++) {
			String tableName = "mz_user.mz_user_";
			tableName = tableName + i;

			String sql = "UPDATE " + tableName + " set password= 'a9b8723541a92a0f20149e0211ee33e1c12f15d04I1kP29GTkGagwtv4Mzs5Q==';";
			System.out.println(sql);
		}
	}

}
