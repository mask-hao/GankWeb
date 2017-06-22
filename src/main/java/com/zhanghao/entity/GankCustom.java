package com.zhanghao.entity;

import java.util.List;

/**
 * Created by zhanghao on 2017/6/22.
 */
public class GankCustom {
    private boolean error;
    private List<GankItem> itemList;
    private List<GankItem> photos;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<GankItem> itemList) {
        this.itemList = itemList;
    }


    public List<GankItem> getPhotos() {
        return photos;
    }

    public void setPhotos(List<GankItem> photos) {
        this.photos = photos;
    }
}
