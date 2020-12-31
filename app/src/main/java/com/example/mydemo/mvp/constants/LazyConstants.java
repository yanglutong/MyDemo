package com.example.mydemo.mvp.constants;

import com.example.mvplibrary.base.model.ModelBaseCallBack;
import com.example.mvplibrary.base.view.BaseView;
import com.example.mydemo.bean.LazyBean;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

public interface LazyConstants {
    interface LazyView extends BaseView{
        void showData(ArrayList<LazyBean> beans);
        void showError(String msg,int i);
    }
    interface LazyPresenter{
        void getData();
    }
    interface LazyModel{
        void getModelData(ModelBaseCallBack<ArrayList<LazyBean>> modelBaseCallBack,LifecycleProvider lifecycleProvider);
    }
}
