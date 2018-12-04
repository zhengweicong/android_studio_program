package com.zwc.sellsys.android.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.zwc.sellsys.android.entity.PagerBean;
import com.zwc.sellsys.android.entity.UserBean;
import com.zwc.sellsys.android.utils.CloseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库增删改查操作类
 */
public class UserDao extends BaseDao {
    public static final String TAG = UserDao.class.getSimpleName();

    public UserBean createUserBean(Cursor cursor) {
        UserBean userBean = new UserBean();
        userBean.setName(cursor.getString(cursor.getColumnIndex(UserBean.Entry.COLUMN_NAME_NAME)));
        userBean.setAddress(cursor.getString(cursor.getColumnIndex(UserBean.Entry.COLUMN_NAME_ADDRESS)));
        userBean.setPhone(cursor.getString(cursor.getColumnIndex(UserBean.Entry.COLUMN_NAME_PHONE)));
        userBean.setUserId(cursor.getInt(cursor.getColumnIndex(UserBean.Entry._ID)) + "");
        return userBean;
    }


    public long insert(UserBean user) {
        ContentValues values = new ContentValues();
        values.put(UserBean.Entry.COLUMN_NAME_NAME, user.getName());
        values.put(UserBean.Entry.COLUMN_NAME_ADDRESS, user.getAddress());
        values.put(UserBean.Entry.COLUMN_NAME_PHONE, user.getPhone());
        long result = db.insert(UserBean.Entry.TABLE_NAME, null, values);
        return result;
    }

    public boolean getUser(UserBean userBean) {

        Cursor cursor = db.query(UserBean.Entry.TABLE_NAME, null,
                UserBean.Entry.COLUMN_NAME_PHONE + " = ?",
                new String[]{userBean.getPhone()}, null, null, null);

        if (null != cursor && cursor.moveToFirst()) {
            return true;
        } else {
            Log.i(TAG, "cursor is null");
        }

        CloseUtil.close(cursor);
        return false;
    }

    public UserBean getUserById(int id) {
        UserBean userBean = null;
        Cursor cursor = db.query(UserBean.Entry.TABLE_NAME, null,
                UserBean.Entry._ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (null != cursor && cursor.moveToFirst()) {
            userBean = createUserBean(cursor);
        } else {
            userBean = new UserBean();
            Log.i(TAG, "cursor is null");
        }

        CloseUtil.close(cursor);
        return userBean;
    }

    /**
     * 分页查询用户数据
     *
     * @return
     */
    public List<UserBean> getUsers(PagerBean pagerBean) {

        List<UserBean> users = new ArrayList<UserBean>();
        Cursor cursor = null;
        Cursor countCursor = null;

        db.beginTransaction();
        try {
            cursor = db.query(
                    UserBean.Entry.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    UserBean.Entry._ID + " desc",
                    PagerBean.PAGE_SIZE * (pagerBean.getCurrentPage() - 1) + "," + PagerBean.PAGE_SIZE);

            //查询总条目数
            countCursor = db.rawQuery("select count(*) from " + UserBean.Entry.TABLE_NAME, null);
            countCursor.moveToFirst();
            int count = (int) countCursor.getLong(0);
            Log.i(TAG, "totalCount = " + count);
            pagerBean.setTotalCount(count);
            pagerBean.setPageCount(count % PagerBean.PAGE_SIZE == 0
                    ? count / PagerBean.PAGE_SIZE
                    : count / PagerBean.PAGE_SIZE + 1);

            //循环读取分页数据
            while (cursor.moveToNext()) {
                users.add(createUserBean(cursor));
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            CloseUtil.close(cursor);
            CloseUtil.close(countCursor);
        }
        return users;
    }

    /**
     * 查询所有用户数据
     *
     * @return
     */
    public List<UserBean> getUsers() {
        List<UserBean> users = new ArrayList<UserBean>();

        Cursor cursor = db.query(
                UserBean.Entry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                UserBean.Entry._ID + " desc",
                null);

        //循环读取分页数据
        while (cursor.moveToNext()) {
            users.add(createUserBean(cursor));
        }
        CloseUtil.close(cursor);

        return users;
    }
}
