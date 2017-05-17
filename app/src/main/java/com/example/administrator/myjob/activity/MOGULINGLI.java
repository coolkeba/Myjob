package com.example.administrator.myjob.activity;

import android.app.Application;
import android.graphics.Bitmap;


import com.example.administrator.myjob.massage_class.Ware_Cart;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/26.
 */

public class MOGULINGLI extends Application {

    public ArrayList<Ware_Cart> ware_carts=new ArrayList<>();
    public ArrayList<Ware_Cart> getWare_carts(){
        return this.ware_carts;
    }
    public void setWare_carts(Bitmap ware_bitmap,
            String ware_name,
            String ware_end_date,
            double ware_number,
            double one_ware_all_price){
        Ware_Cart ware_cart =new Ware_Cart();
        ware_cart.setWare_bitmap(ware_bitmap);
        ware_cart.setWare_name(ware_name);
        ware_cart.setWare_number(ware_number);
        ware_cart.setWare_end_date(ware_end_date);
        ware_cart.setOne_ware_all_price(one_ware_all_price);
        ware_carts.add(ware_cart);
    }
    public void addWare_carts(Ware_Cart ware_cart){
        ware_carts.add(ware_cart);
    }
}
