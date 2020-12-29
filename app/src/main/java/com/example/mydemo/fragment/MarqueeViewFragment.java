package com.example.mydemo.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.adapter.Marquee_Adapter;
import com.example.mydemo.base.BaseFragment;
import com.xj.marqueeview.MarqueeView;
import com.xj.marqueeview.base.MultiItemTypeAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


public class MarqueeViewFragment extends BaseFragment  {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_marquee;
    }

    @Override
    protected void initView() {
        //跑马灯
        final MarqueeView tvMarqueeView = view.findViewById(R.id.tvMarqueeView);
        //跑马灯
        ArrayList<String> arr=new ArrayList<String>();
        arr.add("新用户立减1000元优惠券");
        arr.add("夏日炎炎，第一波福利还有30秒到达战场");
        //跑马灯
        Marquee_Adapter adapter =new Marquee_Adapter(context,arr);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (tvMarqueeView.isStart()) {
                    tvMarqueeView.stopFilp();
                }
            }
        });
        tvMarqueeView.setAdapter(adapter);
    }
}