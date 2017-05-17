package com.example.administrator.myjob.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myjob.R;
import com.example.administrator.myjob.massage_class.User;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by lenovo on 2017/4/21.
 */
public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {
    User user = new User();
    Toolbar toolbar;
    SharedPreferences sp;
    ImageView imageView;
    TextView nameTextView, sexTextView, numberTextView, addressTextView;
    //    LinearLayout headLinearLayout,nameLinearLayout,sexLinearLayout,numberLinearLayout,addressLinearLayout;
    String number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        sp = getSharedPreferences("isfirst", MODE_PRIVATE);
        number = sp.getString("isfirstIn", "");
        user.setPhone(number);
        toolbar = (Toolbar) findViewById(R.id.personal_bar);
        toolbar.setTitle("个人信息");
        setSupportActionBar(toolbar);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imageView = (ImageView) findViewById(R.id.image_head);
        nameTextView = (TextView) findViewById(R.id.text_name);
        sexTextView = (TextView) findViewById(R.id.text_sex);
        numberTextView = (TextView) findViewById(R.id.text_number);
        addressTextView = (TextView) findViewById(R.id.text_address);
        if (number.equals("")) {
            Toast.makeText(this, "文件丢失", Toast.LENGTH_SHORT).show();
        } else {
            numberTextView.setText(number);
            getMessage();
        }
//        headLinearLayout = (LinearLayout) findViewById(R.id.head_linear);
//        nameLinearLayout = (LinearLayout) findViewById(R.id.name_linear);
//        sexLinearLayout = (LinearLayout) findViewById(R.id.sex_linear);
//        numberLinearLayout = (LinearLayout) findViewById(R.id.number_linear);
//        addressLinearLayout = (LinearLayout) findViewById(R.id.address_linear);
//        headLinearLayout.setOnClickListener(this);
//        nameLinearLayout.setOnClickListener(this);
//        sexLinearLayout.setOnClickListener(this);
//        numberLinearLayout.setOnClickListener(this);
//        addressLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_linear:
                break;
            case R.id.name_linear:
                nameDialog();
                break;
            case R.id.sex_linear:
                sexDialog();
                break;
            case R.id.number_linear:
                break;
            case R.id.address_linear:
                addressDialog();
                break;
        }
    }

    private void nameDialog() {
    /*@setView 装入一个EditView
     */
        final EditText editText = new EditText(PersonalActivity.this);
        final AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(PersonalActivity.this);
        inputDialog.setTitle("输入名称").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText.getText().toString().equals("")) {
                            dialog.dismiss();
                        } else {
                            user.setName(editText.getText().toString());
                            nameTextView.setText(editText.getText().toString());
                        }
                    }
                }).show();
    }

    private void sexDialog() {
        final String[] items = {"男", "女"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(PersonalActivity.this);
        listDialog.setTitle("选择性别");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                user.setSex(items[which]);
                sexTextView.setText(items[which]);
            }
        }).show();
    }

    private void addressDialog() {
    /*@setView 装入一个EditView
     */
        final EditText editText = new EditText(PersonalActivity.this);
        final AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(PersonalActivity.this);
        inputDialog.setTitle("输入地址").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText.getText().toString().equals("")) {
                            dialog.dismiss();
                        } else {
                            user.setAddress(editText.getText().toString());
                            addressTextView.setText(editText.getText().toString());
                        }
                    }
                }).show();
    }

    private void getMessage() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        OkHttpUtils.post()//
                //.addFile("img", "2.jpg", file)
                .addParams("phone", number)
//                .addParams("user",  new Gson().toJson(user))
                .url("http://s14i594712.iask.in:40293/Action/Action_UserAction_login?phone=17855834179")
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.fillInStackTrace());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        user = new Gson().fromJson(response, User.class);
                        if (user.getName() != null) {
                            nameTextView.setText(user.getName());
                        }
                        if (user.getSex() != null) {
                            sexTextView.setText(user.getSex());
                        }
                        if (user.getAddress() != null) {
                            addressTextView.setText(user.getAddress());
                        }
                    }
                });
    }

    private void sendMessage() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        OkHttpUtils.post()//
                //.addFile("img", "2.jpg", file)
//                .addParams("phone", number)
                .addParams("user", new Gson().toJson(user))
                .url("http://s14i594712.iask.in:40293/Action/Action_UserAction_alert")
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.fillInStackTrace());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(PersonalActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sendMessage();
    }
}
