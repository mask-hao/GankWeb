package com.zhanghao.model;

import com.zhanghao.entity.User;
import com.zhanghao.util.DataEncoder;
import org.omg.CORBA.StringHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 张浩 on 2017/1/23.
 */
public class UserPool extends ConcurrentHashMap<String,HttpSession> {

//    private static UserPool userPool;


    private UserPool(){
        logger.info("用户池初始化");
    }


    public  static UserPool getUserPool(){
        return UserPoolHolder.userPool;
    }


    private static class UserPoolHolder{
        private final static UserPool userPool=new UserPool();
    }


    private Logger logger= LoggerFactory.getLogger(this.getClass());


    /**
     * 添加一个合法的用户
     * @param md5 用户表示
     * @param session 用户实体
     */
//        public void addOneUser(String md5,User user){
//            put(md5,user);
//        }

        public void addOneUser(String md5, HttpSession session){
            put(md5,session);
        }


    /**
     * 移除session用户
     * @param md5
     */
        public boolean removeUser(String md5){
               Object obj= remove(md5);
               return obj==null;
        }


        public boolean removeUserByAccount(String userAccount) {
            String oldMd5;
            for (Entry<String, HttpSession> stringHttpSessionEntry : entrySet()) {
                User user = (User) stringHttpSessionEntry.getValue().getAttribute("user");
                if (user.getUserAccount().equals(userAccount)){
                    oldMd5 = user.getUserToken();
                    boolean isRemove=removeUser(oldMd5);
                    stringHttpSessionEntry.getValue().invalidate();
                    return  isRemove;
                }
            }
            return false;
        }

        /**
         * 检查用户是否合法
         * @param md5
         * @return 用户是否过期
         */
        public boolean checkUser(String md5) {
            return !(md5 == null || md5.equals("") || md5.isEmpty()) && get(md5) != null;
        }


    /**
     *通过用户凭证从用户池中获取一个用户
     * @param token 用户凭证
     * @return user实体
     */
    public HttpSession getUser(String token) throws NullPointerException{
            return  get(token);
        }



    @Override
    public String toString() {
        List<HttpSession> list=new ArrayList<>(values());
        logger.info(String.valueOf(list.size()));
        StringBuilder builder=new StringBuilder();
        for (HttpSession httpSession : list) {
            User user= (User) httpSession.getAttribute("user");
            builder.append(user.toString());
        }
        return builder.toString();
    }
}
