package com.example.mydemo.adapter;

import android.content.Context;

import com.example.mvplibrary.base.adapter.BaseAdapter;
import com.example.mvplibrary.base.adapter.BaseViewHolder;
import com.example.mydemo.R;
import com.example.mydemo.bean.LazyBean;

import java.util.List;

public class LazyAdapter extends BaseAdapter<LazyBean> {
    public LazyAdapter(List<LazyBean> data, Context context) {
        super(data, context, R.layout.lazy_item);
    }

    @Override
    public void bindData(BaseViewHolder holder, int position) {
        holder.setImage(R.id.lazy_image,data.get(position).getImagePath());
        holder.setText(R.id.lazy_title, data.get(position).getTitle());
        holder.setText(R.id.lazy_id, data.get(position).getId()+"");
    }
}
