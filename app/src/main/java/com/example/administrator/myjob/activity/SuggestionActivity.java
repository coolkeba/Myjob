package com.example.administrator.myjob.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myjob.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by lenovo on 2017/4/24.
 */

public class SuggestionActivity extends AppCompatActivity{
    Toolbar toolbar;
    EditText editText,phoneEditText;
    Button button;
    String suggestion,phone;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(R.layout.activity_suggestion);
        editText = (EditText) findViewById(R.id.suggestion_edit);
        phoneEditText = (EditText) findViewById(R.id.suggestion_number);
        toolbar = (Toolbar) findViewById(R.id.suggestion_bar);
        toolbar.setTitle("意见反馈");//父标题
        setSupportActionBar(toolbar);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button = (Button) findViewById(R.id.button_commit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suggestion = editText.getText().toString();
                phone = phoneEditText.getText().toString();
                if (suggestion.equals("")) {
                    Toast.makeText(SuggestionActivity.this, "请输入问题和意见",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (phone.equals("")) {
                        phone = "0";
                    }
                        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                                //其他配置
                                .build();
                        OkHttpUtils.initClient(okHttpClient);
                        OkHttpUtils.post()//
                                //.addFile("img", "2.jpg", file)
                                .addParams("suggest", suggestion)
                                .addParams("phone", phone)
                                .url("http://s14i594712.iask.in:40293/Action/Action_SuggestAction_add")
                                .build()//
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        System.out.println(e.fillInStackTrace());
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        String string = response;
                                        Toast.makeText(SuggestionActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        finish();
                }
            }
        });
    }
}
