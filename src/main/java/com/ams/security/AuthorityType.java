package com.ams.security;

/**
 * @author Alexey Mironov
 */
public enum AuthorityType {
    ADMIN("ADMIN"),
    SELL("SELL"),
    ORDER("ORDER");

    private final String name;

    AuthorityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
