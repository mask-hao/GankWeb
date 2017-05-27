package com.zhanghao.listener;

import com.zhanghao.entity.User;
import com.zhanghao.model.UserPool;
import com.zhanghao.model.VerifyCodePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by 张浩 on 2017/1/23.
 */
@WebListener
public class MySessionListener implements HttpSessionListener{

    private UserPool userPool=UserPool.getUserPool();
    private VerifyCodePool verifyCodePool=VerifyCodePool.getInstance();

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("session创建了");
        logger.info(httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("session销毁了");
//        User user= (User) httpSessionEvent.getSession().getAttribute("user");
//        if (user!=null)
//            userPool.removeUser(user.getUserToken());
//        userPool.removeUser()
        String md5=httpSessionEvent.getSession().getId();
        userPool.removeUser(md5);
        verifyCodePool.removeCodeByAccount(md5);
        // TODO: 2017/5/1  移除时候空指针异常

    }
}
