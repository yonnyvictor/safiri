package com.lighteye.safiri.data.source.entities.seatcharges;

import android.content.ContentValues;

import com.lighteye.safiri.data.SeatCharge;
import com.lighteye.safiri.data.source.local.SafiriPersistenceContract;

/**
 * Created by yonny on 7/20/16.
 */
public class SeatChargeValues {
    public static ContentValues form(SeatCharge item){
        long created = item.getTimestampCreated().getTimestamp();
        long modified = item.getTimestampCreated().getTimestamp();
        ContentValues values = new ContentValues();
        //values.put(SafiriPersistenceContract.SeatChargesEntry._ID, item.getId());
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_CHARGE, item.getCharge());
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_NODE_KEY, item.getNodeKey());
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_ROUTE_ID, item.getRouteId());
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_ROUTE_KEY, item.getRouteKey());
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_SEAT_TYPE_ID, item.getSeatTypeId());
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_SEAT_TYPE_KEY, item.getSeatTypeKey());
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_CREATED, created);
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_MODIFIED, modified);
        values.put(SafiriPersistenceContract.SeatChargesEntry.COLUMN_CURRENT, item.isCurrent());
        return values;
    }
}
