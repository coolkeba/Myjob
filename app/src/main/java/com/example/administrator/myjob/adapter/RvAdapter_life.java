package com.example.administrator.myjob.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myjob.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/8.
 */

public class RvAdapter_life extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> content = new ArrayList<>();
    private int[] images = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private Context context;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private RvAdapter_personal.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(RvAdapter_personal.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public RvAdapter_life(Context context) {
        this.context = context;
        content.add("专业服务");
        content.add("保洁");
        content.add("电脑维修");
        content.add("干洗");
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_life_item0, parent, false);
            holder = new RvAdapter_life.MyViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_life_item1, parent, false);
            holder = new RvAdapter_life.MyViewHolder1(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

        if (getItemViewType(position) == 0) {
            RvAdapter_life.MyViewHolder holder0 = (RvAdapter_life.MyViewHolder) holder;
            Log.d("输出", content.get(position));
            holder0.textView.setText(content.get(position));
        } else {
            RvAdapter_life.MyViewHolder1 holder1 = (RvAdapter_life.MyViewHolder1) holder;
            holder1.textView.setText(content.get(position));
            holder1.imageView.setImageResource(images[position - 1]);
        }
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    //fragment_personal_item1 的ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_life_item0);
        }
    }

    //fragment_personal_item2 的ViewHolder
    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_life_item1);
            imageView = (ImageView) itemView.findViewById(R.id.image_life_item1);
        }
    }
}
