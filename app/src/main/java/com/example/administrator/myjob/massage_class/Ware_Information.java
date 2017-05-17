package com.example.administrator.myjob.massage_class;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017/4/5.
 */

public class Ware_Information {
    Drawable ware_drawable;
    String ware_name;
    double ware_price;
    String ware_date;
    String ware_picture_string;
    Bitmap ware_bitmap;
    String ware_introductio;
    String Ware_overplus;
    //可以利用构造函数对不同的fragment进行赋值，或者利用fragment的构造函数来区分不同的fragment。
    public Ware_Information(){

    }
    //神坑啊，Bitmap对象不能直接用=赋值！！！
    public void setWare_Bitmap(Bitmap ware_bitmap){this.ware_bitmap=Bitmap.createBitmap(ware_bitmap);}
    public void setWare_drawable(Drawable ware_drawable){
        this.ware_drawable=ware_drawable;
    }
    public void setWare_name(String ware_name){
        this.ware_name=ware_name;
    }
    public void setWare_price(double ware_price){
        this.ware_price=ware_price;
    }
    public void setWare_date(String ware_data){
        this.ware_date=ware_data;
    }
    public void setWare_picture_string(String ware_picture_string){this.ware_picture_string=ware_picture_string;}
    public void setWare_introduction(String ware_introduction){this.ware_introductio=ware_introduction;}
    public void setWare_overplus(String Ware_overplus){this.Ware_overplus=Ware_overplus;}
    public Bitmap getWare_Bitmap(){return ware_bitmap;}
    public Drawable getWare_drawable(){
        return ware_drawable;
    }
    public String getWare_name(){
        return ware_name;
    }
    public double getWare_price(){
        return ware_price;
    }
    public String getWare_date(){
        return  ware_date;
    }
    public String getWare_picture_string(){return ware_picture_string;}
    public String getWare_introduction(){return ware_introductio;}
    public String getWare_overplus(){return Ware_overplus;}
}
