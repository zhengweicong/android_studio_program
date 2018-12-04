package com.zwc.sellsys.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zwc.sellsys.android.entity.CompensationBean;
import com.zwc.sellsys.android.entity.OrderBean;
import com.zwc.sellsys.android.entity.SpecificationBean;
import com.zwc.sellsys.android.entity.UserBean;

public class OpenDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 4;
    private static final String DB_NAME = "sellsys.db";
    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INT = " INTEGER";
    private static final String TYPE_DOUBLE = " DOUBLE";
    private static final String COMM_SPA = ",";


    private String SQL_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " +
            UserBean.Entry.TABLE_NAME + " (" +
            UserBean.Entry._ID + TYPE_INT + " PRIMARY KEY AUTOINCREMENT" + COMM_SPA +
            UserBean.Entry.COLUMN_NAME_NAME + TYPE_TEXT + COMM_SPA +
            UserBean.Entry.COLUMN_NAME_PHONE + TYPE_TEXT + COMM_SPA +
            UserBean.Entry.COLUMN_NAME_ADDRESS + TYPE_TEXT +
            ")";

    private String SQL_CREATE_TABLE_SPECIFICATION = "CREATE TABLE IF NOT EXISTS " +
            SpecificationBean.Entry.TABLE_NAME + "(" +
            SpecificationBean.Entry._ID + TYPE_INT + " PRIMARY KEY AUTOINCREMENT" + COMM_SPA +
            SpecificationBean.Entry.COLUMN_NAME_TYPE + TYPE_INT + COMM_SPA +
            SpecificationBean.Entry.COLUMN_NAME_WEIGHT + TYPE_DOUBLE + COMM_SPA +
            SpecificationBean.Entry.COLUMN_NAME_PRICE + TYPE_DOUBLE + COMM_SPA +
            SpecificationBean.Entry.COLUMN_NAME_DESCRIPTION + TYPE_TEXT +
            ")";

    private String SQL_CREATE_TABLE_COMPENSATION = "CREATE TABLE IF NOT EXISTS " +
            CompensationBean.Entry.TABLE_NAME + "(" +
            CompensationBean.Entry._ID + TYPE_INT + " PRIMARY KEY AUTOINCREMENT" + COMM_SPA +
            CompensationBean.Entry.COLUMN_NAME_ORDER_ID + TYPE_INT + COMM_SPA +
            CompensationBean.Entry.COLUMN_NAME_PRICE + TYPE_DOUBLE + COMM_SPA +
            CompensationBean.Entry.COLUMN_NAME_DESCRIPTION + TYPE_TEXT +
            ")";

    private String SQL_CREATE_TABLE_ORDER = "CREATE TABLE IF NOT EXISTS " +
            OrderBean.Entry.TABLE_NAME + "(" +
            OrderBean.Entry._ID + TYPE_INT + " PRIMARY KEY AUTOINCREMENT" + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_INTRODUCER_ID + TYPE_INT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_BUYER_ID + TYPE_INT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_SPECIFICATION_ID + TYPE_INT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_ORDER_NO + TYPE_TEXT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_EXPRESS_COMPANY + TYPE_TEXT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_COST_PRICE + TYPE_DOUBLE + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_SELL_PRICE + TYPE_DOUBLE + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_DISCOUNT + TYPE_INT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_COUNT + TYPE_INT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_DESCRIPTION + TYPE_TEXT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_IS_FREE + TYPE_INT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_IS_PAY + TYPE_INT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_BUY_DATE + TYPE_TEXT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_PAY_DATE + TYPE_TEXT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_SEND_DATE + TYPE_TEXT + COMM_SPA +
            OrderBean.Entry.COLUMN_NAME_DELIVERY_DATE + TYPE_TEXT +
            ")";

    public OpenDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (null != db) {
            db.execSQL(SQL_CREATE_TABLE_USER);
            db.execSQL(SQL_CREATE_TABLE_SPECIFICATION);
            db.execSQL(SQL_CREATE_TABLE_ORDER);
            db.execSQL(SQL_CREATE_TABLE_COMPENSATION);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
