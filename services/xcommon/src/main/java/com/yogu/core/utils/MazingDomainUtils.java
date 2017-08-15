package com.yogu.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 米星域名判断工具
 * @author ten 2015/11/19.
 */
public class MazingDomainUtils {

    public static boolean isMazingDomain(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        int pos = url.indexOf(".mazing.com/");
        if (pos == 0) {
            return url.endsWith(".mazing.com");
        }
        return true;
    }
}
