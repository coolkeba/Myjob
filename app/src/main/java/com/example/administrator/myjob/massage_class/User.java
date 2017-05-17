package com.example.administrator.myjob.massage_class;


public class User {
    private String phone;
    private String password;
    private String name;
    private String address;
    private String avatar;
    private String sex;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String phone, String password, String name, String address, String avatar, String sex) {
        super();
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.address = address;
        this.avatar = avatar;
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex(){
        return sex;
    }
}

