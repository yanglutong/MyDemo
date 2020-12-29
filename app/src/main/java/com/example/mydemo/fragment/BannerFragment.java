package com.example.mydemo.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


public class BannerFragment extends BaseFragment implements OnBannerListener {
    private Banner banner;
    private ArrayList<String> list=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void initView() {
        banner = view.findViewById(R.id.banner);
        initBanner();
    }

    private void initBanner() {
        //创建数据源存放banner数据
        for (int i = 0; i <3 ; i++) {
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg");
        }
        banner.setImages(list);
        //设置内置样式，共有六种可以点入方法内逐一体验使用。

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方

        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合

        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Accordion);
        //设置轮播图的标题集合
//        banner.setBannerTitles(mutableListOf)
        //设置轮播间隔时间
        banner.setDelayTime(2000);

        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);

        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.RIGHT) //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this) //必须最后调用的方法，启动轮播图。
                .start();
    }

    //此方法为轮播图的监听
    @Override
    public void OnBannerClick(int position) {

    }

    //自定义的图片加载器
    class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String)path).into(imageView);
        }
    }
}