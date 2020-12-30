package com.example.mydemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mydemo.fragment.BannerFragment;
import com.example.mydemo.fragment.DouYinFragment;
import com.example.mydemo.fragment.HuaLangFragment;
import com.example.mydemo.fragment.Load_Fragment;
import com.example.mydemo.fragment.MarqueeViewFragment;
import com.example.mydemo.fragment.PopupWindowFragment;
import com.example.mydemo.fragment.SearchViewFragment;
import com.example.mydemo.fragment.ShoppingCartFragment;
import com.example.mydemo.zxing.ZxingFragment;
import com.example.mydemo.utils.MyViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {
    private MyViewPager mVp;
    private TabLayout mTab;
    private ArrayList<Fragment> list;
    private ArrayList<String> strings;
    private ZxingFragment zxingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //找到控件对象
        initView();
        //初始化数据源
        initData();
        //加载数据
        initAdd();
    }



    private void initAdd() {
        Load_Fragment fragment = new Load_Fragment();
        SearchViewFragment searchViewFragment = new SearchViewFragment();
        zxingFragment = new ZxingFragment();
        DouYinFragment douYinFragment = new DouYinFragment();
        HuaLangFragment huaLangFragment = new HuaLangFragment();
        BannerFragment bannerFragment = new BannerFragment();
        MarqueeViewFragment marqueeViewFragment = new MarqueeViewFragment();
        ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();

        list.add(fragment);
        list.add(searchViewFragment);
        list.add(zxingFragment);
        list.add(douYinFragment);
        list.add(huaLangFragment);
        list.add(bannerFragment);
        list.add(marqueeViewFragment);
        list.add(shoppingCartFragment);
        list.add(new PopupWindowFragment());

        this.strings.add("home");
        this.strings.add("搜索框");
        this.strings.add("二维码功能");
        this.strings.add("抖音视频");
        this.strings.add("画廊效果");
        this.strings.add("Banner");
        strings.add("跑马灯");
        strings.add("购物车");
        strings.add("pop");


        MyFragmentVp fragmentVp = new MyFragmentVp(getSupportFragmentManager(), list, this.strings);
        mVp.setAdapter(fragmentVp);
        mTab.setupWithViewPager(mVp);


        for (int i = 0; i <list.size() ; i++) {
            mTab.getTabAt(i).setIcon(R.drawable.tab_selector);
        }


        //设置tab的下划线隐藏
        mTab.setSelectedTabIndicator(0);
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

    //获取扫描结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            Toast.makeText(this, "进来了", Toast.LENGTH_SHORT).show();
            Log.i("TAG", "onActivityResult: "+"进来了");
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                zxingFragment.setResult(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}