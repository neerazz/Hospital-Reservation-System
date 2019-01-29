package com.neeraj.assignment.model;

import javax.persistence.*;

@Entity
public class Resource {

    public Resource() {
    }

    @Id
    @Column(name="resourceid")
    private int resourceId;

    @Column(name="resourcename")
    private String resourceName;

    public Resource(int resourceId) {
        this.resourceId = resourceId;
    }
    public Resource(int resourceId, String resourceName) {
        super();
        this.resourceId = resourceId;
        this.resourceName = resourceName;
    }

    public int getResourceId() {
        return resourceId;
    }
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
