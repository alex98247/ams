package com.ams.service.warehouse;

import static java.util.Objects.hash;

/**
 * @author Alexey Mironov
 */
public class Good {

    private long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
