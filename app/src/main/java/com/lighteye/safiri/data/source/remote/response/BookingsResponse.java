package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 8/11/16.
 */
public class BookingsResponse {

    @SerializedName("userKey")
    private String userKey;

    @SerializedName("seatChargeKey")
    private String seatChargeKey;

    @SerializedName("seatKey")
    private String seatKey;

    @SerializedName("traveller")
    private String traveller;

    @SerializedName("timestampCreated")
    private TimestampResponse timestampCreated;

    @SerializedName("timestampLastChanged")
    private TimestampResponse timestampLastChanged;

    @SerializedName("status")
    private String status;

    @SerializedName("bookingId")
    private int bookingId;

    private String nodeKey;

    public BookingsResponse() {
    }

    public BookingsResponse(String userKey, String seatChargeKey, String seatKey,
                            String traveller, TimestampResponse timestampCreated,
                            TimestampResponse timestampLastChanged, String status, int bookingId) {
        this.userKey = userKey;
        this.seatChargeKey = seatChargeKey;
        this.seatKey = seatKey;
        this.traveller = traveller;
        this.timestampCreated = timestampCreated;
        this.timestampLastChanged = timestampLastChanged;
        this.status = status;
        this.bookingId = bookingId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getSeatChargeKey() {
        return seatChargeKey;
    }

    public void setSeatChargeKey(String seatChargeKey) {
        this.seatChargeKey = seatChargeKey;
    }

    public String getSeatKey() {
        return seatKey;
    }

    public void setSeatKey(String seatKey) {
        this.seatKey = seatKey;
    }

    public String getTraveller() {
        return traveller;
    }

    public void setTraveller(String traveller) {
        this.traveller = traveller;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    @Override
    public String toString() {
        return "BookingsResponse{" +
                "userKey='" + userKey + '\'' +
                ", seatChargeKey='" + seatChargeKey + '\'' +
                ", seatKey='" + seatKey + '\'' +
                ", traveller='" + traveller + '\'' +
                ", timestampCreated=" + timestampCreated +
                ", timestampLastChanged=" + timestampLastChanged +
                ", status='" + status + '\'' +
                ", bookingId=" + bookingId +
                ", nodeKey='" + nodeKey + '\'' +
                '}';
    }
}
