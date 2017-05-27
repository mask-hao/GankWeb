package com.zhanghao.web;

import com.zhanghao.entity.User;
import com.zhanghao.entity.VerifyCode;
import com.zhanghao.model.CommonResponse;
import com.zhanghao.model.VerifyCodePool;
import com.zhanghao.util.Constant;
import com.zhanghao.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.WebContentGenerator;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhanghao on 2017/4/26.
 */
@Controller
@RequestMapping(value = "/api/verification")
public class EmailController{



    @Resource
    private JavaMailSenderImpl sender;

    private VerifyCodePool verifyCodePool=VerifyCodePool.getInstance();

    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    public @ResponseBody CommonResponse<String> sendEmail(@RequestBody User user, HttpServletRequest request){

        CommonResponse<String> commonResponse=new CommonResponse<>();
        String account=user.getUserAccount();
        HttpSession httpSession=request.getSession();
        httpSession.setMaxInactiveInterval(60*5);
        String verificationCode= EmailUtil.getRandomRegisterCode();
        String emailContent=EmailUtil.getRegisterEmailString(account,verificationCode);

        try {
            MimeMessage message=sender.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message,false,"UTF-8");
            message.setContent(emailContent,"text/html;charset=UTF-8");
            messageHelper.setSubject("验证邮件");
            messageHelper.setFrom(sender.getUsername());
            messageHelper.setTo(account);
            sender.send(message);

            VerifyCode code=new VerifyCode(account,verificationCode);
            httpSession.setAttribute("code",code);
            String sessionId=httpSession.getId();
            verifyCodePool.addOneCode(sessionId,httpSession);
            commonResponse.setResult(Constant.SEND_EMAIL_SUCCESS);
            commonResponse.setContent(sessionId);
            return commonResponse;

        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setResult(Constant.SEND_EMAIL_ERROR);
            commonResponse.setContent(e.getMessage());
            return commonResponse;
        }
    }
}
