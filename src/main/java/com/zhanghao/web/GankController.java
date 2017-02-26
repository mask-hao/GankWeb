package com.zhanghao.web;

import com.zhanghao.entity.*;
import com.zhanghao.model.CommonMessage;
import com.zhanghao.model.GankFavMessage;
import com.zhanghao.service.UserService;
import com.zhanghao.service.GankWebService;
import com.zhanghao.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张浩 on 2017/1/30.
 */
@Controller
@RequestMapping("/api")
public class GankController {


    @Resource
    private UserService userService;
    @Resource
    private GankWebService gankWebService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/addFav/{token}", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonMessage addFavorite(@RequestBody GankItemFWB.ResultsBean resultsBean, @PathVariable("token") String token) {
        CommonMessage message = new CommonMessage();
        if (userService.checkUser(token)) {
            User userInfo = userService.getUserByUserToken(token);
            if (gankWebService.addOneFav(userInfo, resultsBean)) {
                message.setResult(Constant.ADDFAV_SUCCESS);
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

    /**
     * @param year  年
     * @param month 月
     * @param day   日
     * @param user  用户实体
     */
    @RequestMapping(value = "/day/{year}/{month}/{day}", method = RequestMethod.POST)
    public
    @ResponseBody
    GankDailyAllItemFWB getLatestRes(@PathVariable("year") String year,
                                     @PathVariable("month") String month,
                                     @PathVariable("day") String day,
                                     @RequestBody User user) {
        String userToken = user.getUserToken();
        String date = year + "/" + month + "/" + day;
        gankWebService.init();
        GankDailyAllItemFWB gankDailyAllItemFWB = gankWebService.getLatestRes(date);
        if (userService.checkUser(userToken)) {
            return processApi(gankDailyAllItemFWB, getUserFavList(userToken));
        } else return gankDailyAllItemFWB;
    }

    private GankDailyAllItemFWB processApi(GankDailyAllItemFWB gankDailyAllItemFWB, List<GankFav> gankFavs) {
        GankDailyAllItemFWB.ResultsBean resultBean = gankDailyAllItemFWB.getResults();
        List<GankDailyAllItemFWB.ResultsBean.AndroidBean> androidList = resultBean.getAndroid();
        List<GankDailyAllItemFWB.ResultsBean.IOSBean> iOSList = resultBean.getIOS();
        List<GankDailyAllItemFWB.ResultsBean.前端Bean> webList = resultBean.get前端();

        if (gankFavs != null && gankFavs.size() > 0) {

            List<GankFav> androidFavList = new ArrayList<>();
            List<GankFav> iOSFavList = new ArrayList<>();
            List<GankFav> webFavList = new ArrayList<>();

            for (GankFav gankFav : gankFavs) {
                String type = gankFav.getFav_type();
                switch (type) {
                    case "Android":
                        androidFavList.add(gankFav);
                        continue;
                    case "iOS":
                        iOSFavList.add(gankFav);
                        continue;
                    case "前端":
                        webFavList.add(gankFav);
                        continue;
                    default:
                }
            }
            if (androidFavList.size() > 0)
                for (GankFav gankFav : androidFavList) {
                    String favUrl = gankFav.getFav_url();
                    if (androidList != null && androidList.size() > 0) {
                        for (GankDailyAllItemFWB.ResultsBean.AndroidBean oldAndroid : androidList) {
                            String oldUrl = oldAndroid.getUrl().trim();
                            if (favUrl.equals(oldUrl))
                                oldAndroid.setFav(true);
                        }
                    }
                }

            if (iOSFavList.size() > 0) {
                for (GankFav gankFav : iOSFavList) {
                    String favUrl = gankFav.getFav_url();
                    if (iOSList != null && iOSList.size() > 0) {
                        for (GankDailyAllItemFWB.ResultsBean.IOSBean oldIos : iOSList) {
                            String oldUrl = oldIos.getUrl().trim();
                            if (favUrl.equals(oldUrl))
                                oldIos.setFav(true);
                        }
                    }
                }
            }

            if (webFavList.size() > 0) {
                for (GankFav gankFav : webFavList) {
                    String favUrl = gankFav.getFav_url();
                    if (webList != null && webList.size() > 0) {
                        for (GankDailyAllItemFWB.ResultsBean.前端Bean oldWeb : webList) {
                            String oldUrl = oldWeb.getUrl();
                            if (oldUrl.equals(favUrl))
                                oldWeb.setFav(true);
                        }
                    }
                }
            }
        }
        return gankDailyAllItemFWB;
    }


    @RequestMapping(value = "/getFav/{token}", method = RequestMethod.GET)
    public
    @ResponseBody
    GankFavMessage getUserFav(@PathVariable("token") String token) {
        GankFavMessage message = new GankFavMessage();
        if (userService.checkUser(token)) {
            List<GankFav> userFavList = getUserFavList(token);
            if (userFavList != null && userFavList.size() > 0) {
                List<GankFav> androidFavList = new ArrayList<>();
                List<GankFav> iOSFavList = new ArrayList<>();
                List<GankFav> webFavList = new ArrayList<>();
                for (GankFav gankFav : userFavList) {
                    String type = gankFav.getFav_type();
                    switch (type) {
                        case "Android":
                            androidFavList.add(gankFav);
                            continue;
                        case "iOS":
                            iOSFavList.add(gankFav);
                            continue;
                        case "前端":
                            webFavList.add(gankFav);
                            continue;
                        default:
                    }
                }
                GankFavMessage.Content content = new GankFavMessage.Content();
                List<String> category = new ArrayList<>();
                if (androidFavList.size() > 0) {
                    content.setAndroid(androidFavList);
                    category.add("Android");
                }
                if (iOSFavList.size() > 0) {
                    content.setIos(iOSFavList);
                    category.add("iOS");
                }
                if (webFavList.size() > 0) {
                    content.setWeb(webFavList);
                    category.add("Web");
                }
                message.setCategory(category);
                message.setContent(content);
                message.setError(false);
                return message;
            } else {
                message.setError(false);
                message.setCategory(null);
                message.setContent(null);
                return message;
            }
        } else {
            message.setCategory(null);
            message.setError(true);
            message.setContent(null);
            return message;
        }
    }


    /**
     * 获取用户的收藏列表
     * @param token 用户凭证
     * @return list
     */
    private List<GankFav> getUserFavList(String token){
        User userInfo = userService.getUserByUserToken(token);
        int id = userInfo.getUserId();
        return gankWebService.getFavListById(id);
    }


    /**
     * 根据类型获取用户的收藏
     * @param token 用户凭证
     * @param type 收藏的类型
     * @return list
     */
    private List<GankFav> getUserFavListByType(String token,String type){
        User info=userService.getUserByUserToken(token);
        int id=info.getUserId();
        return gankWebService.getFavListByIdAndType(id,type);
    }



    @RequestMapping(value = "/data/{type}/{page}",method = RequestMethod.POST)
    public
    @ResponseBody
    GankItemFWB getResByType(@PathVariable("type")String type,
                             @PathVariable("page")int page,
                             @RequestBody User user){
        GankItemFWB gankItemFWB;
        gankWebService.init();
        try{
            gankItemFWB=gankWebService.getResByType(type,page);
        }catch (NullPointerException e){
            e.printStackTrace();
            return new GankItemFWB();
        }
        String token=user.getUserToken();
        if (userService.checkUser(token))
            return proFavItem1(token,type,gankItemFWB);
        else
            return gankItemFWB;

    }

    private GankItemFWB proFavItem1(String token,String type,GankItemFWB gankItemFWB){
        switch (type){
            case Constant.ANDROID:
                List<GankFav> androidFavList=getUserFavListByType(token,Constant.ANDROID);
                return proFavItem2(gankItemFWB,androidFavList);
            case Constant.IOS:
                List<GankFav> iosFavList=getUserFavListByType(token,Constant.IOS);
                return proFavItem2(gankItemFWB,iosFavList);
            case Constant.WEB:
                List<GankFav> webFavList=getUserFavListByType(token,Constant.WEB);
                return proFavItem2(gankItemFWB,webFavList);
            default:
                return gankItemFWB;
        }
    }

    /**
     * 对于按类型加载的数据的收藏标志的处理
     * @param gankItemFWB 数据
     * @param gankFavList 收藏数据
     * @return gankweb
     */
    private GankItemFWB proFavItem2(GankItemFWB gankItemFWB, List<GankFav> gankFavList){
        if (gankFavList!=null&&gankFavList.size()>0){
            List<GankItemFWB.ResultsBean> resultsBeanList=gankItemFWB.getResults();
            for (GankFav gankFav : gankFavList) {
                String fav_Url=gankFav.getFav_url();
                for (GankItemFWB.ResultsBean resultsBean : resultsBeanList) {
                    if (fav_Url.equals(resultsBean.getUrl()))
                        resultsBean.setFav(true);
                }
            }
        }
        return gankItemFWB;
    }



}

