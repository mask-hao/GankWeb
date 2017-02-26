package com.zhanghao.dao;

import com.zhanghao.entity.GankFav;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 张浩 on 2017/2/2.
 */
public interface GankDao {
    int addOneFav( @Param("fav_id")String fav_id,
                   @Param("fav_desc")String desc,
                   @Param("fav_type")String fav_type,
                   @Param("fav_images")String fav_images,
                   @Param("fav_url")String fav_url);
    int addOneFav_User(@Param("userId")int userId,
                       @Param("fav_id")String fav_id);
    List<GankFav> selectFavById(@Param("userId")int userId);
    List<GankFav> selectFavByTypeAndId(@Param("userId")int userId,
                                       @Param("fav_type")String fav_type);
}
