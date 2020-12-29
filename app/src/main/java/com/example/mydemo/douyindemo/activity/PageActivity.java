package com.example.mydemo.douyindemo.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mydemo.R;
import com.example.mydemo.douyindemo.adapter.VerticalViewPagerAdapter;
import com.example.mydemo.douyindemo.widget.VerticalViewPager2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 翻页
 */
public class PageActivity extends AppCompatActivity {

    @BindView(R.id.vvp_back_play)
    VerticalViewPager2 vvpBackPlay;
    @BindView(R.id.srl_page)
    SmartRefreshLayout srlPage;
    private List<String> urlList;
    private VerticalViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ButterKnife.bind(this);
        initView();
        addListener();
    }

    private void addListener() {
        srlPage.setEnableAutoLoadMore(false);
        srlPage.setEnableLoadMore(false);
        srlPage.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                srlPage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        urlList.addAll(urlList);
                        pagerAdapter.setUrlList(urlList);
                        pagerAdapter.notifyDataSetChanged();

                        srlPage.finishLoadMore();
                    }
                }, 2000);

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    private void initView() {
        makeData();
        pagerAdapter = new VerticalViewPagerAdapter(getSupportFragmentManager());
//        vvpBackPlay.setVertical(true);
        vvpBackPlay.setOffscreenPageLimit(10);
        pagerAdapter.setUrlList(urlList);
        vvpBackPlay.setAdapter(pagerAdapter);

        vvpBackPlay.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == urlList.size() - 1) {
                    srlPage.setEnableAutoLoadMore(true);
                    srlPage.setEnableLoadMore(true);
                } else {
                    srlPage.setEnableAutoLoadMore(false);
                    srlPage.setEnableLoadMore(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void makeData() {
        urlList = new ArrayList<>();
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");


    }
}
