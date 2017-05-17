package com.example.administrator.myjob.DataHttp;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/4/6.
 */

public class DataUpdata {

    Context context;
    String kd_id;
    String kd_name;
    String url;
    JSONArray jArray;
    public String resp;

    public DataUpdata(Context context, String url) {
        this.context = context;
        this.url = url;

    }



    public void onCreate() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        OkHttpUtils.post()//
                //.addFile("img", "2.jpg", file)
                //.headers(params)
                .addParams("phone","17855834179")
                .url(url)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println(e.fillInStackTrace());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        resp = response;

                    }
                });
//        OkHttpUtils.post().url(url).addParams("phone","17855834179").build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                    }
//                    @Override
//                    public void onResponse(String response, int id) {
//                        resp = response;
//
//
//                    }
//                });
    }
//    private void JsonSolve(String response) {
//        try {
//            jArray = new JSONArray(response);
//            for (int i = 0; i < jArray.length(); i++) {
//                JSONObject json_data = jArray.getJSONObject(i);
//                kd_id = json_data.getString("id");
//                kd_name = json_data.getString("name");
//
//                //Toast.makeText(context,kd_id+kd_name,Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public String getKd_id() {
        return kd_id;
    }

    public String getKd_name() {
        return kd_name;
    }
}

