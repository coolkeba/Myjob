package com.example.administrator.myjob.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myjob.R;


/**
 * Created by Administrator on 2017/4/27.
 */

public class Activity_shopping_cart_RecyclerViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout  ware_item_layout;
    ImageView item_ware_bitmap;
    TextView item_ware_name,item_ware_number,item_ware_price,item_ware_enddate;
    CheckBox checkBox;

    public Activity_shopping_cart_RecyclerViewHolder(View view){
        super(view);
        ware_item_layout=(RelativeLayout)view.findViewById(R.id.ware_item_layout);
        item_ware_bitmap=(ImageView)view.findViewById(R.id.item_ware_bitmap);
        item_ware_name=(TextView)view.findViewById(R.id.item_ware_name);
        item_ware_number=(TextView)view.findViewById(R.id.item_ware_number);
        item_ware_price=(TextView)view.findViewById(R.id.item_ware_price);
        item_ware_enddate=(TextView)view.findViewById(R.id.item_ware_enddate);
        checkBox=(CheckBox)view.findViewById(R.id.checkBox);
    }
}
