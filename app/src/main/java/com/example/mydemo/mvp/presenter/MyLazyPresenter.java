package com.example.mydemo.mvp.presenter;

import com.example.mvplibrary.base.model.ModelBaseCallBack;
import com.example.mvplibrary.base.presenter.BasePresenterIml;
import com.example.mydemo.bean.LazyBean;
import com.example.mydemo.fragment.LazyItemFragment;
import com.example.mydemo.mvp.constants.LazyConstants;
import com.example.mydemo.mvp.model.MyLazyModel;

import java.util.ArrayList;

public class MyLazyPresenter extends BasePresenterIml<LazyConstants.LazyView, MyLazyModel> implements LazyConstants.LazyPresenter, ModelBaseCallBack<ArrayList<LazyBean>> {
    @Override
    public void getData() {
        getModel().getModelData(this, getLifecycleProvider());
    }

    @Override
    public void onSuccess(ArrayList<LazyBean> data) {
        getView().showData(data);
    }

    @Override
    public void onError(String msg, int code) {
        getView().showError(msg,code);
    }
}
