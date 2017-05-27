package com.zhanghao.entity;

import java.util.List;

/**
 * Created by zhanghao on 2017/5/1.
 */
public class GankFavs {

    private boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankFavItem> getResult() {
        return result;
    }

    public void setResult(List<GankFavItem> result) {
        this.result = result;
    }

    private List<GankFavItem> result;
}
