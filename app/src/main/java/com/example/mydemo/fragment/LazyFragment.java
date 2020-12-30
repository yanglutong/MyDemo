package com.example.mydemo.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.base.BaseFragment;
import com.example.mydemo.base.BaseLazyFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


public class LazyFragment extends BaseLazyFragment  {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lazy;
    }
    //初始化操作
    @Override
    protected void initViewCreated(View view) {

    }

    //此方法为懒加载方法
    @Override
    protected void initData() {

    }
}