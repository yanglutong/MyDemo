package com.example.mydemo.mvp.model;

import com.example.mvplibrary.base.model.BaseModel;
import com.example.mvplibrary.base.model.ModelBaseCallBack;
import com.example.mydemo.bean.LazyBean;
import com.example.mydemo.mvp.callback.LazyHttpCallBack;
import com.example.mydemo.mvp.constants.LazyConstants;
import com.google.gson.JsonElement;
import com.http.httplibrary.client.HttpClient;
import com.http.httplibrary.utils.JsonUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyLazyModel extends BaseModel implements LazyConstants.LazyModel {

    @Override
    public void getModelData(ModelBaseCallBack<ArrayList<LazyBean>> modelBaseCallBack, LifecycleProvider lifecycleProvider) {
        new HttpClient.Buidler()
                .get()
                .setBaseUrl("https://www.wanandroid.com/")
                .setApiUrl("banner/json")
                .setLifecycleProvider(lifecycleProvider)
                .build().enqueue(new LazyHttpCallBack<ArrayList<LazyBean>>() {
            @Override
            public void error(String error, int code) {
                modelBaseCallBack.onError(error, code);
            }

            @Override
            public ArrayList<LazyBean> convert(JsonElement result) {
                return JsonUtils.jsonToClassList(result, LazyBean.class);
            }

            @Override
            public void onSuccess(ArrayList<LazyBean> beans) {
                modelBaseCallBack.onSuccess(beans);
            }
        });
    }
}
