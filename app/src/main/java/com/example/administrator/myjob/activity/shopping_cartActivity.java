package com.example.administrator.myjob.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.administrator.myjob.R;
import com.example.administrator.myjob.RecyclerView.Activity_shopping_cart_RecyclerViewadapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/4/21.
 */
public class shopping_cartActivity extends AppCompatActivity {
    public MOGULINGLI mogulingli;
    @Bind(R.id.shopping_cart_toolbar)
    Toolbar shoppingCartToolbar;
    @Bind(R.id.shopping_cart_recyclerview)
    RecyclerView shoppingCartRecyclerview;
    @Bind(R.id.ware_all)
    TextView wareAll;
    @Bind(R.id.submit_order)
    ImageButton submitOrder;
    @Bind(R.id.delete_bt)
    ImageButton delete_bt;
    @Bind(R.id.all_checked)
    ImageButton all_checked;
    long firstclicktime;
    public ArrayList<Boolean> listBoolean;
    public Activity_shopping_cart_RecyclerViewadapter activity_shopping_cart_recyclerViewadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        ButterKnife.bind(this);
        setSupportActionBar(shoppingCartToolbar);
        final ActionBar ab = getSupportActionBar();
        //使能app bar的导航  功能
        ab.setDisplayHomeAsUpEnabled(true);
        mogulingli = (MOGULINGLI) getApplication();
        listBoolean=new ArrayList<>();
        for(int i=0;i<mogulingli.getWare_carts().size();i++){
            listBoolean.add(false);
        }
        activity_shopping_cart_recyclerViewadapter=new Activity_shopping_cart_RecyclerViewadapter(this,mogulingli.getWare_carts(),listBoolean);
        shoppingCartRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        shoppingCartRecyclerview.setAdapter(activity_shopping_cart_recyclerViewadapter);
        firstclicktime=System.currentTimeMillis();

//        activity_shopping_cart_recyclerViewadapter.setOnItemClickListener(new Activity_shopping_cart_RecyclerViewadapter.OnItemClickListener() {
//            @Override
//            //item点击事件
//            public void onClick(int position) {
////                    Toast.makeText(shopping_cartActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
////                    mogulingli.getWare_carts().remove(position);
//////                activity_shopping_cart_recyclerViewadapter.notify();
        //下面这句代码有动画  所以会有一些BUG  暂时不会解决
////                    activity_shopping_cart_recyclerViewadapter.notifyItemRemoved(position);
////
////                    double all_price = 0;
////
////                    for (int i = 0; i < mogulingli.getWare_carts().size(); i++) {
////                        all_price += mogulingli.getWare_carts().get(i).getOne_ware_all_price();
////                    }
////                    wareAll.setText(String.valueOf(all_price));
//
//            }
//
//            @Override
//            public void onLongClick(int position) {
//
//            }
//        });
//        double all_price=0;
//        for(int i=0;i<mogulingli.getWare_carts().size();i++)
//        {
//            all_price+=mogulingli.getWare_carts().get(i).getOne_ware_all_price();
//        }
//        wareAll.setText(String.valueOf(all_price));

        activity_shopping_cart_recyclerViewadapter.setOnCheckClickListener(new Activity_shopping_cart_RecyclerViewadapter.onCheckClickListener() {
            @Override
            public void onClick(int position, boolean isChecked) {
                   listBoolean.set(position,isChecked);
                double all_price=0;
                for(int i=0;i<mogulingli.getWare_carts().size();i++)
                {
                    if(listBoolean.get(i)) {
                        all_price += mogulingli.getWare_carts().get(i).getOne_ware_all_price();
                    }
                }
                wareAll.setText(String.valueOf(all_price));
            }
        });
        all_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double all_price=0;
                for(int i=0;i<mogulingli.getWare_carts().size();i++){
                    listBoolean.set(i,true);
                    all_price += mogulingli.getWare_carts().get(i).getOne_ware_all_price();
                }
                wareAll.setText(String.valueOf(all_price));
                activity_shopping_cart_recyclerViewadapter.notifyDataSetChanged();
            }
        });
        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //一定要从大到小进行for循环 不然会出现靠后选中的item无法删除依旧存在的问题 ，因为这里是一个一个删除的顺序而不是整合起来一起删！！
                for(int i=mogulingli.getWare_carts().size()-1;i>=0;i--){
                    if(listBoolean.get(i)){
                        mogulingli.getWare_carts().remove(i);
                        listBoolean.remove(i);
                        activity_shopping_cart_recyclerViewadapter.notifyDataSetChanged();
                    }

                }
                wareAll.setText("0.0");
                //数据更新
//                activity_shopping_cart_recyclerViewadapter.notifyDataSetChanged();
            }
        });
        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(shopping_cartActivity.this,Order_For_Goods.class);
                Bundle bundle=new Bundle();
                //这个传递方法不可行啊 ~~~
//                ArrayList list=new ArrayList();
//                for(int i=0;i<listBoolean.size();i++){
//                    list.add(listBoolean.get(i));
//                }
//              bundle.putParcelableArrayList("listboolean",list);
                ArrayList<Integer> arrayList=new ArrayList<>();
                for(int i=0;i<listBoolean.size();i++){
                    if(listBoolean.get(i))
                        arrayList.add(1);
                    else
                        arrayList.add(0);
                }
                bundle.putIntegerArrayList("arrayList",arrayList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
//            listBoolean = new ArrayList<>();
//            for (int i = 0; i < mogulingli.getWare_carts().size(); i++) {
//                listBoolean.add(false);
//            }
            for(int i=listBoolean.size()-1;i>=0;i--){
                if(listBoolean.get(i))
                    listBoolean.remove(i);
            }
            wareAll.setText("0.0");
            activity_shopping_cart_recyclerViewadapter.notifyDataSetChanged();

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
