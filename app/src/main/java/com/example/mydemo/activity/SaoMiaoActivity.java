package com.example.mydemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mydemo.R;

public class SaoMiaoActivity extends AppCompatActivity {
    private ImageView mScanHorizontalLineImageView;
    private ImageView mScanVerticalLineImageView;
    private View mPreviewView;
    private RelativeLayout r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_miao);
        initView();
    }

    private void initView() {
        mScanHorizontalLineImageView = (ImageView) findViewById(R.id.scanHorizontalLineImageView);
        mScanVerticalLineImageView = (ImageView) findViewById(R.id.scanVerticalLineImageView);
        r = (RelativeLayout) findViewById(R.id.r);
        mPreviewView = findViewById(R.id.previewView);

        r.setOnClickListener(view -> finish());
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        int[] location = new int[2];

        // getLocationInWindow方法要在onWindowFocusChanged方法里面调用
        // 个人理解是onCreate时，View尚未被绘制，因此无法获得具体的坐标点
        mPreviewView.getLocationInWindow(location);

        // 模拟的mPreviewView的左右上下坐标坐标
        int left = mPreviewView.getLeft();
        int right = mPreviewView.getRight();
        int top = mPreviewView.getTop();
        int bottom = mPreviewView.getBottom();

        // 从上到下的平移动画
        Animation verticalAnimation = new TranslateAnimation(left, left, top, bottom);
        verticalAnimation.setDuration(3000); // 动画持续时间
        verticalAnimation.setRepeatCount(Animation.INFINITE); // 无限循环

        // 播放动画
        mScanHorizontalLineImageView.setAnimation(verticalAnimation);
        verticalAnimation.startNow();

        // 从左到右的平移动画
        Animation horizontalAnimation = new TranslateAnimation(left, right, top, top);
        horizontalAnimation.setDuration(3000); // 动画持续时间
        horizontalAnimation.setRepeatCount(Animation.INFINITE); // 无限循环

        //播放动画
        mScanVerticalLineImageView.setAnimation(horizontalAnimation);
        horizontalAnimation.startNow();

    }
}