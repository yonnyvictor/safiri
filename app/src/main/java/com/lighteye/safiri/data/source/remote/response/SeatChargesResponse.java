package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatChargesResponse {
    @SerializedName("seatTypeKey")
    private String seatTypeKey;
    @SerializedName("charge")
    private float charge;
    @SerializedName("timestampCreated")
    private TimestampResponse timestampCreated;
    @SerializedName("timestampLastChanged")
    private TimestampResponse timestampLastChanged;
    @SerializedName("current")
    private boolean current;

    public SeatChargesResponse() {
    }

    public SeatChargesResponse(String seatTypeKey, float charge, TimestampResponse timestampCreated,
                               TimestampResponse timestampLastChanged, boolean current) {
        this.seatTypeKey = seatTypeKey;
        this.charge = charge;
        this.timestampCreated = timestampCreated;
        this.timestampLastChanged = timestampLastChanged;
        this.current = current;
    }

    public String getSeatTypeKey() {
        return seatTypeKey;
    }

    public void setSeatTypeKey(String seatTypeKey) {
        this.seatTypeKey = seatTypeKey;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public TimestampResponse getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(TimestampResponse timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public TimestampResponse getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public void setTimestampLastChanged(TimestampResponse timestampLastChanged) {
        this.timestampLastChanged = timestampLastChanged;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
