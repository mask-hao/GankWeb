package com.zhanghao.dao;

import com.zhanghao.entity.GankFavItem;
import com.zhanghao.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 张浩 on 2017/2/2.
 */
public interface GankDao {
//    #{fav_id},#{fav_createdAt},#{fav_desc},#{fav_publishAt},#{fav_source},#{fav_type},#{fav_url},#{fav_used},#{fav_images},#{fav_who}

    int addOneFav(GankFavItem item);


    int addOneFav_User(@Param("userId")int userId,
                       @Param("fav_id")String fav_id);


    List<String> selectFavIdsByUserId(@Param("userId")int userId);

    List<String> selectFavIdsByUserIdAndType(@Param("userId")int userId,@Param("type")String type);

    List<GankFavItem> selectFavByType(@Param("userId")int userId,
                                      @Param("type")String type,
                                      @Param("offset")int offset,
                                      @Param("count")int count);
    int deleteOneFav_User(
            @Param("userId")int userId,
            @Param("_id")String _id
    );
    /*--------------------------------------------------------------------------------------*/

    int addOneHis(GankFavItem item);
    int addOneHis_User(@Param("userId") int userId,
                        @Param("_id")String _id
                        );
    List<String> selectHisTypeByUserId(@Param("userId")int UserId);


    int addOneTag_User(@Param("userId")int userId,
                       @Param("tagId")int tagId);

    List<String> selectUserTagsByUserId(@Param("userId")int userId);

    List<Tag> selectAllTags();

    String selectTagByTagId(@Param("id")int tagId);

}
