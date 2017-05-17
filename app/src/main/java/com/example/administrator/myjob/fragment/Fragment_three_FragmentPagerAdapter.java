package com.example.administrator.myjob.fragment;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/4/3.
 */

public class Fragment_three_FragmentPagerAdapter extends FragmentPagerAdapter {
    Fragment_Daily_commodity fragment;
    private ArrayList<Fragment_Daily_commodity> fragment_dailycommodities;
    ArrayList<String> listdate;

    //妈比，滑到第三个fragment后才会创建这个适配器，什么鬼哦
    public Fragment_three_FragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment_Daily_commodity> fragment_dailycommodities, ArrayList<String> listdate) {
        super(fm);
        this.fragment_dailycommodities = fragment_dailycommodities;
        this.listdate=listdate;

    }

    @Override
    public int getCount() {
        return fragment_dailycommodities.size();
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //解除了碎片和fragment的绑定
        System.out.println("解除绑定的碎片位置：" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment_Daily_commodity getItem(int position) {

        fragment= fragment_dailycommodities.get(position);
        return fragment;
    }

    //重写这个方法，将设置每个Tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return listdate.get(position);
    }
}
