package com.example.mydemo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydemo.R;
import com.example.mydemo.adapter.ShoppingAdapter;
import com.example.mydemo.base.BaseFragment;
import com.example.mydemo.bean.ThreeBean;
import com.example.mydemo.utils.MyTitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ShoppingCartFragment extends BaseFragment {

    private RecyclerView recycler;
    private CheckBox checkbox;
    //初始化集合
    private ArrayList<ThreeBean> threeBeans=new ArrayList<>();
    private boolean allCheck;
    private ShoppingAdapter adapter;
    private RelativeLayout managerRl;
    private MyTitleBar titleBar;
    private TextView include_manager;
    private TextView tv_price;
    private int  po;
    private TextView tv_del;
    boolean isCheck=false;
    boolean isAll=false;
    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView() {
        Log.i("杨路通", "onCreate: "+"杨路通");
        find();
        adapter.setItem((b, position) -> {
            isCheck=b;
            po=position;
        });
    }

    private void find() {
        recycler = view.findViewById(R.id.recyclerView);
        checkbox = view.findViewById(R.id.checkbox);
        tv_price = view.findViewById(R.id.tv_price);
        initData();
    }

    @Nullable

    private void initData() {
        //加载资源
        for (int i = 0; i <10 ; i++) {
            ThreeBean threeBean = new ThreeBean(Long.parseLong(i+""),"列表"+i,
                    "https://gank.io/images/b140f015a16e444aad6d76262f676a78",
                    15.0f,false,100,0
                    );
            threeBeans.add(threeBean);
        }
        recycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ShoppingAdapter(threeBeans,context);
        recycler.setAdapter(adapter);


        checkbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){//全部选中
                float price2 = 0;
                for (int i = 0; i <threeBeans.size() ; i++) {
                    threeBeans.get(i).setBiao(true);
                    float price1 = threeBeans.get(i).getXian_price();

                    price2 +=price1;
                }
                tv_price.setText("￥"+price2);
                isAll=true;
            }else{
                if(allCheck){//取消全选
                    for (int i = 0; i <threeBeans.size() ; i++) {
                        threeBeans.get(i).setBiao(false);
                        tv_price.setText("￥ 0.0");
                    }
                }
            }
            adapter.notifyDataSetChanged();
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true) //主线程接收事件
    public void onEventBus(ArrayList<ThreeBean> data){
        float priceCount=0;
        allCheck=true;//先默认设置为选中状态
        //接受过来选中的那一条
        for (int i = 0; i <data.size() ; i++) {//遍历集合中当前所有条目的选中状态
            if(!data.get(i).isBiao()){//获取条目里的每一个状态 若有一个未选中则设置全部选中按钮为false
                allCheck = false;
            }else{
                priceCount+=data.get(i).getCount()*data.get(i).getXian_price();
            }
        }

        tv_price.setText("￥"+priceCount);

        checkbox.setChecked(allCheck);//全部按钮设置为全选

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}