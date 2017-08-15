package com.yogu.commons.server.decompress.servlet.wrapper;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.StringUtils;

public class HttpAnalyticScheme {

	private String separate;
	private String assignment;

	public HttpAnalyticScheme() {
		this("[&]", "[=]");
	}

	public HttpAnalyticScheme(String separate, String assignment) {
		Args.notNull(separate, "separate");
		Args.notNull(assignment, "assignment");
		this.separate = separate;
		this.assignment = assignment;
	}

	/**
     * 按照http参数的形式解析文本，保证return不为null
     */
	public Map<String, String[]> analytical(String text) {
		Map<String, String[]> params = new HashMap<String, String[]>();
		analytical(params, text);
		return params;
	}

    /**
     * 解析http参数，保证return不为null
     * 
     * @throws UnsupportedEncodingException
     */
    public Map<String, String[]> analytical(String queryString, byte[] data, String enc)
            throws UnsupportedEncodingException {
        Map<String, String[]> params = new HashMap<String, String[]>();
        if (ArrayUtils.isNotEmpty(data))
            analytical(params, new String(data, enc));
        analytical(params, queryString);
        return params;
    }

    private void analytical(Map<String, String[]> params, String text) {
        if (StringUtils.isBlank(text))
            return;

        // split 是比较费性能的，以后要改成 通过下标来截取内容
        String[] duans = text.split(separate);
        for (String duan : duans) {
            if (null == duan)
                continue;
            String[] vs = duan.split(assignment);
            String k = vs[0];
            String v = (2 <= vs.length ? vs[1] : null);// vs[1];
            if (null == v)
                continue;
            String[] strings = params.get(k);
            if (null == strings) {
                strings = new String[] { v };
            } else {
                int l = strings.length;
                strings = Arrays.copyOf(strings, l + 1);
                strings[l] = v;
            }
            params.put(k, strings);
        }
    }

    protected String[] split(String text, String c) {
        // if(null == text)return null;
        int i = text.indexOf(c);
        if (-1 < i) {
            String k = text.substring(0, i);
            String v = text.substring(i + 1);
            return new String[] { k, v };
        }
        return new String[] { text, null };
    }

}
