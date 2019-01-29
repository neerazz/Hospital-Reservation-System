package com.neeraj.assignment.model;

import javax.persistence.Entity;

@Entity
public class ResourceInventory {
    private Resource resource;
    private int resourceCount;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }
}
