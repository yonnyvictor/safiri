package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/18/16.
 */
public class TimestampResponse {

    @SerializedName("timestamp")
    private long timestamp;

    public TimestampResponse() {
    }



    public TimestampResponse(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
