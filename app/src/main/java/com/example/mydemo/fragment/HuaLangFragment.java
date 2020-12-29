package com.example.mydemo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.R;
import com.example.mydemo.adapter.MyTopicAdapter;

import java.util.ArrayList;

import me.crosswall.lib.coverflow.CoverFlow;

public class HuaLangFragment extends Fragment {
    final String HOME_TOPIC_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502881866380&di=d252e1e8dd3a5a836fe360b02531f917&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f5ce56e112ef6ac72531cb37bec4.png%40900w_1l_2o_100sh.jpg";
    final String HOME_TOPIC_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502881904494&di=7a16834200a70469e1d3b6a4ab04c514&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F010d11554baebf000001bf721352ac.jpg";
    final String HOME_TOPIC_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876222250&di=aa3290c84822ba5570f19cb76e1012af&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0146d25768b5a10000018c1b00cf27.jpg%40900w_1l_2o_100sh.jpg";
    final String HOME_TOPIC_FOUR = "http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png";
    final String HOME_TOPIC_FIVE = "http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg";
    private ViewPager mTopicPager;
    private ArrayList<String> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hua_lang, container, false);
        initD(view);
        return view;
    }

    private void initD(View view) {
        mTopicPager = view.findViewById(R.id.mTopicPager);


        list.add(HOME_TOPIC_ONE);
        list.add(HOME_TOPIC_TWO);
        list.add(HOME_TOPIC_THREE);
        list.add(HOME_TOPIC_FOUR);
        list.add(HOME_TOPIC_FIVE);



        //话题
        mTopicPager.setAdapter(new MyTopicAdapter(getContext(), list));
        mTopicPager.setCurrentItem(1);
        mTopicPager.setOffscreenPageLimit(5);
        new CoverFlow.Builder().with(mTopicPager).
                scale(0.3f).pagerMargin(-30.0f).
                spaceSize(0.0f).build();
    }
}