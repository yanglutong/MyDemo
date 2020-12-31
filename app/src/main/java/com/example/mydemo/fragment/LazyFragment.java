package com.example.mydemo.fragment;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mvplibrary.base.fragment.BaseFragment;
import com.example.mvplibrary.base.fragment.BaseLazyFragment;
import com.example.mvplibrary.base.fragment.BaseMvpFragment;
import com.example.mvplibrary.base.presenter.BasePresenterIml;
import com.example.mydemo.R;
import com.example.mydemo.adapter.LazyAdapter;
import com.example.mydemo.bean.LazyBean;
import com.example.mydemo.utils.LogUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;



public class LazyFragment extends BaseMvpFragment {
    private ArrayList<Fragment> fragments;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.viewPager)
    ViewPager mVp;


    //用于网络请求 更新数据库操作 懒加载
    @Override
    protected int createLayoutId() {
        return R.layout.fragment_lazy;
    }

    //初始化操作
    @Override
    protected void initDataMvp(View view) {
        fragments=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            fragments.add(new LazyItemFragment());
        }

        mVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "懒加载 "+position;
            }
        });
        mTab.setupWithViewPager(mVp);
    }

    @Override
    protected BasePresenterIml createPresenter() {
        return null;
    }


}