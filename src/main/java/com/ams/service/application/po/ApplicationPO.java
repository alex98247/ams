package com.ams.service.application.po;

public class ApplicationPO {
    public static final String FIELD_ID = "id";
    public static final String FIELD_CUSTOMER_ID = "customer_id";
    public static final String FIELD_MANAGER = "manager_username";
    public static final String FIELD_FINISHED = "finished";

    private long id;
    private long customerId;
    private Boolean finished;
    private String managerUsername;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
