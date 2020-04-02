package com.petrique.openstack;

public class Instances {
    private String region, type, name;

    public Instances() {
    }

    public Instances(String region, String type, String name) {
        this.region = region;
        this.type = type;
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}