package com.zhanghao.model;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhanghao on 2017/4/26.
 */
public class VerifyCodePool extends ConcurrentHashMap<String,HttpSession>{
    private VerifyCodePool(){}
    public static VerifyCodePool getInstance(){
        return VerifyCodePoolHolder.verifyCodePool;
    }

    private static class VerifyCodePoolHolder{
        private static VerifyCodePool verifyCodePool=new VerifyCodePool();
    }


    public void addOneCode(String md5,HttpSession httpSession){
        put(md5,httpSession);
    }


    public void removeCodeByAccount(String md5){
        remove(md5);
    }


}
