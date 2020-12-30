package com.example.mvplibrary.base.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvplibrary.base.model.BaseModel;
import com.example.mvplibrary.base.presenter.BasePresenterIml;
import com.example.mvplibrary.base.view.BaseView;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.fragment
 * 文件名：BaseLazyFragment
 * 创建者：杨路通
 * 创建时间：2020/11/9  10:35
 * 描述：TODO
 */
public abstract class BaseLazyFragment<V extends BaseView,M extends BaseModel,P extends BasePresenterIml<V,M>> extends BaseFragment implements BaseView{
    boolean mIsPrepare = false;        //初始化视图
    boolean mIsVisible = false;        //不可见
    boolean mIsFirstLoad = true;    //第一次加载
    private Unbinder unbinder;
    public P mPresenter;
    @Nullable

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.bindView((V) this);
        }
        initLazyMvpData();
    }

    protected abstract P createPresenter();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            mIsVisible=true;
        }else{
            mIsVisible=false;
        }
        initLazyMvpData();
    }


    public void initLazyMvpData(){
        if(mIsPrepare&&mIsVisible&&mIsFirstLoad){
            if(mPresenter!=null){
                lazyData();
            }
            mIsFirstLoad=false;
        }
    }
    //子类加载数据
    public abstract void lazyData();

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
}
