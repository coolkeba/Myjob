package com.example.administrator.myjob.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myjob.R;


/**
 * Created by Administrator on 2017/5/8.
 */

public class Order_For_Goods_RecyclerViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout order_ware_layout;
    ImageView order_ware_bitmap;
    TextView order_ware_name,order_ware_number,order_ware_price,order_ware_enddate;
    CheckBox checkBox;

    public Order_For_Goods_RecyclerViewHolder(View view){
        super(view);
        order_ware_layout=(RelativeLayout)view.findViewById(R.id.order_ware_layout);
        order_ware_bitmap=(ImageView)view.findViewById(R.id.order_ware_bitmap);
        order_ware_name=(TextView)view.findViewById(R.id.order_ware_name);
        order_ware_number=(TextView)view.findViewById(R.id.order_ware_number);
        order_ware_price=(TextView)view.findViewById(R.id.order_ware_price);
        order_ware_enddate=(TextView)view.findViewById(R.id.order_ware_enddate);
    }

}
