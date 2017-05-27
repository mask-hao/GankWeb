package com.zhanghao.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhanghao on 2017/5/1.
 */
public class GankTypeItem{

    @SerializedName("error")
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankItem> getResult() {
        return result;
    }

    public void setResult(List<GankItem> result) {
        this.result = result;
    }

    @SerializedName("results")
    private List<GankItem> result;
}
