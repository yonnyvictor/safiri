package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/20/16.
 */
public class RoutesResponse {
    @SerializedName("fleetTypeKey")
    private String fleetTypeKey;
    @SerializedName("name")
    private String name;
    @SerializedName("origin")
    private String origin;
    @SerializedName("destination")
    private String destination;
    @SerializedName("departureTime")
    private int departureTime;
    @SerializedName("arrivalTime")
    private String arrivalTime;
    @SerializedName("mon")
    private boolean mon;
    @SerializedName("tue")
    private boolean tue;
    @SerializedName("wed")
    private boolean wed;
    @SerializedName("thu")
    private boolean thu;
    @SerializedName("fri")
    private boolean fri;
    @SerializedName("sat")
    private boolean sat;
    @SerializedName("sun")
    private boolean sun;
    @SerializedName("timestampCreated")
    private TimestampResponse timestampCreated;
    @SerializedName("timestampLastChanged")
    private TimestampResponse timestampLastChanged;
    @SerializedName("status")
    private String status;

    public RoutesResponse() {
    }

    public RoutesResponse(String fleetTypeKey, String name, String origin,
                          String destination, int departureTime, String arrivalTime,
                          boolean mon, boolean tue, boolean wed, boolean thu, boolean fri,
                          boolean sat, boolean sun, TimestampResponse timestampCreated,
                          TimestampResponse timestampLastChanged, String status) {
        this.fleetTypeKey = fleetTypeKey;
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.timestampCreated = timestampCreated;
        this.timestampLastChanged = timestampLastChanged;
        this.status = status;
    }

    public String getFleetTypeKey() {
        return fleetTypeKey;
    }

    public void setFleetTypeKey(String fleetTypeKey) {
        this.fleetTypeKey = fleetTypeKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
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
}
