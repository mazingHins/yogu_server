/**
 * 
 */
package com.yogu.commons.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * 
 * JFan 2014年12月10日 下午12:33:35
 */
public final class StringUtils {

	private StringUtils() {
	}

	/**
	 * The empty String {@code ""}.
	 * 
	 * @since 2.0
	 */
	public static final String EMPTY = "";

	/**
	 * 将str字符串，根据以一个ch的位置进行分割成两端<br>
	 * 如果指定的字符串没有出现，则返回只包含原字符串的数组（length = 1）
	 */
	public static String[] subString(String str, String ch) {
		int indexOf = str.indexOf(ch);
		if (-1 >= indexOf)
			return new String[] { str };

		String str1 = str.substring(0, indexOf);
		String str2 = str.substring(indexOf + ch.length());
		return new String[] { str1, str2 };
	}

	/**
	 * 将字符串数组，进行驼峰化之后返回<br>
	 * 数组中空的字符（isBlank）会忽略
	 */
	public static String humpCapitalize(String[] strs) {
		if (null == strs)
			return null;

		StringBuilder sb = new StringBuilder();
		boolean isOne = true;
		for (String str : strs) {
			String s = trimToNull(str);
			if (null == s)
				continue;

			if (isOne)
				sb.append(s);
			else {
				boolean isOne2 = true;
				for (char ch : s.toCharArray()) {
					if (isOne2)
						sb.append(Character.toUpperCase(ch));
					else
						sb.append(ch);
					isOne2 = false;
				}
			}

			isOne = false;
		}
		return sb.toString();
	}

	// Empty checks
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if a CharSequence is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the CharSequence. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is empty or null
	 * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
	 */
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty ("") and not null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null
	 * @since 3.0 Changed signature from isNotEmpty(String) to isNotEmpty(CharSequence)
	 */
	public static boolean isNotEmpty(final CharSequence cs) {
		return !StringUtils.isEmpty(cs);
	}

	/**
	 * <p>
	 * Checks if any one of the CharSequences are empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAnyEmpty(null)             = true
	 * StringUtils.isAnyEmpty(null, "foo")      = true
	 * StringUtils.isAnyEmpty("", "bar")        = true
	 * StringUtils.isAnyEmpty("bob", "")        = true
	 * StringUtils.isAnyEmpty("  bob  ", null)  = true
	 * StringUtils.isAnyEmpty(" ", "bar")       = false
	 * StringUtils.isAnyEmpty("foo", "bar")     = false
	 * </pre>
	 * 
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if any of the CharSequences are empty or null
	 * @since 3.2
	 */
	public static boolean isAnyEmpty(CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return true;
		}
		for (CharSequence cs : css) {
			if (isEmpty(cs)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Checks if none of the CharSequences are empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNoneEmpty(null)             = false
	 * StringUtils.isNoneEmpty(null, "foo")      = false
	 * StringUtils.isNoneEmpty("", "bar")        = false
	 * StringUtils.isNoneEmpty("bob", "")        = false
	 * StringUtils.isNoneEmpty("  bob  ", null)  = false
	 * StringUtils.isNoneEmpty(" ", "bar")       = true
	 * StringUtils.isNoneEmpty("foo", "bar")     = true
	 * </pre>
	 * 
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if none of the CharSequences are empty or null
	 * @since 3.2
	 */
	public static boolean isNoneEmpty(CharSequence... css) {
		return !isAnyEmpty(css);
	}

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
	 */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty (""), not null and not whitespace only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null and not whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
	 */
	public static boolean isNotBlank(final CharSequence cs) {
		return !StringUtils.isBlank(cs);
	}

	/**
	 * <p>
	 * Checks if any one of the CharSequences are blank ("") or null and not whitespace only..
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isAnyBlank(null)             = true
	 * StringUtils.isAnyBlank(null, "foo")      = true
	 * StringUtils.isAnyBlank(null, null)       = true
	 * StringUtils.isAnyBlank("", "bar")        = true
	 * StringUtils.isAnyBlank("bob", "")        = true
	 * StringUtils.isAnyBlank("  bob  ", null)  = true
	 * StringUtils.isAnyBlank(" ", "bar")       = true
	 * StringUtils.isAnyBlank("foo", "bar")     = false
	 * </pre>
	 * 
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if any of the CharSequences are blank or null or whitespace only
	 * @since 3.2
	 */
	public static boolean isAnyBlank(CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return true;
		}
		for (CharSequence cs : css) {
			if (isBlank(cs)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Checks if none of the CharSequences are blank ("") or null and whitespace only..
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNoneBlank(null)             = false
	 * StringUtils.isNoneBlank(null, "foo")      = false
	 * StringUtils.isNoneBlank(null, null)       = false
	 * StringUtils.isNoneBlank("", "bar")        = false
	 * StringUtils.isNoneBlank("bob", "")        = false
	 * StringUtils.isNoneBlank("  bob  ", null)  = false
	 * StringUtils.isNoneBlank(" ", "bar")       = false
	 * StringUtils.isNoneBlank("foo", "bar")     = true
	 * </pre>
	 * 
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if none of the CharSequences are blank or null or whitespace only
	 * @since 3.2
	 */
	public static boolean isNoneBlank(CharSequence... css) {
		return !isAnyBlank(css);
	}

	// Trim
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String, handling {@code null} by returning {@code null}.
	 * </p>
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip whitespace use {@link #strip(String)}.
	 * </p>
	 * 
	 * <p>
	 * To trim your choice of characters, use the {@link #strip(String, String)} methods.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed string, {@code null} if null String input
	 */
	public static String trim(final String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String returning {@code null} if the String is empty ("") after the trim or if it is {@code null}.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip whitespace use {@link #stripToNull(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToNull(null)          = null
	 * StringUtils.trimToNull("")            = null
	 * StringUtils.trimToNull("     ")       = null
	 * StringUtils.trimToNull("abc")         = "abc"
	 * StringUtils.trimToNull("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed String, {@code null} if only chars &lt;= 32, empty or null String input
	 * @since 2.0
	 */
	public static String trimToNull(final String str) {
		final String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	public static String trimToNullDef(final String str, final String def) {
		final String ts = trim(str);
		return isEmpty(ts) ? def : ts;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String returning an empty String ("") if the String is empty ("") after the trim or if it is {@code null}.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip whitespace use {@link #stripToEmpty(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = ""
	 * StringUtils.trimToEmpty("")            = ""
	 * StringUtils.trimToEmpty("     ")       = ""
	 * StringUtils.trimToEmpty("abc")         = "abc"
	 * StringUtils.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed String, or an empty String if {@code null} input
	 * @since 2.0
	 */
	public static String trimToEmpty(final String str) {
		return str == null ? EMPTY : str.trim();
	}

	public static String trimToEmptyDef(final String str, final String def) {
		final String ts = (str == null ? EMPTY : str.trim());
		return (EMPTY.equals(ts) ? def : ts);
	}

	// Left/Right/Mid
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Gets the leftmost {@code len} characters of a String.
	 * </p>
	 * 
	 * <p>
	 * If {@code len} characters are not available, or the String is {@code null}, the String will be returned without an exception. An empty String is returned if len is negative.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.left(null, *)    = null
	 * StringUtils.left(*, -ve)     = ""
	 * StringUtils.left("", *)      = ""
	 * StringUtils.left("abc", 0)   = ""
	 * StringUtils.left("abc", 2)   = "ab"
	 * StringUtils.left("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str the String to get the leftmost characters from, may be null
	 * @param len the length of the required String
	 * @return the leftmost characters, {@code null} if null String input
	 */
	public static String left(final String str, final int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}

	/**
	 * <p>
	 * Gets the rightmost {@code len} characters of a String.
	 * </p>
	 * 
	 * <p>
	 * If {@code len} characters are not available, or the String is {@code null}, the String will be returned without an an exception. An empty String is returned if len is negative.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.right(null, *)    = null
	 * StringUtils.right(*, -ve)     = ""
	 * StringUtils.right("", *)      = ""
	 * StringUtils.right("abc", 0)   = ""
	 * StringUtils.right("abc", 2)   = "bc"
	 * StringUtils.right("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str the String to get the rightmost characters from, may be null
	 * @param len the length of the required String
	 * @return the rightmost characters, {@code null} if null String input
	 */
	public static String right(final String str, final int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}

	/**
	 * <p>
	 * Gets {@code len} characters from the middle of a String.
	 * </p>
	 * 
	 * <p>
	 * If {@code len} characters are not available, the remainder of the String will be returned without an exception. If the String is {@code null}, {@code null} will be returned. An empty String is
	 * returned if len is negative or exceeds the length of {@code str}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.mid(null, *, *)    = null
	 * StringUtils.mid(*, *, -ve)     = ""
	 * StringUtils.mid("", 0, *)      = ""
	 * StringUtils.mid("abc", 0, 2)   = "ab"
	 * StringUtils.mid("abc", 0, 4)   = "abc"
	 * StringUtils.mid("abc", 2, 4)   = "c"
	 * StringUtils.mid("abc", 4, 2)   = ""
	 * StringUtils.mid("abc", -2, 2)  = "ab"
	 * </pre>
	 * 
	 * @param str the String to get the characters from, may be null
	 * @param pos the position to start from, negative treated as zero
	 * @param len the length of the required String
	 * @return the middle characters, {@code null} if null String input
	 */
	public static String mid(final String str, int pos, final int len) {
		if (str == null) {
			return null;
		}
		if (len < 0 || pos > str.length()) {
			return EMPTY;
		}
		if (pos < 0) {
			pos = 0;
		}
		if (str.length() <= pos + len) {
			return str.substring(pos);
		}
		return str.substring(pos, pos + len);
	}

	/**
	 * <p>
	 * Checks if the CharSequence contains only Unicode digits. A decimal point is not a Unicode digit and returns false.
	 * </p>
	 * 
	 * <p>
	 * {@code null} will return {@code false}. An empty CharSequence (length()=0) will return {@code false}.
	 * </p>
	 * 
	 * <p>
	 * Note that the method does not allow for a leading sign, either positive or negative. Also, if a String passes the numeric test, it may still generate a NumberFormatException when parsed by
	 * Integer.parseInt or Long.parseLong, e.g. if the value is outside the range for int or long respectively.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNumeric(null)   = false
	 * StringUtils.isNumeric("")     = false
	 * StringUtils.isNumeric("  ")   = false
	 * StringUtils.isNumeric("123")  = true
	 * StringUtils.isNumeric("12 3") = false
	 * StringUtils.isNumeric("ab2c") = false
	 * StringUtils.isNumeric("12-3") = false
	 * StringUtils.isNumeric("12.3") = false
	 * StringUtils.isNumeric("-123") = false
	 * StringUtils.isNumeric("+123") = false
	 * </pre>
	 * 
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if only contains digits, and is non-null
	 * @since 3.0 Changed signature from isNumeric(String) to isNumeric(CharSequence)
	 * @since 3.0 Changed "" to return false and not true
	 */
	public static boolean isNumeric(final CharSequence cs) {
		if (isEmpty(cs)) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the CharSequence contains only Unicode digits or space ({@code ' '}). A decimal point is not a Unicode digit and returns false.
	 * </p>
	 * 
	 * <p>
	 * {@code null} will return {@code false}. An empty CharSequence (length()=0) will return {@code true}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNumericSpace(null)   = false
	 * StringUtils.isNumericSpace("")     = true
	 * StringUtils.isNumericSpace("  ")   = true
	 * StringUtils.isNumericSpace("123")  = true
	 * StringUtils.isNumericSpace("12 3") = true
	 * StringUtils.isNumericSpace("ab2c") = false
	 * StringUtils.isNumericSpace("12-3") = false
	 * StringUtils.isNumericSpace("12.3") = false
	 * </pre>
	 * 
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if only contains digits or space, and is non-null
	 * @since 3.0 Changed signature from isNumericSpace(String) to isNumericSpace(CharSequence)
	 */
	public static boolean isNumericSpace(final CharSequence cs) {
		if (cs == null) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false && cs.charAt(i) != ' ') {
				return false;
			}
		}
		return true;
	}

    /**
     * <p>
     * Check if a CharSequence starts with a specified prefix.
     * </p>
     * 
     * <p>
     * {@code null}s are handled without exceptions. Two {@code null} references are considered to be equal. The
     * comparison is case sensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.startsWith(null, null)      = true
     * StringUtils.startsWith(null, "abc")     = false
     * StringUtils.startsWith("abcdef", null)  = false
     * StringUtils.startsWith("abcdef", "abc") = true
     * StringUtils.startsWith("ABCDEF", "abc") = false
     * </pre>
     * 
     * @see java.lang.String#startsWith(String)
     * @param str the CharSequence to check, may be null
     * @param prefix the prefix to find, may be null
     * @return {@code true} if the CharSequence starts with the prefix, case sensitive, or both {@code null}
     * @since 2.4
     * @since 3.0 Changed signature from startsWith(String, String) to startsWith(CharSequence, CharSequence)
     */
    public static boolean startsWith(final CharSequence str, final CharSequence prefix) {
        return startsWith(str, prefix, false);
    }

    /**
     * <p>
     * Case insensitive check if a CharSequence starts with a specified prefix.
     * </p>
     * 
     * <p>
     * {@code null}s are handled without exceptions. Two {@code null} references are considered to be equal. The
     * comparison is case insensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.startsWithIgnoreCase(null, null)      = true
     * StringUtils.startsWithIgnoreCase(null, "abc")     = false
     * StringUtils.startsWithIgnoreCase("abcdef", null)  = false
     * StringUtils.startsWithIgnoreCase("abcdef", "abc") = true
     * StringUtils.startsWithIgnoreCase("ABCDEF", "abc") = true
     * </pre>
     * 
     * @see java.lang.String#startsWith(String)
     * @param str the CharSequence to check, may be null
     * @param prefix the prefix to find, may be null
     * @return {@code true} if the CharSequence starts with the prefix, case insensitive, or both {@code null}
     * @since 2.4
     * @since 3.0 Changed signature from startsWithIgnoreCase(String, String) to startsWithIgnoreCase(CharSequence,
     *        CharSequence)
     */
    public static boolean startsWithIgnoreCase(final CharSequence str, final CharSequence prefix) {
        return startsWith(str, prefix, true);
    }

    /**
     * <p>
     * Check if a CharSequence starts with a specified prefix (optionally case insensitive).
     * </p>
     * 
     * @see java.lang.String#startsWith(String)
     * @param str the CharSequence to check, may be null
     * @param prefix the prefix to find, may be null
     * @param ignoreCase indicates whether the compare should ignore case (case insensitive) or not.
     * @return {@code true} if the CharSequence starts with the prefix or both {@code null}
     */
    private static boolean startsWith(final CharSequence str, final CharSequence prefix, final boolean ignoreCase) {
        if (str == null || prefix == null) {
            return str == null && prefix == null;
        }
        if (prefix.length() > str.length()) {
            return false;
        }
        return CharSequenceUtils.regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length());
    }

    /**
     * <p>
     * Check if a CharSequence starts with any of an array of specified strings.
     * </p>
     * 
     * <pre>
     * StringUtils.startsWithAny(null, null)      = false
     * StringUtils.startsWithAny(null, new String[] {"abc"})  = false
     * StringUtils.startsWithAny("abcxyz", null)     = false
     * StringUtils.startsWithAny("abcxyz", new String[] {""}) = false
     * StringUtils.startsWithAny("abcxyz", new String[] {"abc"}) = true
     * StringUtils.startsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
     * </pre>
     * 
     * @param string the CharSequence to check, may be null
     * @param searchStrings the CharSequences to find, may be null or empty
     * @return {@code true} if the CharSequence starts with any of the the prefixes, case insensitive, or both
     *         {@code null}
     * @since 2.5
     * @since 3.0 Changed signature from startsWithAny(String, String[]) to startsWithAny(CharSequence, CharSequence...)
     */
    public static boolean startsWithAny(final CharSequence string, final CharSequence... searchStrings) {
        if (isEmpty(string) || ArrayUtils.isEmpty(searchStrings)) {
            return false;
        }
        for (final CharSequence searchString : searchStrings) {
            if (StringUtils.startsWith(string, searchString)) {
                return true;
            }
        }
        return false;
    }

    // endsWith
    // -----------------------------------------------------------------------

    /**
     * <p>
     * Check if a CharSequence ends with a specified suffix.
     * </p>
     * 
     * <p>
     * {@code null}s are handled without exceptions. Two {@code null} references are considered to be equal. The
     * comparison is case sensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.endsWith(null, null)      = true
     * StringUtils.endsWith(null, "def")     = false
     * StringUtils.endsWith("abcdef", null)  = false
     * StringUtils.endsWith("abcdef", "def") = true
     * StringUtils.endsWith("ABCDEF", "def") = false
     * StringUtils.endsWith("ABCDEF", "cde") = false
     * </pre>
     * 
     * @see java.lang.String#endsWith(String)
     * @param str the CharSequence to check, may be null
     * @param suffix the suffix to find, may be null
     * @return {@code true} if the CharSequence ends with the suffix, case sensitive, or both {@code null}
     * @since 2.4
     * @since 3.0 Changed signature from endsWith(String, String) to endsWith(CharSequence, CharSequence)
     */
    public static boolean endsWith(final CharSequence str, final CharSequence suffix) {
        return endsWith(str, suffix, false);
    }

    /**
     * <p>
     * Case insensitive check if a CharSequence ends with a specified suffix.
     * </p>
     * 
     * <p>
     * {@code null}s are handled without exceptions. Two {@code null} references are considered to be equal. The
     * comparison is case insensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.endsWithIgnoreCase(null, null)      = true
     * StringUtils.endsWithIgnoreCase(null, "def")     = false
     * StringUtils.endsWithIgnoreCase("abcdef", null)  = false
     * StringUtils.endsWithIgnoreCase("abcdef", "def") = true
     * StringUtils.endsWithIgnoreCase("ABCDEF", "def") = true
     * StringUtils.endsWithIgnoreCase("ABCDEF", "cde") = false
     * </pre>
     * 
     * @see java.lang.String#endsWith(String)
     * @param str the CharSequence to check, may be null
     * @param suffix the suffix to find, may be null
     * @return {@code true} if the CharSequence ends with the suffix, case insensitive, or both {@code null}
     * @since 2.4
     * @since 3.0 Changed signature from endsWithIgnoreCase(String, String) to endsWithIgnoreCase(CharSequence,
     *        CharSequence)
     */
    public static boolean endsWithIgnoreCase(final CharSequence str, final CharSequence suffix) {
        return endsWith(str, suffix, true);
    }

    /**
     * <p>
     * Check if a CharSequence ends with a specified suffix (optionally case insensitive).
     * </p>
     * 
     * @see java.lang.String#endsWith(String)
     * @param str the CharSequence to check, may be null
     * @param suffix the suffix to find, may be null
     * @param ignoreCase indicates whether the compare should ignore case (case insensitive) or not.
     * @return {@code true} if the CharSequence starts with the prefix or both {@code null}
     */
    private static boolean endsWith(final CharSequence str, final CharSequence suffix, final boolean ignoreCase) {
        if (str == null || suffix == null) {
            return str == null && suffix == null;
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        final int strOffset = str.length() - suffix.length();
        return CharSequenceUtils.regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
    }

    // Contains
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if CharSequence contains a search character, handling {@code null}. This method uses
     * {@link String#indexOf(int)} if possible.
     * </p>
     * 
     * <p>
     * A {@code null} or empty ("") CharSequence will return {@code false}.
     * </p>
     * 
     * <pre>
     * StringUtils.contains(null, *)    = false
     * StringUtils.contains("", *)      = false
     * StringUtils.contains("abc", 'a') = true
     * StringUtils.contains("abc", 'z') = false
     * </pre>
     * 
     * @param seq the CharSequence to check, may be null
     * @param searchChar the character to find
     * @return true if the CharSequence contains the search character, false if not or {@code null} string input
     * @since 2.0
     * @since 3.0 Changed signature from contains(String, int) to contains(CharSequence, int)
     */
    public static boolean contains(final CharSequence seq, final int searchChar) {
        if (isEmpty(seq)) {
            return false;
        }
        return CharSequenceUtils.indexOf(seq, searchChar, 0) >= 0;
    }

    /**
     * <p>
     * Checks if CharSequence contains a search CharSequence, handling {@code null}. This method uses
     * {@link String#indexOf(String)} if possible.
     * </p>
     * 
     * <p>
     * A {@code null} CharSequence will return {@code false}.
     * </p>
     * 
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
     * 
     * @param seq the CharSequence to check, may be null
     * @param searchSeq the CharSequence to find, may be null
     * @return true if the CharSequence contains the search CharSequence, false if not or {@code null} string input
     * @since 2.0
     * @since 3.0 Changed signature from contains(String, String) to contains(CharSequence, CharSequence)
     */
    public static boolean contains(final CharSequence seq, final CharSequence searchSeq) {
        if (seq == null || searchSeq == null) {
            return false;
        }
        return CharSequenceUtils.indexOf(seq, searchSeq, 0) >= 0;
    }

    /**
     * <p>
     * Checks if CharSequence contains a search CharSequence irrespective of case, handling {@code null}.
     * Case-insensitivity is defined as by {@link String#equalsIgnoreCase(String)}.
     * 
     * <p>
     * A {@code null} CharSequence will return {@code false}.
     * </p>
     * 
     * <pre>
     * StringUtils.contains(null, *) = false
     * StringUtils.contains(*, null) = false
     * StringUtils.contains("", "") = true
     * StringUtils.contains("abc", "") = true
     * StringUtils.contains("abc", "a") = true
     * StringUtils.contains("abc", "z") = false
     * StringUtils.contains("abc", "A") = true
     * StringUtils.contains("abc", "Z") = false
     * </pre>
     * 
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @return true if the CharSequence contains the search CharSequence irrespective of case or false if not or
     *         {@code null} string input
     * @since 3.0 Changed signature from containsIgnoreCase(String, String) to containsIgnoreCase(CharSequence,
     *        CharSequence)
     */
    public static boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        final int len = searchStr.length();
        final int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given CharSequence contains any whitespace characters.
     * 
     * @param seq the CharSequence to check (may be {@code null})
     * @return {@code true} if the CharSequence is not empty and contains at least 1 whitespace character
     * @see java.lang.Character#isWhitespace
     * @since 3.0
     */
    // From org.springframework.util.StringUtils, under Apache License 2.0
    public static boolean containsWhitespace(final CharSequence seq) {
        if (isEmpty(seq)) {
            return false;
        }
        final int strLen = seq.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(seq.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // Equals
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Compares two CharSequences, returning {@code true} if they represent equal sequences of characters.
     * </p>
     * 
     * <p>
     * {@code null}s are handled without exceptions. Two {@code null} references are considered to be equal. The
     * comparison is case sensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     * </pre>
     * 
     * @see Object#equals(Object)
     * @param cs1 the first CharSequence, may be {@code null}
     * @param cs2 the second CharSequence, may be {@code null}
     * @return {@code true} if the CharSequences are equal (case-sensitive), or both {@code null}
     * @since 3.0 Changed signature from equals(String, String) to equals(CharSequence, CharSequence)
     */
    public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        return CharSequenceUtils.regionMatches(cs1, false, 0, cs2, 0, Math.max(cs1.length(), cs2.length()));
    }

    /**
     * <p>
     * Compares two CharSequences, returning {@code true} if they represent equal sequences of characters, ignoring
     * case.
     * </p>
     * 
     * <p>
     * {@code null}s are handled without exceptions. Two {@code null} references are considered equal. Comparison is
     * case insensitive.
     * </p>
     * 
     * <pre>
     * StringUtils.equalsIgnoreCase(null, null)   = true
     * StringUtils.equalsIgnoreCase(null, "abc")  = false
     * StringUtils.equalsIgnoreCase("abc", null)  = false
     * StringUtils.equalsIgnoreCase("abc", "abc") = true
     * StringUtils.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     * 
     * @param str1 the first CharSequence, may be null
     * @param str2 the second CharSequence, may be null
     * @return {@code true} if the CharSequence are equal, case insensitive, or both {@code null}
     * @since 3.0 Changed signature from equalsIgnoreCase(String, String) to equalsIgnoreCase(CharSequence,
     *        CharSequence)
     */
    public static boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2) {
        if (str1 == null || str2 == null) {
            return str1 == str2;
        } else if (str1 == str2) {
            return true;
        } else if (str1.length() != str2.length()) {
            return false;
        } else {
            return CharSequenceUtils.regionMatches(str1, true, 0, str2, 0, str1.length());
        }
    }

    // Splitting
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Splits the provided text into an array, using whitespace as the separator. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
     * more control over the split use the StrTokenizer class.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null)       = null
     * StringUtils.split("")         = []
     * StringUtils.split("abc def")  = ["abc", "def"]
     * StringUtils.split("abc  def") = ["abc", "def"]
     * StringUtils.split(" abc ")    = ["abc"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @return an array of parsed Strings, {@code null} if null String input
     */
    public static String[] split(final String str) {
        return split(str, null, -1);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator specified. This is an alternative to using StringTokenizer.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
     * more control over the split use the StrTokenizer class.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("a.b.c", '.')    = ["a", "b", "c"]
     * StringUtils.split("a..b.c", '.')   = ["a", "b", "c"]
     * StringUtils.split("a:b:c", '.')    = ["a:b:c"]
     * StringUtils.split("a b c", ' ')    = ["a", "b", "c"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separatorChar the character used as the delimiter
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.0
     */
    public static String[] split(final String str, final char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    /**
     * <p>
     * Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
     * more control over the split use the StrTokenizer class.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separatorChars splits on whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("abc def", null) = ["abc", "def"]
     * StringUtils.split("abc def", " ")  = ["abc", "def"]
     * StringUtils.split("abc  def", " ") = ["abc", "def"]
     * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separatorChars the characters used as the delimiters, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String input
     */
    public static String[] split(final String str, final String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    /**
     * <p>
     * Splits the provided text into an array with a maximum length, separators specified.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as one separator.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separatorChars splits on whitespace.
     * </p>
     * 
     * <p>
     * If more than {@code max} delimited substrings are found, the last returned string includes all characters after
     * the first {@code max - 1} returned strings (including separator characters).
     * </p>
     * 
     * <pre>
     * StringUtils.split(null, *, *)            = null
     * StringUtils.split("", *, *)              = []
     * StringUtils.split("ab cd ef", null, 0)   = ["ab", "cd", "ef"]
     * StringUtils.split("ab   cd ef", null, 0) = ["ab", "cd", "ef"]
     * StringUtils.split("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
     * StringUtils.split("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separatorChars the characters used as the delimiters, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit
     * @return an array of parsed Strings, {@code null} if null String input
     */
    public static String[] split(final String str, final String separatorChars, final int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator string specified.
     * </p>
     * 
     * <p>
     * The separator(s) will not be included in the returned String array. Adjacent separators are treated as one
     * separator.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separator splits on whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.splitByWholeSeparator(null, *)               = null
     * StringUtils.splitByWholeSeparator("", *)                 = []
     * StringUtils.splitByWholeSeparator("ab de fg", null)      = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparator("ab   de fg", null)    = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparator("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
     * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String was input
     */
    public static String[] splitByWholeSeparator(final String str, final String separator) {
        return splitByWholeSeparatorWorker(str, separator, -1, false);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator string specified. Returns a maximum of {@code max} substrings.
     * </p>
     * 
     * <p>
     * The separator(s) will not be included in the returned String array. Adjacent separators are treated as one
     * separator.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separator splits on whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.splitByWholeSeparator(null, *, *)               = null
     * StringUtils.splitByWholeSeparator("", *, *)                 = []
     * StringUtils.splitByWholeSeparator("ab de fg", null, 0)      = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparator("ab   de fg", null, 0)    = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparator("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
     * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
     * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the returned array. A zero or negative value implies no
     *            limit.
     * @return an array of parsed Strings, {@code null} if null String was input
     */
    public static String[] splitByWholeSeparator(final String str, final String separator, final int max) {
        return splitByWholeSeparatorWorker(str, separator, max, false);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator string specified.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separator splits on whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.splitByWholeSeparatorPreserveAllTokens(null, *)               = null
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("", *)                 = []
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null)      = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null)    = ["ab", "", "", "de", "fg"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String was input
     * @since 2.4
     */
    public static String[] splitByWholeSeparatorPreserveAllTokens(final String str, final String separator) {
        return splitByWholeSeparatorWorker(str, separator, -1, true);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator string specified. Returns a maximum of {@code max} substrings.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separator splits on whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.splitByWholeSeparatorPreserveAllTokens(null, *, *)               = null
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("", *, *)                 = []
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null, 0)      = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null, 0)    = ["ab", "", "", "de", "fg"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
     * </pre>
     * 
     * @param str the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the returned array. A zero or negative value implies no
     *            limit.
     * @return an array of parsed Strings, {@code null} if null String was input
     * @since 2.4
     */
    public static String[] splitByWholeSeparatorPreserveAllTokens(final String str, final String separator, final int max) {
        return splitByWholeSeparatorWorker(str, separator, max, true);
    }

    /**
     * Performs the logic for the {@code splitByWholeSeparatorPreserveAllTokens} methods.
     * 
     * @param str the String to parse, may be {@code null}
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the returned array. A zero or negative value implies no
     *            limit.
     * @param preserveAllTokens if {@code true}, adjacent separators are treated as empty token separators; if
     *            {@code false}, adjacent separators are treated as one separator.
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.4
     */
    private static String[] splitByWholeSeparatorWorker(final String str, final String separator, final int max, final boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }

        final int len = str.length();

        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        if (separator == null || EMPTY.equals(separator)) {
            // Split on whitespace.
            return splitWorker(str, null, max, preserveAllTokens);
        }

        final int separatorLength = separator.length();

        final ArrayList<String> substrings = new ArrayList<String>();
        int numberOfSubstrings = 0;
        int beg = 0;
        int end = 0;
        while (end < len) {
            end = str.indexOf(separator, beg);

            if (end > -1) {
                if (end > beg) {
                    numberOfSubstrings += 1;

                    if (numberOfSubstrings == max) {
                        end = len;
                        substrings.add(str.substring(beg));
                    } else {
                        // The following is OK, because String.substring( beg, end ) excludes
                        // the character at the position 'end'.
                        substrings.add(str.substring(beg, end));

                        // Set the starting point for the next search.
                        // The following is equivalent to beg = end + (separatorLength - 1) + 1,
                        // which is the right calculation:
                        beg = end + separatorLength;
                    }
                } else {
                    // We found a consecutive occurrence of the separator, so skip it.
                    if (preserveAllTokens) {
                        numberOfSubstrings += 1;
                        if (numberOfSubstrings == max) {
                            end = len;
                            substrings.add(str.substring(beg));
                        } else {
                            substrings.add(EMPTY);
                        }
                    }
                    beg = end + separatorLength;
                }
            } else {
                // String.substring( beg ) goes from 'beg' to the end of the String.
                substrings.add(str.substring(beg));
                end = len;
            }
        }

        return substrings.toArray(new String[substrings.size()]);
    }

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Splits the provided text into an array, using whitespace as the separator, preserving all tokens, including empty
     * tokens created by adjacent separators. This is an alternative to using StringTokenizer. Whitespace is defined by
     * {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}.
     * </p>
     * 
     * <pre>
     * StringUtils.splitPreserveAllTokens(null)       = null
     * StringUtils.splitPreserveAllTokens("")         = []
     * StringUtils.splitPreserveAllTokens("abc def")  = ["abc", "def"]
     * StringUtils.splitPreserveAllTokens("abc  def") = ["abc", "", "def"]
     * StringUtils.splitPreserveAllTokens(" abc ")    = ["", "abc", ""]
     * </pre>
     * 
     * @param str the String to parse, may be {@code null}
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(final String str) {
        return splitWorker(str, null, -1, true);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator specified, preserving all tokens, including empty tokens
     * created by adjacent separators. This is an alternative to using StringTokenizer.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}.
     * </p>
     * 
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *)         = null
     * StringUtils.splitPreserveAllTokens("", *)           = []
     * StringUtils.splitPreserveAllTokens("a.b.c", '.')    = ["a", "b", "c"]
     * StringUtils.splitPreserveAllTokens("a..b.c", '.')   = ["a", "", "b", "c"]
     * StringUtils.splitPreserveAllTokens("a:b:c", '.')    = ["a:b:c"]
     * StringUtils.splitPreserveAllTokens("a\tb\nc", null) = ["a", "b", "c"]
     * StringUtils.splitPreserveAllTokens("a b c", ' ')    = ["a", "b", "c"]
     * StringUtils.splitPreserveAllTokens("a b c ", ' ')   = ["a", "b", "c", ""]
     * StringUtils.splitPreserveAllTokens("a b c  ", ' ')   = ["a", "b", "c", "", ""]
     * StringUtils.splitPreserveAllTokens(" a b c", ' ')   = ["", a", "b", "c"]
     * StringUtils.splitPreserveAllTokens("  a b c", ' ')  = ["", "", a", "b", "c"]
     * StringUtils.splitPreserveAllTokens(" a b c ", ' ')  = ["", a", "b", "c", ""]
     * </pre>
     * 
     * @param str the String to parse, may be {@code null}
     * @param separatorChar the character used as the delimiter, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(final String str, final char separatorChar) {
        return splitWorker(str, separatorChar, true);
    }

    /**
     * Performs the logic for the {@code split} and {@code splitPreserveAllTokens} methods that do not return a maximum
     * array length.
     * 
     * @param str the String to parse, may be {@code null}
     * @param separatorChar the separate character
     * @param preserveAllTokens if {@code true}, adjacent separators are treated as empty token separators; if
     *            {@code false}, adjacent separators are treated as one separator.
     * @return an array of parsed Strings, {@code null} if null String input
     */
    private static String[] splitWorker(final String str, final char separatorChar, final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<String>();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * <p>
     * Splits the provided text into an array, separators specified, preserving all tokens, including empty tokens
     * created by adjacent separators. This is an alternative to using StringTokenizer.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separatorChars splits on whitespace.
     * </p>
     * 
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *)           = null
     * StringUtils.splitPreserveAllTokens("", *)             = []
     * StringUtils.splitPreserveAllTokens("abc def", null)   = ["abc", "def"]
     * StringUtils.splitPreserveAllTokens("abc def", " ")    = ["abc", "def"]
     * StringUtils.splitPreserveAllTokens("abc  def", " ")   = ["abc", "", def"]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":")   = ["ab", "cd", "ef"]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef:", ":")  = ["ab", "cd", "ef", ""]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef::", ":") = ["ab", "cd", "ef", "", ""]
     * StringUtils.splitPreserveAllTokens("ab::cd:ef", ":")  = ["ab", "", cd", "ef"]
     * StringUtils.splitPreserveAllTokens(":cd:ef", ":")     = ["", cd", "ef"]
     * StringUtils.splitPreserveAllTokens("::cd:ef", ":")    = ["", "", cd", "ef"]
     * StringUtils.splitPreserveAllTokens(":cd:ef:", ":")    = ["", cd", "ef", ""]
     * </pre>
     * 
     * @param str the String to parse, may be {@code null}
     * @param separatorChars the characters used as the delimiters, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(final String str, final String separatorChars) {
        return splitWorker(str, separatorChars, -1, true);
    }

    /**
     * <p>
     * Splits the provided text into an array with a maximum length, separators specified, preserving all tokens,
     * including empty tokens created by adjacent separators.
     * </p>
     * 
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for
     * empty tokens. Adjacent separators are treated as one separator.
     * </p>
     * 
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separatorChars splits on whitespace.
     * </p>
     * 
     * <p>
     * If more than {@code max} delimited substrings are found, the last returned string includes all characters after
     * the first {@code max - 1} returned strings (including separator characters).
     * </p>
     * 
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *, *)            = null
     * StringUtils.splitPreserveAllTokens("", *, *)              = []
     * StringUtils.splitPreserveAllTokens("ab de fg", null, 0)   = ["ab", "cd", "ef"]
     * StringUtils.splitPreserveAllTokens("ab   de fg", null, 0) = ["ab", "cd", "ef"]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
     * StringUtils.splitPreserveAllTokens("ab   de fg", null, 2) = ["ab", "  de fg"]
     * StringUtils.splitPreserveAllTokens("ab   de fg", null, 3) = ["ab", "", " de fg"]
     * StringUtils.splitPreserveAllTokens("ab   de fg", null, 4) = ["ab", "", "", "de fg"]
     * </pre>
     * 
     * @param str the String to parse, may be {@code null}
     * @param separatorChars the characters used as the delimiters, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(final String str, final String separatorChars, final int max) {
        return splitWorker(str, separatorChars, max, true);
    }

    /**
     * Performs the logic for the {@code split} and {@code splitPreserveAllTokens} methods that return a maximum array
     * length.
     * 
     * @param str the String to parse, may be {@code null}
     * @param separatorChars the separate character
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit.
     * @param preserveAllTokens if {@code true}, adjacent separators are treated as empty token separators; if
     *            {@code false}, adjacent separators are treated as one separator.
     * @return an array of parsed Strings, {@code null} if null String input
     */
    private static String[] splitWorker(final String str, final String separatorChars, final int max, final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * <p>
     * Splits a String by Character type as returned by {@code java.lang.Character.getType(char)}. Groups of contiguous
     * characters of the same type are returned as complete tokens.
     * 
     * <pre>
     * StringUtils.splitByCharacterType(null)         = null
     * StringUtils.splitByCharacterType("")           = []
     * StringUtils.splitByCharacterType("ab de fg")   = ["ab", " ", "de", " ", "fg"]
     * StringUtils.splitByCharacterType("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
     * StringUtils.splitByCharacterType("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
     * StringUtils.splitByCharacterType("number5")    = ["number", "5"]
     * StringUtils.splitByCharacterType("fooBar")     = ["foo", "B", "ar"]
     * StringUtils.splitByCharacterType("foo200Bar")  = ["foo", "200", "B", "ar"]
     * StringUtils.splitByCharacterType("ASFRules")   = ["ASFR", "ules"]
     * </pre>
     * 
     * @param str the String to split, may be {@code null}
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.4
     */
    public static String[] splitByCharacterType(final String str) {
        return splitByCharacterType(str, false);
    }

    /**
     * <p>
     * Splits a String by Character type as returned by {@code java.lang.Character.getType(char)}. Groups of contiguous
     * characters of the same type are returned as complete tokens, with the following exception: the character of type
     * {@code Character.UPPERCASE_LETTER}, if any, immediately preceding a token of type
     * {@code Character.LOWERCASE_LETTER} will belong to the following token rather than to the preceding, if any,
     * {@code Character.UPPERCASE_LETTER} token.
     * 
     * <pre>
     * StringUtils.splitByCharacterTypeCamelCase(null)         = null
     * StringUtils.splitByCharacterTypeCamelCase("")           = []
     * StringUtils.splitByCharacterTypeCamelCase("ab de fg")   = ["ab", " ", "de", " ", "fg"]
     * StringUtils.splitByCharacterTypeCamelCase("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
     * StringUtils.splitByCharacterTypeCamelCase("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
     * StringUtils.splitByCharacterTypeCamelCase("number5")    = ["number", "5"]
     * StringUtils.splitByCharacterTypeCamelCase("fooBar")     = ["foo", "Bar"]
     * StringUtils.splitByCharacterTypeCamelCase("foo200Bar")  = ["foo", "200", "Bar"]
     * StringUtils.splitByCharacterTypeCamelCase("ASFRules")   = ["ASF", "Rules"]
     * </pre>
     * 
     * @param str the String to split, may be {@code null}
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.4
     */
    public static String[] splitByCharacterTypeCamelCase(final String str) {
        return splitByCharacterType(str, true);
    }

    /**
     * <p>
     * Splits a String by Character type as returned by {@code java.lang.Character.getType(char)}. Groups of contiguous
     * characters of the same type are returned as complete tokens, with the following exception: if {@code camelCase}
     * is {@code true}, the character of type {@code Character.UPPERCASE_LETTER}, if any, immediately preceding a token
     * of type {@code Character.LOWERCASE_LETTER} will belong to the following token rather than to the preceding, if
     * any, {@code Character.UPPERCASE_LETTER} token.
     * 
     * @param str the String to split, may be {@code null}
     * @param camelCase whether to use so-called "camel-case" for letter types
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.4
     */
    private static String[] splitByCharacterType(final String str, final boolean camelCase) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final char[] c = str.toCharArray();
        final List<String> list = new ArrayList<String>();
        int tokenStart = 0;
        int currentType = Character.getType(c[tokenStart]);
        for (int pos = tokenStart + 1; pos < c.length; pos++) {
            final int type = Character.getType(c[pos]);
            if (type == currentType) {
                continue;
            }
            if (camelCase && type == Character.LOWERCASE_LETTER && currentType == Character.UPPERCASE_LETTER) {
                final int newTokenStart = pos - 1;
                if (newTokenStart != tokenStart) {
                    list.add(new String(c, tokenStart, newTokenStart - tokenStart));
                    tokenStart = newTokenStart;
                }
            } else {
                list.add(new String(c, tokenStart, pos - tokenStart));
                tokenStart = pos;
            }
            currentType = type;
        }
        list.add(new String(c, tokenStart, c.length - tokenStart));
        return list.toArray(new String[list.size()]);
    }

}
