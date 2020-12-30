package com.example.mydemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydemo.R;
import com.example.mydemo.bean.ThreeBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;


public class ShoppingAdapter extends RecyclerView.Adapter {
    private ArrayList<ThreeBean> data;
    private Context context;

    public ShoppingAdapter(ArrayList<ThreeBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Day(LayoutInflater.from(context).inflate(R.layout.item_booking_car, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tv_name = holder.itemView.findViewById(R.id.tv_name);
        TextView tv_totalPrice = holder.itemView.findViewById(R.id.tv_totalPrice);
        TextView tv_num = holder.itemView.findViewById(R.id.tv_num);
        tv_name.setText(data.get(position).getName());
        tv_totalPrice.setText(data.get(position).getXian_price()+"");
        tv_num.setText(data.get(position).getCount()+"");
        ImageView image = holder.itemView.findViewById(R.id.image);
        String pic = data.get(position).getPic();
        Glide.with(context).load(pic).into(image);

        TextView tv_reduce = holder.itemView.findViewById(R.id.tv_reduce);
        TextView tv_plus = holder.itemView.findViewById(R.id.tv_plus);
        CheckBox checkBox  = holder.itemView.findViewById(R.id.checkbox);

        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item!=null){
                        item.onClick(b,position);
                    }
                }
            });

            if(b){
                String string = tv_num.getText().toString();
                int i = Integer.parseInt(string);
                float price = data.get(position).getXian_price();
                float f=i*price;
                Log.i("杨路通", "onCheckedChanged: "+f);
            }
            //设置当前已选中的条目
            data.get(position).setBiao(b);
            //获取到所有的条目集合
            EventBus.getDefault().postSticky(data);
        });
        //设置条目为选中
        checkBox.setChecked(data.get(position).isBiao());
        tv_reduce.setOnClickListener(view -> {
            int count = data.get(position).getCount();
            if (count > 1) {
                --count;
            }
            data.get(position).setCount(count);
            notifyItemChanged(position,true);
            if(data.get(position).isBiao()){
                EventBus.getDefault().postSticky(data);
            }
        });
        tv_plus.setOnClickListener(view -> {
            int count = data.get(position).getCount();
            ++count;
            data.get(position).setCount(count);
            notifyItemChanged(position,true);
            if(data.get(position).isBiao()){
                EventBus.getDefault().postSticky(data);
            }
        });
        tv_num.setText(data.get(position).getCount()+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class Day extends RecyclerView.ViewHolder{
        public Day(@NonNull View itemView) {
            super(itemView);
        }
    }


    public interface OnItem{
        void onClick(boolean b, int position);
    }
    OnItem item;

    public void setItem(OnItem item) {
        this.item = item;
    }
}
