package com.petrique.openstack.OverviewFragment;

public class Endpoints {
    private String region, type, name;

    public Endpoints() {
    }

    public Endpoints(String region, String type, String name) {
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
