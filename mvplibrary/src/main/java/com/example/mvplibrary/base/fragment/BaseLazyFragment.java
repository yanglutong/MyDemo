package com.example.mvplibrary.base.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mvplibrary.base.model.BaseModel;
import com.example.mvplibrary.base.presenter.BasePresenterIml;
import com.example.mvplibrary.base.view.BaseView;

import butterknife.Unbinder;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.fragment
 * 文件名：BaseLazyFragment
 * 创建者：杨路通
 * 创建时间：2020/11/9  10:35
 * 描述：TODO
 */
public  abstract class BaseLazyFragment<V extends BaseView,M extends BaseModel,P extends BasePresenterIml<V,M>> extends BaseFragment implements BaseView{
    /*标志位 判断数据是否初始化*/
    private boolean isInitData = false;

    /*标志位 判断fragment是否可见*/
    private boolean isVisibleToUser = false;

    /*标志位 判断view已经加载完成 避免空指针操作*/
    private boolean isPrepareView = false;
    private Unbinder unbinder;
    public P mPresenter;
    @Nullable

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.bindView((V) this);

            //判断view已经加载完成 P层对象已经持有
            isPrepareView=true;
        }

        initDataAndEvent();
    }


    //初始化数据
    protected abstract void initDataAndEvent();


    /*加载数据的方法,由子类实现*/
    protected abstract void initLazyData();

    protected abstract P createPresenter();

    /*当fragment由可见变为不可见和不可见变为可见时回调*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;
        lazyInitData();
    }

    /*懒加载方法*/
    private void lazyInitData() {
        //当前fragment显示在界面上并且view已经绘制完成
        //没有加载过资源就进行网络请求或数据库操作
        if (!isInitData && isVisibleToUser && isPrepareView) {
            isInitData = true;
            //使用此方法进行懒加载
            initLazyData();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
        }
        if(mPresenter!=null){
            mPresenter.onUnBindView();
        }
    }
    /*fragment生命周期中onViewCreated之后的方法 在这里调用一次懒加载 避免第一次可见不加载数据*/
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyInitData();
    }
    @Override
    public void showLoadingView() {

    }

    @Override
    public void showSuccessView() {

    }

    @Override
    public void showErrorView() {

    }
}
