package com.example.administrator.myjob.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.myjob.R;
import com.example.administrator.myjob.massage_class.Ware_Cart;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/23.
 */

public class One_Ware_Acivity extends AppCompatActivity {
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.one_ware_toolbar)
    Toolbar one_ware_toolbar;
    @Bind(R.id.ware_big_picture)
    ImageView ware_big_picture;
    @Bind(R.id.add_bt)
    ImageButton add_bt;
    @Bind(R.id.sub_bt)
    ImageButton sub_bt;
    @Bind(R.id.add_cart)
    ImageButton add_cart;
    @Bind(R.id.ware_small_picture)
    ImageView ware_small_picture;
    @Bind(R.id.one_ware_price)
    TextView one_ware_price;
    @Bind(R.id.one_all_price)
    TextView one_all_price;
    @Bind(R.id.number)
    TextView number;
    @Bind(R.id.ware_nametx)
    TextView ware_nametx;
    @Bind(R.id.ware_introduction)
    TextView ware_introduction;
    public ArrayList<Ware_Cart> ware_carts=new ArrayList<>();
    public Ware_Cart ware_cart;
    Bundle bundle;
    public MOGULINGLI mogulingli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_ware_activity);
        ButterKnife.bind(this);
        mogulingli=(MOGULINGLI)getApplication();
        Intent intent = getIntent();
        bundle = intent.getExtras();
        String title = bundle.getString("name");
        one_ware_toolbar.setTitle(title);
        setSupportActionBar(one_ware_toolbar);
        ActionBar ab = getSupportActionBar();
        one_ware_price.setText(bundle.getString("price"));
        ware_nametx.setText(bundle.getString("name"));
        ware_introduction.setText(bundle.getString("introduction"));
        //设置传过来的处理过的小图片
        ware_small_picture.setImageBitmap((Bitmap) bundle.getParcelable("bitmap"));
        //使能app bar的导航功能(返回上一个activity)
        ab.setDisplayHomeAsUpEnabled(true);

        Glide.with(this).load("http://s14i594712.iask.in:40293/images/"+bundle.getString("pitcure_url")).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ware_big_picture.setImageBitmap(resource);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shopping_cart_bt, menu);
        return true;
    }

    //界面右上角item按钮
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.shopping_cart_activity:
                Intent intent = new Intent(One_Ware_Acivity.this, shopping_cartActivity.class);
                startActivity(intent);
                break;
        }
        //调用父类的方法返回上一个activity
        return super.onOptionsItemSelected(item);
    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.add_bt:
                number.setText(String.valueOf(Double.parseDouble(number.getText().toString())+0.5));
                one_all_price.setText(String.valueOf(Double.parseDouble(number.getText().toString())*Double.parseDouble(one_ware_price.getText().toString())));
                break;
            case R.id.sub_bt:
                if (Double.parseDouble(number.getText().toString())>0){
                    number.setText(String.valueOf(Double.parseDouble(number.getText().toString())-0.5));
                    one_all_price.setText(String.valueOf(Double.parseDouble(number.getText().toString())*Double.parseDouble(one_ware_price.getText().toString())));
                }
                break;
            case R.id.add_cart:
                    if(Double.parseDouble(number.getText().toString())>0){
                        //这里把数据放入到对象中 再把对象放到bundle中。(还未成功)
                        ware_cart=new Ware_Cart();
                        ware_cart.setWare_bitmap((Bitmap) bundle.getParcelable("bitmap"));
                        ware_cart.setWare_name(ware_nametx.getText().toString());
                        ware_cart.setWare_number(Double.parseDouble(number.getText().toString()));
                        ware_cart.setOne_ware_all_price(Double.parseDouble(one_all_price.getText().toString()));
                        ware_cart.setWare_end_date(bundle.getString("date"));
                        mogulingli.addWare_carts(ware_cart);
                    }
                    else Toast.makeText(One_Ware_Acivity.this,"数量为0无法加入购物车哦",Toast.LENGTH_SHORT).show();
                break;

        }
    }


}
