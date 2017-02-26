package com.zhanghao.entity;

/**
 * Created by 张浩 on 2017/2/2.
 */
public class GankFav {
    public String getFav_id() {
        return fav_id;
    }

    public void setFav_id(String fav_id) {
        this.fav_id = fav_id;
    }

    public String getFav_url() {
        return fav_url;
    }

    public void setFav_url(String fav_url) {
        this.fav_url = fav_url;
    }

    public String getFav_desc() {
        return fav_desc;
    }

    public void setFav_desc(String fav_desc) {
        this.fav_desc = fav_desc;
    }

    public String getFav_type() {
        return fav_type;
    }

    public void setFav_type(String fav_type) {
        this.fav_type = fav_type;
    }

    public String getFav_images() {
        return fav_images;
    }

    public void setFav_images(String fav_images) {
        this.fav_images = fav_images;
    }


    private String fav_id;
    private String fav_url;
    private String fav_desc;
    private String fav_type;

    @Override
    public String toString() {
        return "GankFav{" +
                "fav_id='" + fav_id + '\'' +
                ", fav_url='" + fav_url + '\'' +
                ", fav_desc='" + fav_desc + '\'' +
                ", fav_type='" + fav_type + '\'' +
                ", fav_images='" + fav_images + '\'' +
                '}';
    }

    private String fav_images;
}
