package com.zhanghao.service;

import com.zhanghao.entity.GankDailyAllItemFWB;
import com.zhanghao.entity.GankFav;
import com.zhanghao.entity.GankItemFWB;
import com.zhanghao.entity.User;

import java.util.List;

/**
 * Created by 张浩 on 2017/2/2.
 */
public interface GankWebService {
    /**
     * Retrofit进行初始化
     */
    void init();

    /**
     * 获取最新的文章
     * @param date 日期
     * @return GankDailyAllItemFWB 对象
     */
    GankDailyAllItemFWB getLatestRes(String date);


    /**
     * 根据类型获取数据
     * @param type 类型
     * @param page 页数
     * @return gankitemFwb
     */
    GankItemFWB getResByType(String type,int page);




    /**
     * 根据用户id查询用户的收藏情况
     * @param userId  用户id
     * @return list
     */
    List<GankFav> getFavListById(int userId);


    /**
     * 根据类型和id获取用户收藏
     * @param userId 用户ID
     * @param type 收藏的类型
     * @return list
     */
    List<GankFav> getFavListByIdAndType(int userId,String type);


    boolean addOneFav(User user,GankItemFWB.ResultsBean itemFWB);

}
