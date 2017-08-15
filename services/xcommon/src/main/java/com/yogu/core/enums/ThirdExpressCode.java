package com.yogu.core.enums;

/**
 * 第三方配送定义
 *
 * @date 2016年10月10日 下午7:12:55
 * @author hins
 */
public enum ThirdExpressCode {
	
	SF(1001, "顺丰专送", "SF-Express");
	
	private long code;
	
	private String cnName;
	
	private String enName;

    private ThirdExpressCode(long code, String cnName, String enName) {
        this.code = code;
        this.cnName = cnName;
        this.enName = enName;
    }

	public long getCode() {
		return code;
	}

	public String getCnName() {
		return cnName;
	}

	public String getEnName() {
		return enName;
	}
	
	public static ThirdExpressCode switchByCode(long code) {
		if (code == 1001) {
			return SF;
		}

		return null;
	}
	

}
