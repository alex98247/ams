package com.ams.service.warehouse.po;

/**
 * @author Alexey Mironov
 */
public class ReservePO {

    public static final String FIELD_GOOD_ID = "good_id";
    public static final String COUNT = "count";

    private Long goodId;
    private int count;

    public ReservePO() {
    }

    public ReservePO(Long goodId, int count) {
        this.goodId = goodId;
        this.count = count;
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
