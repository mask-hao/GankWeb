package com.zhanghao.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;


public class DataEncoder {
    /*
    * @param 待输入字符串
    * @return 返回加密MD5
    * */
    public static String getMD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = new byte[0];
        try {
            byteArray = inStr.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /*
    * @param 待输入字符串
    * @return 返回加密SHA1
    * */
//    public static String getSHA1(String inStr) {
//        MessageDigest sha = null;
//        try {
//            sha = MessageDigest.getInstance("SHA");
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//            return "";
//        }
//        byte[] byteArray = new byte[0];
//        try {
//            byteArray = inStr.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        byte[] md5Bytes = sha.digest(byteArray);
//        StringBuffer hexValue = new StringBuffer();
//        for (int i = 0; i < md5Bytes.length; i++) {
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16) {
//                hexValue.append("0");
//            }
//            hexValue.append(Integer.toHexString(val));
//        }
//        return hexValue.toString();
//    }

    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
