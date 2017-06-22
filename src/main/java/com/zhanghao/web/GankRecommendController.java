package com.zhanghao.web;

import com.zhanghao.entity.GankCustom;
import com.zhanghao.entity.GankItem;
import com.zhanghao.entity.Tag;
import com.zhanghao.entity.User;
import com.zhanghao.model.CommonResponse;
import com.zhanghao.service.GankWebService;
import com.zhanghao.service.UserService;
import com.zhanghao.util.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.GET;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghao on 2017/6/22.
 */


@Controller
@RequestMapping(value = "/api", produces = {"application/json;charset=UTF-8"})
public class GankRecommendController {

    @Resource
    private UserService userService;
    @Resource
    private GankWebService gankWebService;

    @RequestMapping(value = "/addHis/{token}",method = RequestMethod.POST)
    public @ResponseBody
    void addOneHis(@RequestBody GankItem item,@PathVariable("token") String token) {
        if (userService.checkUser(token)){
                User userInfo = userService.getUserByUserToken(token);
                gankWebService.addOneHis(userInfo,item);
        }
    }


    @RequestMapping(value = "/getCustomData",method = RequestMethod.POST)
    public @ResponseBody
    GankCustom getCustomData(@RequestBody User user) {
        String token = user.getUserToken();
        if (userService.checkUser(token)){
            //个性推荐
            User info = userService.getUserByUserToken(token);
            int userId = info.getUserId();
            gankWebService.init();
            return gankWebService.getCustomData(userId);
        }else{
            //随机返回
            return null;
        }
    }



    @RequestMapping(value = "/getAllTags",method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<Tag>> getAllTags(){
        CommonResponse<List<Tag>> commonResponse = new CommonResponse<>();
        List<Tag> tags =  gankWebService.getAllTags();
        commonResponse.setContent(tags);
        commonResponse.setResult(Constant.GET_DATE_FAILED);
        return commonResponse;
    }


}
