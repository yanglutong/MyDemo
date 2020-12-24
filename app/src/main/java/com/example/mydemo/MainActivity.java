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

import com.example.mydemo.fragment.Load_Fragment;
import com.example.mydemo.fragment.SearchViewFragment;
import com.example.mydemo.zing.ZingFragment;
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
    private ZingFragment zingFragment;

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
        zingFragment = new ZingFragment();

        list.add(fragment);
        list.add(searchViewFragment);
        list.add(zingFragment);
        strings.add("home");
        strings.add("搜索框");
        strings.add("二维码功能");

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
                zingFragment.setResult(result.getContents());
//                text_result.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}