package com.example.mvplibrary.base.presenter;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.presenter
 * 文件名：BasePresenterIml
 * 创建者：杨路通
 * 创建时间：2020/11/7  9:54
 * 描述：TODO
 */
public interface BasePresenter<V> {
    //绑定View
    void bindView(V v);
    //解绑View
    void onUnBindView();
}
