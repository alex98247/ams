package com.ams.service.warehouse;

import static java.util.Objects.hash;

/**
 * @author Alexey Mironov
 */
public class Good {

    private Long id;

    private String name;

    private int cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Good) {
            return this.id == ((Good) obj).id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
