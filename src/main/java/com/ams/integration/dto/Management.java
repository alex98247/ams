package com.ams.integration.dto;

/**
 * Contains information about management of legal entity.
 *
 * @author Alexey Mironov
 */
public class Management {
    /**
     * Name of manager of legal entity.
     */
    private String name;
    /**
     * Post of manager of legal entity.
     */
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
