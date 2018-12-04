package com.zwc.sellsys.android.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.zwc.sellsys.android.db.DBManager;

public class BaseDao {
    public DBManager mDBManager;
    public SQLiteDatabase db;

    public BaseDao() {
        mDBManager = new DBManager();
        db = mDBManager.getWritableDb();
    }
}
