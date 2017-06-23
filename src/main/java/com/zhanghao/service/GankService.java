package com.zhanghao.service;

import com.zhanghao.entity.Gank;
import com.zhanghao.entity.GankItem;
import com.zhanghao.entity.GankTypeItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by zhanghao on 2016/11/19.
 */

public interface GankService{

    /*
    *
    *   分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
        数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
        请求个数： 数字，大于0
        第几页：数字，大于0
    *
    *
    * */
    @GET("/api/data/{type}/10/{page}")
    Call<GankTypeItem> getGankdataByType(
            @Path("type") String type,
            @Path("page") int page);


    //每天数据
    @GET("/api/day/{date}")
    Call<Gank> getGankItemByDay(
            @Path("date") String date
    );


    @GET("/api/data/福利/1/{page}")
    Call<GankTypeItem> getDate(
            @Path("page")int page
            );



    @GET("/api/random/data/{type}/{count}")
    Call<GankTypeItem> getRandomData(@Path("type")String type,@Path("count")int count);


}
