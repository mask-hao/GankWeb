package com.zhanghao.util;

import com.sun.javafx.binding.StringFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 张浩 on 2017/2/24.
 */
public class ConfigUtil {
    private static InputStream mInputStream=null;
    private static Properties mProperties;


    static{
        loadProperty(null);
    }

    /**
     * 加载配置文件
     * @param fileName 文件名称
     *  fileName == null 则文件默认为 config.properties
     */
    private static void loadProperty(String fileName){
        fileName = fileName == null?"config.properties":fileName;
        mProperties=new Properties();
        mInputStream=ConfigUtil.class.getClassLoader().getResourceAsStream(fileName);
        try {
            mProperties.load(mInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * @param fileName 文件名
     * @param key 键
     * @return 值
     */
    public static String getValue(String fileName,String key){
        if (fileName!=null){
            loadProperty(fileName);
        }
        return mProperties.getProperty(key);
    }


    public static String getValue(String key){
        return getValue(null,key);
    }

    public static String getFileStorePath(){
        return getValue("file_save_path");
    }


}
