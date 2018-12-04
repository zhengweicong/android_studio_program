package com.zwc.sellsys.android.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.zwc.sellsys.android.entity.OrderBean;
import com.zwc.sellsys.android.utils.CloseUtil;
import com.zwc.sellsys.android.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao {
    public static final String TAG = OrderDao.class.getSimpleName();

    public OrderBean createOrderBean(Cursor cursor) {
        OrderBean orderBean = new OrderBean();
        orderBean.setBuyTime(cursor.getString(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_BUY_DATE)));
        orderBean.setPayTime(cursor.getString(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_PAY_DATE)));
        orderBean.setSendTime(cursor.getString(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_SEND_DATE)));
        orderBean.setBuyerId(cursor.getString(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_BUYER_ID)));
        orderBean.setCostPrice(cursor.getDouble(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_COST_PRICE)));
        orderBean.setCount(cursor.getInt(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_COUNT)));
        orderBean.setDescription(cursor.getString(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_DESCRIPTION)));
        orderBean.setOrderNumber(cursor.getString(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_ORDER_NO)));
        orderBean.setDiscount(cursor.getInt(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_DISCOUNT)));
        orderBean.setDeliveryTime(cursor.getString(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_DELIVERY_DATE)));
        orderBean.setIntroducerId(cursor.getInt(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_INTRODUCER_ID)));
        orderBean.setSellPrice(cursor.getDouble(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_SELL_PRICE)));
        orderBean.setSpecificationId(cursor.getInt(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_SPECIFICATION_ID)));
        orderBean.setFree(cursor.getInt(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_IS_FREE)));
        orderBean.setIsPay(cursor.getInt(cursor.getColumnIndex(OrderBean.Entry.COLUMN_NAME_IS_PAY)));
        orderBean.setOrderId(cursor.getInt(cursor.getColumnIndex(OrderBean.Entry._ID)));
        return orderBean;
    }


    public boolean insert(OrderBean order) {

        ContentValues values = new ContentValues();
        values.put(OrderBean.Entry.COLUMN_NAME_BUY_DATE, order.getBuyTime());
        values.put(OrderBean.Entry.COLUMN_NAME_DELIVERY_DATE, order.getDeliveryTime());
        values.put(OrderBean.Entry.COLUMN_NAME_PAY_DATE, order.getPayTime());
        values.put(OrderBean.Entry.COLUMN_NAME_SEND_DATE, order.getSendTime());
        values.put(OrderBean.Entry.COLUMN_NAME_BUYER_ID, order.getBuyerId());
        values.put(OrderBean.Entry.COLUMN_NAME_COST_PRICE, order.getCostPrice());
        values.put(OrderBean.Entry.COLUMN_NAME_COUNT, order.getCount());
        values.put(OrderBean.Entry.COLUMN_NAME_DESCRIPTION, order.getDescription());
        values.put(OrderBean.Entry.COLUMN_NAME_ORDER_NO, order.getOrderNumber());
        values.put(OrderBean.Entry.COLUMN_NAME_DISCOUNT, order.getDiscount());
        values.put(OrderBean.Entry.COLUMN_NAME_IS_FREE, order.getFree());
        values.put(OrderBean.Entry.COLUMN_NAME_INTRODUCER_ID, order.getIntroducerId());
        values.put(OrderBean.Entry.COLUMN_NAME_SELL_PRICE, order.getSellPrice());
        values.put(OrderBean.Entry.COLUMN_NAME_SPECIFICATION_ID, order.getSpecificationId());
        values.put(OrderBean.Entry.COLUMN_NAME_IS_PAY, order.getIsPay());


        long result = db.insert(OrderBean.Entry.TABLE_NAME, null, values);
        if (result > 0) {
            LogUtil.i(TAG, "增加订单成功");
            return true;
        } else {
            LogUtil.i(TAG, "增加订单失败");
            return false;
        }
    }

    public List<OrderBean> getOrders() {

        List<OrderBean> orderBeans = new ArrayList<OrderBean>();
        Cursor cursor = db.query(OrderBean.Entry.TABLE_NAME, null, null, null, null, null, null, null);
        if (null == cursor) {
            return orderBeans;
        }
        while (cursor.moveToNext()) {
            orderBeans.add(createOrderBean(cursor));
        }
        CloseUtil.close(cursor);
        return orderBeans;
    }
}
