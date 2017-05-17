package com.example.administrator.myjob.massage_class;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/4/2.
 */

public class Person {

    String title;
    String context1;
    String context2;
    String zan;
    Bitmap bitmap;

    public Person(Bitmap bitmap, String title, String context1, String context2, String zan){
        this.title = title;
        this.context1 = context1;
        this.context2 = context2;
        this.bitmap = bitmap;
        this.zan = zan;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getContext1() {
        return context1;
    }

    public String getTitle() {
        return title;
    }

    public String getContext2() {
        return context2;
    }

    public String getZan() {
        return zan;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setContext1(String context1) {
        this.context1 = context1;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext2(String context2) {
        this.context2 = context2;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }
}
