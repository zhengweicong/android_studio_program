package com.zwc.sellsys.android.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.zwc.sellsys.android.entity.SpecificationBean;
import com.zwc.sellsys.android.utils.CloseUtil;
import com.zwc.sellsys.android.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class SpecificationDao extends BaseDao {
    public static final String TAG = SpecificationDao.class.getSimpleName();

    public SpecificationBean createSpecificationBean(Cursor cursor) {
        SpecificationBean bean = new SpecificationBean();
        bean.setWeight(cursor.getString(cursor.getColumnIndex(SpecificationBean.Entry.COLUMN_NAME_WEIGHT)));
        bean.setPrice(cursor.getString(cursor.getColumnIndex(SpecificationBean.Entry.COLUMN_NAME_PRICE)));
        bean.setDescription(cursor.getString(cursor.getColumnIndex(SpecificationBean.Entry.COLUMN_NAME_DESCRIPTION)));
        bean.setSpecificationId(cursor.getString(cursor.getColumnIndex(SpecificationBean.Entry._ID)));
        String type = cursor.getString(cursor.getColumnIndex(SpecificationBean.Entry.COLUMN_NAME_TYPE));
        if (type.equals("2")) {
            bean.setType(SpecificationBean.SizeType.MEDIUM);
        } else if (type.equals("1")) {
            bean.setType(SpecificationBean.SizeType.SMALL);
        } else if (type.equals("3")) {
            bean.setType(SpecificationBean.SizeType.BIG);
        } else if (type.equals("4")) {
            bean.setType(SpecificationBean.SizeType.SUPER_BIG);
        } else if (type.equals("0")) {
            bean.setType(SpecificationBean.SizeType.SUPER_SMALL);
        }
        return bean;
    }

    public ContentValues createContentValues(SpecificationBean bean) {
        ContentValues values = new ContentValues();
        values.put(SpecificationBean.Entry.COLUMN_NAME_DESCRIPTION, bean.getDescription());
        values.put(SpecificationBean.Entry.COLUMN_NAME_PRICE, bean.getPrice());
        values.put(SpecificationBean.Entry.COLUMN_NAME_WEIGHT, bean.getWeight());
        if (bean.getType().equals(SpecificationBean.SizeType.SMALL)) {
            values.put(SpecificationBean.Entry.COLUMN_NAME_TYPE, "1");
        } else if (bean.getType().equals(SpecificationBean.SizeType.MEDIUM)) {
            values.put(SpecificationBean.Entry.COLUMN_NAME_TYPE, "2");
        } else if (bean.getType().equals(SpecificationBean.SizeType.BIG)) {
            values.put(SpecificationBean.Entry.COLUMN_NAME_TYPE, "3");
        } else if (bean.getType().equals(SpecificationBean.SizeType.SUPER_BIG)) {
            values.put(SpecificationBean.Entry.COLUMN_NAME_TYPE, "4");
        } else if (bean.getType().equals(SpecificationBean.SizeType.SUPER_SMALL)) {
            values.put(SpecificationBean.Entry.COLUMN_NAME_TYPE, "0");
        }
        return values;
    }

    public List<SpecificationBean> findAll() {
        List<SpecificationBean> beans = new ArrayList<SpecificationBean>();

        Cursor cursor = db.query(SpecificationBean.Entry.TABLE_NAME, null, null, null, null, null,
                SpecificationBean.Entry.COLUMN_NAME_TYPE + " desc ");
        while (cursor.moveToNext()) {
            beans.add(createSpecificationBean(cursor));
        }
        CloseUtil.close(cursor);
        return beans;
    }

    public boolean insert(SpecificationBean bean) {
        ContentValues values = createContentValues(bean);
        long success = db.insert(SpecificationBean.Entry.TABLE_NAME, null, values);
        if (success >= 1) {
            LogUtil.i(TAG, "增加规格成功");
            return true;
        } else {
            LogUtil.i(TAG, "增加规格失败");
            return false;
        }
    }

    public boolean isExists(SpecificationBean bean) {
        boolean result;
        Cursor cursor = db.query(SpecificationBean.Entry.TABLE_NAME, null,
                SpecificationBean.Entry.COLUMN_NAME_WEIGHT + " = ?", new String[]{bean.getWeight()}, null, null, null);
        if (cursor.moveToFirst()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean update(SpecificationBean from, SpecificationBean to) {
        ContentValues values = createContentValues(to);
        int result = db.update(SpecificationBean.Entry.TABLE_NAME, values,
                SpecificationBean.Entry._ID + "= ?", new String[]{from.getSpecificationId()});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(SpecificationBean target) {
        int result = db.delete(SpecificationBean.Entry.TABLE_NAME, SpecificationBean.Entry.COLUMN_NAME_WEIGHT + " = ?", new String[]{target.getWeight()});
        if (result > 0) {
            LogUtil.i(TAG, "删除成功");
            return true;
        } else {
            LogUtil.i(TAG, "删除失败");
            return false;
        }
    }

    public SpecificationBean getSpecificationById(int id) {
        SpecificationBean bean = null;
        Cursor cursor = db.query(SpecificationBean.Entry.TABLE_NAME, null,
                SpecificationBean.Entry._ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (null != cursor && cursor.moveToFirst()) {
            bean = createSpecificationBean(cursor);
        } else {
            bean = new SpecificationBean();
            Log.i(TAG, "cursor is null");
        }
        CloseUtil.close(cursor);
        return bean;
    }
}
