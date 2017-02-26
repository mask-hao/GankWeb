package com.zhanghao.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by zhanghao on 2016/11/28.
 */

public class GankDailyAllItemFWB {

    /**
     * category : ["Android","休息视频","拓展资源","福利","前端","iOS"]
     * error : false
     * results : {"Android":[{"_id":"583129bf421aa929ac960afc","createdAt":"2016-11-20T12:42:39.884Z","desc":"Android 实现视屏播放器、边播边缓存功能、外加铲屎（IJKPlayer）","images":["http://img.gank.io/8196d110-32cf-41bc-86c6-801af152a743"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/9fe377dd9750","used":true,"who":"Jason"},{"_id":"583a2a98421aa91cb7afe7f4","createdAt":"2016-11-27T08:36:40.493Z","desc":"很赞的登录注册布局","images":["http://img.gank.io/dacc7f4c-3872-4c00-b669-3ab13b430e01"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"Android","url":"https://github.com/irfaan008/OnePageSigninSignup","used":true,"who":"蒋朋"},{"_id":"583b7e97421aa9711460f744","createdAt":"2016-11-28T08:47:19.286Z","desc":"清晰灵活简单易用的应用更新库","images":["http://img.gank.io/9d7deebb-3fa8-43dc-a36c-81a11044b394"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"web","type":"Android","url":"https://github.com/czy1121/update","used":true,"who":"ezy"},{"_id":"583b99d1421aa9710cf54c3e","createdAt":"2016-11-28T10:43:29.756Z","desc":"目测是目前来看做 Blur 效果速度最快的库","images":["http://img.gank.io/f826f969-027d-43d6-bb00-a89684e37346"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"Android","url":"https://github.com/wonderkiln/blurkit-android","used":true,"who":"嗲马甲"}],"iOS":[{"_id":"583ba071421aa9710cf54c40","createdAt":"2016-11-28T11:11:45.849Z","desc":"自动滚动Label","publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"iOS","url":"https://github.com/cbess/AutoScrollLabel","used":true,"who":"Dear宅学长"}],"休息视频":[{"_id":"5831b1f4421aa929ac960b00","createdAt":"2016-11-20T22:23:48.641Z","desc":"【单身狗慎入】经典表白镜头混剪","publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av1774863/","used":true,"who":"lxxself"}],"前端":[{"_id":"583b9a54421aa971108b658e","createdAt":"2016-11-28T10:45:40.970Z","desc":"Vue 实现的 Markdown 编辑器","images":["http://img.gank.io/85faeba8-c2f3-46bc-b04e-568d1da8f9b0"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"前端","url":"https://github.com/egoist/eme","used":true,"who":"代码家"},{"_id":"583b9aab421aa971108b658f","createdAt":"2016-11-28T10:47:07.234Z","desc":"Material CSS 框架","publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"前端","url":"https://github.com/Dogfalo/materialize","used":true,"who":"css"}],"拓展资源":[{"_id":"5838322e421aa91cb7afe7ed","createdAt":"2016-11-25T20:44:30.469Z","desc":"linux 下 使用vim聊qq以及微信，对于没有gui的用户来说，也许是福利。","images":["http://img.gank.io/4af93c14-5683-4a18-93d5-1734604396bc"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"web","type":"拓展资源","url":"https://github.com/wsdjeg/vim-chat","used":true,"who":"Wang Shidong"},{"_id":"583a6816421aa91cb7afe7f5","createdAt":"2016-11-27T12:59:02.254Z","desc":"EasyProxy,简单易用的TCP代理","publishedAt":"2016-11-28T11:32:07.534Z","source":"web","type":"拓展资源","url":"http://xsank.com/2016/11/25/EasyProxy%E4%B9%8Bgolang%E5%88%9D%E6%AD%A5%E5%AD%A6%E4%B9%A0/","used":true,"who":"xsank"},{"_id":"583b9a1e421aa9711460f748","createdAt":"2016-11-28T10:44:46.764Z","desc":"p2p vpn，路子很野，不知道会不会发展起来，PS. Rust 实现","publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"拓展资源","url":"https://github.com/dswd/vpncloud.rs","used":true,"who":"代码家"}],"福利":[{"_id":"583b8285421aa9710cf54c3b","createdAt":"2016-11-28T09:04:05.479Z","desc":"11-28","publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg","used":true,"who":"daimajia"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public static GankDailyAllItemFWB objectFromData(String str) {

        return new Gson().fromJson(str, GankDailyAllItemFWB.class);
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {
        private List<AndroidBean> Android;
        private List<IOSBean> iOS;
        private List<休息视频Bean> 休息视频;
        private List<前端Bean> 前端;
        private List<拓展资源Bean> 拓展资源;
        private List<福利Bean> 福利;
        private List<瞎推荐Bean> 瞎推荐;
        private List<AppBean> App;


        public List<AppBean> getApp() {
            return App;
        }

        public void setApp(List<AppBean> app) {
            App = app;
        }


        public List<瞎推荐Bean> get瞎推荐() {
            return 瞎推荐;
        }

        public void set瞎推荐(List<瞎推荐Bean> 瞎推荐) {
            this.瞎推荐 = 瞎推荐;
        }

        public static ResultsBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultsBean.class);
        }

        public List<AndroidBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<AndroidBean> Android) {
            this.Android = Android;
        }

        public List<IOSBean> getIOS() {
            return iOS;
        }

        public void setIOS(List<IOSBean> iOS) {
            this.iOS = iOS;
        }

        public List<休息视频Bean> get休息视频() {
            return 休息视频;
        }

        public void set休息视频(List<休息视频Bean> 休息视频) {
            this.休息视频 = 休息视频;
        }

        public List<前端Bean> get前端() {
            return 前端;
        }

        public void set前端(List<前端Bean> 前端) {
            this.前端 = 前端;
        }

        public List<拓展资源Bean> get拓展资源() {
            return 拓展资源;
        }

        public void set拓展资源(List<拓展资源Bean> 拓展资源) {
            this.拓展资源 = 拓展资源;
        }

        public List<福利Bean> get福利() {
            return 福利;
        }

        public void set福利(List<福利Bean> 福利) {
            this.福利 = 福利;
        }

        public static class AndroidBean {
            @Override
            public String toString() {
                return "AndroidBean{" +
                        "_id='" + _id + '\'' +
                        ", createdAt='" + createdAt + '\'' +
                        ", desc='" + desc + '\'' +
                        ", publishedAt='" + publishedAt + '\'' +
                        ", source='" + source + '\'' +
                        ", type='" + type + '\'' +
                        ", url='" + url + '\'' +
                        ", used=" + used +
                        ", who='" + who + '\'' +
                        ", images=" + images +
                        '}';
            }

            /**
             * _id : 583129bf421aa929ac960afc
             * createdAt : 2016-11-20T12:42:39.884Z
             * desc : Android 实现视屏播放器、边播边缓存功能、外加铲屎（IJKPlayer）
             * images : ["http://img.gank.io/8196d110-32cf-41bc-86c6-801af152a743"]
             * publishedAt : 2016-11-28T11:32:07.534Z
             * source : chrome
             * type : Android
             * url : http://www.jianshu.com/p/9fe377dd9750
             * used : true
             * who : Jason
             */




            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;
            private boolean fav;

            public boolean isFav() {
                return fav;
            }

            public void setFav(boolean fav) {
                this.fav = fav;
            }



            public static AndroidBean objectFromData(String str) {

                return new Gson().fromJson(str, AndroidBean.class);
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class IOSBean {
            /**
             * _id : 583ba071421aa9710cf54c40
             * createdAt : 2016-11-28T11:11:45.849Z
             * desc : 自动滚动Label
             * publishedAt : 2016-11-28T11:32:07.534Z
             * source : chrome
             * type : iOS
             * url : https://github.com/cbess/AutoScrollLabel
             * used : true
             * who : Dear宅学长
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private boolean fav;

            public boolean isFav() {
                return fav;
            }

            public void setFav(boolean fav) {
                this.fav = fav;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            private List<String> images;

            public static IOSBean objectFromData(String str) {

                return new Gson().fromJson(str, IOSBean.class);
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }

        public static class 休息视频Bean {
            /**
             * _id : 5831b1f4421aa929ac960b00
             * createdAt : 2016-11-20T22:23:48.641Z
             * desc : 【单身狗慎入】经典表白镜头混剪
             * publishedAt : 2016-11-28T11:32:07.534Z
             * source : chrome
             * type : 休息视频
             * url : http://www.bilibili.com/video/av1774863/
             * used : true
             * who : lxxself
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public static 休息视频Bean objectFromData(String str) {

                return new Gson().fromJson(str, 休息视频Bean.class);
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }

        public static class 前端Bean  {
            /**
             * _id : 583b9a54421aa971108b658e
             * createdAt : 2016-11-28T10:45:40.970Z
             * desc : Vue 实现的 Markdown 编辑器
             * images : ["http://img.gank.io/85faeba8-c2f3-46bc-b04e-568d1da8f9b0"]
             * publishedAt : 2016-11-28T11:32:07.534Z
             * source : chrome
             * type : 前端
             * url : https://github.com/egoist/eme
             * used : true
             * who : 代码家
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;
            private boolean fav;

            public boolean isFav() {
                return fav;
            }

            public void setFav(boolean fav) {
                this.fav = fav;
            }

            public static 前端Bean objectFromData(String str) {

                return new Gson().fromJson(str, 前端Bean.class);
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class 拓展资源Bean  {
            /**
             * _id : 5838322e421aa91cb7afe7ed
             * createdAt : 2016-11-25T20:44:30.469Z
             * desc : linux 下 使用vim聊qq以及微信，对于没有gui的用户来说，也许是福利。
             * images : ["http://img.gank.io/4af93c14-5683-4a18-93d5-1734604396bc"]
             * publishedAt : 2016-11-28T11:32:07.534Z
             * source : web
             * type : 拓展资源
             * url : https://github.com/wsdjeg/vim-chat
             * used : true
             * who : Wang Shidong
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public static 拓展资源Bean objectFromData(String str) {

                return new Gson().fromJson(str, 拓展资源Bean.class);
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class 福利Bean  {
            /**
             * _id : 583b8285421aa9710cf54c3b
             * createdAt : 2016-11-28T09:04:05.479Z
             * desc : 11-28
             * publishedAt : 2016-11-28T11:32:07.534Z
             * source : chrome
             * type : 福利
             * url : http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg
             * used : true
             * who : daimajia
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public static 福利Bean objectFromData(String str) {

                return new Gson().fromJson(str, 福利Bean.class);
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }

        public static class 瞎推荐Bean {
            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

        }

        public static class AppBean {
            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
        }

    }
}
