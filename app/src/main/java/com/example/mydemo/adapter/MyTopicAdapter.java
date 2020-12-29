package com.example.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.utils.RoundRectImageView;


import java.util.ArrayList;

public class MyTopicAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> list;

    public MyTopicAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return  list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View rooView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item, null);
        RoundRectImageView mTopicIv = rooView.findViewById(R.id.mTopicIv);
        Glide.with(context).load(list.get(position)).placeholder(R.drawable.icon_back).error(R.drawable.icon_back).centerCrop().into(
                mTopicIv
        );
        container.addView(rooView);
        return rooView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
