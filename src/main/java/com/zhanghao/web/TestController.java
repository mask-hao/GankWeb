package com.zhanghao.web;

//import com.mysql.jdbc.log.LogFactory;
import com.zhanghao.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by 张浩 on 2017/2/24.
 */
@RequestMapping(value = "/test")
@Controller
public class TestController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSenderImpl sender;

    @RequestMapping(value = "/path",method = RequestMethod.GET)
    public void getFilePath(HttpServletRequest request){
        String path="/usr/local/apache-tomcat-8.5.5/webapps/resource/images/3b02897c-29d0-4964-82e5-bdb8f3dd0c63.png";
        int a=path.indexOf("resource");
        String newPath= path.substring(a,path.length());
        logger.info(newPath);
    }


    @RequestMapping(value = "/mail",method = RequestMethod.GET)
    public void sendEmail(){
        try {
            MimeMessage message=sender.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message,false,"UTF-8");
            message.setContent(EmailUtil.getRegisterEmailString("281079145@qq.com","9999"),"text/html;charset=UTF-8");
            messageHelper.setSubject("验证邮件");
            messageHelper.setFrom(sender.getUsername());
            messageHelper.setTo("281079145@qq.com");
            sender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
