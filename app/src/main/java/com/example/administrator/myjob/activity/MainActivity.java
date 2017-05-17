package com.example.administrator.myjob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.myjob.R;
import com.example.administrator.myjob.fragment.Fragment_Express;
import com.example.administrator.myjob.fragment.Fragment1;
import com.example.administrator.myjob.fragment.Fragment_Life;
import com.example.administrator.myjob.fragment.Fragment_Personal;
import com.example.administrator.myjob.fragment.Fragment_three;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;
    BottomNavigationBar mBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(R.layout.activity_main);
        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        initButtonNavigationBar();
        sendRequestWithOkHttp();


    }

    private Fragment fragment1 = new Fragment_Life();
    private Fragment fragment2 = new Fragment_Express();
    private Fragment fragment3 = new Fragment_three(this);
    private Fragment fragment4 = new Fragment_Personal();
    private FragmentTransaction fragmentTransaction;
    private ArrayList<Fragment> list_fragment = new ArrayList<>();

    private void initFrameLayout() {
        list_fragment.add(fragment1);
        list_fragment.add(fragment2);
        list_fragment.add(fragment3);
        list_fragment.add(fragment4);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < list_fragment.size(); i++) {
            fragmentTransaction.add(R.id.main_content, list_fragment.get(i));
        }
        fragmentTransaction.commit();
        showFragment(2);
    }

    private void hideFragment() {
        for (int i = 0; i < list_fragment.size(); i++) {
            fragmentTransaction.hide(list_fragment.get(i));
        }
    }

    private void showFragment(int index) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment();
        fragmentTransaction.show(list_fragment.get(index));
        fragmentTransaction.commit();
    }

    private void initButtonNavigationBar() {
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.mall, "服务"))
                .addItem(new BottomNavigationItem(R.mipmap.express, "快递"))
                .addItem(new BottomNavigationItem(R.mipmap.my4, "商城"))
                .addItem(new BottomNavigationItem(R.mipmap.person, "我的"))
                .setActiveColor(R.color.xianyu_color)
                .setBarBackgroundColor(R.color.white)
                .setInActiveColor(R.color.black_80)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setFirstSelectedPosition(2)
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        showFragment(position);
                    }

                    @Override
                    public void onTabUnselected(int position) {
                    }

                    @Override
                    public void onTabReselected(int position) {
                    }
                })
                .initialise();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
        }
        return super.onKeyDown(keyCode, event);
    }

    public String responseData;
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                URL url = null;
                try {
                    url = new URL("http://s14i594712.iask.in:40293/Action/Action_WareAction_getAll");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = client.newCall(request).execute();
                    responseData = response.body().string();
//                    parseJSONWithJSONObject(responseData);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initFrameLayout();

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public String getjsonData(){
        return responseData;
    }


}