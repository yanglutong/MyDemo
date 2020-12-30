package com.example.mvplibrary.base.model;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.model
 * 文件名：ModelBaseCallBack
 * 创建者：杨路通
 * 创建时间：2020/11/7  14:28
 * 描述：TODO
 *
 * //回调数据接口
 */
public interface ModelBaseCallBack<T> {

    void onSuccess(T data);

    void onError(String msg,int code);
}
