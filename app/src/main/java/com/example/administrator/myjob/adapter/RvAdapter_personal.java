package com.example.administrator.myjob.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myjob.R;
import com.example.administrator.myjob.activity.HistoryActivity;
import com.example.administrator.myjob.activity.LoginActivity;
import com.example.administrator.myjob.activity.PersonalActivity;
import com.example.administrator.myjob.activity.SuggestionActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenovo on 2017/4/21.
 */

public class RvAdapter_personal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> content = new ArrayList<>();
    private int[] imageid = new int[]{R.mipmap.suggestion,
            R.mipmap.phone, R.mipmap.version, R.mipmap.unlogin};
    private Context context;

    //建立枚举 2个item 类型

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public RvAdapter_personal(Context context) {
        this.context = context;
        content.add("意见反馈");
        content.add("联系客服");
        content.add("当前版本");
        content.add("退出登录");
    }

    //设置ITEM类型，可以自由发挥，这里设置item position单数显示item1 偶数显示item2
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 2) {
            return 1;
        } else if (position == 1 || position == 3) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_personal_item0, parent, false);
            holder = new MyViewHolder(view);
        } else if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_personal_item1, parent, false);
            holder = new MyViewHolder1(view);
        } else if (viewType == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
            holder = new MyViewHolder3(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_personal_item2, parent, false);
            holder = new MyViewHolder2(view);
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
            MyViewHolder holder0 = (MyViewHolder) holder;
            holder0.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PersonalActivity.class);
                    context.startActivity(intent);
                }
            });
        } else if (getItemViewType(position) == 1) {
            MyViewHolder1 holder1 = (MyViewHolder1) holder;
//            holder1.linearLayout1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, HistoryActivity.class);
//                    intent.putExtra("position", "0");
//                    context.startActivity(intent);
//                }
//            });
            holder1.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HistoryActivity.class);
                    intent.putExtra("position", 0);
                    context.startActivity(intent);
                }
            });
            holder1.imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HistoryActivity.class);
                    intent.putExtra("position", 1);
                    context.startActivity(intent);
                }
            });
            holder1.imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HistoryActivity.class);
                    intent.putExtra("position", 2);
                    context.startActivity(intent);
                }
            });
            holder1.imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HistoryActivity.class);
                    intent.putExtra("position", 3);
                    context.startActivity(intent);
                }
            });
        } else if (getItemViewType(position) == 2) {
            MyViewHolder3 holder3 = (MyViewHolder3) holder;
        } else {
            MyViewHolder2 holder2 = (MyViewHolder2) holder;
            holder2.imageView.setImageResource(imageid[position - 4]);
            holder2.textView.setText(content.get(position - 4));
            holder2.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 4:
                            Intent intent = new Intent(context, SuggestionActivity.class);
                            context.startActivity(intent);
                            break;
                        case 5:
                            dialog();
                            break;
                        case 6:
                            break;
                        case 7:
                            dialogExit();
                            break;
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return content.size() + 4;
    }

    //fragment_personal_item1 的ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_0);
        }
    }

    //fragment_personal_item2 的ViewHolder
    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView imageView1, imageView2, imageView3, imageView4;

        public MyViewHolder1(View itemView) {
            super(itemView);
            imageView1 = (ImageView) itemView.findViewById(R.id.item1_orders);
            imageView2 = (ImageView) itemView.findViewById(R.id.item1_obligation);
            imageView3 = (ImageView) itemView.findViewById(R.id.item1_waiting);
            imageView4 = (ImageView) itemView.findViewById(R.id.item1_received);
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder2(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_personal_item2);
            textView = (TextView) itemView.findViewById(R.id.text_personal_item2);
        }
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder {

        public MyViewHolder3(View itemView) {
            super(itemView);

        }
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  //先得到构造器
        builder.setMessage("拨打客服电话:13454783099?"); //设置内容,客服电话：13454783099
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "17855831283"));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    private void dialogExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  //先得到构造器
        builder.setMessage("确定退出当前账号？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sp = context.getSharedPreferences("isfirst", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("isfirstIn", "");
                editor.commit();
                Intent intent1 = new Intent(context, LoginActivity.class);
                context.startActivity(intent1);
                ((Activity) context).finish();
                context.startActivity(intent1);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
