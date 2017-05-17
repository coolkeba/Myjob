package com.example.administrator.myjob.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myjob.R;


/**
 * Created by Administrator on 2017/4/4.
 */

public class Fragment_three_RecyclerViewHolder extends RecyclerView.ViewHolder{
    ImageView ware_picture;
    RelativeLayout ware_layout;
    TextView ware_nametx,ware_overplus_number;
    public Fragment_three_RecyclerViewHolder(View view){
        super(view);
        ware_layout=(RelativeLayout)view.findViewById(R.id.ware_layout);
        ware_nametx=(TextView)view.findViewById(R.id.ware_nametx);
        ware_picture=(ImageView)view.findViewById(R.id.ware_picture);
        ware_overplus_number=(TextView)view.findViewById(R.id.ware_overplus_number);


    }
}
