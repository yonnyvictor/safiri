package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/17/16.
 */
public class OrganizationsResponse {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("address")
    private String address;

    @SerializedName("contacts")
    private String contacts;

    @SerializedName("townKey")
    private String townKey;

    @SerializedName("timestampCreated")
    private TimestampResponse timestampCreated;
//    private Map<String, TimestampResponse> timestampCreated;
//
    @SerializedName("timestampLastChanged")
    private TimestampResponse timestampLastChanged;
//    private Map<String, TimestampResponse> timestampLastChanged;

    public OrganizationsResponse() {
    }

    public OrganizationsResponse(String name, String type, String address, String contacts,
                                 String townKey, TimestampResponse timestampCreated,
                                 TimestampResponse timestampLastChanged) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.contacts = contacts;
        this.townKey = townKey;
        this.timestampCreated = timestampCreated;
        this.timestampLastChanged = timestampLastChanged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTownKey() {
        return townKey;
    }

    public void setTownKey(String townKey) {
        this.townKey = townKey;
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

    //    public Map<String, TimestampResponse> getTimestampCreated() {
//        return timestampCreated;
//    }
//
//    public void setTimestampCreated(Map<String, TimestampResponse> timestampCreated) {
//        this.timestampCreated = timestampCreated;
//    }
//
//    public Map<String, TimestampResponse> getTimestampLastChanged() {
//        return timestampLastChanged;
//    }
//
//    public void setTimestampLastChanged(Map<String, TimestampResponse> timestampLastChanged) {
//        this.timestampLastChanged = timestampLastChanged;
//    }
}
