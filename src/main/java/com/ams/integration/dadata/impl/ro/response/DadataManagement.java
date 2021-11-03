package com.ams.integration.dadata.impl.ro.response;

import java.io.Serializable;

public class DadataManagement implements Serializable {

    private String name;

    private String post;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
