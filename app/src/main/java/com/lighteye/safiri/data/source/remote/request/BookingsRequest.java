package com.lighteye.safiri.data.source.remote.request;

import com.lighteye.safiri.data.Timestamp;

/**
 * Created by yonny on 8/13/16.
 */
public class BookingsRequest {

    private String userKey;

    private String seatChargeKey;

    private String seatKey;

    private String traveller;

    private int bookingId;

    private Timestamp timestampCreated;

    private Timestamp timestampLastChanged;

    private String status;

    public BookingsRequest(String userKey, String seatChargeKey, String seatKey, String traveller,
                           int bookingId, Timestamp timestampCreated, Timestamp timestampLastChanged,
                           String status) {
        this.userKey = userKey;
        this.seatChargeKey = seatChargeKey;
        this.seatKey = seatKey;
        this.traveller = traveller;
        this.bookingId = bookingId;
        this.timestampCreated = timestampCreated;
        this.timestampLastChanged = timestampLastChanged;
        this.status = status;
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

    public Timestamp getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(Timestamp timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public Timestamp getTimestampLastChanged() {
        return timestampLastChanged;
    }

    public void setTimestampLastChanged(Timestamp timestampLastChanged) {
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
}
