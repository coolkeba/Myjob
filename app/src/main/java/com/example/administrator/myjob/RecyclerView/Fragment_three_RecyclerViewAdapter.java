package com.example.administrator.myjob.RecyclerView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.myjob.R;
import com.example.administrator.myjob.activity.MainActivity;
import com.example.administrator.myjob.massage_class.Ware_Information;


import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */

public class Fragment_three_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;

    private List<Ware_Information> list;
    OnItemClickListener mOnItemClickListener;


    MainActivity activity;
    int firstware_size,secondware_size;

    public Fragment_three_RecyclerViewAdapter(Activity activity, List<Ware_Information> list,int firstware_size,int secondware_size) {
        this.activity =(MainActivity) activity;
        this.inflater = LayoutInflater.from(activity);
        this.list=list;
        this.firstware_size=firstware_size;
        this.secondware_size=secondware_size;

    }
    //根据位置判断返回onCreateViewHolder方法中viewType的值
    public int getItemViewType(int position) {
//         switch (position){
//             case 0:
//                 return 0;
        // case后面不能跟变量 所以不用switch
//             case firstware_size:
//                 return firstware_size;
//             default:
//                 return position;
//         }
        if(position==0)
            return 0;
        else if (position==firstware_size)
            return firstware_size;
        else if (position==secondware_size)
            return  secondware_size;
        else
            return position;
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  ;
        RecyclerView.ViewHolder holder;
//         switch (viewType){
//            case  0:
//                 view = inflater.inflate(R.layout.fragment_mall_shoppingtype, parent, false);
//                 holder = new Fragment_three_RecyclerViewHoldertype(view);
//                return holder;
//             case  2:
//                 view = inflater.inflate(R.layout.fragment_mall_shoppingtype, parent, false);
//                 holder = new Fragment_three_RecyclerViewHoldertype(view);
//                 return holder;
//            default:
//                 view = inflater.inflate(R.layout.fragment_mall_onegoods, parent, false);
//                 holder = new Fragment_three_RecyclerViewHolder(view);
//                return holder;
//        }
        if(viewType==0 || viewType==firstware_size || viewType==secondware_size){
            view = inflater.inflate(R.layout.fragment_mall_shoppingtype, parent, false);
            holder = new Fragment_three_RecyclerViewHoldertype(view);
            return holder;
        }

        else {
            view = inflater.inflate(R.layout.fragment_mall_onegoods, parent, false);
            holder = new Fragment_three_RecyclerViewHolder(view);
            return holder;
        }

    }
//    Fragment_three_RecyclerViewHolder holder1;
//    Fragment_three_RecyclerViewHoldertype holder2;
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        if(getItemViewType(position)==0 || getItemViewType(position)==firstware_size ) {
//            Fragment_three_RecyclerViewHoldertype holder2=(Fragment_three_RecyclerViewHoldertype)holder;
//            if(getItemViewType(position)==0)
//                holder2.texttype.setText("每日水果");
//            else
//                holder2.texttype.setText("每日海鲜");
//        }
        if(holder instanceof Fragment_three_RecyclerViewHoldertype){
            if(getItemViewType(position)==0)
                ((Fragment_three_RecyclerViewHoldertype) holder).texttype.setText("每日水果");
            else if(getItemViewType(position)==firstware_size)
                ((Fragment_three_RecyclerViewHoldertype) holder).texttype.setText("杂货");
            else if(getItemViewType(position)==secondware_size)
                ((Fragment_three_RecyclerViewHoldertype) holder).texttype.setText("每日海鲜");

        }

        else {
           final Fragment_three_RecyclerViewHolder holder1=(Fragment_three_RecyclerViewHolder)holder;
            //坑！ new Target<Bitmap> 对象并不会执行onResourceReady方法，无法获取到bitmap对象，别的方法都是默认的，不知道是不是要修改别的方法内的内容。
            if (list.get(position).getWare_Bitmap()==null) {
                Glide.with(activity).load("http://s14i594712.iask.in:40293/images/" + list.get(position).getWare_picture_string()).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder1.ware_picture.setImageBitmap(resource);
                        list.get(position).setWare_Bitmap(resource);
                    }
                });
            }
            holder1.ware_picture.setImageBitmap(list.get(position).getWare_Bitmap());
            holder1.ware_nametx.setText(String.valueOf(list.get(position).getWare_name()));
            if(list.get(position).getWare_overplus()!=null)
                holder1.ware_overplus_number.setText(list.get(position).getWare_overplus());
            //这里设置接口，当Adapter对象被 创建时再赋予具体的实现内容
            if( mOnItemClickListener!= null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemClickListener.onLongClick(position);
                        return false;
                    }
                });
            }
        }
        System.out.println(list.get(position).getWare_name());

    }
    //设置接口
    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener=onItemClickListener;
    }

    public int getItemCount() {
        return list.size();
    }


}
