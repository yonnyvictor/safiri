package com.lighteye.safiri.data;

import android.database.Cursor;

import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatsConfiguration {
    private int id;
    private int organizationId;
    private int fleetTypeId;
    private String organizationKey;
    private String nodeKey;
    private String fleetTypeKey;
    private int rows;
    private int columns;
    private int driverRowSeats;
    private int doorRowSeats;
    private int lastRowSeats;
    private int pathColumn;
    private int doorRow;

    public SeatsConfiguration() {
    }

    public SeatsConfiguration(int id, int organizationId, int fleetTypeId, String organizationKey,
                              String nodeKey, String fleetTypeKey, int rows, int columns,
                              int driverRowSeats, int doorRowSeats, int lastRowSeats,
                              int pathColumn, int doorRow) {
        this.id = id;
        this.organizationId = organizationId;
        this.fleetTypeId = fleetTypeId;
        this.organizationKey = organizationKey;
        this.nodeKey = nodeKey;
        this.fleetTypeKey = fleetTypeKey;
        this.rows = rows;
        this.columns = columns;
        this.driverRowSeats = driverRowSeats;
        this.doorRowSeats = doorRowSeats;
        this.lastRowSeats = lastRowSeats;
        this.pathColumn = pathColumn;
        this.doorRow = doorRow;
    }

    public static SeatsConfiguration from(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry._ID));
        int seatRows = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_SEAT_ROWS));
        int seatColumns = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_SEAT_COLUMNS));
        int pathColumn = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_PATH_COLUMN));
        int driverRowSeats = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_DRIVER_ROW_SEATS));
        int doorRow = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_DOOR_ROW));
        int doorRowSeats = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_DOOR_ROW_SEATS));
        int lastRowSeats = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_LAST_ROW_SEATS));
        String nodeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_NODE_KEY));
        String organizationKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_ORGANIZATION_KEY));
        int organizationId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_ORGANIZATION_ID));
        String fleetTypeKey = cursor.getString(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_FLEET_TYPE_KEY));
        int fleetTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(
                SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_FLEET_TYPE_ID));



        SeatsConfiguration seatsConfiguration = new SeatsConfiguration(
                id,
                organizationId,
                fleetTypeId,
                organizationKey,
                nodeKey,
                fleetTypeKey,
                seatRows,
                seatColumns,
                driverRowSeats,
                doorRowSeats,
                lastRowSeats,
                pathColumn,
                doorRow
        );

        //seatsConfiguration.setDoorRow(doorRow);

        return seatsConfiguration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getFleetTypeId() {
        return fleetTypeId;
    }

    public void setFleetTypeId(int fleetTypeId) {
        this.fleetTypeId = fleetTypeId;
    }

    public String getOrganizationKey() {
        return organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        this.organizationKey = organizationKey;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
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

    public int getDoorRow() {
        return doorRow;
    }

    public void setDoorRow(int doorRow) {
        this.doorRow = doorRow;
    }
}
