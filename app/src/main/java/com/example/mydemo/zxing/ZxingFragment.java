package com.example.mydemo.zxing;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydemo.R;
import com.example.mydemo.activity.SaoMiaoActivity;
import com.google.zxing.integration.android.IntentIntegrator;

public class ZxingFragment extends Fragment implements View.OnClickListener {
    private TextView text_result;
    private Button button1;


    public ZxingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zing, container, false);
        initData(view);
        return view;
    }

    private void initData(View view) {
        text_result = view.findViewById(R.id.text_result);
        Button btSao = view.findViewById(R.id.btSao);
        button1 = view.findViewById(R.id.button1);
        button1.setOnClickListener(this);
        btSao.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), SaoMiaoActivity.class));
        });

    }



    //点击监听进行扫描
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                /*以下是启动我们自定义的扫描活动*/
                IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
                intentIntegrator.setBeepEnabled(true);
                /*设置启动我们自定义的扫描活动，若不设置，将启动默认活动*/
                intentIntegrator.setCaptureActivity(ScanActivity.class);
                intentIntegrator.initiateScan();
                break;
        }
    }
    private void requsetPermission(){
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.CAMERA)!=     PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA},1);

            }else {

            }
        }else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //这里已经获取到了摄像头的权限，想干嘛干嘛了可以

                }else {
                    //这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以。
                    Toast.makeText(getContext(),"请手动打开相机权限",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    public void setResult(String contents) {
        text_result.setText(contents);
    }

}