package com.ams.rest.request;

import com.ams.service.warehouse.Good;

import java.util.Map;

/**
 * @author Alexey Mironov
 */
public class ApplicationRequest {
    private Long id;
    private long customerId;
    private boolean needDelivery;
    private Boolean finished;
    private String managerUsername;
    private Map<Long, Integer> goods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
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

    public boolean needDelivery() {
        return needDelivery;
    }

    public void setNeedDelivery(boolean needDelivery) {
        this.needDelivery = needDelivery;
    }
}
