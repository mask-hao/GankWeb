package com.zhanghao.service.impl;

import com.zhanghao.dao.GankDao;
import com.zhanghao.entity.GankDailyAllItemFWB;
import com.zhanghao.entity.GankFav;
import com.zhanghao.entity.GankItemFWB;
import com.zhanghao.entity.User;
import com.zhanghao.service.GankService;
import com.zhanghao.service.GankWebService;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by 张浩 on 2017/1/30.
 */
@Service
public class GankWebServiceImpl implements GankWebService {


    @Resource
    private GankDao gankDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String baseUrl = "http://gank.io";
    private GankService gankService;

    @Override
    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gankService = retrofit.create(GankService.class);

    }

    @Override
    public GankDailyAllItemFWB getLatestRes(String date) {
        try {
            return gankService.getGankItemByDay(date).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GankItemFWB getResByType(String type, int page) {
        try {
            return gankService.getGankdataByType(type,page).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GankFav> getFavListById(int userId) {
        return gankDao.selectFavById(userId);
    }

    @Override
    public List<GankFav> getFavListByIdAndType(int userId, String type) {
        return gankDao.selectFavByTypeAndId(userId,type);
    }

    @Override
    public boolean addOneFav(User user, GankItemFWB.ResultsBean itemFWB) {
        int userId = user.getUserId();
        String fav_id = itemFWB.get_id();
        String fav_type = itemFWB.getType();
        String fav_desc = itemFWB.getDesc();
        String fav_url = itemFWB.getUrl();
        String fav_images = "";
        List<String> imagesList = itemFWB.getImages();
        if (imagesList != null && imagesList.size() > 0) {
            fav_images += ",";
        }
        return gankDao.addOneFav(fav_id, fav_desc, fav_type, fav_images, fav_url) >= 0
                && (gankDao.addOneFav_User(userId, fav_id) > 0);

    }

}
