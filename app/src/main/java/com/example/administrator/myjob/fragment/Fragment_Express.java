package com.example.administrator.myjob.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.myjob.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/19.
 */

public class Fragment_Express extends Fragment implements OnTabSelectListener {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"未签收", "已签收", "寄件"};
    private MyPagerAdapter mAdapter;
    ViewPager viewPager;
    Fragment_Take quJian;
    Fragment_Send send;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.express,container,false);
        init();
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        SlidingTabLayout t2 = (SlidingTabLayout) view.findViewById(R.id.sliding);
        t2.setViewPager(viewPager);
        t2.setOnTabSelectListener(this);
        viewPager.setCurrentItem(0);
        return view;
    }

    private void init() {
        for (int i = 0; i < mTitles.length-1; i++) {
            quJian = new Fragment_Take();
            mFragments.add(quJian);
        }
        send = new Fragment_Send();
        mFragments.add(send);
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mTitles[position % mTitles.length];
        }

    }
}
