package com.example.mydemo.douyindemo.activity;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mydemo.R;
import com.example.mydemo.douyindemo.widget.MyVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 视频预览
 */
public class PrepareActivity extends AppCompatActivity {

    @BindView(R.id.mp_video)
    MyVideoPlayer mpVideo;

    public static final String VIDEO_PATH = "VIDEO_PATH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String path = getIntent().getStringExtra(VIDEO_PATH);
        if (!TextUtils.isEmpty(path)) {
            mpVideo.setUp(path, path, MyVideoPlayer.STATE_NORMAL);
            mpVideo.startVideo();
        }
    }
}
