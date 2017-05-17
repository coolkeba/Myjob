package com.example.administrator.myjob.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.administrator.myjob.R;

/**
 * Created by lenovo on 2017/3/27.
 */

public class StartActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private Boolean IsFirst = false;
    private final int DISPLAY = 1500; //延迟三秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                sp = getSharedPreferences("isfirst", MODE_PRIVATE);
                String isLogin = sp.getString("isfirstIn", "");
                if(IsFirst || !TextUtils.isEmpty(isLogin)){
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, DISPLAY);
    }
}
