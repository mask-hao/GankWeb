package com.zhanghao.web;

import com.mysql.jdbc.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/path",method = RequestMethod.GET)
    public void getFilePath(HttpServletRequest request){
        String path="/usr/local/apache-tomcat-8.5.5/webapps/resource/images/3b02897c-29d0-4964-82e5-bdb8f3dd0c63.png";
        int a=path.indexOf("resource");
        String newPath= path.substring(a,path.length());
        logger.info(newPath);
    }
}
