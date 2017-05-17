package com.example.administrator.myjob.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myjob.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.mob.tools.utils.ResHelper.getStringRes;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CODE_ING = 1;   //已发送，倒计时
    private static final int CODE_REPEAT = 2;  //重新发送
    private static final int SMSDDK_HANDLER = 3;  //短信回调
    private int time = 0;
    private String appKey = "1c3ea1014b8c9";
    private String appSecret = "c662e664c659926570ac3ecc22bf0ec4";
    private String phoneNumber, number;
    private EditText phone;
    private EditText num;
    private Button getSMS;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.login);
        phone = (EditText) findViewById(R.id.edit_number);
        num = (EditText) findViewById(R.id.edit_SMS);
        getSMS = (Button) findViewById(R.id.get_SMS);
        login = (Button) findViewById(R.id.button_login);
        getSMS.setOnClickListener(this);
        login.setOnClickListener(this);
        SMSSDK.initSDK(this, appKey, appSecret, true);
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = SMSDDK_HANDLER;
                handle.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_SMS:
                phoneNumber = phone.getText().toString().trim();
                if (phoneNumber.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入手机号",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (phoneNumber.length() == 11) {
//                        if (ContextCompat.checkSelfPermission(LoginActivity.this,
//                                Manifest.permission.)
//                                != PackageManager.PERMISSION_GRANTED) {
//                            ActivityCompat.requestPermissions(LoginActivity.this,
//                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                    6);
//                        }
                        SMSSDK.getVerificationCode("86", phoneNumber);
                        getSMS.setClickable(false);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                time = 60;
                                for (int i = 60; i > 0; i--) {
                                    handle.sendEmptyMessage(CODE_ING);
                                    time = time - 1;
                                    if (i <= 0) {
                                        break;
                                    }
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                handle.sendEmptyMessage(CODE_REPEAT);
                            }
                        }).start();
                    } else {
                        Toast.makeText(LoginActivity.this, "请输入正确的手机号",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.button_login:
//                phoneNumber = "17855834196";
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                intent.putExtra("num", phoneNumber);
//                startActivity(intent);
//                finish();
                if (num.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入验证码",
                            Toast.LENGTH_SHORT).show();
                } else {
                    number = num.getText().toString().trim();
                    SMSSDK.submitVerificationCode("86", phoneNumber, number);
                }
                break;
            default:
                break;
        }
    }

    Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_ING:
                    getSMS.setText("重新发送(" + time + ")");
                    break;
                case CODE_REPEAT:
                    getSMS.setText("获取验证码");
                    getSMS.setClickable(true);
                    break;
                case SMSDDK_HANDLER:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //回调完成
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            //提交验证码成功
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            SharedPreferences sp = getSharedPreferences("isfirst", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("isfirstIn", phoneNumber);
                            editor.commit();
                            finish();
                        } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            //获取验证码成功
                        } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                            //返回支持发送验证码的国家列表
                        }
                    } else {
                        ((Throwable) data).printStackTrace();
                        int resId = getStringRes(LoginActivity.this, "smssdk_network_error");
                        Toast.makeText(LoginActivity.this, "验证码错误",
                                Toast.LENGTH_SHORT).show();
                        num.selectAll();
                        if (resId > 0) {
                            Toast.makeText(LoginActivity.this, resId,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
