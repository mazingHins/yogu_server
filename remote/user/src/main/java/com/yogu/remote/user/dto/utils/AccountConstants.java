package com.yogu.remote.user.dto.utils;

/**
 * 帐号相关的常量
 * @author linyi 2015/5/20.
 */
public class AccountConstants {

    /**
     * 用户来源：app注册
     */
    public static final short SOURCE_APP_REG = 1;

    /**
     * 用户来源：微信
     */
    public static final short SOURCE_WEI_XIN = 2;

    /**
     * 用户来源：微博
     */
    public static final short SOURCE_WEI_BO = 3;

    /**
     * 帐号状态：正常
     */
    public static final short STATUS_NORMAL = 1;

    /**
     * 帐号状态：封号
     */
    public static final short STATUS_BANNED = 2;

    /**
     * 帐号状态：待激活
     */
    public static final short STATUS_WAITING_ACTIVED = 3;
}
