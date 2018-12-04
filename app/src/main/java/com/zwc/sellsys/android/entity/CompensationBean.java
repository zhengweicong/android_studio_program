package com.zwc.sellsys.android.entity;

import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * 赔付实体类
 * 用于记录用户购买后，有产生赔偿行为信息
 */
public class CompensationBean implements Serializable {

    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "compensation_tb";
        public static final String COLUMN_NAME_ORDER_ID = "order_id";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

    /**
     * 赔付表id
     */
    private String compensationId;
    /**
     * 订单ID
     */
    private int orderId;
    /**
     * 赔付总额
     */
    private Double price;
    /**
     * 赔付描述，因为什么原因产生的，如何赔付的
     */
    private String description;

    public String getCompensationId() {
        return compensationId;
    }

    public void setCompensationId(String compensationId) {
        this.compensationId = compensationId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
