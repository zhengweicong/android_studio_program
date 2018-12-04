package com.zwc.sellsys.android.entity;

import android.provider.BaseColumns;

/**
 * 订单实体类
 */
public class OrderBean extends BaseBean {

    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "order_tb";
        public static final String COLUMN_NAME_BUYER_ID = "buyer_id";
        public static final String COLUMN_NAME_BUY_DATE = "buy_time";
        public static final String COLUMN_NAME_PAY_DATE = "pay_time";
        public static final String COLUMN_NAME_DELIVERY_DATE = "delivery_time";
        public static final String COLUMN_NAME_SEND_DATE = "send_time";
        public static final String COLUMN_NAME_ORDER_NO = "order_number";
        public static final String COLUMN_NAME_SELL_PRICE = "sell_price";
        public static final String COLUMN_NAME_COST_PRICE = "cost_price";
        public static final String COLUMN_NAME_COUNT = "count";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_INTRODUCER_ID = "introducer_id";
        public static final String COLUMN_NAME_IS_FREE = "is_free";
        public static final String COLUMN_NAME_DISCOUNT = "discount";
        public static final String COLUMN_NAME_SPECIFICATION_ID = "specification_id";
        public static final String COLUMN_NAME_IS_PAY = "is_pay";
        public static final String COLUMN_NAME_EXPRESS_COMPANY = "express_company";
    }

    /**
     * 订单序号，
     */
    private int orderId;


    /**
     * 是否支付
     */
    private int isPay;
    /**
     * 规格id
     */
    private int specificationId;

    /**
     * 折扣
     */
    private float discount;
    /**
     * 本单是否免费，
     * 0，不免费
     * 1，免费
     */
    private int free;
    /**
     * 介绍人id
     */
    private int introducerId;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 购买数量
     */
    private int count;
    /**
     * 售卖价格
     */
    private double sellPrice;
    /**
     * 成本价
     */
    private double costPrice;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 购买者
     */
    private String buyerId;
    /**
     * 购买时间
     */
    private String buyTime;
    /**
     * 支付时间
     */
    private String payTime;
    /**
     * 收货时间
     */
    private String deliveryTime;
    /**
     * 发货时间
     */
    private String sendTime;

    private UserBean buyer;
    private UserBean introducer;
    private SpecificationBean specification;

    public UserBean getBuyer() {
        return buyer;
    }

    public void setBuyer(UserBean buyer) {
        this.buyer = buyer;
    }

    public UserBean getIntroducer() {
        return introducer;
    }

    public void setIntroducer(UserBean introducer) {
        this.introducer = introducer;
    }

    public SpecificationBean getSpecification() {
        return specification;
    }

    public void setSpecification(SpecificationBean specification) {
        this.specification = specification;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public int getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(int specificationId) {
        this.specificationId = specificationId;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getIntroducerId() {
        return introducerId;
    }

    public void setIntroducerId(int introducerId) {
        this.introducerId = introducerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
