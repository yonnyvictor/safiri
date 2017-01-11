package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatsResponse {
    @SerializedName("name")
    private String name;
    @SerializedName("row")
    private int row;
    @SerializedName("column")
    private int column;
    @SerializedName("seatTypeKey")
    private String seatTypeKey;
    @SerializedName("status")
    private String status;

    public SeatsResponse() {
    }

    public SeatsResponse(String name, int row, int column, String seatTypeKey, String status) {
        this.name = name;
        this.row = row;
        this.column = column;
        this.seatTypeKey = seatTypeKey;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getSeatTypeKey() {
        return seatTypeKey;
    }

    public void setSeatTypeKey(String seatTypeKey) {
        this.seatTypeKey = seatTypeKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
