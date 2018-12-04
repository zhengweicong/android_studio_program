package com.zwc.sellsys.android.entity;

import android.provider.BaseColumns;

import java.io.Serializable;

public class UserBean implements Serializable {

    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "user_tb";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_ADDRESS = "address";
    }

    private String userId;

    /**
     * 类型：
     * 0 :寄件人
     * 1 ：收件人
     */
    private int type;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 家庭地址
     */
    private String address;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
