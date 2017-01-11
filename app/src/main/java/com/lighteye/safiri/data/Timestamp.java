package com.lighteye.safiri.data;

/**
 * Created by yonny on 7/17/16.
 */
public class Timestamp {

    private long timestamp;

    public Timestamp() {
    }

    public Timestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Timestamp{" +
                "timestamp=" + timestamp +
                '}';
    }
}
