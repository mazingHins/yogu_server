package com.yogu.core.web;

import com.yogu.core.web.exception.ServiceException;

/**
 * 用户模块的错误代码
 * @author linyi 2015/6/4.
 */
public class UserErrorCode {

    // 1000~1999 是用户模块的错误代码

    /**
     * 用户已经被封
     */
    public static final int USER_WAS_BANNED = 1000;

    /**
     * 用户不存在
     */
    public static final int USER_NOT_FOUND = 1005;

    /**
     * 帐号已经存在，请使用其他帐号
     */
    public static final int USER_EXISTED = 1006;

    /**
     * 昵称已经存在
     */
    public static final int NICKNAME_EXISTED = 1007;

    /**
     * 用户未登录
     */
    public static final int USER_NOT_LOGIN = 1010;
    
    /**
     * 验证码错误
     */
    public static final int IDCODE_ERROR = 1020;
    
    /**
     * 验证码发送失败
     */
    public static final int IDCODE_SEND_ERROR = 1021;
    
    
    

    /**
     * 图片文件格式不支持
     */
    public static final int FILE_FORMAT_NOT_SUPPORT = 1030;

    /**
     * 图片文件数据错误
     */
    public static final int FILE_DATA_ERROR = 1040;

    /**
     * 图片文件太大
     */
    public static final int FILE_TOO_LARGE = 1050;

    /**
     * 密码过于简单
     */
    public static final int PASSWORD_TOO_SIMPLE = 1060;
    /**
     * 密码没有变化（修改密码）
     */
    public static final int PASSWORD_NOT_CHANGE = 1061;
    
    /**
     * 更新密码失败
     */
    public static final int PASSWORD_CHANGE_FAILED = 1062;
    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR = 1063;
    
    /**
     * 已经绑定过了
     */
    public static final int HAS_ALREADY_BIND = 1065;
    
    /**
     * 插入门店收藏数据失败
     */
    public static final int INSERT_FAV_STORE_ERROR = 1070;
    
    /**
     * 插入菜品收藏数据失败
     */
    public static final int INSERT_FAV_DISH_ERROR = 1071;
    
    /**
     * 用户系统消息不存在
     */
    public static final int SITE_MESSAGE_NOT_EXIST = 1080;
    
    /**
	 * 对应的区域不存在
	 */
	public static final int DISTRICT_NOT_EXIST = 1090;
    
    /**
	 * 对应的城市不存在
	 */
	public static final int CITY_NOT_EXIST = 1091;
	
	/**
	 * 对应的省份不存在
	 */
	public static final int PROVINCE_NOT_EXIST = 1092;
	
	/**
	 * 用户的账户不合法
	 */
	public static final int USER_ACCOUNT_INVALID = 1093;

	public static ServiceException notLogin() {
		throw new ServiceException(USER_NOT_LOGIN, "用户没有登录", false);
	}

}
