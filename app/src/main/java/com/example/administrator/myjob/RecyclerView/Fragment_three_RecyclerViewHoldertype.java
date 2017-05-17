package com.example.administrator.myjob.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myjob.R;


/**
 * Created by Administrator on 2017/5/3.
 */

public class Fragment_three_RecyclerViewHoldertype extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    TextView texttype;
    public Fragment_three_RecyclerViewHoldertype(View view){
        super(view);
        linearLayout=(LinearLayout)view.findViewById(R.id.linearlayouttype);
        texttype=(TextView)view.findViewById(R.id.shopping_type);
    }
}
