package com.example.administrator.myjob.RecyclerView;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.myjob.R;
import com.example.administrator.myjob.activity.shopping_cartActivity;
import com.example.administrator.myjob.massage_class.Ware_Cart;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */

public class Activity_shopping_cart_RecyclerViewadapter extends RecyclerView.Adapter<Activity_shopping_cart_RecyclerViewHolder> {

    private LayoutInflater inflater;
    Activity_shopping_cart_RecyclerViewHolder holder;
    private List<Ware_Cart> list;
//    OnItemClickListener mOnItemClickListener;
    onCheckClickListener oncheckclicklistener;
    public shopping_cartActivity shoppingcartActivity;
    public boolean isShow=false;
    public List<Boolean> listBoolean;
    public Activity_shopping_cart_RecyclerViewadapter(Activity activity, List<Ware_Cart> list,List<Boolean> listBoolean) {
        this.shoppingcartActivity =(shopping_cartActivity) activity;
        this.inflater = LayoutInflater.from(activity);
        this.list=list;
        this.listBoolean=listBoolean;
    }

    public Activity_shopping_cart_RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.one_ware_item, parent, false);
        holder = new Activity_shopping_cart_RecyclerViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(final Activity_shopping_cart_RecyclerViewHolder holder, final int position) {
        holder.item_ware_bitmap.setImageBitmap(list.get(position).getWare_bitmap());
        holder.item_ware_name.setText(list.get(position).getWare_name());
        holder.item_ware_number.setText(String.valueOf(list.get(position).getWare_number()));
        holder.item_ware_enddate.setText(list.get(position).getWare_end_date());
        holder.item_ware_price.setText(String.valueOf(list.get(position).getOne_ware_all_price()));
        holder.checkBox.setChecked(listBoolean.get(position));

//        //这里设置接口，当Adapter对象被创建时再赋予具体的实现内容
//        if( mOnItemClickListener!= null){
//            holder. itemView.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onClick(position);
//                }
//            });
//
//            holder. itemView.setOnLongClickListener( new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    mOnItemClickListener.onLongClick(position);
//                    return false;
//                }
//            });
//        }

        if(oncheckclicklistener!=null){
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oncheckclicklistener.onClick(position,holder.checkBox.isChecked());
                }
            });
        }
    }
//    //设置接口
//    public interface OnItemClickListener{
//        void onClick( int position);
//        void onLongClick( int position);
//    }
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
//        this.mOnItemClickListener=onItemClickListener;
//    }
    public interface onCheckClickListener{
        void onClick(int position, boolean isChecked);
    }
    public void setOnCheckClickListener(onCheckClickListener oncheckclicklistener ){
        this.oncheckclicklistener=oncheckclicklistener;
    }
    public int getItemCount() {
        return list.size();
    }
}
