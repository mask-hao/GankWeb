package com.zhanghao.entity;

import java.util.List;

/**
 * Created by 张浩 on 2017/1/30.
 */
public class GankItemFWB {

    /**
     * error : false
     * results : [{"_id":"587cd403421aa911912f52c7","createdAt":"2017-01-16T22:09:07.915Z","desc":"Android 任意View边沿渐变透明(类似视频直播评论列表边沿处理)","images":["http://img.gank.io/9fbf4dc7-3f14-4cba-9214-8c36f021abb9"],"publishedAt":"2017-01-23T11:35:32.450Z","source":"web","type":"Android","url":"https://github.com/qinci/EdgeTranslucent","used":true,"who":null},{"_id":"587f7a77421aa911912f52d6","createdAt":"2017-01-18T22:23:51.619Z","desc":"integrate Camera Fragment for Android","publishedAt":"2017-01-23T11:35:32.450Z","source":"chrome","type":"Android","url":"https://github.com/florent37/CameraFragment","used":true,"who":"Jason"},{"_id":"587f93cc421aa9119735ac49","createdAt":"2017-01-19T00:11:56.530Z","desc":"一个解耦良好的计时控件，可自由扩展","images":["http://img.gank.io/9c0c6a8d-2f14-4472-8f1e-3f304b5530b3"],"publishedAt":"2017-01-23T11:35:32.450Z","source":"chrome","type":"Android","url":"https://github.com/fashare2015/TimerView","used":true,"who":"Jason"},{"_id":"58848fe7421aa95eae2d92ae","createdAt":"2017-01-22T18:56:39.230Z","desc":"非侵入式的 RxLifecycle","images":["http://img.gank.io/7dee57c9-3e46-49d4-aa5e-c4558dff0d40"],"publishedAt":"2017-01-23T11:35:32.450Z","source":"web","type":"Android","url":"https://github.com/nekocode/rxlifecycle","used":true,"who":"nekocode"},{"_id":"58855557421aa95eae2d92b0","createdAt":"2017-01-23T08:59:03.7Z","desc":"在RecyclerView上实现吸顶布局","images":["http://img.gank.io/03fb2407-9a99-4975-9cdb-14f83143ebc1"],"publishedAt":"2017-01-23T11:35:32.450Z","source":"web","type":"Android","url":"https://github.com/TellH/RecyclerStickyHeaderView","used":true,"who":null},{"_id":"58857300421aa95ea7cbcf05","createdAt":"2017-01-23T11:05:36.387Z","desc":"Android SVG 动画效果，做的非常棒。","images":["http://img.gank.io/34edb12d-b657-4424-80c1-9790b9fc9653"],"publishedAt":"2017-01-23T11:35:32.450Z","source":"chrome","type":"Android","url":"https://github.com/jrummyapps/AnimatedSvgView","used":true,"who":"代码家"},{"_id":"58807d41421aa911912f52e3","createdAt":"2017-01-19T16:48:01.15Z","desc":"实现 RecyclerView 布局切换动画的示例","images":["http://img.gank.io/589e9554-0c6f-4856-bd8f-c2208f2fdadb"],"publishedAt":"2017-01-22T11:39:29.779Z","source":"web","type":"Android","url":"https://github.com/gjiazhe/LayoutSwitch","used":true,"who":"郭佳哲"},{"_id":"588418ad421aa95ea7cbcf00","createdAt":"2017-01-22T10:27:57.636Z","desc":"Android 通过 JSON \b实现原生 UI 布局，太棒了这个！","images":["http://img.gank.io/a530fc9c-bd23-43f4-a610-6112fb3c8426"],"publishedAt":"2017-01-22T11:39:29.779Z","source":"chrome","type":"Android","url":"https://github.com/flipkart-incubator/proteus","used":true,"who":"代码家"},{"_id":"58841902421aa95ead13c4c1","createdAt":"2017-01-22T10:29:22.989Z","desc":"Android Textview Fading 动画效果。","images":["http://img.gank.io/b11f12b4-233e-4eb8-a0cf-8ff834d6d19f"],"publishedAt":"2017-01-22T11:39:29.779Z","source":"chrome","type":"Android","url":"https://github.com/rosenpin/FadingTextView","used":true,"who":"代码家"},{"_id":"58841a85421aa95eae2d92aa","createdAt":"2017-01-22T10:35:49.542Z","desc":"又一个漂亮的 Android MD 风格日历效果。","images":["http://img.gank.io/58902b99-37a4-432f-8d33-d4feb15f5136"],"publishedAt":"2017-01-22T11:39:29.779Z","source":"chrome","type":"Android","url":"https://github.com/jMavarez/MaterialCalendar","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 587cd403421aa911912f52c7
         * createdAt : 2017-01-16T22:09:07.915Z
         * desc : Android 任意View边沿渐变透明(类似视频直播评论列表边沿处理)
         * images : ["http://img.gank.io/9fbf4dc7-3f14-4cba-9214-8c36f021abb9"]
         * publishedAt : 2017-01-23T11:35:32.450Z
         * source : web
         * type : Android
         * url : https://github.com/qinci/EdgeTranslucent
         * used : true
         * who : null
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private Object who;

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who=" + who +
                    ", images=" + images +
                    ", fav=" + fav +
                    '}';
        }

        private List<String> images;
        private boolean fav;

        public boolean isFav() {
            return fav;
        }

        public void setFav(boolean fav) {
            this.fav = fav;
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

        public Object getWho() {
            return who;
        }

        public void setWho(Object who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
