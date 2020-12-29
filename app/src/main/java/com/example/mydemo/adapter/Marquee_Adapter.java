package com.example.mydemo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.mydemo.R;
import com.xj.marqueeview.base.CommonAdapter;
import com.xj.marqueeview.base.ViewHolder;


import java.util.ArrayList;

/**
 * @author gzp
 * @description:
 * @date : 2020/11/27 20:00
 */
public class Marquee_Adapter extends CommonAdapter<String> {


    public Marquee_Adapter(Context context, ArrayList<String> arrayList) {
        super(context, R.layout.marquee_item, arrayList);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        TextView mText = viewHolder.getView(R.id.ke_mtext);
        mText.setText(item);
    }
}
