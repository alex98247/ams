package com.ams.service.po;

import java.util.Collections;
import java.util.List;

public class RolePO {

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";

    private long id;
    private String name;
    private List<RightPO> rights = Collections.emptyList();

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

    public List<RightPO> getRights() {
        return rights;
    }

    public void setRights(List<RightPO> rights) {
        this.rights = rights;
    }
}
