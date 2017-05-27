package com.zhanghao.util;

import com.zhanghao.entity.Gank;
import com.zhanghao.entity.GankItem;
import com.zhanghao.entity.GankTypeItem;

import java.util.List;

/**
 * Created by zhanghao on 2017/5/1.
 */
public class GankHelper{


    private GankHelper(){

    }

    public static GankHelper getInstance(){
      return  GankHelperHolder.h;
    }

    private static class GankHelperHolder{
        private static final GankHelper h=new GankHelper();
    }



    public Gank progressData(Gank gank, List<String> ids) {

        Gank.ResultsBean resultsBean=gank.getResults();
        List<GankItem> androids=resultsBean.getAndroid();
        List<GankItem> ios=resultsBean.getiOS();
        List<GankItem> webs=resultsBean.getWeb();
        List<GankItem> expands=resultsBean.getExpands();
        List<GankItem> recommends=resultsBean.getRecommends();
        List<GankItem> apps=resultsBean.getApp();
        List<GankItem> videos=resultsBean.getVideo();


        if (androids!=null)
        for (GankItem gankItem : androids) {
            for (String id : ids) {
                if (gankItem.get_id().equals(id))
                    gankItem.setFav(true);
            }
        }
        if (ios!=null)
        for (GankItem gankItem :ios) {
            for (String id : ids) {
                if (gankItem.get_id().equals(id))
                    gankItem.setFav(true);
            }
        }
        if (webs!=null)
        for (GankItem gankItem : webs) {
            for (String id : ids) {
                if (gankItem.get_id().equals(id))
                    gankItem.setFav(true);
            }
        }

        if (recommends!=null)
        for (GankItem gankItem : recommends) {
            for (String id : ids) {
                if (gankItem.get_id().equals(id))
                    gankItem.setFav(true);
            }
        }

        if (apps!=null)
        for (GankItem gankItem : apps) {
            for (String id : ids) {
                if (gankItem.get_id().equals(id))
                    gankItem.setFav(true);
            }
        }

        if (expands!=null)
        for (GankItem gankItem : expands) {
            for (String id : ids) {
                if (gankItem.get_id().equals(id))
                    gankItem.setFav(true);
            }
        }

        if (videos!=null)
        for (GankItem gankItem : videos){
            for (String id : ids) {
                if (gankItem.get_id().equals(id))
                    gankItem.setFav(true);
            }
        }
        return gank;
    }

    public List<GankItem> progressData(GankTypeItem gankTypeItem,List<String> ids){
        List<GankItem> items=gankTypeItem.getResult();
        if (items!=null){
            for (GankItem item : items) {
                for (String id : ids) {
                    if (item.get_id().equals(id))
                        item.setFav(true);
                }
            }
        }
        return items;
    }


}
