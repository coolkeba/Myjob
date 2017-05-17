package com.example.administrator.myjob;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/12.
 */

public class DLView{

    Drawable bg;
    boolean isbg1;
    boolean isbg2;
    boolean isbg3;
    int day = 0;
    Context context;

    public DLView(Context context) {
        this.context = context;
    }

    public View dView(){
        View view = LayoutInflater.from(context).inflate(R.layout.day,null);
        final LinearLayout dayLayout0 = (LinearLayout) view.findViewById(R.id.day_00);
        final LinearLayout dayLayout1 = (LinearLayout) view.findViewById(R.id.day_11);
        final LinearLayout dayLayout2 = (LinearLayout) view.findViewById(R.id.day_22);
        bg = ContextCompat.getDrawable(context, R.drawable.circle_step_done);
        int c = context.getResources().getColor(cn.pedant.SweetAlert.R.color.error_stroke_color);
        bg.setColorFilter(new PorterDuffColorFilter(c, PorterDuff.Mode.SRC_IN));
        isbg1 = false;
        isbg2 = false;
        isbg3 = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            dayLayout0.setBackground(bg);
            day = 1;
        }
        dayLayout1.setBackgroundResource(0);
        dayLayout2.setBackgroundResource(0);
        dayLayout0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isbg1 == true) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        dayLayout0.setBackground(bg);
                        day = 1;
                    }
                    isbg1 = false;
                    dayLayout1.setBackgroundResource(0);
                    isbg2 = false;
                    dayLayout2.setBackgroundResource(0);
                    isbg3 = false;

                }
            }
        });
        dayLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isbg2 == false) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        dayLayout1.setBackground(bg);
                        day = 2;
                    }
                    isbg2 = true;
                    dayLayout0.setBackgroundResource(0);
                    isbg1 = true;
                    dayLayout2.setBackgroundResource(0);
                    isbg3 = false;

                }
            }
        });
        dayLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isbg3 == false) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        dayLayout2.setBackground(bg);
                        day = 3;
                    }
                    isbg3 = true;
                    dayLayout0.setBackgroundResource(0);
                    isbg1 = true;
                    dayLayout1.setBackgroundResource(0);
                    isbg2 = false;

                }
            }
        });
        final TextView dayText0 = (TextView) dayLayout0.findViewById(cn.pedant.SweetAlert.R.id.day);
        final TextView dayText1 = (TextView) dayLayout1.findViewById(cn.pedant.SweetAlert.R.id.day);
        final TextView dayText2 = (TextView) dayLayout2.findViewById(cn.pedant.SweetAlert.R.id.day);
        dayText0.setText("1");
        dayText1.setText("2");
        dayText2.setText("3");

        return view;
    }
}
