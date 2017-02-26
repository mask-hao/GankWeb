package com.zhanghao.web;

import com.zhanghao.entity.User;
import com.zhanghao.model.CommonMessage;
import com.zhanghao.model.UserPool;
import com.zhanghao.service.UserService;
import com.zhanghao.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 张浩 on 2017/1/23.
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserPool userPool = UserPool.getUserPool();
    @Resource
    private UserService userService;


    /**
     * 用户登录
     * @param user
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonMessage login(@RequestBody User user, HttpServletRequest httpServletRequest) {
        CommonMessage message = new CommonMessage();
        String userToken = user.getUserToken();
        //如果携带token
        if (!userToken.isEmpty() && !userToken.equals("")) {
            //token未过期
            if (userService.checkUser(userToken)) {

                User userInfo = userService.getUserByUserToken(userToken);

                message.setResult(Constant.LOGIN_SUCCESS);
                message.setContent(userInfo);
                return message;

            } else {
                //token过期
                message.setResult(Constant.USER_INVALID);
                return message;
            }
        } else {
            //如果不携带token
            if (userService.login(user)) {
                User userInfo = userService.getUserByUserAccount(user.getUserAccount());
                userInfo.setUserPassword("");
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setMaxInactiveInterval(30 * 24 * 3600);
                httpSession.setAttribute("user", userInfo);
                String newUserToken = httpSession.getId();
                userInfo.setUserToken(newUserToken);
                userPool.addOneUser(newUserToken, httpSession);
                message.setResult(Constant.LOGIN_SUCCESS);
                message.setContent(userInfo);
                return message;
            } else {
                //登录失败
                message.setContent(Constant.LOGIN_ERROR);
                message.setResult(null);
                return message;
            }
        }
    }


    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonMessage register(@RequestBody User user) {
        CommonMessage message = new CommonMessage();
        String account = user.getUserAccount();
        String password = user.getUserPassword();
        String username = user.getUserName();
        if (account.isEmpty() || account.equals("")) {
            message.setResult(Constant.REGISTER_ERROR);
            message.setContent(Constant.ACCOUNT_EMPTY);
            return message;
        } else if (password.isEmpty() || password.equals("")) {
            message.setResult(Constant.REGISTER_ERROR);
            message.setContent(Constant.PASSWORD_EMPTY);
            return message;
        } else if (username.isEmpty() || username.equals("")) {
            message.setResult(Constant.REGISTER_ERROR);
            message.setContent(Constant.USERNAME_EMPTY);
            return message;
        } else {
            int registerCode = userService.register(account, password, username);
            if (registerCode<0){
                message.setResult(Constant.REGISTER_ERROR);
                return message;
            }else if (registerCode==0){
                message.setResult(Constant.REGISTER_ERROR);
                message.setContent(Constant.ACCOUNT_USED);
                return message;
            }else
                message.setResult(Constant.REGISTER_SUCCESS);
            return message;
        }
    }


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonMessage updateUserInfo(@RequestBody User user) {
        CommonMessage message = new CommonMessage();
        String userToken = user.getUserToken();
        String userAccount = user.getUserAccount();
        if (userService.updateUserInfoByUserAccount(userToken, user)) {
            User newUser = userService.getUserByUserAccount(userAccount);
            newUser.setUserPassword("");
            userService.updateUserInUserPool(userToken, newUser);
            message.setResult(Constant.UPDATEUSERINFO_SUCCESS);
            message.setContent(newUser);
            return message;

        } else {
            message.setResult(Constant.UPDATEUSERINFO_ERROR);
            return message;
        }
    }


    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonMessage updateUserPassword(@RequestParam("token") String token,
                                     @RequestParam("old") String oldPass, @RequestParam("new") String newPass) {
        CommonMessage message = new CommonMessage();
            if (userService.checkUser(token)) {
                User user = userService.getUserByUserToken(token);
                String account = user.getUserAccount();
                String oldPassDB = userService.getUserPassword(account);
                if (oldPass.equals(oldPassDB)) {
                    if (userService.updateUserPassword(account, newPass)) {
                        message.setResult(Constant.UPDATEPASS_SUCCESS);
                        return message;
                    } else {
                        message.setResult(Constant.UPDATEPASS_ERROR);
                        return message;
                    }
                } else {
                    message.setResult(Constant.OLDPASS_ERROR);
                    return message;
                }
            } else {
                message.setResult(Constant.USER_INVALID);
                return message;
            }
    }

    /**
     * 用户登出
     * @param user
     * @return
     */
    @RequestMapping(value = "/logout")
    public
    @ResponseBody
    CommonMessage logout(@RequestBody User user) {
        CommonMessage message = new CommonMessage();
        String userToken = user.getUserToken();
        if (userService.logout(userToken)) {
            message.setResult(Constant.LOGOUT_SUCCESS);
            return message;
        } else {
            message.setResult(Constant.LOGIN_ERROR);
            return message;
        }
    }






}
