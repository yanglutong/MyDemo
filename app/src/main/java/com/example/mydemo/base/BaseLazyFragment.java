package com.example.mydemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseLazyFragment extends Fragment {
    /*标志位 判断数据是否初始化*/
    private boolean isInitData = false;

    /*标志位 判断fragment是否可见*/
    private boolean isVisibleToUser = false;

     /*标志位 判断view已经加载完成 避免空指针操作*/
     private boolean isPrepareView = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container,false);
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepareView=true;

        //进行平常的初始化操作
        initViewCreated(view);
    }

    protected abstract void initViewCreated(View view);

    /*加载数据的方法,由子类实现*/
    protected abstract void initData();

    /*当fragment由可见变为不可见和不可见变为可见时回调*/
    public void setUserVisibleHint(Boolean isVisibleToUser) {
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
            initData();
        }
    }
    /*fragment生命周期中onViewCreated之后的方法 在这里调用一次懒加载 避免第一次可见不加载数据*/
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyInitData();
    }
}
