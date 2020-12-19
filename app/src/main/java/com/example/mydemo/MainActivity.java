package com.example.mydemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.mydemo.fragment.Load_Fragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;
    private ArrayList<Fragment> list;
    private ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件对象
        initView();
        //初始化数据源
        initData();
        //加载数据
        initAdd();
    }

    private void initAdd() {
        Load_Fragment fragment = new Load_Fragment();
        list.add(fragment);

        strings.add("home");

        MyFragmentVp fragmentVp = new MyFragmentVp(getSupportFragmentManager(), list, strings);
        mVp.setAdapter(fragmentVp);

        mTab.setupWithViewPager(mVp);
    }

    private void initData() {
        list = new ArrayList<>();
        strings = new ArrayList<>();
    }

    private void initView() {
        mVp = findViewById(R.id.myViewPager);
        mTab = findViewById(R.id.myTabLayout);
    }

    class MyFragmentVp extends FragmentPagerAdapter{
        ArrayList<String> strings;
        ArrayList<Fragment> fragments;
        public MyFragmentVp(@NonNull FragmentManager fm,ArrayList<Fragment> fragments, ArrayList<String> strings) {
            super(fm);
            this.fragments=fragments;
            this.strings=strings;
        }

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
            return strings.get(position);
        }
    }
}