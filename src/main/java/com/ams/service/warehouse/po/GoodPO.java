package com.ams.service.warehouse.po;

/**
 * @author Alexey Mironov
 */
public class GoodPO {

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String COUNT = "count";

    private Long id;
    private String name;
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
