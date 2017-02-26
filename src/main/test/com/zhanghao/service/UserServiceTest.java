package com.zhanghao.service;

import com.zhanghao.entity.User;
import com.zhanghao.service.UserService;
import com.zhanghao.service.impl.UserServiceImpl;
import com.zhanghao.util.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 张浩 on 2017/1/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class UserServiceTest {

    private Logger logger= LoggerFactory.getLogger(this.getClass());



    private UserService userService=new UserServiceImpl();

    @Test
    public void loginTest(){
//        User user=new User();
//        user.setUserAccount("281079145@qq.com");
//        user.setUserPassword("19951029");
//        user.setUserToken("");
//        LAndRMessage message=new LAndRMessage();
//
//        String userToken=user.getUserToken();
//        //如果携带token
//        if (!userToken.isEmpty()&&!userToken.equals("")){
//            //token未过期
//            if (userService.checkUser(userToken)){
//                message.setContent(Constant.LOGIN_SUCCESS);
//                message.setResult(user);
//                logger.debug(message.toString());
//            }else{
//                //token过期
//                message.setContent(Constant.USER_INVALID);
//                message.setResult(null);
//                logger.debug(message.toString());
//            }
//        }else{
//            //如果不携带token
//            String newUserToken=userService.login(user);
//            //登录成功
//            if (!newUserToken.isEmpty()&&!newUserToken.equals("")){
//                user.setUserToken(newUserToken);
////                httpSession.setMaxInactiveInterval(10);
////                httpSession.setAttribute("user",user);
//                message.setResult(user);
//                message.setContent(Constant.LOGIN_SUCCESS);
//                logger.debug(message.toString());
//            }else{
//                //登录失败
//                message.setContent(Constant.LOGIN_ERROR);
//                message.setResult(null);
//                logger.debug(message.toString());
//            }
//        }
    }
}
