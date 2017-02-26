package com.zhanghao.dao;

import com.zhanghao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 张浩 on 2017/1/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class userDaoTest {
    //注入Dao实现依赖
    @Resource
    private UserDao userDao;

    @Test
    public void  testFindPassByAccount(){
       String account="281079145@qq.com";
       String result= userDao.findPassByAccount(account);
        System.out.println(result);
    }

}
