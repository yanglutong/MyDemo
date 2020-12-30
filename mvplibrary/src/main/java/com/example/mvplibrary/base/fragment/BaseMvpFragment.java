package com.example.mvplibrary.base.fragment;

import android.content.Context;
import android.os.Bundle;

import android.view.View;

import com.example.mvplibrary.base.model.BaseModel;
import com.example.mvplibrary.base.presenter.BasePresenterIml;
import com.example.mvplibrary.base.view.BaseView;

import androidx.annotation.Nullable;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.fragment
 * 文件名：BaseMvpFragment
 * 创建者：杨路通
 * 创建时间：2020/11/9  10:31
 * 描述：TODO
 */
public abstract class BaseMvpFragment<V extends BaseView,M extends BaseModel,P extends BasePresenterIml<V,M>> extends BaseFragment implements BaseView{

    public P mPresenter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.bindView((V) this);
        }
        initDataMvp();
    }

    //初始化Mvp数据方法
    protected abstract void initDataMvp();

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter!=null){
            mPresenter.onUnBindView();
        }
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
