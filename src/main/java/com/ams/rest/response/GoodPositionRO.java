package com.ams.rest.response;

import com.ams.service.warehouse.Good;

/**
 * @author Alexey Mironov
 */
public class GoodPositionRO {
    private long id;
    private String name;
    private int count;

    public static GoodPositionRO of(Good good, int count) {
        GoodPositionRO result = new GoodPositionRO();
        result.setId(good.getId());
        result.setName(good.getName());
        result.setCount(count);

        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
