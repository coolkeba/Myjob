package com.example.administrator.myjob.RecyclerView;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.myjob.R;
import com.example.administrator.myjob.activity.Order_For_Goods;
import com.example.administrator.myjob.massage_class.Ware_Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class Order_For_Goods_RecyclerViewAdapter extends RecyclerView.Adapter<Order_For_Goods_RecyclerViewHolder> {

    private LayoutInflater inflater;
    Order_For_Goods_RecyclerViewHolder holder;
    private List<Ware_Cart> list;
    public Order_For_Goods Order_For_Goods_activity;

    public Order_For_Goods_RecyclerViewAdapter(Activity activity, ArrayList<Ware_Cart> list) {
        this.Order_For_Goods_activity =(Order_For_Goods) activity;
        this.inflater = LayoutInflater.from(activity);
        this.list=list;
    }

    public Order_For_Goods_RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_ware_item, parent, false);
        holder = new Order_For_Goods_RecyclerViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(final Order_For_Goods_RecyclerViewHolder holder, final int position) {
        holder.order_ware_bitmap.setImageBitmap(list.get(position).getWare_bitmap());
        holder.order_ware_name.setText(list.get(position).getWare_name());
        holder.order_ware_number.setText(String.valueOf(list.get(position).getWare_number()));
        holder.order_ware_enddate.setText(list.get(position).getWare_end_date());
        holder.order_ware_price.setText(String.valueOf(list.get(position).getOne_ware_all_price()));
    }


    public int getItemCount() {
        return list.size();
    }
}
