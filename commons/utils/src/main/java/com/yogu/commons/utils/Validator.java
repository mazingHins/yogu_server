package com.yogu.commons.utils;

import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//验证类 create by seagull 2008-05-02
/*--------------------------------------------------|
 |validator 9.66                                     |
 |create by GaoZuhui(winner) 2008-04-15              |
 |description: public JavaScript Validate functions  |
 |--------------------------------------------------*/

///************************************************
//function list
//1.isEmpty(elementValue)
//2.isSelected(elementValue)
//3.isChinese(elementValue)
//4.isEnglish(elementValue)
//5.isNumber(elementValue)
//6.isInteger(elementValue)
//7.isDouble(elementValue)
//8.isEmail(elementValue)
//9.isUrl(elementValue)
//10.isPhoneNo(elementValue)
//11.isCurrency(elementValue)
//12.isMobileNo(elementValue)
//13.isBeginMobileNo(elementValue)
//14.isEndMobileNo(elementValue)
//15.isPostNo(elementValue)
//16.isIdCard(elementValue)
//17.isQQNo(elementValue)
//18.isDate(elementValue, formatString)
//19.isSafe(elementValue)
//20.compare(op1,operator,op2)
//21.mustChecked(name, min, max)
//22.isMatch(op, reg)
//23.isLimit(len,minLength, maxLength)
//24.isBetween(elementValue,minValue, maxValue)
//25.lengthB(str)
//26.trim(str)
//27.cutStrLength(str, iLen) 
//28.checkFieldLength(fieldName, fieldDesc, fieldLength)
public final class Validator {

	private Validator() {
	}

	private static final Logger logger = LoggerFactory.getLogger(Validator.class);

	/**
	 * create by seagull 2008-04-18 验证是否为合法的移动手机号码格式
	 * 
	 * @param card_number卡件号码
	 * @return 非法卡号
	 */

	/**
	 * 正则表达式验证
	 */
	public final static boolean isMatch(String reg, String elementValue) {

		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile(reg); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为空
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isEmpty(String elementValue) {
		boolean bool = true;
		bool = ("".equals(elementValue) || (null == elementValue) || (elementValue.length() == 0));
		return bool;
	}

	/**
	 * 验证下拉列表框是否有选择值
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isSelected(String elementValue) {
		return (!"".equals(elementValue) && !(elementValue.length() == 0) && !(elementValue == "-1"));
	}

	/**
	 * 验证是否为中文字符
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isChinese(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("[\u0391-\uFFE5]+"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为英文字符
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isEnglish(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("[A-Za-z]+"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}
	
	/**
	 * 验证是否为英文字符串, 包括空格
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isEnglishWithSpace(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("[A-Za-z| ]+"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为数字
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isNumber(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("\\d+"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为整数
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isInteger(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("[-\\+]?\\d+"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为整数
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isDouble(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("[-\\+]?\\d+(\\.\\d+)?"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为邮件地址
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isEmail(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为网址
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isUrl(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("http:\\/\\/[A-Za-z0-9]+\\.[A-Za-z0-9]+[\\/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\"\"])*"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为电话号码
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isPhoneNo(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为货币
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isCurrency(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("\\d+(\\.\\d+)?"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为移动手机号码
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isMobileNo(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		// p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(14[0-9])|(18[0-9]))\\d{8}$"); // 正则表达式
		// p = Pattern.compile("^0?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$"); // 正则表达式

		p = Pattern.compile("^1\\d{10}");// 手机号只校验长度,数字,1开头; 因为运营商的手机号规则随时会变化

		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}
	
	/**
	 * 字符串s中的字符是否都相同
	 * 
	 * @param s 待检查字符串
	 * @return true,都是同一字符组成;false,不都是同一字符
	 * @author sky 2016-03-09
	 */
	public static boolean isSameSymbol(String s) {

		// 当s为空字符串或者null,认为不是由同一字符构成的
		if (StringUtils.isEmpty(s))
			return false;

		// 当只有一个字符的时候，认为由同一字符构成
		if (1 == s.length())
			return true;

		char chacter = s.charAt(0);
		for (int i = 1; i <= s.length() - 1; i++) {
			if (chacter != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * 验证是否为品牌卡类型
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isCardType(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("([1-6])"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证品牌卡的使用类型
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isCardUseType(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("([0-5])"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证品牌卡的分盒类型
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isCardGroupType(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("([LS])"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为邮政编码
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isPostNo(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("[1-9]\\d{5}"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 验证是否为身份证号码
	 * 
	 * @param elementValue
	 * @return
	 */
	public final static boolean isIdCard(String elementValue) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("((\\d{15})|(\\d{18})|(\\d{17}[xX]))"); // 正则表达式
		m = p.matcher(elementValue);
		bool = m.matches();

		return bool;
	}

	/**
	 * 判断文件是否为正确的格式
	 */
	public final static boolean validateFileType(String fileName, String fileType) {
		boolean bool = true;
		if ("".equals(fileName) || null == fileName || "".equals(fileType) || null == fileType) {
			bool = false;
			return bool;
		}
		bool = fileName.substring(fileName.length() - fileType.length()).equalsIgnoreCase(fileType);
		return bool;
	}

	/**
	 * create by seagull 2008-05-06 判断散号资源是否为合法的移动手机号码格式
	 * 
	 * @param cardnumber_list_arr 卡号资源数组格式参数
	 * @return 非法卡号
	 */
	public final static String validateCardNumbers(String[] cardnumber_list_arr) {
		String result = "";
		for (int i = 0; i < cardnumber_list_arr.length; i++) {
			if (!Validator.isMobileNo(cardnumber_list_arr[i])) {
				result += cardnumber_list_arr[i] + "\\n";
			}
		}
		return result;
	}

	/**
	 * 验证新业务消息格式(客户手机号码#业务简码,客户手机号码#拼音简码,客户手机号码#中文名称)
	 * 
	 * @param newBusinessMessage
	 * @return
	 */
	public final static boolean validateNewBusinessMessageFormat(String newBusinessMessage) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("((13)[4-9]|(15)[0-9])\\d{8}([#＃])(.+)"); // 正则表达式
		m = p.matcher(newBusinessMessage);
		bool = m.matches();

		return bool;
	}

	/**
	 * 判断某字节是否为数字
	 * 
	 * @param ch char
	 * @return boolean:true:是数字;false:不是数字;
	 */
	public final static boolean isDigit(char ch) {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(String.valueOf(ch));
		boolean flag = matcher.matches();
		return flag;
	}

	/**
	 * 判断某字节是否为字母
	 * 
	 * @param ch char
	 * @return boolean:true:是字母;false:不是字母
	 */
	public final static boolean isLetter(char ch) {
		Pattern pattern = Pattern.compile("[A-Za-z]");
		Matcher matcher = pattern.matcher(String.valueOf(ch));
		boolean flag = matcher.matches();
		return flag;
	}

	/**
	 * 判断字符串是否为全数字
	 * 
	 * @param str String
	 * @return boolean:true:是数字;false:不是数字;
	 */
	public final static boolean isDigit(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher matcher = pattern.matcher(str);
		boolean flag = matcher.matches();
		return flag;
	}

	/**
	 * 判断字符串是否为全字母
	 * 
	 * @param str char
	 * @return boolean:true:是字母;false:不是字母
	 */
	public final static boolean isLetter(String str) {
		Pattern pattern = Pattern.compile("[A-Za-z]*");
		Matcher matcher = pattern.matcher(str);
		boolean flag = matcher.matches();
		return flag;
	}

	/**
	 * 判断是否浮点型数据
	 */
	public final static boolean checkFloat(String str) {
		boolean b = true;
		int flag = 0;
		String model = "-1234567890.";
		char strs[] = str.toCharArray();
		for (int i = 0; i < strs.length; i++) {
			flag = model.indexOf(String.valueOf(strs[i]));
			if (flag == -1) {
				b = false;// 不存在这样的字符，退出，不是浮点型
				return b;
			}
		}
		return b;
	}

	// 检查日期,合法返回真
	public final static boolean checkDate(String date) {
		if (date == null || date == "")
			return false;
		DateFormat fmt = DateFormat.getDateInstance(DateFormat.DEFAULT);
		try {
			fmt.parse(date.trim());
		} catch (Exception e) {
			logger.error("error msg : ", e);
			return false;
		}
		return true;
	}

	public final static boolean isValidDate(String sDate) {
		boolean b = true;
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "\\d{2}/\\d{2}/\\d{4}";

		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			b = match.matches();
			if (!b) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				b = match.matches();
			}
		}
		return b;
	}
	
	/**
	 * 是否存在特殊字符(允许汉字，字母，数字,符号'&!℃·_-（）()和空格)
	 * 
	 * @param str
	 * @return true,存在; false,不存在
	 */
	public final static boolean containsSpecialCharacter(String str) {

		// String regEx = "[\u4e00-\u9fa5]*[a-z|A-Z]*\\d*-*_*\\s*";// 允许字符
		// String regEx = "/^[\\w\u4e00-\u9fa5]+$/gi";// 允许字符

		// String regEx = "[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？]";

		// "' & ! ℃ ·"

		String regEx = "[\u4e00-\u9fa5|a-z|A-Z|'|&|!|℃|·（）()|\\d|\\-|_|\\s]*";

		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);

		// 若匹配正常字符
		boolean isNormal = m.matches();

		return isNormal == true ? false : true;// 正常字符,返回false表示不包含特殊字符

	}

	// /**
	// * 正确的描述语句(包含字母，数字，汉字，小数点) 如： 1.23kilo米
	// *
	// * @param str
	// * @return true,正常;false,不正常
	// */
	// public final static boolean isCorrectDesc(String str) {
	// String regEx = "[\u4e00-\u9fa5|a-z|A-Z|.|\\d|\\s]*";
	// Pattern p = Pattern.compile(regEx);
	// Matcher m = p.matcher(str);
	//
	// // 若匹配正常字符
	// boolean isNormal = m.matches();
	//
	// return isNormal;
	// }


	/**
	 * 只检查像emoji这样的特殊输入,允许一些标点符号（在containsSpecialCharacter 正则上加入了标点的允许）
	 * 
	 * @param str
	 * @return true,包含; false,不包含
	 */
	public static boolean containsEmoji(String str) {
//		String regEx = "[\u4e00-\u9fa5|a-z|A-Z|'|&|!|℃|·|\\\\|\"|\\[|\\]|\\d|！—@￥%‘’、.，#,。？?……~～/（）()：:{}“”★☆|\\-|_|\\s]*";
//		Pattern p = Pattern.compile(regEx);
		/*
		boolean contains = false;
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(str)) {
			final int LEN = str.length();
			for (int i=0; i < LEN; i++) {
				char ch = str.charAt(i);
				if ((ch >= 0xd83c && ch < 0xdfff) || (ch >= 0x2600 && ch <= 0x27ff)) {
					contains = true;
					break;
				}
			}
		}
		return contains;
		*/

//		// 若匹配正常字符
//		boolean isNormal = m.matches();
//
//		return isNormal == true ? false : true;// 正常字符,返回false表示不包含特殊字符
		return hasEmoji(str);

	}

	/**
	 * 验证是否为合规的固定电话
	 * 
	 * @param str 固定电话字符串
	 * @return
	 */
	public static boolean isFixlinePhone(String str) {
		// String regEx = "[0-9|\\-|,| ]*";
		String regEx = "0\\d{2,3}-\\d{5,9}|\\d{5,9}|";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);

		return m.matches();
	}
	
	/**
	 * 校验是否为客服电话
	 * @param str
	 * @return
	 */
	public static boolean isClientServiceNumber(String str){
		// String regEx = "[0-9|\\-|,| ]*";
		String regEx = "[\\d|\\-]*";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);

		return m.matches();
	}

	/**
	 * 校验是否为图片链接,
	 * 
	 * @param link 待校验的图片链接
	 * @return true,是; false,否
	 */
	public static boolean isValidPicLink(String link) {

		if (StringUtils.isEmpty(link))
			return false;

		if (link.endsWith(".jpeg") || link.endsWith(".png") || link.endsWith(".jpg"))
			return true;
		else
			return false;
	}

	/**
	 * 宽松校验是否为证件号码(身份证号,护照号码)
	 * 
	 * @param str
	 * @return true,是; false,否
	 */
	public static final boolean isIdentityCard(String str) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		// p = Pattern.compile("^(P\\d{7}|G\\d{8}|S\\d{7,8}|D\\d+|1[4,5]\\d{7})$"); // 正则表达式

		p = Pattern.compile("^([A-Z|a-z|\\d]{8,18})");// 8-18位的英文字母数字组合
		m = p.matcher(str);
		bool = m.matches();

		return bool;
	}
	
	/**
	 * 检测是否纯小写字母
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean islowerCase(String str) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("[a-z]+");
		m = p.matcher(str);
		bool = m.matches();

		return bool;
	}
	
	/**
	 * 检测是否纯大写字母
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isUpperCase(String str) {
		boolean bool = true;
		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("[A-Z]+");
		m = p.matcher(str);
		bool = m.matches();

		return bool;
	}

	// EMOJI 表情
	private static final Pattern EMOJI_PATTERN = Pattern.compile("(" +
			"\\ud83c\\uddec\\ud83c\\udde7|\\ud83c\\uddfa\\ud83c\\uddf8|\\ud83c\\udde9\\ud83c\\uddea|\\ud83c\\uddea\\ud83c\\uddf8|" +
					"\\ud83c\\uddeb\\ud83c\\uddf7|\\ud83c\\udde8\\ud83c\\uddf3|\\ud83c\\uddee\\ud83c\\uddf9|\\ud83c\\uddef\\ud83c\\uddf5|" +
					"\\ud83c\\uddf0\\ud83c\\uddf7|\\ud83c\\uddf7\\ud83c\\uddfa|\\ud83d\\udc7a|\\ud83c\\udd94|\\ud83c\\udd95|\\ud83c\\udd96|" +
					"\\ud83c\\udd97|\\ud83c\\udd98|\\ud83c\\udd99|\\ud83c\\udd9a|\\ud83c\\udde6|\\ud83c\\udde7|\\ud83d\\udc83|\\ud83c\\udde8|" +
					"\\ud83c\\udccf|\\ud83c\\udde9|\\ud83c\\udd70|\\ud83c\\uddea|\\ud83c\\udd71|\\ud83c\\uddeb|\\ud83c\\udd7e|\\ud83c\\uddec|" +
					"\\ud83c\\udded|\\ud83c\\uddee|\\ud83c\\udd8e|\\ud83c\\uddef|\\ud83c\\udd91|\\ud83c\\uddf0|\\ud83c\\uddf1|\\ud83c\\uddf2|" +
					"\\ud83c\\uddf3|\\ud83c\\uddf4|\\ud83c\\uddf5|\\ud83c\\uddf6|\\ud83c\\udd92|\\ud83c\\uddf7|\\ud83c\\uddf8|\\ud83c\\uddf9|" +
					"\\ud83c\\udd93|\\ud83c\\uddfa|\\ud83c\\uddfb|\\ud83c\\uddfc|\\ud83c\\uddfd|\\ud83c\\uddfe|\\ud83c\\uddff|\\ud83c\\ude01|" +
					"\\ud83c\\ude02|\\ud83c\\ude32|\\ud83c\\ude33|\\ud83c\\ude34|\\ud83c\\ude35|\\ud83c\\ude36|\\ud83c\\ude37|\\ud83c\\ude38|" +
					"\\ud83c\\ude39|\\ud83c\\ude3a|\\ud83c\\ude50|\\ud83c\\ude51|\\ud83c\\udf00|\\ud83c\\udf01|\\ud83c\\udf02|\\ud83c\\udf03|" +
					"\\ud83c\\udf04|\\ud83c\\udf05|\\ud83c\\udf06|\\ud83c\\udf07|\\ud83c\\udf08|\\ud83c\\udf09|\\ud83c\\udf0a|\\ud83c\\udf0b|" +
					"\\ud83c\\udf0c|\\ud83c\\udf0d|\\ud83c\\udf0e|\\ud83c\\udf0f|\\ud83c\\udf10|\\ud83c\\udf11|\\ud83c\\udf12|\\ud83c\\udf13|" +
					"\\ud83c\\udf14|\\ud83c\\udf15|\\ud83c\\udf16|\\ud83c\\udf17|\\ud83c\\udf18|\\ud83c\\udf19|\\ud83c\\udf1a|\\ud83c\\udf1b|" +
					"\\ud83c\\udf1c|\\ud83c\\udf1d|\\ud83c\\udf1e|\\ud83c\\udf1f|\\ud83c\\udf20|\\ud83c\\udf30|\\ud83c\\udf31|\\ud83c\\udf32|" +
					"\\ud83c\\udf33|\\ud83c\\udf34|\\ud83c\\udf35|\\ud83c\\udf37|\\ud83c\\udf38|\\ud83c\\udf39|\\ud83c\\udf3a|\\ud83c\\udf3b|" +
					"\\ud83c\\udf3c|\\ud83c\\udf3d|\\ud83c\\udf3e|\\ud83c\\udf3f|\\ud83c\\udf40|\\ud83c\\udf41|\\ud83c\\udf42|\\ud83c\\udf43|" +
					"\\ud83c\\udf44|\\ud83c\\udf45|\\ud83c\\udf46|\\ud83c\\udf47|\\ud83c\\udf48|\\ud83c\\udf49|\\ud83c\\udf4a|\\ud83c\\udf4b|" +
					"\\ud83c\\udf4c|\\ud83c\\udf4d|\\ud83c\\udf4e|\\ud83c\\udf4f|\\ud83c\\udf50|\\ud83c\\udf51|\\ud83c\\udf52|\\ud83c\\udf53|" +
					"\\ud83c\\udf54|\\ud83c\\udf55|\\ud83c\\udf56|\\ud83c\\udf57|\\ud83c\\udf58|\\ud83c\\udf59|\\ud83c\\udf5a|\\ud83c\\udf5b|" +
					"\\ud83c\\udf5c|\\ud83c\\udf5d|\\ud83c\\udf5e|\\ud83c\\udf5f|\\ud83c\\udf60|\\ud83c\\udf61|\\ud83c\\udf62|\\ud83c\\udf63|" +
					"\\ud83c\\udf64|\\ud83c\\udf65|\\ud83c\\udf66|\\ud83c\\udf67|\\ud83c\\udf68|\\ud83c\\udf69|\\ud83c\\udf6a|\\ud83c\\udf6b|" +
					"\\ud83c\\udf6c|\\ud83c\\udf6d|\\ud83c\\udf6e|\\ud83c\\udf6f|\\ud83c\\udf70|\\ud83c\\udf71|\\ud83c\\udf72|\\ud83c\\udf73|" +
					"\\ud83c\\udf74|\\ud83c\\udf75|\\ud83c\\udf76|\\ud83c\\udf77|\\ud83c\\udf78|\\ud83c\\udf79|\\ud83c\\udf7a|\\ud83c\\udf7b|" +
					"\\ud83c\\udf7c|\\ud83c\\udf80|\\ud83c\\udf81|\\ud83c\\udf82|\\ud83c\\udf83|\\ud83c\\udf84|\\ud83c\\udf85|\\ud83c\\udf86|" +
					"\\ud83c\\udf87|\\ud83c\\udf88|\\ud83c\\udf89|\\ud83c\\udf8a|\\ud83c\\udf8b|\\ud83c\\udf8c|\\ud83c\\udf8d|\\ud83c\\udf8e|" +
					"\\ud83c\\udf8f|\\ud83c\\udf90|\\ud83c\\udf91|\\ud83c\\udf92|\\ud83c\\udf93|\\ud83c\\udfa0|\\ud83c\\udfa1|\\ud83c\\udfa2|" +
					"\\ud83c\\udfa3|\\ud83c\\udfa4|\\ud83c\\udfa5|\\ud83c\\udfa6|\\ud83c\\udfa7|\\ud83c\\udfa8|\\ud83c\\udfa9|\\ud83c\\udfaa|" +
					"\\ud83c\\udfab|\\ud83c\\udfac|\\ud83c\\udfad|\\ud83c\\udfae|\\ud83c\\udfaf|\\ud83c\\udfb0|\\ud83c\\udfb1|\\ud83c\\udfb2|" +
					"\\ud83c\\udfb3|\\ud83c\\udfb4|\\ud83c\\udfb5|\\ud83c\\udfb6|\\ud83c\\udfb7|\\ud83c\\udfb8|\\ud83c\\udfb9|\\ud83c\\udfba|" +
					"\\ud83c\\udfbb|\\ud83c\\udfbc|\\ud83c\\udfbd|\\ud83c\\udfbe|\\ud83c\\udfbf|\\ud83c\\udfc0|\\ud83c\\udfc1|\\ud83c\\udfc2|" +
					"\\ud83c\\udfc3|\\ud83c\\udfc4|\\ud83c\\udfc6|\\ud83c\\udfc7|\\ud83c\\udfc8|\\ud83c\\udfc9|\\ud83c\\udfca|\\ud83c\\udfe0|" +
					"\\ud83c\\udfe1|\\ud83c\\udfe2|\\ud83c\\udfe3|\\ud83c\\udfe4|\\ud83c\\udfe5|\\ud83c\\udfe6|\\ud83c\\udfe7|\\ud83c\\udfe8|" +
					"\\ud83c\\udfe9|\\ud83c\\udfea|\\ud83c\\udfeb|\\ud83c\\udfec|\\ud83c\\udfed|\\ud83c\\udfee|\\ud83c\\udfef|\\ud83c\\udff0|" +
					"\\ud83d\\udc00|\\ud83d\\udc01|\\ud83d\\udc02|\\ud83d\\udc03|\\ud83d\\udc04|\\ud83d\\udc05|\\ud83d\\udc06|\\ud83d\\udc07|" +
					"\\ud83d\\udc08|\\ud83d\\udc09|\\ud83d\\udc0a|\\ud83d\\udc0b|\\ud83d\\udc0c|\\ud83d\\udc0d|\\ud83d\\udc0e|\\ud83d\\udc0f|" +
					"\\ud83d\\udc10|\\ud83d\\udc11|\\ud83d\\udc12|\\ud83d\\udc13|\\ud83d\\udc14|\\ud83d\\udc15|\\ud83d\\udc16|\\ud83d\\udc17|" +
					"\\ud83d\\udc18|\\ud83d\\udc19|\\ud83d\\udc1a|\\ud83d\\udc1b|\\ud83d\\udc1c|\\ud83d\\udc1d|\\ud83d\\udc1e|\\ud83d\\udc1f|" +
					"\\ud83d\\udc20|\\ud83d\\udc21|\\ud83d\\udc22|\\ud83d\\udc23|\\ud83d\\udc24|\\ud83d\\udc25|\\ud83d\\udc26|\\ud83d\\udc27|" +
					"\\ud83d\\udc28|\\ud83d\\udc29|\\ud83d\\udc2a|\\ud83d\\udc2b|\\ud83d\\udc2c|\\ud83d\\udc2d|\\ud83d\\udc2e|\\ud83d\\udc2f|" +
					"\\ud83d\\udc30|\\ud83d\\udc31|\\ud83d\\udc32|\\ud83d\\udc33|\\ud83d\\udc34|\\ud83d\\udc35|\\ud83d\\udc36|\\ud83d\\udc37|" +
					"\\ud83d\\udc38|\\ud83d\\udc39|\\ud83d\\udc3a|\\ud83d\\udc3b|\\ud83d\\udc3c|\\ud83d\\udc3d|\\ud83d\\udc3e|\\ud83d\\udc40|" +
					"\\ud83d\\udc42|\\ud83d\\udc43|\\ud83d\\udc44|\\ud83d\\udc45|\\ud83d\\udc46|\\ud83d\\udc47|\\ud83d\\udc48|\\ud83d\\udc49|" +
					"\\ud83d\\udc4a|\\ud83d\\udc4b|\\ud83d\\udc4c|\\ud83d\\udc4d|\\ud83d\\udc4e|\\ud83d\\udc4f|\\ud83d\\udc50|\\ud83d\\udc51|" +
					"\\ud83d\\udc52|\\ud83d\\udc53|\\ud83d\\udc54|\\ud83d\\udc55|\\ud83d\\udc56|\\ud83d\\udc57|\\ud83d\\udc58|\\ud83d\\udc59|" +
					"\\ud83d\\udc5a|\\ud83d\\udc5b|\\ud83d\\udc5c|\\ud83d\\udc5d|\\ud83d\\udc5e|\\ud83d\\udc5f|\\ud83d\\udc60|\\ud83d\\udc61|" +
					"\\ud83d\\udc62|\\ud83d\\udc63|\\ud83d\\udc64|\\ud83d\\udc65|\\ud83d\\udc66|\\ud83d\\udc67|\\ud83d\\udc68|\\ud83d\\udc69|" +
					"\\ud83d\\udc6a|\\ud83d\\udc6b|\\ud83d\\udc6c|\\ud83d\\udc6d|\\ud83d\\udc6e|\\ud83d\\udc6f|\\ud83d\\udc70|\\ud83d\\udc71|" +
					"\\ud83d\\udc72|\\ud83d\\udc73|\\ud83d\\udc74|\\ud83d\\udc75|\\ud83d\\udc76|\\ud83d\\udc77|\\ud83d\\udc78|\\ud83d\\udc79|" +
					"\\ud83d\\udc7b|\\ud83d\\udc7c|\\ud83d\\udc7d|\\ud83d\\udc7e|\\ud83d\\udc7f|\\ud83d\\udc80|\\ud83d\\udc81|\\ud83d\\udc82|" +
					"\\ud83d\\udc84|\\ud83d\\udc85|\\ud83d\\udc86|\\ud83d\\udc87|\\ud83d\\udc88|\\ud83d\\udc89|\\ud83d\\udc8a|\\ud83d\\udc8b|" +
					"\\ud83d\\udc8c|\\ud83d\\udc8d|\\ud83d\\udc8e|\\ud83d\\udc8f|\\ud83d\\udc90|\\ud83d\\udc91|\\ud83d\\udc92|\\ud83d\\udc93|" +
					"\\ud83d\\udc94|\\ud83d\\udc95|\\ud83d\\udc96|\\ud83d\\udc97|\\ud83d\\udc98|\\ud83d\\udc99|\\ud83d\\udc9a|\\ud83d\\udc9b|" +
					"\\ud83d\\udc9c|\\ud83d\\udc9d|\\ud83d\\udc9e|\\ud83d\\udc9f|\\ud83d\\udca0|\\ud83d\\udca1|\\ud83d\\udca2|\\ud83d\\udca3|" +
					"\\ud83d\\udca4|\\ud83d\\udca5|\\ud83d\\udca6|\\ud83d\\udca7|\\ud83d\\udca8|\\ud83d\\udca9|\\ud83d\\udcaa|\\ud83d\\udcab|" +
					"\\ud83d\\udcac|\\ud83d\\udcad|\\ud83d\\udcae|\\ud83d\\udcaf|\\ud83d\\udcb0|\\ud83d\\udcb1|\\ud83d\\udcb2|\\ud83d\\udcb3|" +
					"\\ud83d\\udcb4|\\ud83d\\udcb5|\\ud83d\\udcb6|\\ud83d\\udcb7|\\ud83d\\udcb8|\\ud83d\\udcb9|\\ud83d\\udcba|\\ud83d\\udcbb|" +
					"\\ud83d\\udcbc|\\ud83d\\udcbd|\\ud83d\\udcbe|\\ud83d\\udcbf|\\ud83d\\udcc0|\\ud83d\\udcc1|\\ud83d\\udcc2|\\ud83d\\udcc3|" +
					"\\ud83d\\udcc4|\\ud83d\\udcc5|\\ud83d\\udcc6|\\ud83d\\udcc7|\\ud83d\\udcc8|\\ud83d\\udcc9|\\ud83d\\udcca|\\ud83d\\udccb|" +
					"\\ud83d\\udccc|\\ud83d\\udccd|\\ud83d\\udcce|\\ud83d\\udccf|\\ud83d\\udcd0|\\ud83d\\udcd1|\\ud83d\\udcd2|\\ud83d\\udcd3|" +
					"\\ud83d\\udcd4|\\ud83d\\udcd5|\\ud83d\\udcd6|\\ud83d\\udcd7|\\ud83d\\udcd8|\\ud83d\\udcd9|\\ud83d\\udcda|\\ud83d\\udcdb|" +
					"\\ud83d\\udcdc|\\ud83d\\udcdd|\\ud83d\\udcde|\\ud83d\\udcdf|\\ud83d\\udce0|\\ud83d\\udce1|\\ud83d\\udce2|\\ud83d\\udce3|" +
					"\\ud83d\\udce4|\\ud83d\\udce5|\\ud83d\\udce6|\\ud83d\\udce7|\\ud83d\\udce8|\\ud83d\\udce9|\\ud83d\\udcea|\\ud83d\\udceb|" +
					"\\ud83d\\udcec|\\ud83d\\udced|\\ud83d\\udcee|\\ud83d\\udcef|\\ud83d\\udcf0|\\ud83d\\udcf1|\\ud83d\\udcf2|\\ud83d\\udcf3|" +
					"\\ud83d\\udcf4|\\ud83d\\udcf5|\\ud83d\\udcf6|\\ud83d\\udcf7|\\ud83d\\udcf9|\\ud83d\\udcfa|\\ud83d\\udcfb|\\ud83d\\udcfc|" +
					"\\ud83d\\udd00|\\ud83d\\udd01|\\ud83d\\udd02|\\ud83d\\udd03|\\ud83d\\udd04|\\ud83d\\udd05|\\ud83d\\udd06|\\ud83d\\udd07|" +
					"\\ud83d\\udd08|\\ud83d\\udd09|\\ud83d\\udd0a|\\ud83d\\udd0b|\\ud83d\\udd0c|\\ud83d\\udd0d|\\ud83d\\udd0e|\\ud83d\\udd0f|" +
					"\\ud83d\\udd10|\\ud83d\\udd11|\\ud83d\\udd12|\\ud83d\\udd13|\\ud83d\\udd14|\\ud83d\\udd15|\\ud83d\\udd16|\\ud83d\\udd17|" +
					"\\ud83d\\udd18|\\ud83d\\udd19|\\ud83d\\udd1a|\\ud83d\\udd1b|\\ud83d\\udd1c|\\ud83d\\udd1d|\\ud83d\\udd1e|\\ud83d\\udd1f|" +
					"\\ud83d\\udd20|\\ud83d\\udd21|\\ud83d\\udd22|\\ud83d\\udd23|\\ud83d\\udd24|\\ud83d\\udd25|\\ud83d\\udd26|\\ud83d\\udd27|" +
					"\\ud83d\\udd28|\\ud83d\\udd29|\\ud83d\\udd2a|\\ud83d\\udd2b|\\ud83d\\udd2c|\\ud83d\\udd2d|\\ud83d\\udd2e|\\ud83d\\udd2f|" +
					"\\ud83d\\udd30|\\ud83d\\udd31|\\ud83d\\udd32|\\ud83d\\udd33|\\ud83d\\udd34|\\ud83d\\udd35|\\ud83d\\udd36|\\ud83d\\udd37|" +
					"\\ud83d\\udd38|\\ud83d\\udd39|\\ud83d\\udd3a|\\ud83d\\udd3b|\\ud83d\\udd3c|\\ud83d\\udd3d|\\ud83d\\udd50|\\ud83d\\udd51|" +
					"\\ud83d\\udd52|\\ud83d\\udd53|\\ud83d\\udd54|\\ud83d\\udd55|\\ud83d\\udd56|\\ud83d\\udd57|\\ud83d\\udd58|\\ud83d\\udd59|" +
					"\\ud83d\\udd5a|\\ud83d\\udd5b|\\ud83d\\udd5c|\\ud83d\\udd5d|\\ud83d\\udd5e|\\ud83d\\udd5f|\\ud83d\\udd60|\\ud83d\\udd61|" +
					"\\ud83d\\udd62|\\ud83d\\udd63|\\ud83d\\udd64|\\ud83d\\udd65|\\ud83d\\udd66|\\ud83d\\udd67|\\ud83d\\uddfb|\\ud83d\\uddfc|" +
					"\\ud83d\\uddfd|\\ud83d\\uddfe|\\ud83d\\uddff|\\ud83d\\ude00|\\ud83d\\ude01|\\ud83d\\ude02|\\ud83d\\ude03|\\ud83d\\ude04|" +
					"\\ud83d\\ude05|\\ud83d\\ude06|\\ud83d\\ude07|\\ud83d\\ude08|\\ud83d\\ude09|\\ud83d\\ude0a|\\ud83d\\ude0b|\\ud83d\\ude0c|" +
					"\\ud83d\\ude0d|\\ud83d\\ude0e|\\ud83d\\ude0f|\\ud83d\\ude10|\\ud83d\\ude11|\\ud83d\\ude12|\\ud83d\\ude13|\\ud83d\\ude14|" +
					"\\ud83d\\ude15|\\ud83d\\ude16|\\ud83d\\ude17|\\ud83d\\ude18|\\ud83d\\ude19|\\ud83d\\ude1a|\\ud83d\\ude1b|\\ud83d\\ude1c|" +
					"\\ud83d\\ude1d|\\ud83d\\ude1e|\\ud83d\\ude1f|\\ud83d\\ude20|\\ud83d\\ude21|\\ud83d\\ude22|\\ud83d\\ude23|\\ud83d\\ude24|" +
					"\\ud83d\\ude25|\\ud83d\\ude26|\\ud83d\\ude27|\\ud83d\\ude28|\\ud83d\\ude29|\\ud83d\\ude2a|\\ud83d\\ude2b|\\ud83d\\ude2c|" +
					"\\ud83d\\ude2d|\\ud83d\\ude2e|\\ud83d\\ude2f|\\ud83d\\ude30|\\ud83d\\ude31|\\ud83d\\ude32|\\ud83d\\ude33|\\ud83d\\ude34|" +
					"\\ud83d\\ude35|\\ud83d\\ude36|\\ud83d\\ude37|\\ud83d\\ude38|\\ud83d\\ude39|\\ud83d\\ude3a|\\ud83d\\ude3b|\\ud83d\\ude3c|" +
					"\\ud83d\\ude3d|\\ud83d\\ude3e|\\ud83d\\ude3f|\\ud83d\\ude40|\\ud83d\\ude45|\\ud83d\\ude46|\\ud83d\\ude47|\\ud83d\\ude48|" +
					"\\ud83d\\ude49|\\ud83d\\ude4a|\\ud83d\\ude4b|\\ud83d\\ude4c|\\ud83d\\ude4d|\\ud83d\\ude4e|\\ud83d\\ude4f|\\ud83d\\ude80|" +
					"\\ud83d\\ude81|\\ud83d\\ude82|\\ud83d\\ude83|\\ud83d\\ude84|\\ud83d\\ude85|\\ud83d\\ude86|\\ud83d\\ude87|\\ud83d\\ude88|" +
					"\\ud83d\\ude89|\\ud83d\\ude8a|\\ud83d\\ude8b|\\ud83d\\ude8c|\\ud83d\\ude8d|\\ud83d\\ude8e|\\ud83d\\ude8f|\\ud83d\\ude90|" +
					"\\ud83d\\ude91|\\ud83d\\ude92|\\ud83d\\ude93|\\ud83d\\ude94|\\ud83d\\ude95|\\ud83d\\ude96|\\ud83d\\ude97|\\ud83d\\ude98|" +
					"\\ud83d\\ude99|\\ud83d\\ude9a|\\ud83d\\ude9b|\\ud83d\\ude9c|\\ud83d\\ude9d|\\ud83d\\ude9e|\\ud83d\\ude9f|\\ud83d\\udea0|" +
					"\\ud83d\\udea1|\\ud83d\\udea2|\\ud83d\\udea3|\\ud83d\\udea4|\\ud83d\\udea5|\\ud83d\\udea6|\\ud83d\\udea7|\\ud83d\\udea8|" +
					"\\ud83d\\udea9|\\ud83d\\udeaa|\\ud83d\\udeab|\\ud83d\\udeac|\\ud83d\\udead|\\ud83d\\udeae|\\ud83d\\udeaf|\\ud83d\\udeb0|" +
					"\\ud83d\\udeb1|\\ud83d\\udeb2|\\ud83d\\udeb3|\\ud83d\\udeb4|\\ud83d\\udeb5|\\ud83d\\udeb6|\\ud83d\\udeb7|\\ud83d\\udeb8|" +
					"\\ud83d\\udeb9|\\ud83d\\udeba|\\ud83d\\udebb|\\ud83d\\udebc|\\ud83d\\udebd|\\ud83d\\udebe|\\ud83d\\udebf|\\ud83d\\udec0|" +
					"\\ud83d\\udec1|\\ud83d\\udec2|\\ud83d\\udec3|\\ud83d\\udec4|\\ud83d\\udec5|\\u0023\\u20e3|\\u0030\\u20e3|\\u0031\\u20e3|" +
			"\\u0032\\u20e3|\\u0033\\u20e3|\\u0034\\u20e3|\\u0035\\u20e3|\\u0036\\u20e3|\\u0037\\u20e3|\\u0038\\u20e3|\\u0039\\u20e3|" +
			"\\u3030|\\u2705|\\u2728|\\u2122|\\u23e9|\\u23ea|\\u23eb|\\u23ec|\\u23f0|\\u23f3|\\u26ce|\\u270a|\\u270b|\\u274c|\\u274e|" +
			"\\u27b0|\\u27bf|\\u2753|\\u2754|\\u2755|\\u2795|\\u2796|\\u2797|\\u00a9|\\u00ae|\\ue50a|" +
			"\\ud83c\\udd7f|\\ud83c\\ude1a|\\ud83c\\ude2f|\\ud83c\\udc04|\\u2935|\\u3297|\\u3299|\\u2049|\\u2139|\\u2194|\\u2195|" +
			"\\u2196|\\u2197|\\u2198|\\u2199|\\u2600|\\u2601|\\u2611|\\u2614|\\u2615|\\u2648|\\u2649|\\u2650|\\u2651|\\u2652|\\u2653|" +
			"\\u2660|\\u2663|\\u2665|\\u2666|\\u2668|\\u2693|\\u2702|\\u2708|\\u2709|\\u2712|\\u2714|\\u2716|\\u2733|\\u2734|\\u203c|" +
			"\\u21a9|\\u21aa|\\u2744|\\u231a|\\u231b|\\u24c2|\\u25aa|\\u25ab|\\u25b6|\\u25c0|\\u25fb|\\u25fc|\\u25fd|\\u25fe|\\u260e|" +
			"\\u261d|\\u263a|\\u264a|\\u264b|\\u264c|\\u264d|\\u264e|\\u264f|\\u267b|\\u267f|\\u26a0|\\u26a1|\\u26aa|\\u26ab|\\u26bd|" +
			"\\u26be|\\u26c4|\\u26c5|\\u26d4|\\u26ea|\\u26f2|\\u26f3|\\u26f5|\\u26fa|\\u26fd|\\u270c|\\u270f|\\u27a1|\\u2b05|\\u2b06|" +
			"\\u2b07|\\u2b1b|\\u2b1c|\\u2b50|\\u2b55|\\u2747|\\u303d|\\u2757|\\u2764|\\u2934)+");

	/**
	 * 判断是否有 emoji 表情
	 * @param str 字符串
	 * @return 返回true=有emoji表情
	 * @author ten 2015/11/13
	 */
	public static boolean hasEmoji(String str) {
		// 参考：
		// http://stackoverflow.com/questions/24070515/rendering-or-deleting-emoji
		// http://stackoverflow.com/questions/24840667/what-is-the-regex-to-extract-all-the-emojis-from-a-string
		// http://blog.csdn.net/hherima/article/details/38961575
		Matcher m = EMOJI_PATTERN.matcher(str);
		boolean find =  m.find();

		return find;
	}

	public static void main(String[] args) {
		
		System.out.println("400号码 - 400-1234-123:" + isClientServiceNumber("400-1234-123"));
		System.out.println("400号码 - 4001234123:" + isClientServiceNumber("4001234123"));
		System.out.println("手机号码 - 13580480984:" + isClientServiceNumber("13580480984"));
		System.out.println("固话 - 86913245:" + isClientServiceNumber("86913245"));
		System.out.println("固话 - 020-86913245:" + isClientServiceNumber("020-86913245"));
		System.out.println("固话 - 0763-2512106:" + isClientServiceNumber("0763-2512106"));
		System.out.println("多个 - 0763-2512106,4001234123,400-1234-123,13580480984,86913245:" + isClientServiceNumber("0763-2512106,4001234123,400-1234-123,13580480984,86913245"));
//		
//		System.out.println("错误 - 带有英文:" + isClientServiceNumber("0763-2512106,4001234123,400-1234-123,13580a480984,86913245"));
//		System.out.println("错误 - 带有特殊字符:" + isClientServiceNumber("0763-2512106,4001234123,400-1234-123,13580&480984,86913245"));
		
		System.out.println("lower");
		
		System.out.println(islowerCase("abc"));
		System.out.println(islowerCase("abcA"));
		System.out.println(islowerCase("abc1"));
		System.out.println(islowerCase("abc&"));
		System.out.println(islowerCase(""));
		
		System.out.println("upper");
		
		System.out.println(isUpperCase("ABC"));
		System.out.println(isUpperCase("ABCa"));
		System.out.println(isUpperCase("ABC1"));
		System.out.println(isUpperCase("ABC&"));
		System.out.println(isUpperCase(""));
		// String phone = "18968233163";
		// System.out.println(isMobileNo(phone));

		// String input = "044-86999699";
		// System.out.println(isFixlinePhone(input));

		// String str = "Yans℃Kitchen' 中国人";
		// System.out.println(containsSpecialCharacter(str));

		// String testString = "020-86899022,020-86899022";
		// System.out.println(testString);
		// System.out.println(arePhones(testString));

		// String mobile = "1s326823316";

		// System.out.println(isMobileNo(mobile));

		// String passport = "12d";
		// System.out.println(isIdentityCard(passport));
		//
		//
		// String st = "";
		//
		// System.out.println(containsEmoji(st));
		//

//		String s = "[{\"title\":\"口味\",\"content\":\"榴莲（泰国）￥@/*/*/*?asd?？。78978&*（&……&%……￥……%￥##@@@@！！~~@@￥&&（））、——#%椰子（菲律宾）、淡奶油（法国）\\n甜度：\\n口感：绵软细腻\\n口味：乳脂果仁系列 榴莲系列\\n场景：生日 聚会 下午茶 节日庆典 茶歇餐会\\n送礼对象：小朋友 女朋友 男朋友 亲人 朋友 客户 领导\\n保鲜条件：0-4度保藏12小时，4小时食用为佳\\n适合节日：中秋节 情人节 母亲节 父亲节 教师节 感恩节 妇女节 儿童节 圣诞节\"}]";
		// String s = "Yans℃Kitchen' 中国人(）";
/*
		// s = s.replace("[", "").replace("]", "").replace("{", "").replace("}", "");

		s = "嗨？。asdfasdf*()*)(*(&&^*）{}|{''‘’；阿萨德【pl（%￥￥##+。@aAFD";

		System.out.println(containsEmoji(s));
		
		String testString = "傻傻是杀是是是";
		if(Validator.isEnglishWithSpace(testString) && testString.length() > 14){
			System.out.println("收货人名称长度不合法1");
		}else if (!Validator.isEnglishWithSpace(testString) && testString.length() > 7) {
			System.out.println("收货人名称长度不合法2");
		}

		String emoji = "1你好" + (char) 55357 + (char)56834 + (char) 55357 + (char) 56679;
		System.out.println("emoji: [" + emoji + "], " + containsEmoji(emoji));
		System.out.println(StringEscapeUtils.unescapeJava(emoji));
		System.out.println("[" + StringEscapeUtils.escapeJava("\\u1F64F") + "]");

		System.out.println(hasEmoji(emoji));
*/
//		String str = "你好\uD83D\uDCBE\uD83D\uDCBF";
////		String unescaped = StringEscapeUtils.unescapeJava(str);
//		System.out.println("EMOJI: " + StringEscapeUtils.escapeJava(str) + ": " + hasEmoji(str));
//		System.out.println("EMOJI: \\u23ec: " + hasEmoji("\u23ec"));
//		System.out.println("EMOJI: \\u2757: " + hasEmoji("\u2757"));
//		System.out.println("EMOJI: \\ud83c\\ude1a: " + hasEmoji("\ud83c\ude1a"));
//		System.out.println("EMOJI: \\u0034\\u20e3: " + hasEmoji("\u0034\u20e3"));
//		System.out.println("EMOJI: hg: " + hasEmoji("hg"));
//
//		System.out.println("EMOJI: 400g: " + hasEmoji("400g"));
//		System.out.println("EMOJI: 500g: " + hasEmoji("500g"));
//		System.out.println("EMOJI: 100g: " + hasEmoji("100g"));
//		System.out.println("EMOJI: 2: " + hasEmoji("2"));
//		System.out.println("EMOJI: 。、￥*#@；‘’“”【】｛｝，,'\"\"：:;<>|《》-=_()（）" + hasEmoji("。、￥*#@；‘’“”【】｛｝，,'\"\"：:;<>|《》-=_()（）"));
//		
//		
//		
//		System.out.println("Phone:" + isFixlinePhone("02220-86812630"));
		
	}

}
