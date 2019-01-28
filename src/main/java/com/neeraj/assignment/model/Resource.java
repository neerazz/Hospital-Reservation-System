package com.neeraj.assignment.model;

import javax.persistence.*;

@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int resourceId;

    private String resourceName;

    @Column(name = "available_count")
    private int availableCount;

    public Resource(int resourceId) {
        super();
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
