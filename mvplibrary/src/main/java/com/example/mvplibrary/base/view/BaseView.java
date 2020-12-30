package com.example.mvplibrary.base.view;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.view
 * 文件名：BaseView
 * 创建者：杨路通
 * 创建时间：2020/11/7  10:08
 * 描述：TODO
 */
public interface BaseView {

    //网络请求中
    void showLoadingView();

    //网络请求成功
    void showSuccessView();

    //网络请求失败
    void showErrorView();


}
