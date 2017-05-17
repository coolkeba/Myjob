package com.example.administrator.myjob.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myjob.R;
import com.example.administrator.myjob.RecyclerView.Fragment_three_RecyclerViewAdapter;
import com.example.administrator.myjob.activity.MainActivity;
import com.example.administrator.myjob.activity.One_Ware_Acivity;
import com.example.administrator.myjob.massage_class.Ware_Information;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Fragment_Daily_commodity extends Fragment {
    @Bind(R.id.shopping_cart_recycerview)
    RecyclerView shopping_cart_recycerview;
    private ArrayList<Ware_Information> list = new ArrayList<>();
    Fragment_three_RecyclerViewAdapter fragment_three_recyclerViewAdapter;
    String date;
    int i;
    private MainActivity activity;
    int firstware_size;
    int secondware_size;

    public Fragment_Daily_commodity(String date, int i, ArrayList<Ware_Information> list, Activity activity,int firstware_size,int secondware_size) {
        this.date = date;
        this.i = i;
        this.list = list;
        this.activity=(MainActivity) activity;
        this.firstware_size=firstware_size;
        this.secondware_size=secondware_size;
    }




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //？？？为什么从第一个滑到第三个才一起创建碎片
        System.out.printf("创建了一个碎片");

    }
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_ware_list, container, false);
        ButterKnife.bind(this, view);
        initRecycleView();
        return view;
    }
    private void initRecycleView() {
        System.out.println("1sssssssssssssss"+String.valueOf(firstware_size));
        //这个适配器的创建要在activity中进行,否则会报无法运行系统服务的错误
        fragment_three_recyclerViewAdapter = new Fragment_three_RecyclerViewAdapter(activity, list,firstware_size,secondware_size);
        //必须要设置布局管理器 不然无法显示item
//        shopping_cart_recycerview.setLayoutManager(new LinearLayoutManager(activity));
        final GridLayoutManager gridLayoutManager=new GridLayoutManager(activity,3);
        shopping_cart_recycerview.setLayoutManager(gridLayoutManager);
        shopping_cart_recycerview.setAdapter(fragment_three_recyclerViewAdapter);
        //设置占用的列数
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type=shopping_cart_recycerview.getAdapter().getItemViewType(position);
                if(type==0)
                    return gridLayoutManager.getSpanCount();
                else if(type==firstware_size)
                    return gridLayoutManager.getSpanCount();
                else if (type==secondware_size)
                    return gridLayoutManager.getSpanCount();
                else
                    return 1;
            }
        });

        //mRecyclerView.smoothScrollToPosition(adapter.getItemCount());

        //接口回调处理item事件
        fragment_three_recyclerViewAdapter.setOnItemClickListener(new Fragment_three_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //跳转到商品详情界面
                //这里 后台还没有存放商品介绍 暂时用一个textview 来代替
                //这里运用键值对的方式传数据
                Intent intent=new Intent(activity,One_Ware_Acivity.class);
                Bundle bundle=new Bundle();
                //putParcelable()方法有图片大小限制 所以不能传大图片,所以要进行缩放
                Bitmap bitmap=Bitmap.createScaledBitmap(list.get(position).getWare_Bitmap(),200,200,false);
                bundle.putParcelable("bitmap",bitmap);
                bundle.putString("introduction",list.get(position).getWare_introduction());
                bundle.putString("price",String.valueOf(list.get(position).getWare_price()));
                bundle.putString("name",list.get(position).getWare_name());
                bundle.putString("pitcure_url",list.get(position).getWare_picture_string());
                bundle.putString("date",list.get(position).getWare_date());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {

            }
        });
    }
}
