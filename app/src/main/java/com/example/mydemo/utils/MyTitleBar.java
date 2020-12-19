package com.example.mydemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mydemo.R;


public class MyTitleBar extends LinearLayout implements View.OnClickListener {
    public MyTitleBar(Context context) {
        this(context,null);
    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @SuppressLint("ResourceAsColor")
    public MyTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //找到布局
        View view = inflate(context, R.layout.title_toolbar, null);

        TextView include_title = view.findViewById(R.id.include_title);
        TextView include_fan = view.findViewById(R.id.include_fan);
        ImageView include_image = view.findViewById(R.id.include_image);
        TextView include_manager = view.findViewById(R.id.include_manager);


        //使用context获取 加载属性的方法 styleable
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        CharSequence text = array.getText(R.styleable.MyTitleBar_titleText);
        float v = array.getDimension(R.styleable.MyTitleBar_titleSize, 10);
        int color = array.getColor(R.styleable.MyTitleBar_titleDrawable, 0);
        int titleColor = array.getColor(R.styleable.MyTitleBar_titleColor, 0);
        if(text.equals("政治专项")){
            include_manager.setVisibility(GONE);
        }
        if(text.equals("答题")){
            include_manager.setVisibility(GONE);
        }
        if(text.equals("答题卡")){
            include_manager.setVisibility(GONE);
        }
        if(text.equals("真题模拟")){
            include_manager.setVisibility(GONE);
        }
        if(text.equals("收藏")){
            include_manager.setVisibility(GONE);
        }
        if(text.equals("管理收货地址")){
            include_manager.setVisibility(GONE);
        }
        if(text.equals("历年真题")){
            include_manager.setVisibility(GONE);
        }
        if(text.equals("我的课程")){
            include_manager.setVisibility(GONE);
        }
        if(text.equals("关于我们")){
            include_manager.setVisibility(GONE);
        }

        if(text.equals("答题")){
            include_manager.setVisibility(GONE);
        }
        include_title.setTextSize(v);
        include_fan.setTextSize(v);
        include_manager.setTextSize(v);
        include_title.setText(text);

        include_fan.setTextColor(titleColor);
        include_title.setTextColor(titleColor);
        include_manager.setTextColor(titleColor);
        view.setBackgroundColor(color);

        array.recycle();
        //添加view设置和xml布局绑定
        addView(view);

        include_fan.setOnClickListener(this);
        include_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(onClick!=null){
            onClick.onClick(view);
        }
    }
    public interface OnClick{
        void onClick(View v);
    }
    OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }
}
