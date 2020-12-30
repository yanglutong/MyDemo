package com.http.httplibrary.callback;

import com.http.exception.ApiException;
import com.http.exception.ExceptionEngine;
import com.http.httplibrary.disposable.RequestManagerIml;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 项目名：2007
 * 包名：  com.http.httplibrary.callback
 * 文件名：BaseObserver
 * 创建者：杨路通
 * 创建时间：2020/11/4  14:49
 * 描述：TODO
 */
public abstract class BaseObserver implements Observer {
    private String tag;
    @Override
    public void onSubscribe(Disposable d) {
        if(tag!=null){
            RequestManagerIml.getInstance().add(tag,d);
        }
    }

    @Override
    public void onNext(Object o) {
        success(o);
        if(tag!=null){
            RequestManagerIml.getInstance().remove(tag);
        }

    }

    @Override
    public void onError(Throwable e) {
        if(tag!=null){
            RequestManagerIml.getInstance().cancle(tag);
        }
        if(e instanceof ApiException){
            ApiException apiException= (ApiException) e;
            error(apiException.getMsg(),apiException.getCode());
        }else{
            error("未知异常", ExceptionEngine.UN_KNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
        if(tag!=null&&!RequestManagerIml.getInstance().isDispose(tag)){
            RequestManagerIml.getInstance().cancle(tag);
        }
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public abstract void success(Object object);

    public abstract void error(String error,int code);
}
