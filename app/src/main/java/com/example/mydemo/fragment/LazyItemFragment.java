package com.example.mydemo.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplibrary.base.fragment.BaseLazyFragment;
import com.example.mydemo.R;
import com.example.mydemo.adapter.LazyAdapter;
import com.example.mydemo.bean.LazyBean;
import com.example.mydemo.mvp.constants.LazyConstants;
import com.example.mydemo.mvp.model.MyLazyModel;
import com.example.mydemo.mvp.presenter.MyLazyPresenter;
import com.http.httplibrary.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;


public class LazyItemFragment extends BaseLazyFragment<LazyConstants.LazyView, MyLazyModel,MyLazyPresenter> implements LazyConstants.LazyView {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.iv_ani)
    ImageView iv_ani;
    @BindView(R.id.tv_loading)
    TextView tv_loading;
    private AnimationDrawable mAnimationDrawable;

    private ArrayList<LazyBean> list=new ArrayList<>();
    private LazyAdapter lazyAdapter;

    //懒加载方法  大概指第一次看到fragment界面请求一个网络数据
    //可以用户请求网络更新数据库 数据操作 耗时操作
    @Override
    protected void initLazyData() {
        Log.i("杨路通", "LazyItemFragment_initLazyData: 请求懒加载一次");
            if(mPresenter!=null){
                mPresenter.getData();
                //正在加载数据 加载图标
                showLoadingView();
            }
    }

    @Override
    protected MyLazyPresenter createPresenter() {
        return new MyLazyPresenter();
    }



    //初始化操作
    @Override
    protected void initDataAndEvent() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        lazyAdapter = new LazyAdapter(list, context);
        recyclerView.setAdapter(lazyAdapter);


        mAnimationDrawable = (AnimationDrawable) iv_ani.getDrawable();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_lazy_item;
    }



    @Override
    public void showData(ArrayList<LazyBean> beans) {
            showSuccessView();
            Log.i("杨路通", "showData: "+beans.size());
            LogUtils.i("logUtils"+beans.size());
            list.addAll(beans);
            lazyAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError(String msg,int code) {
        Log.i("杨路通", "showError: "+msg+code);
        showErrorView();
    }

    
    public void showLoadingView() {
        Log.i("杨路通", "showLoadingView: 正在加载");
        tv_loading.setVisibility(View.VISIBLE);
        iv_ani.setVisibility(View.VISIBLE);
        animationStart();
    }

    @Override
    public void showSuccessView() {
        Log.i("杨路通", "showSuccessView: 加载成功");
        //停止播放
        tv_loading.setVisibility(View.GONE);
        iv_ani.setVisibility(View.GONE);
        animationStop();
    }

    @Override
    public void showErrorView() {
        Log.i("杨路通", "showErrorView: 加载失败");

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //window初始化完毕 开始播放动画
//        animationStart();
    }

    @Override
    public void onPause() {
        animationStop();
        super.onPause();
    }

    private void animationStart() {
        if (null != mAnimationDrawable)
            mAnimationDrawable.start();//开始播放
    }

    private void animationStop() {
        if (null != mAnimationDrawable)
            mAnimationDrawable.stop();//停止播放
    }
}
