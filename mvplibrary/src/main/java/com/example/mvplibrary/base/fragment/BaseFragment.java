package com.example.mvplibrary.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.trello.rxlifecycle2.components.support.RxFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.fragment
 * 文件名：BaseFragment
 * 创建者：杨路通
 * 创建时间：2020/11/9  10:22
 * 描述：TODO
 */
public abstract class BaseFragment extends RxFragment{
    public Activity activity;
    public Context context;
    private View rootView;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity= (Activity) context;
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(creatLayoutId(),container,false);
        }
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initDataAndEvent();
    }

    //初始化数据
    private void initDataAndEvent() {

    }

    protected abstract int creatLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(rootView!=null){
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
}
