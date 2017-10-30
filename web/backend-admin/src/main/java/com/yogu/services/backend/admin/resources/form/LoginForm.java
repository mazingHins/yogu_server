package com.yogu.services.backend.admin.resources.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 登录表单
 * @author ten 2015/11/18.
 */
public class LoginForm {

    /**
     * 国家代码
     */
    @NotBlank(message = "国家代码不能为空")
    private String countryCode;

    /**
     * 帐号
     */
    @NotBlank(message = "帐号不能为空")
    private String passport;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "回调地址不能为空")
    private String callback;

    /**
     * 时间戳
     */
    private long t;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }
}
