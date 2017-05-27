package com.zhanghao.util;

import java.util.Random;

/**
 * Created by zhanghao on 2017/4/26.
 */
public class EmailUtil {
    public static String getRegisterEmailString(String username,String code){
        return "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" +
                "</head><body>" +
                "您好" +
                username +
                "<br/>您的验证码是：" +
                "<font size=\"6\">" +
                code +
                "</font>" +
                "<br/>此验证码用来验证身份，请勿将验证码透露给其他人，该验证码有效时间为5分钟" +
                "<br/>如果不是您的操作，请忽略此邮件"+
                "</body></html>";
    }


    public static String getRandomRegisterCode(){
        Random random=new Random();
        String s="";
        for (int i = 0; i <4 ; i++) {
            s+=random.nextInt(10);
        }
        return s;
    }

}
