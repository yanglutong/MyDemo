package com.example.mydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydemo.dialogsearview.SearchAdapter;
import com.example.mydemo.dialogsearview.SharedPreference;
import com.example.mydemo.dialogsearview.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class SouSuoActivity extends AppCompatActivity {

    private ArrayList<String> mCountries;
    private ArrayList<String> countryStored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_toolbar_search);
        loadToolBarSearch();
    }


    public void loadToolBarSearch() {



        countryStored = SharedPreference.loadList(this, Utils.PREFS_NAME, Utils.KEY_COUNTRIES);


        LinearLayout parentToolbarSearch = (LinearLayout) findViewById(R.id.parent_toolbar_search);
        ImageView imgToolBack = (ImageView) findViewById(R.id.img_tool_back);
        //点击搜索 刷新搜索历史记录
        TextView search_all = (TextView) findViewById(R.id.search_all);


        //输入框
        final EditText edtToolSearch = (EditText) findViewById(R.id.edt_tool_search);
        final ListView listSearch = (ListView) findViewById(R.id.list_search);
        final TextView txtEmpty = (TextView) findViewById(R.id.txt_empty);

        Utils.setListViewHeightBasedOnChildren(listSearch);

        edtToolSearch.setHint("搜索商品/品类");


        countryStored = (countryStored != null && countryStored.size() > 0) ? countryStored : new ArrayList<String>();
        final SearchAdapter searchAdapter = new SearchAdapter(this, countryStored, false);

        listSearch.setVisibility(View.VISIBLE);
        listSearch.setAdapter(searchAdapter);


        listSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String country = String.valueOf(adapterView.getItemAtPosition(position));
                SharedPreference.addList(SouSuoActivity.this, Utils.PREFS_NAME, Utils.KEY_COUNTRIES, country);
                edtToolSearch.setText(country);
                listSearch.setVisibility(View.GONE);
            }
        });
        edtToolSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                String[] country = SouSuoActivity.this.getResources().getStringArray(R.array.countries_array);
                mCountries = new ArrayList<String>(Arrays.asList(country));
                listSearch.setVisibility(View.VISIBLE);
                searchAdapter.updateList(mCountries, true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> filterList = new ArrayList<String>();
                boolean isNodata = false;
                //正在输入内容 s.toString().trim().toLowerCase()将字符串转换成小写字符
                //startsWith 如果字符串以指定的前缀开始，则返回 true；否则返回 false。
                //如果集合里的数据字符有和用户正在输入的字符前缀相同的话就返回true
                if (s.length() > 0) {
                    for (int i = 0; i < mCountries.size(); i++) {

                        if (mCountries.get(i).toLowerCase().startsWith(s.toString().trim().toLowerCase())) {

                            filterList.add(mCountries.get(i));

                            listSearch.setVisibility(View.VISIBLE);
                            searchAdapter.updateList(filterList, true);
                            isNodata = true;
                        }
                    }
                    if (!isNodata) {
                        listSearch.setVisibility(View.GONE);
                        txtEmpty.setVisibility(View.VISIBLE);
                        txtEmpty.setText("暂无历史记录");
                    }
                    //没有输入内容 让集合和显示的文本都隐藏
                } else {
                    listSearch.setVisibility(View.GONE);
                    txtEmpty.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgToolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        search_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> strings = new ArrayList<>();
                String trim = edtToolSearch.getText().toString().trim();
                if(!TextUtils.isEmpty(trim)){
                    strings.add(trim);
                }
                final SearchAdapter searchAdapter = new SearchAdapter(SouSuoActivity.this, strings, false);
                listSearch.setVisibility(View.VISIBLE);
                listSearch.setAdapter(searchAdapter);
            }
        });

    }
}