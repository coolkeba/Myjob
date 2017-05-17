package com.example.administrator.myjob.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.administrator.myjob.R;
import com.example.administrator.myjob.RecyclerView.Order_For_Goods_RecyclerViewAdapter;
import com.example.administrator.myjob.massage_class.Ware_Cart;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/5.
 */

public class Order_For_Goods extends AppCompatActivity {

    @Bind(R.id.my_name)
    TextView myName;
    @Bind(R.id.my_phone)
    TextView myPhone;
    @Bind(R.id.my_address)
    TextView myAddress;
    @Bind(R.id.order_for_goods_RecyclerView)
    RecyclerView orderForGoodsRecyclerView;
    @Bind(R.id.submit_order_bt)
    ImageButton submitOrderBt;
    @Bind(R.id.order_price)
    TextView orderPrice;
    @Bind(R.id.cancel_bt)
    ImageButton cancelBt;
    public MOGULINGLI mogulingli;
//    ArrayList<Boolean> arrayList;
    ArrayList<Integer> arrayList;
//    ArrayList list;
    ArrayList<Ware_Cart> arrayListware_cart;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_for_goods);
        System.out.println("cccccccccccc");
        ButterKnife.bind(this);
        mogulingli=(MOGULINGLI)getApplication();
        Bundle bundle=getIntent().getExtras();
        //这个传递方法不可行啊 ~~~
//        list=bundle.getParcelableArrayList("listboolean");
//        for(int i=0;i<list.size();i++){
//            arrayList.add((Boolean) list.get(i));
//        }
        arrayList=bundle.getIntegerArrayList("arrayList");
        arrayListware_cart=new ArrayList<>();
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i)==1){
                arrayListware_cart.add(mogulingli.getWare_carts().get(i));
            }

        }
        Order_For_Goods_RecyclerViewAdapter order_for_goods_recyclerViewAdapter=new Order_For_Goods_RecyclerViewAdapter(this,arrayListware_cart);
        orderForGoodsRecyclerView.setAdapter(order_for_goods_recyclerViewAdapter);
        orderForGoodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        double order_all_price=0;
        for(int i=0;i<arrayListware_cart.size();i++){
            order_all_price+=arrayListware_cart.get(i).getOne_ware_all_price();
        }
        orderPrice.setText(String.valueOf(order_all_price));
        submitOrderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送订单给后台,删除购物车中被选中作为订单的商品
                for(int i=arrayList.size()-1;i>=0;i--){
                    if(arrayList.get(i)==1){
                        mogulingli.getWare_carts().remove(i);
                    }

                }
                finish();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void Send_Data(){
        URL url = null;
        try {
            url = new URL("http://s14i594712.iask.in:40293/Practice/request/login.jsp");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        OkHttpClient client = new OkHttpClient();

        RequestBody builder = new FormBody.Builder().add("username", "14480010126").add("password","22222").build();

        final Request request = new Request.Builder().url(url).post(builder).build();
        //   根据Request对象发起Get异步Http请求，并添加请求回调
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //请求成功，此处对请求结果进行处理
                String result = response.body().string();
                System.out.println(result);
                //InputStream is = response.body().byteStream();
                //byte[] bytes = response.body().bytes();

            }

            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
                e.printStackTrace();
                System.out.println("请求失败");
            }
        });
    }

}
