package com.zhanghao.service.impl;
import com.sun.javafx.binding.StringFormatter;
import com.zhanghao.dao.UserDao;
import com.zhanghao.entity.User;
import com.zhanghao.model.UserPool;
import com.zhanghao.service.UserService;
import com.zhanghao.util.DataEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * Created by 张浩 on 2017/1/20.
 */
@Service
public class UserServiceImpl implements UserService{


    @Resource
    private UserDao userDao;


    private UserPool userPool=UserPool.getUserPool();

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean  login(User user) {
        // TODO: 2017/1/20 登录成功返回token
        String account=user.getUserAccount();
        String password=user.getUserPassword();
        //判断用户登录是否正确，进行数据库查询
        String passwordDb=userDao.findPassByAccount(account);
//        if (password.equals(passwordDb)){
////            /userPool.addOneUser(userToken,user);
//            return true;
//        }
//        else
//            return "";
        return password.equals(passwordDb);
    }

    @Override
    public int register(String account,String password,String username) {
        return  userDao.insertUserIntoDb(account,password,username);
    }

    @Override
    public boolean logout(String token) {
        HttpSession httpSession=userPool.get(token);
        if (httpSession!=null){
            logger.info("httpSession不为空！！！");
            httpSession.invalidate();
            userPool.removeUser(token);
            return true;
        }else{
            logger.info("httpSession为空！！！");
            return false;

        }

    }

    @Override
    public boolean checkUser(String userToken) {
        return userPool.checkUser(userToken);
    }

    @Override
    public User getUserByUserAccount(String userAccount) {
        return userDao.findUserByAccount(userAccount);
    }

    @Override
    public boolean removeUserByUserAccount(String userAccount) {
       return userPool.removeUserByAccount(userAccount);
    }

    @Override
    public boolean updateUserImageByUserAccount(String token,String image) {
        User user=getUserByUserToken(token);
        if (user!=null){
            String userAccount=user.getUserAccount();
            int code=userDao.updateUserImageByAccount(image,userAccount);
            if (code>0)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    @Override
    public boolean updateUserInfoByUserAccount(String token, User user) {
        User sessionUser=getUserByUserToken(token);
        if (sessionUser!=null){
            String account=sessionUser.getUserAccount();
            String userName=user.getUserName();
            int updateCode = userDao.updateUserInfoByUserAccount(userName,account);
            return updateCode>0;
        }else
            return false;
    }

    @Override
    public void updateUserInUserPool(String token, User user) {
        HttpSession httpSession=userPool.get(token);
        httpSession.setAttribute("user",user);
        userPool.removeUser(token);
        userPool.addOneUser(token,httpSession);
    }

    @Override
    public boolean updateUserPassword(String account, String newPass) {
        return userDao.updateUserPasswordByUserAccount(account,newPass)>0;
    }

    @Override
    public String getUserPassword(String userAccount) {
        return userDao.findPassByAccount(userAccount);
    }

    @Override
    public User getUserByUserToken(String token) {
        if (checkUser(token))
            return (User)userPool.getUser(token).getAttribute("user");
        return null;
    }






}
