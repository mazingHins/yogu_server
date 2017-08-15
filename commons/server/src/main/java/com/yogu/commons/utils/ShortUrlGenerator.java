package com.yogu.commons.utils;

import com.yogu.commons.utils.DigestUtil;

/**
 * URL短地址生成器 <br>
 * <br>
 * http://blog.csdn.net/springmvc_springdata/article/details/39151747?utm_source=tuicool<br>
 * http://my.oschina.net/fuweiwei/blog/304852?p=1<br>
 * 
 * @author jfan 2016年6月21日 下午5:36:34
 */
public class ShortUrlGenerator {

	public static void main(String[] args) {
		// String sLongUrl = "http://www.mazing.com/open/store/10000";
		// String[] aResult = shortUrl(sLongUrl);
		// for (int i = 0; i < aResult.length; i++) {
		// System.out.println("[" + i + "] >>> " + aResult[i]);
		// }

		// ShortUrlGenerator sug = new ShortUrlGenerator();
		// sug.impactTest(1000000);
	}

	/**
	 * 获取URL的短地址<br>
	 * 返回4个，随机使用一个即可
	 */
	public static String[] shortUrl(String url) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "commons-server>";
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"

		};
		// 对传入网址进行 MD5 加密
		// String sMD5EncryptResult = (new CMyEncrypt()).getMD5OfStr(key + url);
		String sMD5EncryptResult = DigestUtil.md5(key + url);
		String hex = sMD5EncryptResult;

		String[] resUrl = new String[4];
		for (int i = 0; i < 4; i++) {

			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);

			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
			// long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resUrl[i] = outChars;
		}
		return resUrl;
	}

	// ########################################################################
	// ########################################################################
	// ########################################################################

	/*
	private long count;
	private Map<String, Integer> cache;

	private void impactTest(int batch) {
		cache = new HashMap<>();
		count = 0;

		while (true) {
			batchOne(batch);
			System.out.println(count);
		}
	}

	private void batchOne(int batch) {
		String[] strs = source(batch);
		for (String str : strs) {
			String[] surls = shortUrl(str);
			for (String surl : surls) {
				if (cache.containsKey(surl)) {
					int i = cache.get(surl);
					System.out.println("源数据[" + str + "]的shortUrl[" + surl + "]重复 " + i);
					cache.put(surl, i + 1);
				} else
					cache.put(surl, 1);
			}
			count += 1;
		}
	}

	private String[] source(int batch) {
		int index = 0;
		String[] result = new String[batch];
		for (int i = 0; i < batch; i++)
			result[index++] = SixtyTwoCountUtil.gen(count++ + i);
		return result;
	}
	*/

}
