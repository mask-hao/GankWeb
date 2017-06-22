package com.zhanghao.web;

import com.zhanghao.entity.*;
import com.zhanghao.model.CommonResponse;
import com.zhanghao.service.UserService;
import com.zhanghao.service.GankWebService;
import com.zhanghao.util.Constant;
import com.zhanghao.util.GankHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.WebContentGenerator;
import retrofit2.http.Body;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by 张浩 on 2017/1/30.
 */
@Controller
@RequestMapping(value = "/api",produces = {"application/json;charset=UTF-8"})
public class GankController{


    @Resource
    private UserService userService;
    @Resource
    private GankWebService gankWebService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GankHelper gankHelper=GankHelper.getInstance();

    @RequestMapping(value = "/getDate/{page}",method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<String> getDate(@PathVariable("page")int page){


        CacheControl cacheControl=CacheControl.maxAge(0, TimeUnit.SECONDS);
        cacheControl.cachePublic();

           CommonResponse<String> commonResponse =new CommonResponse<>();
           gankWebService.init();
           GankTypeItem typeItem = gankWebService.getDate(page);
           if (typeItem!=null){
              String published = typeItem.getResult().get(0).getPublishedAt();
              String date = getFormatDate(published);
              if (!date.isEmpty()){
                  commonResponse.setResult(Constant.GET_DATE_SUCCESS);
                  commonResponse.setContent(date);
                  return commonResponse;
              }else{
                  commonResponse.setResult(Constant.GET_DATE_FAILED);
                  commonResponse.setContent("");
                  return commonResponse;
              }
           }else{
               commonResponse.setResult(Constant.GET_DATE_FAILED);
               commonResponse.setContent("");
               return commonResponse;
           }

    }

    private String getFormatDate(String date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date1= null;
        try {
            date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        dateFormat.applyPattern("yyyy/MM/dd");
        return dateFormat.format(date1);
    }


    @RequestMapping(value = "/addFav/{token}", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse<String> addFavorite(@RequestBody GankItem gankItem, @PathVariable("token") String token) {
        CommonResponse<String> message = new CommonResponse<>();
        if (userService.checkUser(token)) {
            User userInfo = userService.getUserByUserToken(token);
            if (gankWebService.addOneFav(userInfo, gankItem)) {
                message.setResult(Constant.ADDFAV_SUCCESS);
                message.setContent("");
                return message;
            } else {
                message.setResult(Constant.ADDFAV_ERROR);
                message.setContent(Constant.DBUPDATE_ERROR);
                return message;
            }
        } else {
            message.setResult(Constant.ADDFAV_ERROR);
            message.setContent(Constant.USER_INVALID);
            return message;
        }
    }





    @RequestMapping(value = "deleteFav/{_id}",method = RequestMethod.POST)
    public @ResponseBody CommonResponse<String> deleteOneFav(@RequestBody User user,@PathVariable("_id")String _id){
        CommonResponse<String> commonResponse=new CommonResponse<>();
        String token=user.getUserToken();
        if (userService.checkUser(token)&&!_id.isEmpty()){
            int userId=userService.getUserByUserToken(token).getUserId();
           if (gankWebService.deleteOneFav_User(userId,_id)){
                 commonResponse.setResult(Constant.DELETE_FAV_SUCCESS);
                 commonResponse.setContent("");
                 return commonResponse;
           }else{
               commonResponse.setResult(Constant.DELETE_FAV_ERROR);
               commonResponse.setContent(Constant.DBUPDATE_ERROR);
               return commonResponse;
           }
        }else{
            commonResponse.setResult(Constant.DELETE_FAV_ERROR);
            commonResponse.setContent(Constant.USER_INVALID);
            return commonResponse;
        }
    }


    @RequestMapping(value = "/getFav/{type}/{page}/{count}",method = RequestMethod.POST)
    public
    @ResponseBody
    GankFavs getFavs(@RequestBody User user,
                     @PathVariable("type") String type,
                     @PathVariable("page")int page,
                     @PathVariable("count")int count){
        GankFavs favs=new GankFavs();
        if (userService.checkUser(user.getUserToken())){
            String token=user.getUserToken();
            int userId = userService.getUserByUserToken(token).getUserId();
            List<GankFavItem> list=gankWebService.getFavListByType(userId,type,page,count);

            if (list!=null && list.size()>0){
                favs.setError(false);
                Collections.reverse(list);
                favs.setResult(list);
                return favs;
            }else{
                favs.setError(true);
                favs.setMessage(Constant.NO_MORE_DATA);
                favs.setResult(null);
                return favs;
            }
        }else{
            favs.setError(true);
            favs.setMessage(Constant.USER_INVALID);
            favs.setResult(null);
            return favs;
        }
    }


    /**
     * @param year  年
     * @param month 月
     * @param day   日
     * @param user  用户实体
     */
    @RequestMapping(value = "/day/{year}/{month}/{day}", method = RequestMethod.POST)
    public
    @ResponseBody
    Gank getLatestRes(@PathVariable("year") String year,
                                     @PathVariable("month") String month,
                                     @PathVariable("day") String day,
                                     @RequestBody User user) {
        String userToken = user.getUserToken();
        String date = year + "/" + month + "/" + day;


        gankWebService.init();
        Gank gank = gankWebService.getLatestRes(date);
        if (userService.checkUser(userToken)) {
            int userId=userService.getUserByUserToken(userToken).getUserId();
            List<String> ids=gankWebService.selectFavIdsByUserId(userId);
            if (ids==null||ids.isEmpty())
                return gank;
            else
                return gankHelper.progressData(gank,ids);
        } else
            return gank;
    }


    @RequestMapping(value = "/data/{type}/{page}", method = RequestMethod.POST)
    public
    @ResponseBody
    GankTypeItem getResByType(@PathVariable("type") String type,
                              @PathVariable("page") int page,
                              @RequestBody User user) {
        GankTypeItem gankItems;
        gankWebService.init();
        try {
            gankItems = gankWebService.getResByType(type, page);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new GankTypeItem();
        }
        String token = user.getUserToken();
        if (userService.checkUser(token)) {
            int userId = userService.getUserByUserToken(token).getUserId();
            List<String> ids = gankWebService.selectFavIdsByUserIdAndType(userId, type);
            if (ids == null || ids.isEmpty())
                return gankItems;
            else {
                List<GankItem> list = gankHelper.progressData(gankItems, ids);
                gankItems.setResult(list);
                return gankItems;
            }
        } else
            return gankItems;

    }

}

