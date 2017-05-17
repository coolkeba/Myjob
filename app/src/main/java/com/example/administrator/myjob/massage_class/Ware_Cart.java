package com.example.administrator.myjob.massage_class;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/4/25.
 */

public class Ware_Cart {
    Bitmap ware_bitmap;
    String ware_name;
    String ware_end_date;
    double ware_number;
    double one_ware_all_price;
    public void setWare_bitmap(Bitmap ware_bitmap){
        this.ware_bitmap=Bitmap.createBitmap(ware_bitmap);
    }
    public void setWare_name(String ware_name){
        this.ware_name=ware_name;
    }
    public void setWare_end_date(String ware_end_date){
        this.ware_end_date=ware_end_date;
    }
    public void setWare_number(double ware_number){
        this.ware_number=ware_number;
    }
    public void setOne_ware_all_price(double one_ware_all_price){
        this.one_ware_all_price=one_ware_all_price;
    }
    public Bitmap getWare_bitmap(){
        return this.ware_bitmap;
    }
    public String getWare_name(){
        return this.ware_name;
    }
    public String getWare_end_date(){return this.ware_end_date;}
    public double getWare_number(){
        return this.ware_number;
    }
    public double getOne_ware_all_price(){
        return this.one_ware_all_price;
    }
}
