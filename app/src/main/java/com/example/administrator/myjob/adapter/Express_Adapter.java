package com.example.administrator.myjob.adapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.myjob.DLView;
import com.example.administrator.myjob.R;
import com.example.administrator.myjob.massage_class.Person;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Administrator on 2017/3/31.
 */

public class Express_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private Context context;
    private List<Person> list;
    boolean isf = false;
    boolean isall = false;


    public Express_Adapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0){
//            return 0;
//        }
    
//        if (position == 1){
//            return 1;
//        }
//        return position;
//    }

    Drawable bg;
    boolean isbg1;
    boolean isbg2;
    boolean isbg3;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
//        switch (viewType){
//            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.eee, parent, false);
                holder = new MyViewHolder(view);
//                break;
//            case 1:
//                view = LayoutInflater.from(context).inflate(R.layout.a, parent, false);
//                holder = new MyViewHolder2(view);
//                break;
//        }

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

//        switch (getItemViewType(position)){
//            case 0:
                final MyViewHolder holder1 = (MyViewHolder) holder;
                final Person person = list.get(position);
                holder1.tv1.setText(person.getTitle());
                holder1.tv2.setText(person.getContext1());
                holder1.tv3.setText(person.getContext2());
                holder1.tv5.setText(person.getZan());
                holder1.imageView.setImageBitmap(person.getBitmap());
                holder1.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Are you sure?")
//                                .setContentText("!!!!!!!!!!!!!!!!!!!!!")
//                                .setCancelText("No!")
//                                .setConfirmText("Yes!")
//                                .showCancelButton(true)
//                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sDialog) {
//                                        // reuse previous dialog instance, keep widget user state, reset them if you need
//                                        sDialog.setTitleText("Cancelled!")
//                                                .setContentText("!!!!!!!!!!!!!!!!!!!!")
//                                                .setConfirmText("OK")
//                                                .showCancelButton(false)
//                                                .setCancelClickListener(null)
//                                                .setConfirmClickListener(null)
//                                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
//
//
//                                        // or you can new a SweetAlertDialog to show
//                               /* sDialog.dismiss();
//                                new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                        .setTitleText("Cancelled!")
//                                        .setContentText("Your imaginary file is safe :)")
//                                        .setConfirmText("OK")
//                                        .show();*/
//                                    }
//                                })
//                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sDialog) {
//                                        sDialog.setTitleText("ahahaha!")
//                                                .setContentText("!!!!!!!!!!!!!!!!!!!!!")
//                                                .setConfirmText("OK")
//                                                .showCancelButton(false)
//                                                .setCancelClickListener(null)
//                                                .setConfirmClickListener(null)
//                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//
//                                        person.setZan(new SweetAlertDialog(context).getS());
//                                        holder1.tv5.setText(person.getZan());
//                                        //Toast.makeText(context,new SweetAlertDialog(context).getS(),Toast.LENGTH_SHORT).show();
//
//                                    }
//                                })
//                                .show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setView(new DLView(context).dView());
                        builder.setNegativeButton("取消", null);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.show();
                    }
                });
//                break;
//            case 1:
//                MyViewHolder2 holder2 = (MyViewHolder2) holder;
//                break;
//        }

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
    }

//    public void onBindViewHolder(final MyViewHolder holder, int position) {
//
//        if (position == 1) {
//            new Fragment_Take().re.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
//        }
//            //new Fragment_Take().re.setLayoutManager(new LinearLayoutManager(context));
////        }
//
//        Person person = list.get(position);
//        holder.tv1.setText(person.getTitle());
//        holder.tv2.setText(person.getContext1());
//        holder.tv3.setText(person.getContext2());
//        holder.tv4.setText(person.getTime());
//        holder.tv5.setText(person.getZan());
//        holder.imageView.setImageBitmap(person.getBitmap());
////
////        holder.crossView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                holder.crossView.toggle();
////            }
////        });
//        //holder.crossView.setImageBitmap(person.getBitmap());
////        holder.imageButton.setBackgroundResource(person.getBitmap1());
//
////        holder.imageButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                if (isf == false) {
////                    holder.imageButton.setBackgroundResource(R.drawable.gou);
////                    isf = true;
////                }else if (isf == true){
////                    holder.imageButton.setBackgroundResource(R.drawable.add);
////                    isf = false;
////                }
////            }
////        });
//
//
//        if (mOnItemClickLitener!=null) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int pos = holder.getLayoutPosition();
//                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
//                }
//            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
//                    return false;
//                }
//            });
//        }
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv5;
        ImageView imageView;
        RecyclerView recyclerView;
        Button button;
        Button button2;
//        ImageButton imageButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.txt_title);
            tv2 = (TextView) itemView.findViewById(R.id.txt_1);
            tv3 = (TextView) itemView.findViewById(R.id.txt_2);
            tv5 = (TextView) itemView.findViewById(R.id.txt_0);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.re);
            button = (Button) itemView.findViewById(R.id.button);
            button2 = (Button) itemView.findViewById(R.id.button2);
//            crossView = (CrossView) itemView.findViewById(R.id.sample_cross_view);
            //imageButton = (ImageButton)itemView.findViewById(R.id.ib);
        }
    }

//    static class MyViewHolder2 extends RecyclerView.ViewHolder {
//
//        TextView tv1;
//        TextView tv2;
//        TextView tv3;
//        TextView tv4;
//        TextView tv5;
//        ImageView imageView;
//        RecyclerView recyclerView;
//        ImageButton imageButton;

//        public MyViewHolder2(View itemView) {
//            super(itemView);
//            tv1 = (TextView) itemView.findViewById(R.id.txt_title);
//            tv2 = (TextView) itemView.findViewById(R.id.txt_1);
//            tv3 = (TextView) itemView.findViewById(R.id.txt_2);
//            tv4 = (TextView) itemView.findViewById(R.id.txt_3);
//            tv5 = (TextView) itemView.findViewById(R.id.txt_0);
//            imageView = (ImageView) itemView.findViewById(R.id.img);
//            recyclerView = (RecyclerView) itemView.findViewById(R.id.re);
//
//        }
//    }
}
