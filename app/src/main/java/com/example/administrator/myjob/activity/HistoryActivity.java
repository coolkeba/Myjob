package com.example.administrator.myjob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.myjob.R;
import com.example.administrator.myjob.adapter.MyPagerAdapter;
import com.example.administrator.myjob.fragment.Fragment_Orders;
import com.example.administrator.myjob.fragment.Fragment_Obligation;
import com.example.administrator.myjob.fragment.Fragment_Received;
import com.example.administrator.myjob.fragment.Fragment_Waiting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/4/27.
 */

public class HistoryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list;
    private MyPagerAdapter adapter;
    private String[] titles = {"全部", "待付款", "待接单", "以收获"};
    private int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 9);
        toolbar = (Toolbar) findViewById(R.id.history_bar);
        toolbar.setTitle("历史订单");
        setSupportActionBar(toolbar);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPager = (ViewPager) findViewById(R.id.view_pager1);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        list = new ArrayList<>();
        list.add(new Fragment_Orders());
        list.add(new Fragment_Obligation());
        list.add(new Fragment_Waiting());
        list.add(new Fragment_Received());
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //ViewPager的适配器
        adapter = new MyPagerAdapter(getSupportFragmentManager(), list, titles);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
    }
}
