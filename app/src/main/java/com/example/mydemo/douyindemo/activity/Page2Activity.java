package com.example.mydemo.douyindemo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
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
 * 翻页2
 */
public class Page2Activity extends AppCompatActivity {

    @BindView(R.id.rv_page2)
    RecyclerView rvPage2;
    private List<String> urlList;
    private ListVideoAdapter videoAdapter;
    private PagerSnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        ButterKnife.bind(this);
        initView();
        addListener();
    }


    private void initView() {
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


        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvPage2);


        videoAdapter = new ListVideoAdapter(urlList);
        layoutManager = new LinearLayoutManager(Page2Activity.this, LinearLayoutManager.VERTICAL, false);
        rvPage2.setLayoutManager(layoutManager);
        rvPage2.setAdapter(videoAdapter);

    }

    private void addListener() {

        rvPage2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = snapHelper.findSnapView(layoutManager);

                        //当前固定后的item position
                        int position = recyclerView.getChildAdapterPosition(view);
                        if (currentPosition != position) {
                            //如果当前position 和 上一次固定后的position 相同, 说明是同一个, 只不过滑动了一点点, 然后又释放了
                            MyVideoPlayer.releaseAllVideos();
                            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                            if (viewHolder != null && viewHolder instanceof VideoViewHolder) {
                                ((VideoViewHolder) viewHolder).mp_video.startVideo();
                            }
                        }
                        currentPosition = position;
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }
        });
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
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

            holder.mp_video.setUp(bean, "第" + position + "个视频", MyVideoPlayer.STATE_NORMAL);
            if (position == 0) {
                holder.mp_video.startVideo();
            }
            Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
            holder.tv_title.setText("第" + position + "个视频");
        }

        @Override
        public VideoViewHolder onCreateHolder() {
            return new VideoViewHolder(getViewByRes(R.layout.item_page2));

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
