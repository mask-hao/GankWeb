package com.zhanghao.web;

import com.zhanghao.model.CommonResponse;
import com.zhanghao.model.SplashImgBean;
import com.zhanghao.util.Constant;
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

    private final String defaultImg="https://images.unsplash.com/photo-1489211914964-32c31f87e86b?w=1080&h=1920";

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
//
//    @RequestMapping("/start-image")
//    public @ResponseBody SplashImgBean getSplashImg(){
//        SplashImgBean bean=new SplashImgBean();
//        try {
//            Document document= Jsoup.connect("https://unsplash.com/new")
//                    .timeout(10*1000)
//                    .get();
//            System.out.println(document.outerHtml());
//            Element element=document.getElementById("gridMulti").select("div._3vgBX>a").first();
//            String styleText=element.attr("style");
//            int begin=styleText.indexOf("https:");
//            int end=styleText.indexOf("?");
//            String img=styleText.substring(begin,end);
//            String realUrl=img+"?w=1080&h=1920";
//            bean.setText("nothing");
//            bean.setImg(realUrl);
//            logger.info(img);
//        } catch (IOException e) {
//            e.printStackTrace();
//            bean.setText("nothing");
//            bean.setImg(defaultImg);
//            return bean;
//        }
//        return bean;
//    }




    @RequestMapping("/start-img")
    public @ResponseBody
    CommonResponse<String> getSplashImage(){
        CommonResponse<String> commonResponse=new CommonResponse<>();
        try {
            Document document= Jsoup.connect("https://unsplash.com/new")
                    .timeout(10*1000)
                    .get();
            Element element=document.getElementById("gridMulti").select("div._3vgBX>a").first();
            String styleText=element.attr("style");
            int begin=styleText.indexOf("https:");
            int end=styleText.indexOf("?");
            String img=styleText.substring(begin,end);
            String realUrl=img+"?w=1080&h=1920";
            commonResponse.setResult(Constant.GET_START_IMG_SUCCESS);
            commonResponse.setContent(realUrl);
            logger.info(img);
        } catch (IOException e) {
            e.printStackTrace();
            commonResponse.setResult(Constant.GET_START_IMG_SUCCESS);
            commonResponse.setContent(defaultImg);
            return commonResponse;
        }
        return commonResponse;
    }










}
