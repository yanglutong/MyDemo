package com.example.mydemo.fragment;

import android.view.View;

import com.example.mvplibrary.base.fragment.BaseMvpFragment;
import com.example.mvplibrary.base.presenter.BasePresenterIml;
import com.example.mydemo.R;

public class SkuFragment extends BaseMvpFragment {
    @Override
    protected void initDataMvp(View view) {

    }

    @Override
    protected BasePresenterIml createPresenter() {
        return null;
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_sku;
    }
}
