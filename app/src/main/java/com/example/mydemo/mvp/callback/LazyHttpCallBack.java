package com.example.mydemo.mvp.callback;

import com.google.gson.JsonElement;
import com.http.httplibrary.callback.BaseCallBack;
import com.http.httplibrary.utils.JsonUtils;

/**
 * 项目名：2007
 * 包名：  com.example.liangxq.myapplication2001
 * 文件名：HttpCallBack
 * 创建者：liangxq
 * 创建时间：2020/11/6  11:01
 * 描述：TODO
 */
public abstract class LazyHttpCallBack<T> extends BaseCallBack<T>{
    Response response;
    @Override
    protected T onConvert(String result) {
        T t = null;
        response = JsonUtils.jsonToClass(result, Response.class);
        JsonElement data = response.getData();
        int status = response.getErrorCode();
        String message = response.getErrorMsg();
        switch (status){
            case -1001:
                error(message,status);
                break;
            case 0:
                t=convert(data);
                break;
        }
        return t;
    }

    @Override
    public boolean isCodeSuccess() {
        return response.getErrorCode()==0;
    }

}
