package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/16/16.
 */
public class TownsResponse {
    @SerializedName("name")
    private String name;

    public TownsResponse() {
    }

    public TownsResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
