package com.example.mvplibrary.base.activity;

import android.os.Bundle;


import com.example.mvplibrary.base.model.BaseModel;
import com.example.mvplibrary.base.presenter.BasePresenterIml;
import com.example.mvplibrary.base.view.BaseView;

import androidx.annotation.Nullable;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.activity
 * 文件名：BaseMvpActivity
 * 创建者：杨路通
 * 创建时间：2020/11/7  14:21
 * 描述：TODO
 */
public abstract class BaseMvpActivity<V extends BaseView,M extends BaseModel,P extends BasePresenterIml<V,M>> extends BaseActivity implements BaseView{

    //P 层对象
    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.bindView((V) this);
        }
        initMvpData();
    }


    protected abstract void initMvpData();

    //创建P层对象
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
