package com.lighteye.safiri.data.source.remote.request;

/**
 * Created by yonny on 8/16/16.
 */
public class BookingQueryRequest {
    private String orderBy;
    private String equalTo;

    public BookingQueryRequest() {
    }

    public BookingQueryRequest(String orderBy, String equalTo) {
        this.orderBy = orderBy;
        this.equalTo = equalTo;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getEqualTo() {
        return equalTo;
    }

    public void setEqualTo(String equalTo) {
        this.equalTo = equalTo;
    }
}
