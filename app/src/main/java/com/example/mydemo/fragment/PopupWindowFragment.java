package com.example.mydemo.fragment;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.base.BaseFragment;
import com.example.mydemo.utils.CommonPopupWindow;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


public class PopupWindowFragment extends BaseFragment {

    private TextView tv_pop;
    private ConstraintLayout cos;
    private CommonPopupWindow window;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_popupwindow;
    }

    @Override
    protected void initView() {
        //弹出popup
        tv_pop = view.findViewById(R.id.tv_pop);

        //要弹出在这个布局上
        cos = view.findViewById(R.id.cos);

        tv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopWindow();
            }
        });
    }

    private void initPopWindow() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        // create popup window
        window = new CommonPopupWindow(context, R.layout.layout_pop_item, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight * 0.5)) {
            @Override
            protected void initView() {
                //获取到当前的View
                View view = getContentView();
                view.setOnClickListener(it -> {
                    window.getPopupWindow().dismiss();
                })  ;
            }

            @Override
            protected void initEvent() {

            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getActivity().getWindow().setAttributes(lp);
                    }
                });
            }
        };


        PopupWindow win = window.getPopupWindow();
        win.setAnimationStyle(R.style.animTranslate);
        window.showAtLocation(cos, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.3f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }
}