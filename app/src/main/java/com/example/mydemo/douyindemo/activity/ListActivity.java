package com.example.mydemo.douyindemo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.douyindemo.base.BaseRecAdapter;
import com.example.mydemo.douyindemo.base.BaseRecViewHolder;
import com.example.mydemo.douyindemo.widget.MyVideoPlayer;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 视频列表
 */
public class ListActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private ArrayList<String> urlList;
    private ListVideoAdapter videoAdapter;
    private int firstVisibleItem;
    private int lastVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        urlList = new ArrayList<>();
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");
        urlList.add("http://static.yximgs.com/bs2/adSspRecVideo/MzYxMDQ3Nzk0Mzg_zh_13.mp4");


        videoAdapter = new ListVideoAdapter(urlList);
        rvList.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        rvList.setAdapter(videoAdapter);

        addListener();

    }

    private void addListener() {


        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        /**在这里执行，视频的自动播放与停止*/
                        autoPlayVideo(recyclerView);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        autoPlayVideo(recyclerView);
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        MyVideoPlayer.releaseAllVideos();
                        break;
                }

            }
        });
    }

    /**
     * 自动播放
     */
    private void autoPlayVideo(RecyclerView recyclerView) {

        if (firstVisibleItem == 0 && lastVisibleItem == 0 && recyclerView.getChildAt(0) != null) {

            MyVideoPlayer videoView = null;
            if (recyclerView != null && recyclerView.getChildAt(0) != null) {
                videoView = recyclerView.getChildAt(0).findViewById(R.id.mp_video);
            }
            if (videoView != null) {
                if (videoView.state == MyVideoPlayer.STATE_NORMAL || videoView.state == MyVideoPlayer.STATE_PAUSE) {
                    videoView.startVideo();
                }
            }
        }

        for (int i = 0; i <= lastVisibleItem; i++) {
            if (recyclerView == null || recyclerView.getChildAt(i) == null) {
                return;
            }


            MyVideoPlayer
                    videoView = recyclerView.getChildAt(i).findViewById(R.id.mp_video);
            if (videoView != null) {

                Rect rect = new Rect();
                //获取视图本身的可见坐标，把值传入到rect对象中
                videoView.getLocalVisibleRect(rect);
                //获取视频的高度
                int videoHeight = videoView.getHeight();

                if (rect.top <= 100 && rect.bottom >= videoHeight) {
                    if (videoView.state == MyVideoPlayer.STATE_NORMAL || videoView.state == MyVideoPlayer.STATE_PAUSE) {
                        videoView.startVideo();
                    }
                    return;
                }

                MyVideoPlayer.releaseAllVideos();

            } else {
                MyVideoPlayer.releaseAllVideos();
            }

        }

    }


    @Override
    public void onPause() {
        super.onPause();
        MyVideoPlayer.releaseAllVideos();
    }


    class ListVideoAdapter extends BaseRecAdapter<String, VideoViewHolder> {


        public ListVideoAdapter(List<String> list) {
            super(list);
        }

        @Override
        public void onHolder(VideoViewHolder holder, String bean, int position) {
            holder.mp_video.setUp(bean, "第" + position + "个视频", MyVideoPlayer.STATE_NORMAL);
            if (position == 0) {
                holder.mp_video.startVideo();
            }
            Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
            holder.tv_title.setText("第" + position + "个视频");
        }

        @Override
        public VideoViewHolder onCreateHolder() {
            return new VideoViewHolder(getViewByRes(R.layout.item_video));

        }


    }

    public class VideoViewHolder extends BaseRecViewHolder {
        public View rootView;
        public MyVideoPlayer mp_video;
        public TextView tv_title;

        public VideoViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mp_video = rootView.findViewById(R.id.mp_video);
            this.tv_title = rootView.findViewById(R.id.tv_title);
        }

    }
}
