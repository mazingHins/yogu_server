package com.yogu.services.order.utils.sign.common;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RSA2签名工具类
 * @author east
 * @date 2017年4月6日 上午11:38:00
 */
public class RSA2Util {

	private static final Logger logger = LoggerFactory.getLogger(RSA2Util.class);

	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA256WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * RSA签名
	 * 
	 * @param content 待签名数据
	 * @param privateKey 商户私钥
	 * @param input_charset 编码格式
	 * @return 签名值
	 */
	public static String sign(String content, String privateKey, String charset) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(charset));

			byte[] signed = signature.sign();

			return Base64.encode(signed);
		} catch (Exception e) {
			logger.error("", e);
		}

		return null;
	}

	/**
	 * RSA签名
	 * 
	 * @param content 待签名数据
	 * @param privateKey 商户私钥
	 * @return 签名值
	 */
	public static String sign(String content, String privateKey) {
		return sign(content, privateKey, DEFAULT_CHARSET);
	}

	/**
	 * RSA验签名检查
	 * 
	 * @param content 待签名数据
	 * @param sign 签名值
	 * @param ali_public_key 支付宝公钥
	 * @param input_charset 编码格式
	 * @return 布尔值
	 */
	public static boolean verify(String content, String sign, String publicKey, String charset) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			byte[] encodedKey = Base64.decode(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(charset));

			boolean bverify = signature.verify(Base64.decode(sign));
			return bverify;

		} catch (Exception e) {
			logger.error("", e);
		}

		return false;
	}
	
	public static boolean verify(String content, String sign, String publicKey) {
		return verify(content, sign, publicKey, DEFAULT_CHARSET);
	}

	public static void main(String[] args) {
		String content = "啦啦啦";
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIVhjHrdJe/hpOhhSbOvUyyRXOPkRnzbWGq1kqNqpYaSlBTUj1akcKYLoaQkyqcm+Eai4IRmhC6NadlneXPt9fSdrfegC3Vli0HaZBfXEk8a+XCWuWrvh4S+l+HrnIUjHYqqEoFKyhPnHr4gJGN+hOokCBuKHAqcbz0vwIlMIFYgI2kqTvsAXQ5Z51We1cW5Aa3h7pSrCg1ls0W5N9e7wl2ctQ4c6onpEI7ypeU3Tg2YoYQAgEiDCl09gsjNq0f7cW9RRBiS9uQxUBFk22iXt9+mrSA3DpDM9mjQVIBuxoV9X5wr7c0SlcUmWo2ktKhT+v5r9Af7v8vs81gibX4j3hAgMBAAECggEAAZQ1r0JVGGKK/XPSE0YohtELXpscAbPBMXEUlEeTy3hUUPxLJwR3FCiwnxM7fhHbNsZgCBqApJ27tpGCiw7gWWMqlMH99iGa32MkhMemIs5lwTMLuvAF0qbWam6Cqk5BY4f+W895fFiTyy6P5i5Jk3ePlbEEto2Az1wXLSZD5PoMG+tktNE2wqjJkbOA/PEJJ0N1cbMWUxLPgvk7kzlWDcr1N+xziP4h9sQk7qLo0gN8bBTjm0WHf/v8JWlebvOe9vAmksuZCoou9aVXpVssW5gGpVliUEOJGKI1ji+Ld466pAd4BinKtFK53QtflwpPLt88B0OdhUn5xKmRRkcMUQKBgQD2eUEWteumWQnRRH3aIc7EOp/fo6h7KS75CPSpo3wjr9X3WAYqskwzCig7adBsW3lPhM56t6ROKGDc9EM5QyUDGcVjmCMK3qpyVcgXPHd7RA56aMPhgG2YLZIAhVt9nOmVsAZz0SFYOnzcGR1XTKiCnWCEaGN4Lp49Ab55r2yEXQKBgQCNmxVOvq9EMNgDqphirY5zZ/aA/UZ1f12oZda1FLdSmW0OsyazU9yrRI8LsstlTPIgado3PP3uy9+LG3HR52LTgV0NGKVFOzLxptGn6BYlEae5+0hxgk57onKw+vZtaTbejoFACg/88MhR/Jn/qDnBDwK40ijRKQbrB83Q9m7HVQKBgQC4L87LOPG74wfqIcPm0aLhk2nLx1Jtj04x/lZPuwNW69z6tQYOvCUjczCzjZSalRUnrnhazodzfMs98y+VSC8NjSGsFpIBWnqHbMFVU/N541bqdQYEq2O6+DsGNqIzUaJhISUBQcQoWbTbCc2aYof+67qn2BaVRkksD/SWnNh8ZQKBgFFEq9nhfdz420Bfa81WJeb9x28H7KQKUMoQiESsevmgrAI/tvo8KuzwD9h66Fhe7KbFmUMScfnWb5j6SzxYO1N/btPRTSiKbRZa0wA2Jl9n3obSPCVIz5oNBR2lKkBoneDkkDEclUhpN8411nvvJBY0cy93DY3cqefBNtGyXKlxAoGAJ34ZPg9X4iy9rWI5YFNEcTARsr9I/TVBGniSr+hMMToG6zyy5zZt2xTQslh+bk2OOqyW1BxhVD61stmJAOp/AC/xmQ9LWrNuz+W+QU/D4OZ3PRLf8LoMw007B57F8ZarhzVrzOijv8oJd7vc/jV2FzuJPUoyccFJ+gOTjd33474=";
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiFYYx63SXv4aToYUmzr1MskVzj5EZ821hqtZKjaqWGkpQU1I9WpHCmC6GkJMqnJvhGouCEZoQujWnZZ3lz7fX0na33oAt1ZYtB2mQX1xJPGvlwlrlq74eEvpfh65yFIx2KqhKBSsoT5x6+ICRjfoTqJAgbihwKnG89L8CJTCBWICNpKk77AF0OWedVntXFuQGt4e6UqwoNZbNFuTfXu8JdnLUOHOqJ6RCO8qXlN04NmKGEAIBIgwpdPYLIzatH+3FvUUQYkvbkMVARZNtol7ffpq0gNw6QzPZo0FSAbsaFfV+cK+3NEpXFJlqNpLSoU/r+a/QH+7/L7PNYIm1+I94QIDAQAB";
		String sign = sign(content, privateKey);
		System.out.println(sign);
		
		System.out.println(verify(content, sign, publicKey));
		
	}
}
