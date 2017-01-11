package com.lighteye.safiri.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatsConfigurationResponse {
    @SerializedName("fleetTypeKey")
    private String fleetTypeKey;
    @SerializedName("rows")
    private int rows;
    @SerializedName("columns")
    private int columns;
    @SerializedName("driverRowSeats")
    private int driverRowSeats;
    @SerializedName("doorRowSeats")
    private int doorRowSeats;
    @SerializedName("lastRowSeats")
    private int lastRowSeats;
    @SerializedName("pathColumn")
    private int pathColumn;

    public SeatsConfigurationResponse() {
    }

    public SeatsConfigurationResponse(String fleetTypeKey, int rows, int columns,
                                      int driverRowSeats, int doorRowSeats, int lastRowSeats,
                                      int pathColumn) {
        this.fleetTypeKey = fleetTypeKey;
        this.rows = rows;
        this.columns = columns;
        this.driverRowSeats = driverRowSeats;
        this.doorRowSeats = doorRowSeats;
        this.lastRowSeats = lastRowSeats;
        this.pathColumn = pathColumn;
    }

    public String getFleetTypeKey() {
        return fleetTypeKey;
    }

    public void setFleetTypeKey(String fleetTypeKey) {
        this.fleetTypeKey = fleetTypeKey;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getDriverRowSeats() {
        return driverRowSeats;
    }

    public void setDriverRowSeats(int driverRowSeats) {
        this.driverRowSeats = driverRowSeats;
    }

    public int getDoorRowSeats() {
        return doorRowSeats;
    }

    public void setDoorRowSeats(int doorRowSeats) {
        this.doorRowSeats = doorRowSeats;
    }

    public int getLastRowSeats() {
        return lastRowSeats;
    }

    public void setLastRowSeats(int lastRowSeats) {
        this.lastRowSeats = lastRowSeats;
    }

    public int getPathColumn() {
        return pathColumn;
    }

    public void setPathColumn(int pathColumn) {
        this.pathColumn = pathColumn;
    }
}
