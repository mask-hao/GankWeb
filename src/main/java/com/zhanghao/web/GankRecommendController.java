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
        gankWebService.init();
        String token = user.getUserToken();
        if (userService.checkUser(token)){
            System.out.println("yonghu youxiao ");
            //个性推荐
            User info = userService.getUserByUserToken(token);
            int userId = info.getUserId();

            //todo 数据无效修复
            return gankWebService.getCustomData(userId);
        }else{
            System.out.println("yonghu wuxiao ");
            GankCustom custom=new GankCustom();
            custom.setError(true);
            return custom;
        }
    }




    @RequestMapping(value = "/getCustomRandomData",method = RequestMethod.POST)
    public @ResponseBody
    GankCustom getCustomData(@RequestBody List<Tag> tags) {
        gankWebService.init();
        return gankWebService.getRandomCustomData(tags);
    }





    @RequestMapping(value = "/getAllTags",method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<Tag>> getAllTags(){
        CommonResponse<List<Tag>> commonResponse = new CommonResponse<>();
        List<Tag> tags =  gankWebService.getAllTags();
        commonResponse.setContent(tags);
        commonResponse.setResult(Constant.GET_TAGS_SUCCESS);
        return commonResponse;
    }



    @RequestMapping(value = "/addTags/{token}",method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse<String> addTags(@RequestBody List<Tag> tags,@PathVariable("token")String token){
        CommonResponse<String> commonResponse = new CommonResponse<>();


        System.out.println(tags);

        if(userService.checkUser(token)){
            User info = userService.getUserByUserToken(token);
            int userId = info.getUserId();
            if (gankWebService.addTags(userId,tags)){
                commonResponse.setResult(Constant.UPDATE_TAGS_SUCCESS);
                commonResponse.setContent("");
                return  commonResponse;
            }else{
                commonResponse.setResult(Constant.UPDATE_TAGS_FAILED);
                commonResponse.setContent("");
                return  commonResponse;
            }


        }else{
            commonResponse.setResult(Constant.USER_INVALID);
            commonResponse.setContent(Constant.SAVE_IN_LOCALDB);
            return commonResponse;
        }
    }


}
