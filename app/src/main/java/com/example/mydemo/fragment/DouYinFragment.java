package com.example.mydemo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mydemo.R;
import com.example.mydemo.douyindemo.activity.Jiaozivideoplayer_MainActivity;


public class DouYinFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_dou_yin, container, false);
        initView(inflate);

        return inflate;
    }


    private void initView(View inflate) {
       Button button_open= inflate.findViewById(R.id.button_open);

       button_open.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
//               startActivity(new Intent(getActivity(), Jiaozivideoplayer_MainActivity.class));
               startActivity(new Intent().setClass(getContext(), Jiaozivideoplayer_MainActivity.class));
           }
       });
       /* //自定义View
        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) inflate.findViewById(R.id.videoplayer);
        jzVideoPlayerStandard.setUp("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4",
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                "饺子闭眼睛");
        jzVideoPlayerStandard.thumbImageView.setImageResource(R.drawable.a);*/



    }
}