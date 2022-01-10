package com.ams.service.application;

import com.ams.rest.request.ApplicationRequest;

import java.util.Map;

public class Application {
    private Long id;
    private long customerId;
    private boolean needDelivery;
    private Boolean finished;
    private String managerUsername;
    private String delivery;
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

    public static Application of(ApplicationRequest application) {
        Application result = new Application();
        result.setCustomerId(application.getCustomerId());
        result.setFinished(application.getFinished());
        result.setId(application.getId());
        result.setNeedDelivery(application.needDelivery());
        result.setGoods(application.getGoods());
        result.setManagerUsername(application.getManagerUsername());
        result.setDelivery(application.getDelivery());
        return result;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
