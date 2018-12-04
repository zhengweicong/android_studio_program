package com.zwc.sellsys.android.entity;

import android.provider.BaseColumns;

import java.io.Serializable;

public class SpecificationBean implements Serializable {

    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "specification_tb";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_PRICE = "price";
    }

    /**
     * 超大果85-90及以上
     * 大果80-85
     * 中果75-80
     * 小果70-75
     * 超级小果70以下
     */
    public enum SizeType {
        SUPER_BIG,
        BIG,
        MEDIUM,
        SMALL,
        SUPER_SMALL
    }


    /**
     * 唯一id
     */
    private String specificationId;


    /**
     * 尺寸，80-85mm
     */
    private String description;

    /**
     * 公斤/斤
     */
    private String weight;

    /**
     * 大果，中果，小果
     */
    private SizeType type;

    /**
     * 价格
     */
    private String price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public SizeType getType() {
        return type;
    }

    public void setType(SizeType type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(String specificationId) {
        this.specificationId = specificationId;
    }
}
