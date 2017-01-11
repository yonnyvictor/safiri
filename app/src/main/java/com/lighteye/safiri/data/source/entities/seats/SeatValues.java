package com.lighteye.safiri.data.source.entities.seats;

import android.content.ContentValues;

import com.lighteye.safiri.data.Seat;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatValues {
    public static ContentValues form(Seat item){
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.SeatsEntry._ID, item.getId());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_NAME, item.getName());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_NODE_KEY, item.getNodeKey());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_SEAT_ROW, item.getRow());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_SEAT_COLUMN, item.getColumn());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_SEATS_CONFIGURATION_ID, item.getSeatsConfigurationId());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_SEATS_CONFIGURATION_KEY, item.getSeatsConfigurationKey());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_SEAT_TYPE_ID, item.getSeatTypeId());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_SEAT_TYPE_KEY, item.getSeatTypeKey());
        values.put(SafiriPersistenceContract.SeatsEntry.COLUMN_STATUS, item.getStatus());
        return values;
    }
}
