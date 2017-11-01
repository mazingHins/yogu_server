package com.yogu.services.backend.admin.resources.form;

/**
 * 申请登录的信息
 * @author ten 2015/11/16.
 */
public class ApplyLoginForm {

    /**
     * appKey
     */
    private String akey;

    /**
     * 登录成功后的回调地址
     */
    private String callback;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getAkey() {
        return akey;
    }

    public void setAkey(String akey) {
        this.akey = akey;
    }
}
