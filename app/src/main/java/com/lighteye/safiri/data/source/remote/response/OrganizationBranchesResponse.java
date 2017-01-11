package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/18/16.
 */
public class OrganizationBranchesResponse {

    @SerializedName("address")
    private String address;

    @SerializedName("contacts")
    private String contacts;

    @SerializedName("townKey")
    private String townKey;

    public OrganizationBranchesResponse() {
    }

    public OrganizationBranchesResponse(String address, String contacts, String townKey) {
        this.address = address;
        this.contacts = contacts;
        this.townKey = townKey;
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
}
