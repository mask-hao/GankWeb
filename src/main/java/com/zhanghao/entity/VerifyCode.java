package com.zhanghao.entity;

/**
 * Created by zhanghao on 2017/4/26.
 */
public class VerifyCode {
    private String account;
    private String code;

    public VerifyCode(String account, String code) {
        this.account = account;
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
