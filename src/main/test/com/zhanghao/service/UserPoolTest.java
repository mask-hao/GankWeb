package com.zhanghao.service;

import com.zhanghao.entity.User;
import com.zhanghao.model.UserPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 张浩 on 2017/1/28.
 */
@RunWith(JUnit4.class)
public class UserPoolTest {

    private Logger logger= LoggerFactory.getLogger(this.getClass());


    @Test
    public void testUserPool(){

        User user=new User();
        user.setUserToken("12345");
        user.setUserAccount("281079145@qq.com");
        user.setUserPassword("19951029");

        UserPool userPool=UserPool.getUserPool();

//        userPool.addOneUser("12345",user);


        logger.info(userPool.toString());



        userPool.remove("12345");


        logger.info(userPool.toString());



    }

}
