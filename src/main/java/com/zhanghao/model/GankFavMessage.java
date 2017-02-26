package com.zhanghao.model;

import com.zhanghao.entity.GankFav;

import java.util.List;

/**
 * Created by 张浩 on 2017/2/5.
 */
public class GankFavMessage {
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    private boolean error;
    private Content content;
    private List<String> category;
    public static class Content{
        public List<GankFav> getAndroid() {
            return android;
        }

        public void setAndroid(List<GankFav> android) {
            this.android = android;
        }

        public List<GankFav> getIos() {
            return ios;
        }

        public void setIos(List<GankFav> ios) {
            this.ios = ios;
        }

        public List<GankFav> getWeb() {
            return web;
        }

        public void setWeb(List<GankFav> web) {
            this.web = web;
        }

        private List<GankFav> android;
        private List<GankFav> ios;
        private List<GankFav> web;


    }
}
