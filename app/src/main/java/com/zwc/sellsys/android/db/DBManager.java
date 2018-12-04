package com.zwc.sellsys.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zwc.sellsys.android.utils.AppUtil;

public class DBManager {


    private Context mContext;

    public DBManager(Context context) {

        mContext = context;
    }

    public DBManager() {
        mContext = AppUtil.getAppContext();
    }

    public SQLiteDatabase getWritableDb() {
        OpenDBHelper dbHelper = new OpenDBHelper(mContext);
        return dbHelper.getWritableDatabase();
    }

}
