package com.example.mydemo.douyindemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Jiaozivideoplayer_MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_page)
    Button btnPage;
    @BindView(R.id.btn_list)
    Button btnList;
    @BindView(R.id.btn_record)
    Button btnList2;
    @BindView(R.id.btn_record2)
    Button btnRecord2;
    @BindView(R.id.btn_page2)
    Button btnPage2;
    @BindView(R.id.button_close)
    Button button_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jiaozivideoplayer);
        ButterKnife.bind(this);
        initView();
        addListener();
    }

    private void addListener() {

    }

    private void initView() {


    }


    @OnClick({R.id.btn_page, R.id.btn_list, R.id.btn_record, R.id.btn_record2, R.id.btn_page2,R.id.button_close})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        int i=1;
        switch (view.getId()) {
            case R.id.btn_page:
                intent.setClass(Jiaozivideoplayer_MainActivity.this, PageActivity.class);
                break;
            case R.id.btn_list:
                intent.setClass(Jiaozivideoplayer_MainActivity.this, ListActivity.class);
                break;
            case R.id.btn_record:
                intent.setClass(Jiaozivideoplayer_MainActivity.this, RecordActivity.class);
                break;

            case R.id.btn_record2:
                intent.setClass(Jiaozivideoplayer_MainActivity.this, Record2Activity.class);
                break;

            case R.id.btn_page2:
                intent.setClass(Jiaozivideoplayer_MainActivity.this, Page2Activity.class);

                break;
            case R.id.button_close:
                i=5;
                finish();
                break;
        }
        if(i==1){
            startActivity(intent);
        }
    }
}
