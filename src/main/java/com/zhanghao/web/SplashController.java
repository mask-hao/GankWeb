package com.zhanghao.web;

import com.zhanghao.model.SplashImgBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;;
import java.io.IOException;

/**
 * Created by zhanghao on 2017/3/12.
 */
@Controller
@RequestMapping("/api")
public class SplashController {

    private final String defaultImg="https://images.unsplash.com/photo-1489211914964-32c31f87e86b?dpr=1&auto=compress,format&fit=crop&w=767&h=511&q=80&cs=tinysrgb&crop=";

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/start-image")
    public @ResponseBody SplashImgBean getSplashImg(){
        SplashImgBean bean=new SplashImgBean();
        try {
            Document document= Jsoup.connect("https://unsplash.com/new")
                    .timeout(10*1000)
                    .get();
            Element element=document.getElementById("gridSingle").select("div.y5w1y>a").first();
            String styleText=element.attr("style");
            int begin=styleText.indexOf("https:");
            int end=styleText.indexOf("\");");
            String img=styleText.substring(begin,end);
            bean.setText("nothing");
            bean.setImg(img);
//            logger.info(img);
        } catch (IOException e) {
            e.printStackTrace();
            bean.setText("nothing");
            bean.setImg(defaultImg);
            return bean;
        }
        return bean;
    }
}
