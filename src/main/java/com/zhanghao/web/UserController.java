package com.zhanghao.web;

import com.zhanghao.entity.User;
import com.zhanghao.entity.VerifyCode;
import com.zhanghao.model.CommonResponse;
import com.zhanghao.model.UserPool;
import com.zhanghao.model.VerifyCodePool;
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
@RequestMapping(value = "/api/user",produces = {"application/json;charset=UTF-8"})
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserPool userPool = UserPool.getUserPool();
    private VerifyCodePool verifyCodePool=VerifyCodePool.getInstance();

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
    CommonResponse<User> login(@RequestBody User user, HttpServletRequest httpServletRequest) {
        CommonResponse<User> message = new CommonResponse<>();
        String userAccount=user.getUserAccount();
        String userToken = user.getUserToken();

//        System.out.println(user.toString());

        //如果携带token
        if (!userToken.isEmpty() && !userToken.equals("")) {
            //token未过期
            if (userService.checkUser(userToken)) {
                User userInfo = userService.getUserByUserToken(userToken);
                userInfo.setUserPassword("");
                userInfo.setUserToken(userToken);
                message.setResult(Constant.LOGIN_SUCCESS);
                message.setContent(userInfo);
                return message;

            } else {
                //token过期
                message.setResult(Constant.USER_INVALID);
                message.setContent(new User());
                return message;
            }
        } else {
            //如果不携带token
            if (userService.login(user)) {
                User userInfo = userService.getUserByUserAccount(user.getUserAccount());
                userInfo.setUserPassword("");
                //移除之前的之前的用户
                userService.removeUserByUserAccount(user.getUserAccount());

                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setMaxInactiveInterval(30 * 24 * 3600);
                String newUserToken = httpSession.getId();
                userInfo.setUserToken(newUserToken);

                httpSession.setAttribute("user", userInfo);

                userPool.addOneUser(newUserToken, httpSession);
                message.setResult(Constant.LOGIN_SUCCESS);
                message.setContent(userInfo);
                return message;

            } else {
                //登录失败
                message.setResult(Constant.LOGIN_ERROR);
                message.setContent(new User());
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
    CommonResponse<String> register(@RequestBody User user) {

        logger.info(user.toString());

        CommonResponse<String> message = new CommonResponse<>();
        String account = user.getUserAccount();
        String password = user.getUserPassword();
        String username = user.getUserName();
        String verifyCode=user.getVerifyCode();
        String verifyId=user.getVerifyId();

        if (account==null||account.isEmpty()) {
            message.setResult(Constant.REGISTER_ERROR);
            message.setContent(Constant.ACCOUNT_EMPTY);
            return message;
        } else if (password==null||password.isEmpty()) {
            message.setResult(Constant.REGISTER_ERROR);
            message.setContent(Constant.PASSWORD_EMPTY);
            return message;
        } else if (username==null||username.isEmpty()) {
            message.setResult(Constant.REGISTER_ERROR);
            message.setContent(Constant.USERNAME_EMPTY);
            return message;
        } else if (verifyCode==null||verifyCode.isEmpty()){
            message.setResult(Constant.REGISTER_ERROR);
            message.setContent(Constant.VERIFY_CODE_ERROR);
            return message;
        }else {

            if (verifyCodePool.get(verifyId)!=null){
                HttpSession httpSession=verifyCodePool.get(verifyId);
                VerifyCode code= (VerifyCode) httpSession.getAttribute("code");
                if (code.getCode().equals(verifyCode)){
                    int registerCode = userService.register(account, password, username);
                    if (registerCode<0){
                        message.setResult(Constant.REGISTER_ERROR);
                        message.setContent("");
                        return message;
                    }else if (registerCode==0){
                        message.setResult(Constant.REGISTER_ERROR);
                        message.setContent(Constant.ACCOUNT_USED);
                        return message;
                    }else{
                        message.setResult(Constant.REGISTER_SUCCESS);
                        message.setContent("");
                        return message;
                    }
                }else{
                    message.setResult(Constant.REGISTER_ERROR);
                    message.setContent(Constant.VERIFY_CODE_ERROR);
                    return message;
                }
            }else{
                message.setResult(Constant.REGISTER_ERROR);
                message.setContent(Constant.VERIFY_CODE_INVALIDATE);
                return message;
            }
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
    CommonResponse<User> updateUserInfo(@RequestBody User user) {
        CommonResponse<User> message = new CommonResponse<>();
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
            message.setContent(new User());
            return message;
        }
    }


    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse<String> updateUserPassword(@RequestParam("token") String token,
                                      @RequestParam("old") String oldPass, @RequestParam("new") String newPass) {
        CommonResponse<String> message = new CommonResponse<>();
            if (userService.checkUser(token)) {
                User user = userService.getUserByUserToken(token);
                String account = user.getUserAccount();
                String oldPassDB = userService.getUserPassword(account);
                if (oldPass.equals(oldPassDB)) {
                    if (userService.updateUserPassword(account, newPass)) {
                        message.setResult(Constant.UPDATEPASS_SUCCESS);
                        message.setContent("");
                        return message;
                    } else {
                        message.setResult(Constant.UPDATEPASS_ERROR);
                        message.setContent("");
                        return message;
                    }
                } else {
                    message.setResult(Constant.OLDPASS_ERROR);
                    message.setContent("");
                    return message;
                }
            } else {
                message.setResult(Constant.USER_INVALID);
                message.setContent("");
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
    CommonResponse<String> logout(@RequestBody User user) {
        CommonResponse<String> message = new CommonResponse<>();
        String userToken = user.getUserToken();
        if (userService.logout(userToken)) {
            message.setResult(Constant.LOGOUT_SUCCESS);
            message.setContent("");
            return message;
        } else {
            message.setResult(Constant.LOGOUT_ERROR);
            message.setContent("");
            return message;
        }
    }
}
