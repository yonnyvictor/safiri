package com.lighteye.safiri.data.source.entities.seatsconfiguration;

import android.content.ContentValues;

import com.lighteye.safiri.data.SeatsConfiguration;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatsConfigurationValues {
    public static ContentValues form(SeatsConfiguration item){
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.SeatsConfigurationEntry._ID, item.getId());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_SEAT_ROWS, item.getRows());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_SEAT_COLUMNS, item.getColumns());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_NODE_KEY, item.getNodeKey());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_PATH_COLUMN, item.getPathColumn());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_DRIVER_ROW_SEATS, item.getDriverRowSeats());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_DOOR_ROW, item.getDoorRow());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_DOOR_ROW_SEATS, item.getDoorRowSeats());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_LAST_ROW_SEATS, item.getLastRowSeats());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_ORGANIZATION_ID, item.getOrganizationId());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_ORGANIZATION_KEY, item.getOrganizationKey());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_FLEET_TYPE_ID, item.getFleetTypeId());
        values.put(SafiriPersistenceContract.SeatsConfigurationEntry.COLUMN_FLEET_TYPE_KEY, item.getFleetTypeKey());
        return values;
    }
}
