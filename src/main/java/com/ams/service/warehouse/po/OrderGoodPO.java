package com.ams.service.warehouse.po;

/**
 * @author Alexey Mironov
 */
public class OrderGoodPO {
    public static final String FIELD_ID = "id";
    public static final String FIELD_APPLICATION_ID = "application_id";
    public static final String FIELD_GOOD_ID = "good_id";
    public static final String COUNT = "count";

    private Long id;
    private Long applicationId;
    private Long goodId;
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
