package com.ams.service.application.po;

import java.util.Map;

public class ApplicationPO {
    public static final String FIELD_ID = "id";
    public static final String FIELD_CUSTOMER_ID = "customer_id";
    public static final String FIELD_MANAGER = "manager_username";
    public static final String FIELD_FINISHED = "finished";

    public static final String FIELD_GOOD_ID = "good_id";
    public static final String FIELD_GOOD_COUNT= "count";

    private Long id;
    private Long customerId;
    private Boolean finished;
    private String managerUsername;
    private Map<Long, Integer> goods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public Map<Long, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<Long, Integer> goods) {
        this.goods = goods;
    }
}
