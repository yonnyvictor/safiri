package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/20/16.
 */
public class FleetTypesResponse {
    @SerializedName("name")
    private String name;
    @SerializedName("capacity")
    private int capacity;

    public FleetTypesResponse() {
    }

    public FleetTypesResponse(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
