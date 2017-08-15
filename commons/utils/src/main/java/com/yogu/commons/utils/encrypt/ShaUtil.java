package com.yogu.commons.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaUtil {
	private final static String SHA1 = "SHA-1";

	private final static String SHA256 = "SHA-256";

	public static String encode(String content, String type, String privateKey) {
		try {
			MessageDigest alga = java.security.MessageDigest.getInstance(type);
			alga.update(content.getBytes());
			byte[] digest = alga.digest();
			return byte2hex(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Not supported: " + type, e);
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

	public static String sha1(String content, String privateKey) {
		return encode(content, SHA1, privateKey);
	}

	public static String sha256(String content, String privateKey) {
		return encode(content, SHA256, privateKey);
	}
}
