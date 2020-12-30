package com.example.mvplibrary.base.adapter;

import android.content.Context;

import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;


/**
 * 项目名：2007
 * 包名：  com.example.mvplibrary.base.adapter
 * 文件名：MultipleAdapter
 * 创建者：杨路通  多布局适配器
 * 创建时间：2020/11/11  14:47
 * 描述：TODO
 */
public abstract class MultipleAdapter<DATA> extends BaseAdapter<DATA>{
    private CommonType<DATA> commonType;
    protected int type;
    public MultipleAdapter(List<DATA> datas, Context context, int layoutId,CommonType<DATA> commonType) {
        super(datas, context, layoutId);
        if(layoutId==0){
            this.commonType=commonType;
        }
    }

    @Override
    public int getItemViewType(int position) {
        type=commonType.getType(position, data.get(position));
        return type;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutId = commonType.getTypeLayoutId(viewType);
        return new BaseViewHolder(inflater.inflate(layoutId,viewGroup,false),context);
    }
}
