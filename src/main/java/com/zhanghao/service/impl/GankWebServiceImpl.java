package com.zhanghao.service.impl;

import com.zhanghao.dao.GankDao;
import com.zhanghao.entity.*;
import com.zhanghao.service.GankService;
import com.zhanghao.service.GankWebService;
import com.zhanghao.util.ValueComparator;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by 张浩 on 2017/1/30.
 */
@Service
public class GankWebServiceImpl implements GankWebService{


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
    public Gank getLatestRes(String date) {
        try {
            return gankService.getGankItemByDay(date).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GankTypeItem getResByType(String type, int page) {
        try {
            return gankService.getGankdataByType(type,page).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GankFavItem> getFavListByType(int userId, String type, int page, int count) {
        int offset=(page-1)*count;
        return gankDao.selectFavByType(userId,type,offset,count);
    }

//    @Override
//    public List<GankFavItem> getFavListById(int userId) {
//        return gankDao.selectFavById(userId);
//    }

//    @Override
//    public List<GankFavItem> getFavListByIdAndType(int userId, String type) {
//        return gankDao.selectFavByTypeAndId(userId,type);
//    }

    @Override
    public boolean addOneFav(User user, GankItem item) {
        int userId=user.getUserId();
        String itemId=item.get_id();
        String str = "";
        List<String> images=item.getImages();
        if (images!=null&&images.size()>0){
            for (String image : images) {
                str+=image+",";
            }
        }
        GankFavItem gankFav=new GankFavItem();
        gankFav.set_id(item.get_id());
        gankFav.setCreatedAt(item.getCreatedAt());
        gankFav.setDesc(item.getDesc());
        gankFav.setImages(str);
        gankFav.setPublishedAt(item.getPublishedAt());
        gankFav.setSource(item.getSource());
        gankFav.setType(item.getType());
        gankFav.setUrl(item.getUrl());
        gankFav.setUsed(item.isUsed()?1:0);
        gankFav.setWho(item.getWho());
        return gankDao.addOneFav(gankFav)>=0 && gankDao.addOneFav_User(userId,itemId)>0;
    }

    @Override
    public List<String> selectFavIdsByUserId(int userId) {
        return gankDao.selectFavIdsByUserId(userId);
    }

    @Override
    public List<String> selectFavIdsByUserIdAndType(int userId, String type) {
        return gankDao.selectFavIdsByUserIdAndType(userId,type);
    }

    @Override
    public boolean deleteOneFav_User(int userId, String _id) {
        return gankDao.deleteOneFav_User(userId,_id)>0;
    }

    @Override
    public GankTypeItem getDate(int page) {
        try {
            return gankService.getDate(page).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GankTypeItem getRandomData(String type) {
        try {
            return gankService.getRandomData(type).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean addOneHis(User user, GankItem item) {
        int userId=user.getUserId();
        String itemId=item.get_id();
        String str = "";
//        List<String> images=item.getImages();
//        if (images!=null&&images.size()>0){
//            for (String image : images) {
//                str+=image+",";
//            }
//        }
        GankFavItem gankHis =new GankFavItem();
        gankHis.set_id(item.get_id());
        gankHis.setCreatedAt(item.getCreatedAt());
        gankHis.setDesc(item.getDesc());
        gankHis.setImages(str);
        gankHis.setPublishedAt(item.getPublishedAt());
        gankHis.setSource(item.getSource());
        gankHis.setType(item.getType());
        gankHis.setUrl(item.getUrl());
        gankHis.setUsed(item.isUsed()?1:0);
        gankHis.setWho(item.getWho());
        return gankDao.addOneHis(gankHis)>=0 && gankDao.addOneHis_User(userId,itemId)>0;
    }


    @Override
    public List<String> selectHisTypeByUserId(int userId) {
        return gankDao.selectHisTypeByUserId(userId);
    }


    @Override
    public boolean addTags(User user, List<Tag> tags) {
        return false;
    }

    @Override
    public List<Tag> getAllTags() {
        return gankDao.selectAllTags();
    }

    @Override
    public List<String> selectTagsByUserId(int useriId) {
        return gankDao.selectUserTagsByUerId(useriId);
    }



    @Override
    public GankCustom getCustomData(int userId) {
        List<String> types = selectHisTypeByUserId(userId); //浏览数据统计
        List<String> tags = selectTagsByUserId(userId);    //用户标签选择
        List<String> allTags = new ArrayList<>();
        allTags.addAll(types);
        allTags.addAll(tags);

        List<Map.Entry<String,Integer>> customType = getCustomType(allTags);


        System.out.println(customType);


        GankCustom custom = new GankCustom();
        List<GankItem> customList = new ArrayList<>();
        List<GankItem> customPhotos = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : customType) {
            List<GankItem> items = getRandomData(entry.getKey()).getResult();
            //除了福利之外
            if (items!=null && !items.isEmpty() && !entry.getKey().equals("福利")){
                customList.addAll(items);
            }else if (items!=null && !items.isEmpty() && entry.getKey().equals("福利")){
                customPhotos.addAll(items);
            }
        }
        Collections.shuffle(customList);

        if (customList.size()>0){
            custom.setError(false);
            custom.setItemList(customList);
            custom.setPhotos(customPhotos);
            return custom;
        }else{
            custom.setError(true);
            custom.setItemList(null);
            custom.setPhotos(null);
            return custom;
        }

    }



    private List<Map.Entry<String, Integer>> getCustomType(List<String> types) {
        HashMap<String,Integer> map = new HashMap<>();
        for (String type : types) {
           if (map.containsKey(type)){
               int count = map.get(type);
               map.put(type,++count);
           }else{
               map.put(type,1);
           }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        ValueComparator comparator = new ValueComparator();
        Collections.sort(list,comparator);
        return list.subList(0,3);
    }
}
