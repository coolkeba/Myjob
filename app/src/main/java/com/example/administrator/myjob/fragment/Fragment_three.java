package com.example.administrator.myjob.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.administrator.myjob.R;
import com.example.administrator.myjob.activity.MainActivity;
import com.example.administrator.myjob.massage_class.Ware_Information;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/4/2.
 */

public class Fragment_three extends Fragment {
    private final int PAGER_COUNT = 7;
    Fragment_three_FragmentPagerAdapter fragment_three_fragmentPagerAdapter;
    ArrayList<Fragment_Daily_commodity> fragment_dailycommodities = new ArrayList<>();
    String today;
    long time;
    @Bind(R.id.shopping_cart_viewpager)
    ViewPager shopping_cart_viewpager;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    private MainActivity mallactivity;
    ArrayList<String> listdate=new ArrayList<>();
    public Fragment_three(Activity mallactivity){
        this.mallactivity=(MainActivity) mallactivity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.bind(this, view);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        parseJSONWithJSONObject(mallactivity.getjsonData());
        shopping_cart_viewpager.setAdapter(fragment_three_fragmentPagerAdapter);
        tablayout.setupWithViewPager(shopping_cart_viewpager);
        return view;
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }


    private ArrayList<Ware_Information> getlist;
    Ware_Information ware_information,ware_type1,ware_type2,ware_type3;
    //处理接受来的数据
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < PAGER_COUNT; i++) {
                getlist = new ArrayList<Ware_Information>();
                //后台录入的商品类型是杂乱无章的，所以这里我们进行商品分类处理
                //遍历第一种商品
                //以空信息代表每日水果 每日海鲜的标题，防止position上的冲突
                ware_type1=new Ware_Information();
                getlist.add(ware_type1);
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    System.out.printf(jsonObject.getString("date"));
                    //获取商品信息时间进行比对来确定在哪个碎片创建
                    if (getTime(i).contains(jsonObject.getString("date")) && jsonObject.getString("type").equals("每日水果") ) {
                        ware_information = new Ware_Information();
                        ware_information.setWare_date(jsonObject.getString("date"));
                        ware_information.setWare_name(jsonObject.getString("name"));
                        ware_information.setWare_introduction(jsonObject.getString("detail"));
                        ware_information.setWare_picture_string(jsonObject.getString("img"));
                        System.out.println(jsonObject.getString("img")+"111111111111111");
                        ware_information.setWare_price(Double.parseDouble(jsonObject.getString("price")));
                        if(i==0)
                            ware_information.setWare_overplus(jsonObject.getString("number"));

                        getlist.add(ware_information);
                    }

                }
                int firstware_size=getlist.size();
                //遍历第二类商品
                //以空信息代表每日水果 每日海鲜的标题，防止position上的冲突
                ware_type2=new Ware_Information();
                getlist.add(ware_type2);
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    //获取商品信息时间进行比对来确定在哪个碎片创建
                    if (getTime(i).contains(jsonObject.getString("date")) && jsonObject.getString("type").equals("杂货")) {
                        ware_information = new Ware_Information();
                        ware_information.setWare_date(jsonObject.getString("date"));
                        ware_information.setWare_name(jsonObject.getString("name"));
                        ware_information.setWare_introduction(jsonObject.getString("detail"));
                        ware_information.setWare_picture_string(jsonObject.getString("img"));
                        System.out.println(jsonObject.getString("img")+"22222222222222");
                        ware_information.setWare_price(Double.parseDouble(jsonObject.getString("price")));
                        if(i==0)
                            ware_information.setWare_overplus(jsonObject.getString("number"));
                        getlist.add(ware_information);
                    }

                }
                int secondware_size=getlist.size();
                //遍历第二类商品
                //以空信息代表每日水果 每日海鲜的标题，防止position上的冲突
                ware_type3=new Ware_Information();
                getlist.add(ware_type3);
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    //获取商品信息时间进行比对来确定在哪个碎片创建
                    if (jsonObject.getString("date").equals(getTime(i)) && jsonObject.getString("type").equals("每日海鲜")) {
                        ware_information = new Ware_Information();
                        ware_information.setWare_date(jsonObject.getString("date"));
                        ware_information.setWare_name(jsonObject.getString("name"));
                        ware_information.setWare_introduction(jsonObject.getString("detail"));
                        ware_information.setWare_picture_string(jsonObject.getString("img"));
                        System.out.println(jsonObject.getString("img")+"33333333333333");
                        ware_information.setWare_price(Double.parseDouble(jsonObject.getString("price")));
                        if(i==0)
                            ware_information.setWare_overplus(jsonObject.getString("number"));
                        getlist.add(ware_information);
                    }

                }
                String s=getTime(i).substring(5);

                //布局上方的日期
                listdate.add(s);
                fragment_dailycommodities.add(new Fragment_Daily_commodity(s, i, getlist,mallactivity,firstware_size,secondware_size));
            }
            fragment_three_fragmentPagerAdapter = new Fragment_three_FragmentPagerAdapter(mallactivity.getSupportFragmentManager(), fragment_dailycommodities,listdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取系统日期
    public String getTime(int i) {
//        this.i=i;
        time = System.currentTimeMillis() ;
        //下面这句为了测试用 获取前几天的数据  正式使用时获取当天的
        time -=86400000 * 11;
        time += 86400000 * i;
        //时间显示的一种格式,这里规范书写用来和后台接受的商品时间进行比对，如果日期一样就在那一天显示日期上的商品
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/M/d EE");
        Date date = new Date(time);
        today = formatter.format(date);
        return today;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
