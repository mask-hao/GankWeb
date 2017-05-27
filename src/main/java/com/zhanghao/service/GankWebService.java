package com.zhanghao.service;
import com.zhanghao.entity.*;

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
     * @return Gank 对象
     */
    Gank getLatestRes(String date);


    /**
     * 根据类型获取数据
     * @param type 类型
     * @param page 页数
     * @return gankitemFwb
     */
    GankTypeItem getResByType(String type, int page);




    /**
     * 根据用户id查询用户的收藏情况
     * @param userId  用户id
     * @return list
     */
//    List<GankItem> getFavListById(int userId);


    /**
     * 根据类型和id获取用户收藏
     * @param userId 用户ID
     * @param type 收藏的类型
     * @return list
     */
     List<GankFavItem> getFavListByType(int userId, String type, int page, int count);


    boolean addOneFav(User user,GankItem item);

    boolean deleteOneFav_User(int userId,String _id);

    List<String> selectFavIdsByUserId(int userId);


    List<String> selectFavIdsByUserIdAndType(int userId,String type);

    /**
     * 获取日期
     * @param page
     * @return
     */
    GankTypeItem getDate(int page);

}
